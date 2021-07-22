package base;

/*
* Class to encapsulate the Intention data read in from the input files.
*/
public class Intention {

    private int mindStep;
    private String agent;
    private String intention;
    private String object;
    private String target;

    public Intention(int aMindStep, 
              String aAgent,
              String aIntention,
              String aObject,
              String aTarget){

        mindStep = aMindStep;
        agent = aAgent;
        intention = aIntention;
        object = aObject;
        target = aTarget;
    }
    
    /*
    Setters and Getters
    */
    void setMindStep(int aMindStep) {
       mindStep = aMindStep;
    }

    int mindStep() {
        return mindStep;
    }

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
