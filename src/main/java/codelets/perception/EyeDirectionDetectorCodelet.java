package codelets.perception;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import memory.working.ToMActivationObject;
import memory.working.ToMEyeDirection;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * EDD codelet identifies eye direction and attaches that information to the Attention MOs.
 * It takes as inputs the Memory Containers created by the ID codelet.
 * Input for this exercise is provided through the csv file eye_directions.txt
 */
public class EyeDirectionDetectorCodelet extends Codelet {

    List<ToMEyeDirection> eyeDirections;

    MemoryContainer agentsContainer;
    MemoryContainer objectsContainer;
    MemoryContainer intentionsContainer;        
    MemoryContainer attentionsContainer;

    MemoryObject eddActivationMO;
    MemoryObject samActivationMO;
    MemoryObject eddDoneActivationMO;

    // Mindstep the codelet is currently processing.
    int mindStep;

    public EyeDirectionDetectorCodelet() {

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
        eddDoneActivationMO = (MemoryObject) getOutput("EDD_DONE_ACTIVATION");
        samActivationMO = (MemoryObject) getOutput("SAM_ACTIVATION");

    }

    @Override
    public void calculateActivation() {
        try {
            ToMActivationObject act = (ToMActivationObject) eddActivationMO.getI();
            if (act.Activation() == true) {
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

        // Get Agents from the Memory Container
        ArrayList<Memory> agents = agentsContainer.getAllMemories();
        for (Memory a: agents) {
            String agt = (String) a.getI();
            // Search for the agent Eye Directions in the working memory.
            for (ToMEyeDirection eye: eyeDirections) {
                // Entities from the current mindStep
                if (eye.mindStep() == mindStep && eye.agent() == agt) {
                    // Add to container.
                    attentionsContainer.setI(eye);
                }
            }
        }

        // Sets SAM activation, indicating it can run now.
        ToMActivationObject act = new ToMActivationObject(mindStep, true);
        samActivationMO.setI(act);

        // Deactivate this codelet until the next mind step
        ToMActivationObject self = new ToMActivationObject(mindStep, false);
        eddActivationMO.setI(self);

        // Indicates EDD processing is done
        eddDoneActivationMO.setI(true);
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
