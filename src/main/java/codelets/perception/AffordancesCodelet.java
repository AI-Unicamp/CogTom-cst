package codelets.perception;

import memory.semantic.ToMAffordance;
import memory.working.ToMActivation;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
* The Affordances Codelet create Affordances Memory Objects in Semantic Memory.
* Input is provided by the file affordances.csv
*/
public class AffordancesCodelet extends Codelet {

    List<ToMAffordance> affordances;

    MemoryContainer affordancesContainer;
    MemoryObject affordActivationMO;
    MemoryObject tommActivationMO;
    MemoryObject affordDoneActivationMO;

    public AffordancesCodelet() {
        try {
            Table affordancesTable = Table.read().csv("input/affordances.csv");
            affordances = new ArrayList<ToMAffordance>();

            // Loop through each one of the rows of the tables.
            for (int i = 0; i < affordancesTable.rowCount(); i++) {
                Row r = affordancesTable.row(i);
                String object = r.getString("Object");
                String afford = r.getString("Affordance");
                // Add to List
                ToMAffordance a = new ToMAffordance(object, afford);
                affordances.add(a);
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
            ToMActivation act = (ToMActivation) affordActivationMO.getI();
            if (act.Activation() == true) {
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
        for (ToMAffordance a: affordances) {
               affordancesContainer.setI(a);
            }
         
        // This Codelet only runs once, so reset the activation MO
        ToMActivation self = new ToMActivation(0, false);
        affordActivationMO.setI(self);

        // Affordances processing is done.
        affordDoneActivationMO.setI(true);
    }

}
