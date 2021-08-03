package memory.working.model;

/*
* Class to encapsulate the Intention data read in from the input files.
*/
public class Intention {

    private int mindStep;
    private String agent;
    private String intention;
    private String object;
    private String target;

    public Intention(int aMindStep, String aAgent,String aIntention, String aObject, String aTarget){
        mindStep = aMindStep;
        agent = aAgent;
        intention = aIntention;
        object = aObject;
        target = aTarget;
    }

    /*
    Setters and Getters
    */
    public void setMindStep(int aMindStep) {
        mindStep = aMindStep;
    }

        public int mindStep() {
        return mindStep;
    }

    public void setAgent(String aAgent) {
        agent = aAgent;
    }

    public String agent() {
        return agent;
    }

    public void setIntention(String aIntention) {
        intention = aIntention;
    }

    public String intention() {
        return intention;
    }

    public void setObject(String aObject) {
        object = aObject;
    }

    public String object() {
        return object;
    }

    public void setTarget(String aTarget) {
        target = aTarget;
    }

    public String target() {
        return target;
    }
}
