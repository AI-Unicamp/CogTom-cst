package codelets.perception;

import base.Entity;
import base.Intention;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.IOException;
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

   List<Entity> entities;
   List<Intention> intentions;

   //private Memory visionMO;
   //private Memory knownApplesMO;
   

   public IntentionalityDetector() {

      try {
			Table entityTable = Table.read().csv("input/entities.csv");
         Table intentionTable = Table.read().csv("input/intentions.csv");

			// Loop through each one of the rows of the tables.
			for (int i = 0; i < entityTable.rowCount(); i++) {
				Row r = entityTable.row(i);
            int mindStep = r.getInt("t");
				String name = r.getString("Entity");
				Boolean isAgent = r.getBoolean("Is_Agent");
            // Add to List
            Entity e = new Entity(mindStep, name, isAgent);
            entities.add(e);
			}

         for (int i = 0; i < intentionTable.rowCount(); i++) {
				Row r = intentionTable.row(i);
            int mindStep = r.getInt("t");
				String agent = r.getString("Agent");
            String intention = r.getString("Intention");
				String object = r.getString("Object");
            String target = r.getString("Target");
            // Add to list
            Intention it = new Intention(mindStep, agent, intention, object, target);
            intentions.add(it);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
   }

   @Override
   public void accessMemoryObjects() {
      synchronized (this) {
         //this.visionMO = (MemoryObject) this.getInput("VISION");
      }
      //this.knownApplesMO = (MemoryObject) this.getOutput("KNOWN_APPLES");
   }

   @Override
   public void proc() {

   }// end proc

   @Override
   public void calculateActivation() {

   }

}// end class
