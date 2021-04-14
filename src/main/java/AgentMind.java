import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.Mind;
import codelets.behaviors.Forage;
import codelets.perception.AppleDetector;
import codelets.sensors.InnerSense;
import codelets.sensors.Vision;
import memory.CreatureInnerSense;
import support.MindView;

/**
 *
 * @author fabiogr
 */
public class AgentMind extends Mind {

        public AgentMind(Environment env) {
                super();

                // Declare Memory Objects
                Memory entitiesMO;
                Memory visionMO;
                Memory innerSenseMO;

                // Initialize Memory Objects
                entitiesMO = createMemoryObject("ENTITIES", "");

                visionMO = createMemoryObject("VISION", "");
                CreatureInnerSense cis = new CreatureInnerSense();
                innerSenseMO = createMemoryObject("INNER", cis);

                // Create and Populate MindViewer
                // TODO: Create output system later.
                // MindView mv = new MindView("MindView");
                // mv.addMO(visionMO);
                // mv.addMO(innerSenseMO);
                // mv.StartTimer();
                // mv.setVisible(true);

                // Create Sensor Codelets
                Codelet vision = new Vision();
                vision.addOutput(entitiesMO);
                // vision.addOutput(visionMO);
                insertCodelet(vision); // Creates a vision sensor

                Codelet innerSense = new InnerSense();
                innerSense.addOutput(innerSenseMO);
                insertCodelet(innerSense); // A sensor for the inner state of the creature

                // Create Perception Codelets
                Codelet ad = new AppleDetector();
                ad.addInput(visionMO);
                insertCodelet(ad);

                Codelet forage = new Forage();
                insertCodelet(forage);

                // sets a time step for running the codelets to avoid heating too much your
                // machine
                for (Codelet c : this.getCodeRack().getAllCodelets())
                        c.setTimeStep(200);

                // Start Cognitive Cycle
                start();
        }

}
