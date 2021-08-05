package br.unicamp.multimodalai.cogtom_cst.memory.working.model;

/*
* Class to encapsulate the Belief data on Working Memory.
* Beliefs are structures of the form:
* AGENT MENTAL_STATE OBJECT AFFORDANCE or
* AGENT MENTAL_STATE OBJECT AFFORDANCE TARGET_OBJECT
* For this work the only mental state being considered is the BELIEVING.
*/
public class Belief {
      
   private String agent;
   private String object;
   private String mentalState = "BELIEVES";
   private String affordance;
   private String tgtObject = "None";


   public Belief() {
   }

   public Belief(String aAgent, String aObject) {
      agent = aAgent;
      object = aObject;
   }

   public Belief(String aAgent, String aObject, String aAffordance, String aTgtObject) {
      agent = aAgent;
      object = aObject;
      affordance = aAffordance;
      tgtObject = aTgtObject;
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

   public void setAffordance(String aAffordance) {
      affordance = aAffordance;
   }

   public String affordance() {
      return affordance;
   }

   public void setTgtObject(String aTgtObject) {
      tgtObject = aTgtObject;
   }

   public String tgtObject() {
      return tgtObject;
   }

   /*
   * Output utility - overrides base class Object
   */
   public String toStr() {
      String fullBelief = agent + " " + mentalState + " " + object + " " + affordance + " " + tgtObject;
      return fullBelief;
   }
}
