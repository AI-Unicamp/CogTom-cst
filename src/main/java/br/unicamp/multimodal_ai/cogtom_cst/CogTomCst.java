package br.unicamp.multimodal_ai.cogtom_cst;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.unicamp.cst.core.exceptions.CodeletActivationBoundsException;
import br.unicamp.cst.core.exceptions.CodeletThresholdBoundsException;

/**
 * This is the base Java class.
 */
public class CogTomCst {

        public Logger logger = Logger.getLogger(CogTomCst.class.getName());

        public CogTomCst() {
                Logger.getLogger("codelets").setLevel(Level.SEVERE);
        }

        public void run(ArrayList<InputStream> streams) {
                // Create the Agent Mind and start the cognitive cycles.
                try {
                        AgentMind mind = new AgentMind(streams);
                        mind.run();
                } catch (CodeletThresholdBoundsException e) {
                        e.printStackTrace();
                } catch (CodeletActivationBoundsException e) {
                        e.printStackTrace();
                }
        }

        /**
         * @param args
         */
        public static void main(String[] args) {
                // Execution without a test set
                CogTomCst em = new CogTomCst();
                em.run(null);
        }
}
