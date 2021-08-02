package codelets.perception;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import memory.working.ToMEyeDirection;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

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
    MemoryObject idActivationMO;

    // Codelets do not seem to record the current time step.
    int mindStep;

    public TheoryOfMindModuleCodelet() {

 
    }

    @Override
    public void accessMemoryObjects() {
        // Memory Containers
        agentsMC = (MemoryContainer) getInput("AGENTS");
        objectsMC = (MemoryContainer) getInput("OBJECTS");
        intentionsMC = (MemoryContainer) getInput("INTENTIONS");
        affordancesMC = (MemoryContainer) getInput("AFFORDANCES")
        attentionsMC = (MemoryContainer) getInput("ATTENTIONS");
        sharedAttentionsMC = (MemoryContainer) getInput("SHAREDATTN"); 
        // Activation MOs
        idDoneActivationMO = (MemoryObject) getInput("ID_DONE_ACTIVATION");
        affordDoneActivationMO = (MemoryObject) getInput("AFFORD_DONE_ACTIVATION");
        eddDoneActivationMO = (MemoryObject) getInput("EDD_DONE_ACTIVATION");
        samDoneActivationMO = (MemoryObject) getInput("SAM_DONE_ACTIVATION");
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
    }

    /*
   * Utility Method to clear out memory contents between simulation cycles.
   */
   private void clearMemory() {
 }
}
