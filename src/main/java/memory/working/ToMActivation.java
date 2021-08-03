package memory.working;

/*
* Activation object to indicate that the Codelet should run, specifying the current simulation step
* to be handled.
*/
public class ToMActivation {
    private int mindStep;
    private boolean activation;

    public ToMActivation(int aMindStep, boolean aActivation) {
        mindStep = aMindStep;
        activation = aActivation;
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

    public void setActivation(boolean aActivation) {
        activation = aActivation;
    }

    public boolean Activation() {
        return activation;
    }
}
