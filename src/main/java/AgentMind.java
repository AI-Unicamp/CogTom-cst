import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.Mind;
import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import br.unicamp.cst.core.exceptions.CodeletThresholdBoundsException;
import codelets.perception.EyeDirectionDetector;
import codelets.perception.IntentionalityDetector;

/**
 * 
 * @author fabiogr
 */
public class AgentMind extends Mind {

        Memory agentsMC;
        Memory objectsMC;
        Memory intentionsMC;
        Memory affordancesMC;
        Memory attentionsMC;
        Memory sharedAttentionsMC;
        Memory beliefsMC;

        public AgentMind() throws CodeletThresholdBoundsException,
                                  CodeletActivationBoundsException {
                super();

                // Declare and initialize Memory Containers (for multiple MOs)
                agentsMC = createMemoryContainer("AGENTS");
                objectsMC = createMemoryContainer("OBJECTS");
                intentionsMC = createMemoryContainer("INTENTIONS");
                affordancesMC = createMemoryContainer("AFFORDANCES");
                attentionsMC = createMemoryContainer("ATTENTIONS");
                sharedAttentionsMC = createMemoryContainer("SHAREDATTN");
                beliefsMC = createMemoryContainer("BELIEFS");
               
                // Create and Populate MindViewer
                // TODO: Create output system later.
                // MindView mv = new MindView("MindView");
                // mv.addMO(visionMO);
                // mv.addMO(innerSenseMO);
                // mv.StartTimer();
                // mv.setVisible(true);

                // Create Perception Codelets
                // ID
                Codelet id = new IntentionalityDetector(this);
                id.addOutput(agentsMC);
                id.addOutput(objectsMC);
                id.addOutput(intentionsMC);
                id.setActivation(1.0d);
                id.setThreshold(0.0d);
                insertCodelet(id);

                // EDD
                Codelet edd = new EyeDirectionDetector(this);
                edd.addInput(agentsMC);
                edd.addInput(objectsMC);
                edd.addInput(intentionsMC);
                edd.addOutput(attentionsMC);
                // EDD can only run when activated by ID
                // So the activation will have to be explicitly set.
                edd.setActivation(0.0d);
                edd.setThreshold(1.0d);    
                insertCodelet(edd);

                // TODO: SAM, ToMM

                // sets a time step for running the codelets to avoid heating too much your
                // machine
                for (Codelet c : this.getCodeRack().getAllCodelets())
                        // probably 1 second
                        c.setTimeStep(1000);
        }

        /*
        * Starts running the cognitive cycles
        */
        public void run() {
                // Start Cognitive Cycle
                start();
        }

}
