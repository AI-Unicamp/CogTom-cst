package memory.working;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class ToMAgent {
      
   private String name;

   public ToMAgent(String aName) {
      name = aName;
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
}
