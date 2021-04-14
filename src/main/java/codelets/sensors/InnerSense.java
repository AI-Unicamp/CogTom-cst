package codelets.sensors;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;
import memory.CreatureInnerSense;

/**
 * This class reads information from this agent's state and writes it to an
 * inner sense sensory buffer.
 * 
 * @author fabiogr
 *
 */

public class InnerSense extends Codelet {

        private Memory innerSenseMO;
        private CreatureInnerSense cis;

        public InnerSense() {
        }

        @Override
        public void accessMemoryObjects() {
                innerSenseMO = (MemoryObject) this.getOutput("INNER");
                cis = (CreatureInnerSense) innerSenseMO.getI();
        }

        public void proc() {

        }

        @Override
        public void calculateActivation() {

        }

}
