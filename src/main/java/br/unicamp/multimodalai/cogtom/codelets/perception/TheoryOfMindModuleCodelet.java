package br.unicamp.multimodalai.cogtom.codelets.perception;

import java.util.ArrayList;
import java.util.stream.Collectors;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import br.unicamp.multimodalai.cogtom.memory.working.model.Agent;
import br.unicamp.multimodalai.cogtom.memory.working.model.Attention;
import br.unicamp.multimodalai.cogtom.memory.working.model.Belief;
import br.unicamp.multimodalai.cogtom.memory.working.sync.Activation;

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
         // Clear out memory containers.
        clearMemory();

        ArrayList<Belief> beliefs = new ArrayList<>();

        // First step to compose beliefs is to retrieve for an Agent their objects of interest on a scene.
        int numAgents = agentsMC.getAllMemories().size();
        for (int i = 0; i < numAgents; i++) {
            Agent agt = (Agent) agentsMC.getI(i);
            // For this agent get the objects of interest from EDD.
            int numAttns = attentionsMC.getAllMemories().size();
            for (int j = 0; j < numAttns; j++) {
                Attention attn = (Attention) attentionsMC.getI(j);
                if (attn.agent().equals(agt.name())) {
                    // Object in this attention is of interest
                    // Create Belief
                    Belief b = new Belief(agt.name(), attn.target());
                    // Get affordances for the object.
                    String afford = getAffordance(attn.target());
                    b.setAffordance(afford);

                    beliefs.add(b);
                }
            }
        }

        // Add all beliefs to the Memory Container.


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
    * Utility method to get the affordance for an entity.
    */
    String getAffordance(String entity) {
        Predicate<Memory> pred = mem -> ((Affordance) mem.getI()).object().equals(entity); 
        Affordance aff = (Affordance) affordancesMC.getI(pred);
        return aff.affordance();
    }

    /*
    * Utility Method to clear out memory contents between simulation cycles.
    */
    private void clearMemory() {       
    }
}

