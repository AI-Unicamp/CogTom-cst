package base;

/*
* Class to encapsulate the Intention data read in from the input.
*/
public class Intention {

    private String agent;
    private String intention;
    private String object;
    private String target;

    /*
    Setters and Getters
    */
    void setAgent(String aAgent) {
       agent = aAgent;
    }

    String agent() {
       return agent;
    }

    void setIntention(String aIntention) {
        intention = aIntention;
    }
 
    String intention() {
        return intention;
    }

    void setObject(String aObject) {
        object = aObject;
    }
 
     String object() {
        return object;
    }

    void setTarget(String aTarget) {
        target = aTarget;
    }
 
    String target() {
        return target;
    }
}
