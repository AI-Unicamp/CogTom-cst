package base;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class ToMEntity extends ToMBase {
      
   private String name;
   private boolean isAgent;

   public ToMEntity(int aMindStep, String aName, boolean aIsAgent) {
      setMindStep(aMindStep);
      name = aName;
      isAgent = aIsAgent;
   }

   /*
   Setters and Getters
   */
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
