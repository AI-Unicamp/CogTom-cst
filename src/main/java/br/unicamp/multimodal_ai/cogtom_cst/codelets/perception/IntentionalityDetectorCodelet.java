package br.unicamp.multimodal_ai.cogtom_cst.codelets.perception;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import br.unicamp.multimodal_ai.cogtom_cst.memory.data.IdData;
import br.unicamp.multimodal_ai.cogtom_cst.memory.data.IntentionData;
import br.unicamp.multimodal_ai.cogtom_cst.memory.working.model.Agent;
import br.unicamp.multimodal_ai.cogtom_cst.memory.working.model.Intention;
import br.unicamp.multimodal_ai.cogtom_cst.memory.working.model.Object;
import br.unicamp.multimodal_ai.cogtom_cst.memory.working.sync.Activation;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The Intentionality Detector creates Memory Objects for the Agents, objects
 * and Intentions.
 * Input for this exercise is provided through the csv files entities.txt and intentions.csv
 */
public class IntentionalityDetectorCodelet extends Codelet {

   List<IdData> idData;
   List<IntentionData> intentionsData;

   MemoryContainer agentsMC;
   MemoryContainer objectsMC;
   MemoryContainer intentionsMC;

   MemoryObject idActivationMO;
   MemoryObject idDoneActivationMO;
   MemoryObject eddActivationMO;

   // Mindstep the codelet is currently processing.
   int mindStep;
   // Max mindstep for the simulaton
   int maxMindStep;

   public IntentionalityDetectorCodelet(InputStream entitiesStream, InputStream intentionsStream) {

      // to control the end of the simulation
      maxMindStep = 0;

      try {
			Table entityTable = Table.read().csv(entitiesStream);
         Table intentionTable = Table.read().csv(intentionsStream);

         idData = new ArrayList<>();
         intentionsData = new ArrayList<>();

			// Loop through each one of the rows of the tables.
			for (int i = 0; i < entityTable.rowCount(); i++) {
				Row r = entityTable.row(i);
            int step = r.getInt("t");
				String name = r.getString("Entity");
				Boolean isAgent = r.getBoolean("Is_Agent");
            // Add to List
            if (step > maxMindStep) {
               maxMindStep = step;
            }
            IdData data = new IdData(step, name, isAgent);
            idData.add(data);
			}

         for (int i = 0; i < intentionTable.rowCount(); i++) {
				Row r = intentionTable.row(i);
            int step = r.getInt("t");
				String agent = r.getString("Agent");
            String intention = r.getString("Intention");
				String object = r.getString("Object");
            String target = r.getString("Target");
            // Add to list
            IntentionData data = new IntentionData(step, agent, intention, object, target);
            intentionsData.add(data);
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
         agentsMC = (MemoryContainer) getOutput("AGENTS");
         objectsMC = (MemoryContainer) getOutput("OBJECTS");
         intentionsMC = (MemoryContainer) getOutput("INTENTIONS");
         // Activation MOs
         idActivationMO = (MemoryObject) getInput("ID_ACTIVATION");
         eddActivationMO = (MemoryObject) getOutput("EDD_ACTIVATION");
         idDoneActivationMO = (MemoryObject) getOutput("ID_DONE_ACTIVATION");
      }
   }

   @Override
   public void proc() {

      if (mindStep > maxMindStep) {
         System.out.println("Simulation ended.");
         System.exit(0);
      }

      System.out.println("Simulation running mind step: " + mindStep);
      // Clear out memory containers.
      clearMemory();
      
      // Get sublists based on the current mind step and populate Memory Objects.
      for (IdData e: idData) {
         // Entities from the current mindStep
         if (e.mindStep() == mindStep) {
            if (e.isAgent()) {
               // An Agent, create Memory Object and add to Memory Container
               Agent agent = new Agent(e.name());
               agentsMC.setI(agent);
            } else {
               // An Object, create Memory Object and add to Memory Container
               Object obj = new Object(e.name());
               objectsMC.setI(obj);
            }
         }
      }

      for (IntentionData i: intentionsData) {
         // Entities from the current mindStep
         if (i.mindStep() == mindStep) {
            Intention intn = new Intention(i.agent(), i.intention(), i.object(), i.target());
            intentionsMC.setI(intn);
         }
      }

      // Sets EDD activation, indicating it can run now.
      Activation act = new Activation(mindStep, true);
      eddActivationMO.setI(act);

      // Deactivate this codelet until the next mind step
      Activation self = new Activation(mindStep, false);
      idActivationMO.setI(self);

      // Indicates ID processing is done
      idDoneActivationMO.setI(true);
   }// end proc

   @Override
   public void calculateActivation() {
      try {
         Activation act = (Activation) idActivationMO.getI();
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

   /*
   * Utility Method to clear out memory contents between simulation cycles.
   */
   private void clearMemory() {

      // Reset Memory Containers at every mind step, since 
      // the perception memories are not kept between simulation cycles.
      ArrayList<Memory> agts = agentsMC.getAllMemories();
      agts.clear();
      ArrayList<Memory> objs = objectsMC.getAllMemories();
      objs.clear();
      ArrayList<Memory> ints = intentionsMC.getAllMemories();
      ints.clear();
   }

}// end class
