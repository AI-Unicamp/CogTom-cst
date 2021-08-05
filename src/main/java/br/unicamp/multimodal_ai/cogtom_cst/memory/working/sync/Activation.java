package br.unicamp.multimodal_ai.cogtom_cst.memory.working.sync;

/*
* Activation object to indicate that the Codelet should run, specifying the current simulation step
* to be handled.
*/
public class Activation {
    private int mindStep;
    private boolean active;

    public Activation(int aMindStep, boolean aActive) {
        mindStep = aMindStep;
        active = aActive;
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

    public void setActive(boolean aActive) {
        active = aActive;
    }

    public boolean Active() {
        return active;
    }
}
