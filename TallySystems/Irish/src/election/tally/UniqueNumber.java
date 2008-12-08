
package election.tally;

public class UniqueNumber {
   
private static volatile int uniqueID = 0;

/**
 * Generates a unique ID number
 * 
 * @return A unique ID number
 */
   /*@ public normal_behavior
     @ assignable uniqueID;
     @ ensures \result >= 0;
     @ ensures \old(uniqueID) < uniqueID;
     @*/ 
   public synchronized static int getUniqueID() {
       return uniqueID++;
   }

}
