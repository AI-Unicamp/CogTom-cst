package br.unicamp.multimodal_ai.cogtom_cst.memory.working.model;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class Positioning {
      
   private String name;
   private String location;

   public Positioning(String aName, String aLocation) {
      name = aName;
      location = aLocation;
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

   public void setLocation(String aLocation) {
      location = aLocation;
   }

   public String location() {
      return location;
   }
}
