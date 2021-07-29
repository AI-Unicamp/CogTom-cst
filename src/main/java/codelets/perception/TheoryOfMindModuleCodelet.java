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

    List<ToMEyeDirection> eyeDirections;

    MemoryContainer agentsContainer;
    MemoryContainer objectsContainer;
    MemoryContainer intentionsContainer;        
    MemoryContainer attentionsContainer;

    MemoryObject eddActivationMO;

    // Codelets do not seem to record the current time step.
    int mindStep;

    public TheoryOfMindModuleCodelet() {

        try {
            Table entityTable = Table.read().csv("input/eye_directions.csv");

            eyeDirections = new ArrayList<>();

            // Loop through each one of the rows of the tables.
            for (int i = 0; i < entityTable.rowCount(); i++) {
                Row r = entityTable.row(i);
                int step = r.getInt("t");
                String agt = r.getString("Agent");
                String obj = r.getString("Object");
                // Add to List
                ToMEyeDirection eye = new ToMEyeDirection(step, agt, obj);
                eyeDirections.add(eye);
            }
          } catch (IOException e1) {
              e1.printStackTrace();
          }
        // Started simulation, set mindStep
        mindStep = 1;
    }

    @Override
    public void accessMemoryObjects() {
        // Memory Containers
        agentsContainer = (MemoryContainer) getInput("AGENTS");
        objectsContainer = (MemoryContainer) getInput("OBJECTS");
        intentionsContainer = (MemoryContainer) getInput("INTENTIONS");
        attentionsContainer = (MemoryContainer) getOutput("ATTENTIONS");
        // Activation MOs
        eddActivationMO = (MemoryObject) getInput("EDD_ACTIVATION");

    }

    @Override
    public void calculateActivation() {
        try {
            if ((boolean) eddActivationMO.getI() == true) {
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

        // Get sublists based on the current mind step and populate Memory Objects.
        for (ToMEyeDirection eye: eyeDirections) {
            // Entities from the current mindStep
            if (eye.mindStep() == mindStep) {
                // Current mindstep
                attentionsContainer.setI(eye);
            }
        }
        mindStep++;
    }

    /*
   * Utility Method to clear out memory contents between simulation cycles.
   */
   private void clearMemory() {

        // Reset Memory Containers at every mind step, since 
        // the perception memories are not kept between simulation cycles.
        ArrayList<Memory> attns = attentionsContainer.getAllMemories();
        attns.clear();
 }
}
