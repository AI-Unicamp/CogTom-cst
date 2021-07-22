package base;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class Entity {
      
   private int mindStep;
   private String name;
   private boolean isAgent;

   public Entity(int aMindStep, String aName, boolean aIsAgent) {
      mindStep = aMindStep;
      name = aName;
      isAgent = aIsAgent;
   }

   /*
   Setters and Getters
   */
   void setMindStep(int aMindStep) {
      mindStep = aMindStep;
   }

   int mindStep() {
      return mindStep;
   }
   
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
