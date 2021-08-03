package memory.working;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class ToMAttention {
      
   private String agent;
   private String object;

   public ToMAttention(String aAgent, String aObject) {
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
