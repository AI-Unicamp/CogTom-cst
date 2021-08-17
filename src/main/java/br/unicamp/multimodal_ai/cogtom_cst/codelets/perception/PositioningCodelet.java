package br.unicamp.multimodal_ai.cogtom_cst.codelets.perception;

import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import br.unicamp.multimodal_ai.cogtom_cst.memory.data.PositioningData;
import br.unicamp.multimodal_ai.cogtom_cst.memory.working.model.Positioning;
import br.unicamp.multimodal_ai.cogtom_cst.memory.working.sync.Activation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/*
* The Affordances Codelet create Affordances Memory Objects in Semantic Memory.
* Input is provided by the file affordances.csv
*/
public class PositioningCodelet extends Codelet {

    List<PositioningData> posData;

    MemoryContainer positioningMC;
    MemoryObject positioningActivationMO;
    MemoryObject tommActivationMO;
    MemoryObject positioningDoneActivationMO;

    // Mindstep the codelet is currently processing.
    int mindStep;

    public PositioningCodelet(InputStream posStream) {
        try {
            Table positioningTable = Table.read().csv(posStream);
            posData = new ArrayList<PositioningData>();

            // Loop through each one of the rows of the tables.
            for (int i = 0; i < positioningTable.rowCount(); i++) {
                Row r = positioningTable.row(i);
                int step = r.getInt("t");
                String entity = r.getString("Entity");
                String location = r.getString("Location");
                // Add to List
                PositioningData p = new PositioningData(step, entity, location);
                posData.add(p);
            }
        } catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @Override
    public void accessMemoryObjects() {
        synchronized (this) {
            // Memory Container
            positioningMC = (MemoryContainer) getOutput("POSITIONING");
            // Activation MO
            positioningActivationMO = (MemoryObject) getInput("POSITIONING_ACTIVATION");
            positioningDoneActivationMO = (MemoryObject) getOutput("POSITIONING_DONE_ACTIVATION");
         }
    }

    @Override
    public void calculateActivation() {
        try {
            Activation act = (Activation) positioningActivationMO.getI();
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

        for (PositioningData p: posData) {
            // Entities from the current mindStep
            if (p.mindStep() == mindStep) {
            Positioning position = new Positioning(p.name(), p.location());
            positioningMC.setI(position);
            }
        }

        // Deactivate this codelet until the next mind step
        Activation self = new Activation(mindStep, false);
        positioningActivationMO.setI(self);

        // Indicates ID processing is done
        positioningDoneActivationMO.setI(true);
    }

  /*
   * Utility Method to clear out memory contents between simulation cycles.
   */
   private void clearMemory() {

    // Reset Memory Containers at every mind step, since 
    // the perception memories are not kept between simulation cycles.
    ArrayList<Memory> pos = positioningMC.getAllMemories();
    pos.clear();
 }
}
