package codelets.behaviors;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import ws3dproxy.model.Thing;

/** 
 * 
 * @author fabiogr
 * 
 * 
 */

public class Forage extends Codelet {
    
        private Memory knownMO;
        private List<Thing> known;
        private Memory legsMO;


	/**
	 * Default constructor
	 */
	public Forage(){       
	}

	@Override
	public void proc() {
            known = (List<Thing>) knownMO.getI();
            if (known.size() == 0) {
		JSONObject message=new JSONObject();
			try {
				message.put("ACTION", "FORAGE");
				legsMO.setI(message.toString());
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }            
		
	}

	@Override
	public void accessMemoryObjects() {
            knownMO = (MemoryObject)this.getInput("KNOWN_APPLES");
            legsMO=(MemoryObject)this.getOutput("LEGS");

		// TODO Auto-generated method stub
		
	}
        
        @Override
        public void calculateActivation() {
            
        }


}
