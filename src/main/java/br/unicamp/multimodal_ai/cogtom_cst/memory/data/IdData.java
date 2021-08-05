package br.unicamp.multimodal_ai.cogtom_cst.memory.data;

/*
* Class to encapsulate the Entity data read in from the input files for the ID module.
*/
public class IdData {
      
   private int mindStep;
   private String name;
   private boolean isAgent;

   public IdData(int aMindStep, String aName, boolean aIsAgent) {
      mindStep = aMindStep;
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
