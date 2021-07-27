package codelets.perception;

import br.unicamp.cst.core.entities.Codelet;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

/**
 * EDD codelet identifies eye direction and attaches that information to the Attention MOs.
 * It takes as inputs the Memory Containers created by the ID codelet.
 * Input for this exercise is provided through the csv file eye_directions.txt
 * @author fabiogr
 *
 */
public class EyeDirectionDetector extends Codelet {

    public EyeDirectionDetector() {

        List<ToMEyeDirections> eyeDirections;

        // Codelets do not seem to record the current time step.
        int mindStep;

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
                entities.add(eye);
            }

  
          } catch (IOException e1) {
              e1.printStackTrace();
          }
        // Started simulation, set mindStep
        mindStep = 1;
     }
    @Override
    public void accessMemoryObjects() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void calculateActivation() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void proc() {
        // TODO Auto-generated method stub
        
    }
    
}
