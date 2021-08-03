package codelets.perception;

import java.util.ArrayList;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;

import memory.working.model.Agent;
import memory.working.model.Object;
import memory.working.sync.Activation;

/**
 * SAM
 */
public class SharedAttentionCodelet extends Codelet {
        
    MemoryContainer agentsMC;
    MemoryContainer objectsMC;
    MemoryContainer attentionsMC;
    MemoryObject samActivationMO;
    MemoryObject samDoneActivationMO;

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
        // Activation MOs
        samActivationMO = (MemoryObject) getInput("SAM_ACTIVATION");
        samDoneActivationMO = (MemoryObject) getOutput("SAM_DONE_ACTIVATION");
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
        }
            
        for(Memory o: objects) {
            Object obj = (Object) o.getI();
        }
    }

    /*
   * Utility Method to clear out memory contents between simulation cycles.
   */
   private void clearMemory() {

   }

}
