package memory.working;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class ToMObject {
      
   private String name;

   public ToMObject(String aName) {
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
