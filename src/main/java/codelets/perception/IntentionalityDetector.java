package codelets.perception;

import base.ToMEntity;
import base.ToMIntention;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Intentionality Detector creates Memory Objects for the Agents, objects
 * and Intentions.
 * Input for this exercise is provided through the csv files entities.txt and intentions.csv
 * 
 * @author fabiogr
 *
 */
public class IntentionalityDetector extends Codelet {

   List<ToMEntity> entities;
   List<ToMIntention> intentions;

   MemoryContainer agentsContainer;
   MemoryContainer objectsContainer;
   MemoryContainer intentionsContainer;

   MemoryObject idActivationMO;
   MemoryObject eddActivationMO;

   // Codelets do not seem to record the current time step.
   int mindStep;

   public IntentionalityDetector() {

      try {
			Table entityTable = Table.read().csv("input/entities.csv");
         Table intentionTable = Table.read().csv("input/intentions.csv");

         entities = new ArrayList<>();
         intentions = new ArrayList<>();

			// Loop through each one of the rows of the tables.
			for (int i = 0; i < entityTable.rowCount(); i++) {
				Row r = entityTable.row(i);
            int step = r.getInt("t");
				String name = r.getString("Entity");
				Boolean isAgent = r.getBoolean("Is_Agent");
            // Add to List
            ToMEntity e = new ToMEntity(step, name, isAgent);
            entities.add(e);
			}

         for (int i = 0; i < intentionTable.rowCount(); i++) {
				Row r = intentionTable.row(i);
            int step = r.getInt("t");
				String agent = r.getString("Agent");
            String intention = r.getString("Intention");
				String object = r.getString("Object");
            String target = r.getString("Target");
            // Add to list
            ToMIntention it = new ToMIntention(step, agent, intention, object, target);
            intentions.add(it);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
      // Started simulation, set mindStep
      mindStep = 1;
   }

   @Override
   public void accessMemoryObjects() {
      synchronized (this) {
         // Memory Containers
         agentsContainer = (MemoryContainer) getOutput("AGENTS");
         objectsContainer = (MemoryContainer) getOutput("OBJECTS");
         intentionsContainer = (MemoryContainer) getOutput("INTENTIONS");
         // Activation MOs
         idActivationMO = (MemoryObject) getInput("ID_ACTIVATION");
         eddActivationMO = (MemoryObject) getOutput("EDD_ACTIVATION");
      }
   }

   @Override
   public void proc() {

      // Clear out memory containers.
      clearMemory();
      
      // Get sublists based on the current mind step and populate Memory Objects.
      for (ToMEntity e: entities) {
         // Entities from the current mindStep
         if (e.mindStep() == mindStep) {
            if (e.isAgent()) {
               // An Agent, create Memory Object and add to Memory Container
               agentsContainer.setI(e);
            } else {
               // An Object, create Memory Object and add to Memory Container
               objectsContainer.setI(e);
            }
         }
      }

      for (ToMIntention i: intentions) {
         // Entities from the current mindStep
         if (i.mindStep() == mindStep) {
            intentionsContainer.setI(i);
         }
      }

      mindStep++;

      // Sets EDD activation, indicating it can run now.
      eddActivationMO.setI(true);
   }// end proc

   @Override
   public void calculateActivation() {
      try {
         if ((boolean) idActivationMO.getI() == true) {
            setActivation(1.0d);
         } else {
            setActivation(0.0d);
         }
      } catch (CodeletActivationBoundsException e) {
            e.printStackTrace();
      } 
   }

   /*
   * Utility Method to clear out memory contents between simulation cycles.
   */
   private void clearMemory() {

      // Reset Memory Containers at every mind step, since 
      // the perception memories are not kept between simulation cycles.
      ArrayList<Memory> agts = agentsContainer.getAllMemories();
      agts.clear();
      ArrayList<Memory> objs = objectsContainer.getAllMemories();
      objs.clear();
      ArrayList<Memory> ints = intentionsContainer.getAllMemories();
      ints.clear();
   }

}// end class
