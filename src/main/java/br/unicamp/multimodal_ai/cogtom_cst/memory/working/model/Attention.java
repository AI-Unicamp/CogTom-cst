package br.unicamp.multimodal_ai.cogtom_cst.memory.working.model;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class Attention {
      
   private String agent;
   private String target;

   public Attention(String aAgent, String aTarget) {
      agent = aAgent;
      target = aTarget;
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

   public void setTarget(String aTarget) {
         target = aTarget;
   }

   public String target() {
      return target;
   } 
}
