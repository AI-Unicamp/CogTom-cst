package br.unicamp.multimodalai.cogtom.codelets.perception;

import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import br.unicamp.multimodalai.cogtom.memory.data.AffordanceData;
import br.unicamp.multimodalai.cogtom.memory.semantic.model.Affordance;
import br.unicamp.multimodalai.cogtom.memory.working.sync.Activation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
* The Affordances Codelet create Affordances Memory Objects in Semantic Memory.
* Input is provided by the file affordances.csv
*/
public class AffordancesCodelet extends Codelet {

    List<AffordanceData> affordData;

    MemoryContainer affordancesContainer;
    MemoryObject affordActivationMO;
    MemoryObject tommActivationMO;
    MemoryObject affordDoneActivationMO;

    public AffordancesCodelet() {
        try {
            Table affordancesTable = Table.read().csv("input/affordances.csv");
            affordData = new ArrayList<AffordanceData>();

            // Loop through each one of the rows of the tables.
            for (int i = 0; i < affordancesTable.rowCount(); i++) {
                Row r = affordancesTable.row(i);
                String object = r.getString("Object");
                String afford = r.getString("Affordance");
                // Add to List
                AffordanceData a = new AffordanceData(object, afford);
                affordData.add(a);
            }
        } catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @Override
    public void accessMemoryObjects() {
        synchronized (this) {
            // Memory Container
            affordancesContainer = (MemoryContainer) getOutput("AFFORDANCES");
            // Activation MO
            affordActivationMO = (MemoryObject) getInput("AFFORD_ACTIVATION");
            affordDoneActivationMO = (MemoryObject) getOutput("AFFORD_DONE_ACTIVATION");
         }
    }

    @Override
    public void calculateActivation() {
        try {
            Activation act = (Activation) affordActivationMO.getI();
            if (act.Active() == true) {
               // Set mind step for the codelet.
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
        // Create Affordances Memory Objects
        for (AffordanceData data: affordData) {
                Affordance afford = new Affordance(data.object(), data.affordance());
               affordancesContainer.setI(afford);
            }
         
        // This Codelet only runs once, so reset the activation MO
        Activation self = new Activation(0, false);
        affordActivationMO.setI(self);

        // Affordances processing is done.
        affordDoneActivationMO.setI(true);
    }

}
