package memory.semantic.model;

/*
* Class to encapsulate the Theory of Mind Affordances objects in Memory.
*/
public class Affordance {

    private String object;
    private String affordance;

    public Affordance(String aObject, String aAffordance) {
        object = aObject;
        affordance = aAffordance;
    }

    /*
    Setters and Getters
    */
    public void setObject(String aObject) {
        object = aObject;
    }

    public String object() {
        return object;
    }
    public void setAffordance(String aAffordance) {
        affordance = aAffordance;
    }

    public String affordance() {
        return affordance;
    }
}
