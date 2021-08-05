package br.unicamp.multimodal_ai.cogtom_cst.memory.working.model;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class Agent {
      
   private String name;

   public Agent(String aName) {
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
