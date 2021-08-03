package codelets.perception;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import memory.working.ToMEddData;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * SAM
 */
public class SharedAttentionCodelet extends Codelet {
        
    MemoryContainer attentionsContainer;
    MemoryObject samActivationMO;
    MemoryObject samDoneActivationMO;

    // The current mindstep
    int mindStep;

    public SharedAttentionCodelet() {

    }

    @Override
    public void accessMemoryObjects() {
        // Memory Containers
        attentionsContainer = (MemoryContainer) getInput("ATTENTIONS");
        // Activation MOs
        samActivationMO = (MemoryObject) getInput("SAM_ACTIVATION");
        samDoneActivationMO = (MemoryObject) getOutput("SAM_DONE_ACTIVATION");

    }

    @Override
    public void calculateActivation() {
        try {
            if ((boolean) samActivationMO.getI() == true) {
               setActivation(1.0d);
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


        mindStep++;
    }

    /*
   * Utility Method to clear out memory contents between simulation cycles.
   */
   private void clearMemory() {
 }

}
