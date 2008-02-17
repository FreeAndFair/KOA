
package ie.koa;

public class UniqueNumber {
   
private static volatile int uniqueID = 0;

/**
 * Generates a unique random ID number
 * 
 * @return A unique and random ID number
 */
   /*@ public normal_behavior
     @ ensures \result >= 0;
     @*/ 
   public synchronized static int getUniqueID() {
      return uniqueID++;
   }

}
