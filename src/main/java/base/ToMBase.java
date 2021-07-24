package base;

/*
* Base Class for ToM objects
*/
public class ToMBase {
    
    // The current mindStep for this object in the simulation.
    private int mindStep;

    public void setMindStep(int aMindStep) {
        mindStep = aMindStep;
    }

    public int mindStep() {
        return mindStep;
    }
}
