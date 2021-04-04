package codelets.motor;


import org.json.JSONObject;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;
import java.util.Random;
import java.util.logging.Logger;
import org.json.JSONException;
import ws3dproxy.model.Creature;

/**
 *  Legs Action Codelet monitors working storage for instructions and acts on the World accordingly.
 *  
 * @author fabiogr
 *
 */

public class LegsActionCodelet extends Codelet{

	private Memory legsActionMO;
	private double previousTargetx=0;
	private double previousTargety=0;
	private String previousLegsAction="";
        private Creature c;
        double old_angle = 0;
        int k=0;
        static Logger log = Logger.getLogger(LegsActionCodelet.class.getCanonicalName());

	public LegsActionCodelet(Creature nc) {
		c = nc;
	}
	
	@Override
	public void accessMemoryObjects() {
		legsActionMO=(MemoryObject)this.getInput("LEGS");
	}
	
	@Override
	public void proc() {
            
                String comm = (String) legsActionMO.getI();
                if (comm == null) comm = "";
                Random r = new Random();
		
		if(!comm.equals("") ){
			
			try {
				JSONObject command=new JSONObject(comm);
                                if (command.has("ACTION")) {
                                    int x=0,y=0;
                                    String action=command.getString("ACTION");
                                    if(action.equals("FORAGE")){
                                               //if (!comm.equals(previousLegsAction)) { 
                                               if (!comm.equals(previousLegsAction)) 
                                                    log.info("Sending Forage command to agent");
                                                try {  
                                                      c.rotate(2);     
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
					}
                                    else if(action.equals("GOTO")){
                                        if (!comm.equals(previousLegsAction)) {
                                            double speed=command.getDouble("SPEED");
					    double targetx=command.getDouble("X");
					    double targety=command.getDouble("Y");
					    if (!comm.equals(previousLegsAction))
                                                log.info("Sending move command to agent: ["+targetx+","+targety+"]");
                                            try {
                                                 c.moveto(speed, targetx, targety);
                                            } catch(Exception e) {
                                                e.printStackTrace();
                                            }
					    previousTargetx=targetx;
					    previousTargety=targety;
                                        }
                                        
				    } else {
					log.info("Sending stop command to agent");
                                        try {
                                             c.moveto(0,0,0);
                                        } catch(Exception e) {
                                            e.printStackTrace();
                                        }  
				    }
                                }
			previousLegsAction=comm;
                        k++;	
			} catch (JSONException e) {e.printStackTrace();}
		}
	}//end proc

    @Override
    public void calculateActivation() {
        
    }


}
