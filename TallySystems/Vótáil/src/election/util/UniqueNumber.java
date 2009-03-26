
package election.util;

/*
 * Copyright (c) 2005-2009 Dermot Cochran
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 * Unique, non-repeated number used for internal identifiers.
 * 
 * @author Dermot Cochran
 */

/* <BON>
 * class_chart UNIQUE_NUMBER
 * explanation
 *   "Unique internal identifier"
 * query
 *   "What is the value of the next unique number?"
 * constraint
 *   "No two internal identifiers are the same"
 * </BON>
 */

public class UniqueNumber {
   
private /*@ spec_public @*/ static volatile int uniqueID = 0;

/**
 * Generates a unique ID number
 * 
 * @return A unique ID number
 */
   /*@ public normal_behavior
     @ assignable uniqueID;
     @ ensures 0 < \result;
     @ ensures \old(uniqueID) < \result;
     @*/ 
   public synchronized static int getUniqueID() {
       return ++uniqueID;
   }
   
 /**
  * Simple unit test for uniqueness of generated ID values
  *
  */
   //@ requires args != null;
   public void main (String args[]) {
	   int first = UniqueNumber.getUniqueID();
	   int second = UniqueNumber.getUniqueID();
	   //@ assert first != second;
 	   int next = 0;
	   for (int i = 0; i < args.length; i++) {
		   next = UniqueNumber.getUniqueID();
		   //@ assert next != second;
		   //@ assert next != first;
	   }
   }

}
