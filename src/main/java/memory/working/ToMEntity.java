package memory.working;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class ToMEntity {
      
   private int mindStep;
   private String name;
   private boolean isAgent;

   public ToMEntity(int aMindStep, String aName, boolean aIsAgent) {
      name = aName;
      isAgent = aIsAgent;
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

   public void setIsAgent(boolean aIsAgent) {
         isAgent = aIsAgent;
   }

   public boolean isAgent() {
      return isAgent;
   } 
}