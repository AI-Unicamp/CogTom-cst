package memory.data;

/*
* Class to encapsulate the Affordances read in from the data file.
*/
public class AffordanceData {

    private String object;
    private String affordance;

    public AffordanceData(String aObject, String aAffordance) {
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
