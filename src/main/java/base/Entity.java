package base;

/*
* Class to encapsulate the Entity data read in from the input.
*/
public class Entity {
      
    private String name;
    private boolean isAgent;

    /*
    Setters and Getters
    */
    void setName(String aName) {
       name = aName;
    }

    String name() {
        return name;
    }

    void setIsAgent(boolean aIsAgent) {
       isAgent = aIsAgent;
    }
    boolean isAgent() {
       return isAgent;
    } 
}
