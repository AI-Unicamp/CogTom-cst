package memory.data;

/*
* Class to encapsulate the Eye Direction data read in from the input files for the ID module.
*/
public class EddData {
      
   private int mindStep;
   private String agent;
   private String target;

   public EddData(int aMindStep, String aAgent, String aTarget) {
      mindStep = aMindStep;
      agent = aAgent;
      target = aTarget;
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

   public void setTarget(String aTarget) {
         target = aTarget;
   }

   public String target() {
      return target;
   } 
}
