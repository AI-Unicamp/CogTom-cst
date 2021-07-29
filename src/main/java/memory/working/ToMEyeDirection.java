package memory.working;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class ToMEyeDirection {
      
   private int mindStep;
   private String agent;
   private String object;

   public ToMEyeDirection(int aMindStep, String aAgent, String aObject) {
      mindStep = aMindStep;
      agent = aAgent;
      object = aObject;
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
