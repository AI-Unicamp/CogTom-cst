package br.unicamp.multimodal_ai.cogtom_cst.codelets.perception;

import java.util.ArrayList;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import br.unicamp.multimodalai.cogtom_cst.memory.working.model.Agent;
import br.unicamp.multimodalai.cogtom_cst.memory.working.model.Attention;
import br.unicamp.multimodalai.cogtom_cst.memory.working.model.Object;
import br.unicamp.multimodalai.cogtom_cst.memory.working.model.SharedAttention;
import br.unicamp.multimodalai.cogtom_cst.memory.working.sync.Activation;

/**
 * SAM
 */
public class SharedAttentionCodelet extends Codelet {
        
    MemoryContainer agentsMC;
    MemoryContainer objectsMC;
    MemoryContainer attentionsMC;
    MemoryContainer sharedAttentionsMC;
    MemoryObject samActivationMO;
    MemoryObject samDoneActivationMO;
    MemoryObject tommActivationMO;

    // The current mindstep
    int mindStep;

    public SharedAttentionCodelet() {

    }

    @Override
    public void accessMemoryObjects() {
        // Memory Containers
        agentsMC = (MemoryContainer) getInput("AGENTS");
        objectsMC = (MemoryContainer) getInput("OBJECTS");
        attentionsMC = (MemoryContainer) getInput("ATTENTIONS");
        sharedAttentionsMC = (MemoryContainer) getOutput("SHAREDATTN");
        // Activation MOs
        samActivationMO = (MemoryObject) getInput("SAM_ACTIVATION");
        samDoneActivationMO = (MemoryObject) getOutput("SAM_DONE_ACTIVATION");
        tommActivationMO = (MemoryObject) getOutput("TOMM_ACTIVATION");
    }

    @Override
    public void calculateActivation() {
        try {
            Activation act = (Activation) samActivationMO.getI();
            if (act.Active() == true) {
               // Set mind step for the codelet.
               setActivation(1.0d);
               mindStep = act.mindStep();
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

        // Find each agent or object in the Attentions Container and list Agents that have an interest on it.
        ArrayList<Memory> agents = agentsMC.getAllMemories();
        ArrayList<Memory> objects = objectsMC.getAllMemories();
            
        for(Memory a: agents) {
            Agent agt = (Agent) a.getI();
            ArrayList<Memory> attentions = attentionsMC.getAllMemories();
            ArrayList<String> interested = new ArrayList<String>();
            for (Memory at: attentions) {
                Attention attention = (Attention) at.getI();
                if (agt.name().equals(attention.target())) {
                    interested.add(attention.agent());
                }
            }
            // Check how many agents are interested on this target person
            if (interested.size() > 1) {
                // More than one, so we can create a shared attention object.
                SharedAttention sharedAttn = new SharedAttention(interested, agt.name());
                sharedAttentionsMC.setI(sharedAttn);
            }
        }
            
        for(Memory o: objects) {
            Object obj = (Object) o.getI();
            ArrayList<Memory> attentions = attentionsMC.getAllMemories();
            ArrayList<String> interested = new ArrayList<String>();
            for (Memory at: attentions) {
                Attention attention = (Attention) at.getI();
                if (obj.name().equals(attention.target())) {
                    interested.add(attention.agent());
                }
            }
            // Check how many agents are interested on this target object
            if (interested.size() > 1) {
                // More than one, so we can create a shared attention object.
                SharedAttention sharedAttn = new SharedAttention(interested, obj.name());
                sharedAttentionsMC.setI(sharedAttn);
            }
        }

        // Deactivate this codelet until the next mind step
        Activation self = new Activation(mindStep, false);
        samActivationMO.setI(self);

        // Indicates SAM processing is done
        samDoneActivationMO.setI(true);

        // Sets TOMM activation, indicating it can run now.
        Activation act = new Activation(mindStep, true);
        tommActivationMO.setI(act);
    }

    /*
   * Utility Method to clear out memory contents between simulation cycles.
   */
   private void clearMemory() {
        // Reset Memory Containers at every mind step, since 
        // the perception memories are not kept between simulation cycles.
        ArrayList<Memory> sharedAttns = sharedAttentionsMC.getAllMemories();
        sharedAttns.clear();
   }
}
