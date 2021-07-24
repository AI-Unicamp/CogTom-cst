package base;

/*
* Class to encapsulate the Intention data read in from the input files.
*/
public class ToMIntention extends ToMBase {

    private String agent;
    private String intention;
    private String object;
    private String target;

    public ToMIntention(int aMindStep, 
              String aAgent,
              String aIntention,
              String aObject,
              String aTarget){

        setMindStep(aMindStep);
        agent = aAgent;
        intention = aIntention;
        object = aObject;
        target = aTarget;
    }
    
    /*
    Setters and Getters
    */
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
