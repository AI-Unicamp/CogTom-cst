import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabiogr
 */
public class ExperimentMain {

        public Logger logger = Logger.getLogger(ExperimentMain.class.getName());

        public ExperimentMain() {
                Logger.getLogger("codelets").setLevel(Level.SEVERE);
                // Create the Agent Mind
                AgentMind a = new AgentMind();
        }

        /**
         * @param args
         */
        public static void main(String[] args) {
                ExperimentMain em = new ExperimentMain();
        }

}
