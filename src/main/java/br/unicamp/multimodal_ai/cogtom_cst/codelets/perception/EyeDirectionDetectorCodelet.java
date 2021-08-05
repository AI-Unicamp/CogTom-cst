package br.unicamp.multimodal_ai.cogtom_cst.codelets.perception;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import br.unicamp.multimodal_ai.cogtom_cst.memory.data.EddData;
import br.unicamp.multimodal_ai.cogtom_cst.memory.working.model.Agent;
import br.unicamp.multimodal_ai.cogtom_cst.memory.working.model.Attention;
import br.unicamp.multimodal_ai.cogtom_cst.memory.working.sync.Activation;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;

/**
 * EDD codelet identifies eye direction and attaches that information to the Attention MOs.
 * It takes as inputs the Memory Containers created by the ID codelet.
 * Input for this exercise is provided through the csv file eye_directions.txt
 */
public class EyeDirectionDetectorCodelet extends Codelet {

    List<EddData> eddData;

    MemoryContainer agentsMC;
    MemoryContainer objectsMC;
    MemoryContainer intentionsMC;        
    MemoryContainer attentionsMC;

    MemoryObject eddActivationMO;
    MemoryObject samActivationMO;
    MemoryObject eddDoneActivationMO;

    // Mindstep the codelet is currently processing.
    int mindStep;

    public EyeDirectionDetectorCodelet() {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream entitiesStream = classLoader.getResourceAsStream("input/eye_directions.csv");

			Table eddDataTable = Table.read().csv(entitiesStream);

            eddData = new ArrayList<>();

            // Loop through each one of the rows of the tables.
            for (int i = 0; i < eddDataTable.rowCount(); i++) {
                Row r = eddDataTable.row(i);
                int step = r.getInt("t");
                String agt = r.getString("Agent");
                String tgt = r.getString("Target");
                // Add to List
                EddData data = new EddData(step, agt, tgt);
                eddData.add(data);
            }
          } catch (IOException e1) {
              e1.printStackTrace();
          }
    }

    @Override
    public void accessMemoryObjects() {
        // Memory Containers
        agentsMC = (MemoryContainer) getInput("AGENTS");
        objectsMC = (MemoryContainer) getInput("OBJECTS");
        intentionsMC = (MemoryContainer) getInput("INTENTIONS");
        attentionsMC = (MemoryContainer) getOutput("ATTENTIONS");
        // Activation MOs
        eddActivationMO = (MemoryObject) getInput("EDD_ACTIVATION");
        eddDoneActivationMO = (MemoryObject) getOutput("EDD_DONE_ACTIVATION");
        samActivationMO = (MemoryObject) getOutput("SAM_ACTIVATION");
    }

    @Override
    public void calculateActivation() {
        try {
            Activation act = (Activation) eddActivationMO.getI();
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

        // Get Agents from the Memory Container
        ArrayList<Memory> agents = agentsMC.getAllMemories();
        for (Memory a: agents) {
            Agent agt = (Agent) a.getI();
            // Search for the agent Eye Directions in the EDD Data store
            for (EddData data: eddData) {
                // Entities from the current mindStep
                if (data.mindStep() == mindStep && data.agent().equals(agt.name())) {
                    // Add to container.
                    Attention att = new Attention(data.agent(), data.target());
                    attentionsMC.setI(att);
                }
            }
        }

        // Sets SAM activation, indicating it can run now.
        Activation act = new Activation(mindStep, true);
        samActivationMO.setI(act);

        // Deactivate this codelet until the next mind step
        Activation self = new Activation(mindStep, false);
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
        ArrayList<Memory> attns = attentionsMC.getAllMemories();
        attns.clear();
 }
}
