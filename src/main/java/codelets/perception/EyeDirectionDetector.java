package codelets.perception;

import base.ToMEyeDirection;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * EDD codelet identifies eye direction and attaches that information to the Attention MOs.
 * It takes as inputs the Memory Containers created by the ID codelet.
 * Input for this exercise is provided through the csv file eye_directions.txt
 * @author fabiogr
 *
 */
public class EyeDirectionDetector extends Codelet {

    List<ToMEyeDirection> eyeDirections;

    MemoryContainer agentsContainer;
    MemoryContainer objectsContainer;
    MemoryContainer intentionsContainer;        
    MemoryContainer attentionsContainer;

    // Codelets do not seem to record the current time step.
    int mindStep;

    public EyeDirectionDetector() {

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
    }

    @Override
    public void calculateActivation() {
        // Codelet should be activated only if there are inputs available to it.
        ArrayList<Memory> mem = agentsContainer.getAllMemories();
        try {
            if (!mem.isEmpty()) {
                // Set activation to allow the codelet to run.
                    setActivation(1.0d);
            } else {
                // Keep the codelet from running.
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
