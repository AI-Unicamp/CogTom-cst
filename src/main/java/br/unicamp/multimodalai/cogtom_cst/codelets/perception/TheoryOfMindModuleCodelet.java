package br.unicamp.multimodalai.cogtom_cst.codelets.perception;

import java.util.ArrayList;
import java.util.function.Predicate;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import br.unicamp.multimodalai.cogtom_cst.memory.semantic.model.Affordance;
import br.unicamp.multimodalai.cogtom_cst.memory.working.model.Agent;
import br.unicamp.multimodalai.cogtom_cst.memory.working.model.Attention;
import br.unicamp.multimodalai.cogtom_cst.memory.working.model.Intention;
import br.unicamp.multimodalai.cogtom_cst.memory.working.model.Belief;
import br.unicamp.multimodalai.cogtom_cst.memory.working.sync.Activation;

/**
 * ToMM Codelet
 */
public class TheoryOfMindModuleCodelet extends Codelet {

    MemoryContainer agentsMC;
    MemoryContainer objectsMC;
    MemoryContainer intentionsMC;
    MemoryContainer affordancesMC;
    MemoryContainer attentionsMC;
    MemoryContainer sharedAttentionsMC;
    MemoryContainer beliefsMC;

    MemoryObject idDoneActivationMO;
    MemoryObject affordDoneActivationMO;
    MemoryObject eddDoneActivationMO;
    MemoryObject samDoneActivationMO;
    MemoryObject tommActivationMO;
    MemoryObject idActivationMO;

    // The current mindstep
    int mindStep;

    public TheoryOfMindModuleCodelet() {
    }

    @Override
    public void accessMemoryObjects() {
        // Memory Containers
        agentsMC = (MemoryContainer) getInput("AGENTS");
        objectsMC = (MemoryContainer) getInput("OBJECTS");
        intentionsMC = (MemoryContainer) getInput("INTENTIONS");
        affordancesMC = (MemoryContainer) getInput("AFFORDANCES");
        attentionsMC = (MemoryContainer) getInput("ATTENTIONS");
        sharedAttentionsMC = (MemoryContainer) getInput("SHAREDATTN"); 
        beliefsMC = (MemoryContainer) getOutput("BELIEFS");
        // Activation MOs
        idDoneActivationMO = (MemoryObject) getInput("ID_DONE_ACTIVATION");
        affordDoneActivationMO = (MemoryObject) getInput("AFFORD_DONE_ACTIVATION");
        eddDoneActivationMO = (MemoryObject) getInput("EDD_DONE_ACTIVATION");
        samDoneActivationMO = (MemoryObject) getInput("SAM_DONE_ACTIVATION");
        tommActivationMO = (MemoryObject) getInput("TOMM_ACTIVATION");
        // To activate the next mindstep
        idActivationMO = (MemoryObject) getOutput("ID_ACTIVATION");
    }

    @Override
    public void calculateActivation() {
        try {
            // After all other codelets finished their runs
            if ((boolean) idDoneActivationMO.getI() == true &&
                (boolean) affordDoneActivationMO.getI() == true && 
                (boolean) eddDoneActivationMO.getI() == true &&
                (boolean) samDoneActivationMO.getI() == true) {
                    Activation act = (Activation) tommActivationMO.getI();
                    if (act.Active() == true) {
                        // Set mind step for the codelet.
                        setActivation(1.0d);
                        mindStep = act.mindStep();
                    } else {
                        setActivation(0.0d);     
                    }
            } else {
               setActivation(0.0d);
            }
         } catch (CodeletActivationBoundsException e) {
               e.printStackTrace();
            } 
    }

    @Override
    public void proc() {

        // First step to compose beliefs is to retrieve for an Agent their objects of interest on a scene.
        int numAgents = agentsMC.getAllMemories().size();
        for (int i = 0; i < numAgents; i++) {
            Agent agt = (Agent) agentsMC.getI(i);
            // For this agent get the objects of interest from EDD.
            int numAttns = attentionsMC.getAllMemories().size();
            for (int j = 0; j < numAttns; j++) {
                Attention attn = (Attention) attentionsMC.getI(j);
                if (attn.agent().equals(agt.name())) {                    
                    // Get the intention for this agent
                    Intention intt = getIntention(agt.name());
                    // Get affordances for the object.
                    String afford = getAffordance(attn.target());
                    Belief b = createBelief(agt.name(), attn.target(), intt, afford);
                    addBeliefToMemory(b);
                }
            }
        }

        // Output beliefs at the end of ths simulation cycle
        printBeliefs();

        // Deactivate this codelet until the next mind step
        Activation self = new Activation(mindStep, false);
        tommActivationMO.setI(self);

        // Clear codelets activation statuses
        idDoneActivationMO.setI(false);
        eddDoneActivationMO.setI(false);
        samDoneActivationMO.setI(false);
    
        // Sets ID activation for the next step.
        mindStep++;
        Activation act = new Activation(mindStep, true);
        idActivationMO.setI(act);
    }

    /*
     * Method to encapsulate the main logic for creating a belief.
    */
    Belief createBelief(String agent, String object, Intention intt, String affordance) {
        Belief b = new Belief(agent, object);
        b.setAffordance(affordance);
        return b;
    }

    /*
    * Utility method to get the affordance for an entity.
    */
    String getAffordance(String entity) {
        Predicate<Memory> pred = mem -> ((Affordance) mem.getI()).object().equals(entity); 
        Affordance aff = (Affordance) affordancesMC.getI(pred);
        return aff.affordance();
    }

    /*
    * Utility method to get the intention for an agent.
    */
    Intention getIntention(String agent) {
        Predicate<Memory> pred = mem -> ((Intention) mem.getI()).agent().equals(agent); 
        Intention intent = (Intention) intentionsMC.getI(pred);
        return intent;
    }

    /*
    * Utility method to printout Beliefs at the end of the cycle
    */
    void printBeliefs() {
        System.out.println();
        ArrayList<Memory> beliefs = beliefsMC.getAllMemories();
        for(int i = 0; i < beliefs.size(); i++) {
            Belief b = (Belief) beliefsMC.getI(i);
            System.out.println(b.toStr());
        }
        System.out.println();
    }

    /*
    * Utility Method to update Belief Memory.
    */
    private void addBeliefToMemory(Belief b) {
       // Get current beliefs in Memory
       ArrayList<Memory> existing = beliefsMC.getAllMemories();

       // If memory is empty, just insert.
       if (existing.size() == 0) {
           beliefsMC.setI(b);
           return;
       }
       else {
           // There are beliefs in memory, so search before inserting.
           for(int i = 0; i < existing.size(); i++) {
               Belief search = (Belief) beliefsMC.getI(i);
               if (search.agent().equals(b.agent()) && search.object().equals(b.object())) {
                   // Found in memory, so just update at the index.
                   beliefsMC.setI(b, i);
                   return;
               }
           }
           // Not found, just insert
           beliefsMC.setI(b);
           return;
        }
    }
}

