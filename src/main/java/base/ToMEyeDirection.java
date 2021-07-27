package base;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class ToMEyeDirection extends ToMBase {
      
   private String agent;
   private String object;

   public ToMEyeDirection(int aMindStep, String aAgent, String aObject) {
      setMindStep(aMindStep);
      agent = aAgent;
      object = aObject;
   }

   /*
   Setters and Getters
   */
   public void setAgent(String aAgent) {
       agent = aAgent;
   }

   public String agent() {
      return agent;
   }

   public void setObject(String aObject) {
       object = aObject;
   }
   
   public String object() {
      return object;
   } 
}
