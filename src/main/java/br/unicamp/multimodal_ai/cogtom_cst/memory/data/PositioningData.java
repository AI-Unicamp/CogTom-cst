package br.unicamp.multimodal_ai.cogtom_cst.memory.data;

/*
* Class to encapsulate the Positioning read in from the data file.
*/
public class PositioningData {

    int mindStep;
    private String name;
    private String location;

    public PositioningData(int aMindStep, String aName, String aLocation) {
        mindStep = aMindStep;
        name = aName;
        location = aLocation;
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

    public void setName(String aName) {
        name = aName;
    }

    public String name() {
        return name;
    }
    public void setLocation(String aLocation) {
        location = aLocation;
    }

    public String location() {
        return location;
    }
}
