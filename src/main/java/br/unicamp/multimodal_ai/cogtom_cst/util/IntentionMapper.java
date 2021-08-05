package br.unicamp.multimodal_ai.cogtom_cst.util;

import br.unicamp.multimodal_ai.cogtom_cst.memory.working.model.Belief;
import br.unicamp.multimodal_ai.cogtom_cst.memory.working.model.Intention;

/*
* Utility class to modify the Theory of Mind Module Beliefs based on Intention processing.
*/
public class IntentionMapper {
    
    public IntentionMapper() {

    }

    public void modifyBelief(Belief belief, Intention intent) {
        
        // Beliefs are modified according to the group AGENT-OBJECT
        if (belief.agent().equals(intent.agent()) &&
            belief.object().equals(intent.object())) {
                // Check intention and modify accordingly
                switch(intent.intention()) {
                    case "None":
                    case "Exits":
                    case "Enters":
                    case "Search":
                        skip(belief, intent);
                        break;
                    case "ReachFor":
                        reachFor(belief, intent);
                        break;
                    case "Puts": 
                        put(belief, intent);
                        break;
                    case "Gets":
                        get(belief, intent);
                        break;
                    case "Pickup":
                        pickup(belief, intent);
                        break;
                    case "Go": 
                        go(belief, intent);
                        break;
                    case "Give":
                        give(belief, intent);
                        break;
                    case "Drop":
                        drop(belief, intent);
                        break;
                }
            }

    }

    /*
    * Mapper functions based on the intention
    */
    void skip(Belief belief, Intention intent) {
        // Nothing to do.
    }

    void reachFor(Belief belief, Intention intent) {
        // Reaching for an object results
        // on the object ending up on the agent hand.
        belief.setAffordance("OnHand Of");
        belief.setTgtObject(belief.agent());
    }

    void get(Belief belief, Intention intent) {
        // Same as ReachFor
        belief.setAffordance("OnHand Of");
        belief.setTgtObject(belief.agent());
    }

    void put(Belief belief, Intention intent) {
        // Object ends up inside the target object
        belief.setAffordance("Hidden In");
        belief.setTgtObject(intent.target());
    }

    void pickup(Belief belief, Intention intent) {
        // Same as ReachFor
        belief.setAffordance("OnHand Of");
        belief.setTgtObject(belief.agent());
    }

    void go(Belief belief, Intention intent) {
        belief.setAffordance("Went To");
        belief.setTgtObject(intent.target());
    }

    void give(Belief belief, Intention intent) {
        belief.setAffordance("Was Given To");
        belief.setTgtObject(intent.target());
    }

    void drop(Belief belief, Intention intent) {
        belief.setAffordance("Dropped");
        belief.setTgtObject(intent.target());
    }
}
