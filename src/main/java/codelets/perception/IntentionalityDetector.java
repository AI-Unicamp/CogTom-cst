package codelets.perception;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The Intentionality Detector creates Memory Objects for the Agents, objects
 * and Intentions.
 * Input for this exercise is provided through the csv files entities.txt and intentions.csv
 * 
 * @author fabiogr
 *
 */
public class IntentionalityDetector extends Codelet {

   /*
   * Class to encapsulate the data read in from the input tables.
   */
   class Entity {
      
      private int mindStep;
      private String name;
      private boolean isAgent;

      /*
      Setters and Getters
      */
      void setMindStep(int aStep) {
         mindStep = aStep;
      }

      void setName(String aName) {
         name = aName;
      }

      void setIsAgent(boolean aIsAgent) {
         isAgent = aIsAgent;
      }

      String name() {
         return name;
      }

      boolean isAgent() {
         return isAgent;
      }

      int mindStep() {
         return mindStep;
      } 
   }

   List<Entity> entities;
   private Memory visionMO;
   private Memory knownApplesMO;
   

   public IntentionalityDetector() {

      try {
			Table entityTable = Table.read().csv("input/entities.csv");
         Table intentionTable = Table.read().csv("input/intentions.csv");

			// Loop through each one of the rows of the tables.
			for (int i = 0; i < entityTable.rowCount(); i++) {
				Row r = entityTable.row(i);
            int time = r.getInt("t");
				String name = r.getString("Entity");
				Boolean isAgent = r.getBoolean("Is_Agent");
			}


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   }

   @Override
   public void accessMemoryObjects() {
      synchronized (this) {
         this.visionMO = (MemoryObject) this.getInput("VISION");
      }
      this.knownApplesMO = (MemoryObject) this.getOutput("KNOWN_APPLES");
   }

   @Override
   public void proc() {

   }// end proc

   @Override
   public void calculateActivation() {

   }

}// end class
