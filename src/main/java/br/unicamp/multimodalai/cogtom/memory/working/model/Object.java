package br.unicamp.multimodalai.cogtom.memory.working.model;

/*
* Class to encapsulate the Entity data read in from the input files.
*/
public class Object {
      
   private String name;

   public Object(String aName) {
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
