package codelets.perception;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Detect apples in the vision field. This class detects a number of things
 * related to apples, such as if there are any within reach, any on sight, if
 * they are rotten, and so on.
 * 
 * @author fabiogr
 *
 */
public class AppleDetector extends Codelet {

   private Memory visionMO;
   private Memory knownApplesMO;

   public AppleDetector() {

   }

   @Override
   public void accessMemoryObjects() {
      synchronized (this) {
         this.visionMO = (MemoryObject) this.getInput("VISION");
      }
      this.knownApplesMO = (MemoryObject) this.getOutput("KNOWN_APPLES");
   }

   @Override
   public void proc() {

   }// end proc

   @Override
   public void calculateActivation() {

   }

}// end class
