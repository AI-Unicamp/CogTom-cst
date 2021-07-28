import java.util.logging.Level;
import java.util.logging.Logger;

import br.unicamp.cst.core.exceptions.CodeletThresholdBoundsException;

/**
 *
 * @author fabiogr
 */
public class Main {

        public Logger logger = Logger.getLogger(Main.class.getName());

        public Main() {
                Logger.getLogger("codelets").setLevel(Level.SEVERE);
                // Create the Agent Mind
                try {
                        AgentMind a = new AgentMind();
                } catch (CodeletThresholdBoundsException e) {
                        e.printStackTrace();
                }
        }

        /**
         * @param args
         */
        public static void main(String[] args) {
                Main em = new Main();
        }

}
