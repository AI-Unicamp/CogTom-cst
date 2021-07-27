import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.Mind;
import codelets.perception.EyeDirectionDetector;
import codelets.perception.IntentionalityDetector;

/**
 *
 * @author fabiogr
 */
public class AgentMind extends Mind {

        public AgentMind() {
                super();

                // Declare and initialize Memory Objects
                Memory agentsMC = createMemoryContainer("AGENTS");
                Memory objectsMC = createMemoryContainer("OBJECTS");
                Memory intentionsMC = createMemoryContainer("INTENTIONS");
                Memory affordancesMC = createMemoryContainer("AFFORDANCES");
                Memory attentionsMC = createMemoryContainer("ATTENTIONS");
                Memory sharedAttentionsMC = createMemoryContainer("SHAREDATTN");
                Memory beliefsMC = createMemoryContainer("BELIEFS");
               
                // Create and Populate MindViewer
                // TODO: Create output system later.
                // MindView mv = new MindView("MindView");
                // mv.addMO(visionMO);
                // mv.addMO(innerSenseMO);
                // mv.StartTimer();
                // mv.setVisible(true);

                // Create Perception Codelets
                // ID
                Codelet id = new IntentionalityDetector();
                id.addOutput(agentsMC);
                id.addOutput(objectsMC);
                id.addOutput(intentionsMC);
                insertCodelet(id);

                // EDD
                Codelet edd = new EyeDirectionDetector();
                edd.addInput(agentsMC);
                edd.addInput(objectsMC);
                edd.addInput(intentionsMC);
                edd.addOutput(attentionsMC);
                insertCodelet(edd);

                // TODO: SAM, ToMM

                // sets a time step for running the codelets to avoid heating too much your
                // machine
                for (Codelet c : this.getCodeRack().getAllCodelets())
                        // probably 1 second
                        c.setTimeStep(1000);

                // Start Cognitive Cycle
                start();
        }

}
