package memory.semantic;

/*
* Class to encapsulate the Theory of Mind Affordances objects in Memory.
*/
public class ToMAffordance {

    private String object;
    private String affordance;

    public ToMAffordance(String aObject, String aAffordance) {
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
