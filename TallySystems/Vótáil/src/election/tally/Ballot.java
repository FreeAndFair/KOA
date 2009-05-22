/**
 * Votail - PR-STV ballot counting for Irish elections
 * 
 * Copyright (c) 2005-2009 Dermot Cochran, Joseph R. Kiniry and Patrick E. Tierney
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

package election.tally;

//@ refine "Ballot.java-refined";

import election.util.UniqueNumber;

/* <BON>
 * class_chart BALLOT
 * indexing
 *   author: "Dermot Cochran"
 * explanation
 *   "An electronic representation of a valid ballot paper"
 * query
 *   "To which continuing candidate is this ballot allocated?",
 *   "In which round of counting was this ballot last transfered?",
 *   "Who is the first preference candidate on this ballot?",
 *   "Is this ballot deeper in the pile than another ballot?",
 *   "What is the internal identifier (sequence number) for this ballot?"
 * command
 *   "Load this ballot from the database"
 * constraint
 *   "Every valid ballot has a valid first preference"
 * </BON>
 */

/**
 * The ordered set of preferences from a ballot paper in an Irish election,
 * which uses the Proportional Representation Single Transfer Vote.
 * (PRSTV) system.
 * 
 * @see <a href="http://www.cev.ie/htm/tenders/pdf/1_2.pdf">
   Department of Environment and Local Government, 
   Count Requirements and Commentary on Count Rules,
   section 3-14</a>
 * 
 * @author <a href="http://kind.ucd.ie/products/opensource/KOA/V�t�il.html">Dermot Cochran</a>
 * @copyright 2005-2009
 */


public class Ballot {
  public static final int MAX_BALLOTS = 150000;

/**
 * Candidate ID value to use for non-transferable ballot papers
 * 
 * @design A special candidate ID value is used to indicate
 * non-transferable votes i.e., when the list of preferences has
 * been exhausted and none of the continuing candidates are in the preference list, 
 * the ballot is deemed to be non-transferable.
 * 
 * @see <a href="http://www.cev.ie/htm/tenders/pdf/1_2.pdf">
 * Department Of Environment and Local Government,
 * Count requirements and Commentary an Count Rules,
 * section 7, pages 23-27</a>
 */
public static final int NONTRANSFERABLE = 0;
	
  /** Ballot ID number */
  //@ public invariant (ballotID == 0) | (0 < ballotID);
  /*@ public instance invariant (\forall Ballot a,b;
    @                                    a != null && b != null && 0 < a.ballotID && 0 < b.ballotID;
    @                                    a.ballotID == b.ballotID <==> a==b);
    @*/
  protected /*@ spec_public @*/ int ballotID;
	
  /** Candidate ID to which this ballot is assigned */
  /*@ public invariant (0 < candidateID) ||
    @  (candidateID == NONTRANSFERABLE);
    @ public invariant (0 <= positionInList &&
    @   positionInList < numberOfPreferences) ==>
    @   candidateID == preferenceList[positionInList];
    @ public invariant ( positionInList == numberOfPreferences) ==>
    @   candidateID == NONTRANSFERABLE;
    @*/
  protected /*@ spec_public @*/ int candidateID;
	
  /** Preference list of candidate IDs */	
  /*@ public invariant (\forall int i;
    @   0 <= i && i < numberOfPreferences;
    @   preferenceList[i] > 0 &&
    @   preferenceList[i] != NONTRANSFERABLE);
    @ public invariant (\forall int i,j;
    @   0 < i && i < numberOfPreferences && 0 <= j && j < i;
    @   preferenceList[i] != preferenceList[j]);
    @*/
  //@ public invariant preferenceList.length == numberOfPreferences;
  protected /*@ spec_public non_null@*/ int[] preferenceList = new int [0];
	
  /** Total number of valid preferences on this ballot paper */
  //@ public invariant (0 == numberOfPreferences) | (0 < numberOfPreferences);
  // @design numberOfPreferences == 0 means an empty ballot.
  // @see #ballotID invariant
  protected /*@ spec_public @*/ int numberOfPreferences;
  
  /** Position within preference list */
  //@ public initially positionInList == 0;
  //@ public invariant 0 <= positionInList;
  //@ public invariant positionInList <= numberOfPreferences;
  //@ public constraint \old(positionInList) <= positionInList;
  protected /*@ spec_public @*/ int positionInList;

  /** Maximum possible numbers of counting rounds*/
  public static final int MAXIMUM_ROUNDS_OF_COUNTING = Candidate.MAX_CANDIDATES - 1;

  /** Default value of internal identifier for an empty ballot */
  private static final int NO_ID_YET = 0;
  
  /** Candidate ID to which the vote is assigned at the end of each count */
  //@ public invariant candidateIDAtCount.length == MAXIMUM_ROUNDS_OF_COUNTING;
  protected /*@ spec_public non_null @*/ int[] candidateIDAtCount = 
    new int [MAXIMUM_ROUNDS_OF_COUNTING];

  /** Last count number in which this ballot was transferred */
  //@ public invariant 0 <= countNumberAtLastTransfer;
  //@ public invariant countNumberAtLastTransfer < MAXIMUM_ROUNDS_OF_COUNTING;
  //@ public initially countNumberAtLastTransfer == 0;  
  protected /*@ spec_public @*/ int countNumberAtLastTransfer;
    
  /** Random number used for proportional distribution of surplus votes */
  /*@ public invariant (\forall Ballot a,b; a != null && b != null;
    @   (a.randomNumber == b.randomNumber) <==> (a == b));
    @ public constraint randomNumber == \old (randomNumber);
    @*/
  protected /*@ spec_public @*/ int randomNumber;
  //@ ghost int _randomNumber;
    
  /** 
   * Default constructor
   */
  /*@ also public normal_behavior
    @   ensures numberOfPreferences == 0;
    @   ensures countNumberAtLastTransfer == 0;
    @   ensures positionInList == 0;
    @   ensures candidateID == NONTRANSFERABLE; 
    @   ensures ballotID == NO_ID_YET;
    @*/
  public /*@ pure @*/ Ballot() {
	  
    numberOfPreferences = 0;
    countNumberAtLastTransfer = 0;
    positionInList = 0;
    candidateID = NONTRANSFERABLE; 
    ballotID = NO_ID_YET;
    assignRandomNumber(); 
    //@ set _randomNumber = randomNumber;
  }

 

private void assignRandomNumber() {
	randomNumber = UniqueNumber.getUniqueID();
}
    
  /**
   * Get the location of the ballot at each count
   * 
   * @param countNumber The round of counting which we need to check
   * @return The candidate ID or the NONTRANSFERABLE constant
   */    
  /*@ also public normal_behavior
    @ requires 0 <= countNumber;
    @ requires countNumber <= countNumberAtLastTransfer;
    @ ensures \result == candidateIDAtCount[countNumber];
    @*/
  public /*@ pure @*/ int getPreferenceAtCount(final int countNumber) {
    return candidateIDAtCount[countNumber];
  }
    
  /**
   * Get the count number for the last transfer of this ballot
   * 
   * @return The last count at which this ballot was transferred
   */    
  /*@ also public normal_behavior
    @   ensures \result == countNumberAtLastTransfer;
    @*/
  public /*@ pure @*/ int getCountNumberAtLastTransfer(){
    return countNumberAtLastTransfer;
  }
    
  /**
   * Get the first preference vote from this ballot
   * 
   * @design There must always be a first preference vote in each ballot,
   * otherwise the vote is not included and need not be loaded.
   * The quota is calculated from the number of first preference votes,
   * so that empty ballots are not included.
   * 
   * @reference http://www.cev.ie/htm/tenders/pdf/1_2.pdf, section3, page 12
   * @return The candidate ID of the first preference for this ballot
   */ 
  /*@ also public normal_behavior
    @ requires 0 < numberOfPreferences;
    @ ensures \result != NONTRANSFERABLE;
    @ ensures \result == preferenceList[0];      
    @*/
  public /*@ pure @*/ int getFirstPreference() {
    return preferenceList[0];
  }
    
  /**
   * Load the ballot details
   * 
   * @param candidateIDList
   *            List of candidate IDs in order from first preference
   * 
   * @param listSize
   *            Number of candidate IDs in the list
   * 
   * @design There should be at least one preference in the list. Empty or spoilt
   *         votes should neither be loaded nor counted. There should be no
   *         duplicate preferences in the list and none of the candidate ID values
   *         should match the special value for non transferable votes.
   *         <p>
   *         There should be no duplicates in the preference list; but there is no
   *         need to make this a precondition because duplicates will be ignored
   *         and skipped over.
   */    
  /*@ also public normal_behavior
    @   requires 0 < listSize;
    @   requires (\forall int i; 0 <= i && i < listSize;
    @     (candidateIDList[i]) != NONTRANSFERABLE);
    @   requires (\forall int i; 0 <= i && i < listSize;
    @     (candidateIDList[i]) > 0);
    @   requires candidateIDList.length == listSize;
    @   requires positionInList < listSize;
    @	assignable numberOfPreferences, ballotID, preferenceList, candidateID;
    @   ensures numberOfPreferences == listSize;
    @   ensures ballotID != NO_ID_YET;
    @   ensures preferenceList.length == listSize;
    @   ensures (\forall int i; 0 <= i && i < listSize;
    @     (preferenceList[i] == candidateIDList[i]));
    @*/
   public void load(/*@ non_null @*/ int[] candidateIDList, int listSize){
    numberOfPreferences = listSize;
    
    // Assign a unique internal identifier
    do {
      ballotID = UniqueNumber.getUniqueID();
    } 
    while (ballotID == NO_ID_YET);
    preferenceList = new int [listSize];
    for(int i = 0; i < listSize; i++){
 		preferenceList[i] = candidateIDList[i];
 	  }
    candidateID = getFirstPreference();
  }
    
  /**
   * Get candidate ID to which the ballot is assigned 
   * 
   * @return The candidate ID to which the ballot is assigned
   */    
  /*@ also public normal_behavior
    @   requires 0 <= positionInList;
    @   requires positionInList <= numberOfPreferences;
    @   requires preferenceList != null;
    @   ensures (positionInList == numberOfPreferences) ==>
    @     (\result == NONTRANSFERABLE);
    @   ensures (positionInList < numberOfPreferences) ==>
    @     (\result == preferenceList[positionInList]);
    @*/   
  public /*@ pure @*/ int getCandidateID(){
    if(0 <= positionInList && positionInList <= numberOfPreferences){
      if(preferenceList != null){
        if(positionInList == numberOfPreferences){
          return NONTRANSFERABLE;
        }else if(positionInList < numberOfPreferences){
          return preferenceList[positionInList];
        }
      }
    }
    return 0;
  }
    
  /**
   * Get next preference candidate ID
   * 
   * @param offset The number of preferences to look ahead
   * 
   * @return The next preference candidate ID
   */    
  /*@ also public normal_behavior
    @ requires 0 <= positionInList;
    @ requires 1 <= offset;
    @ requires positionInList <= numberOfPreferences;
    @ requires preferenceList != null;
    @ ensures (positionInList + offset >= numberOfPreferences) ==>
    @   (\result == NONTRANSFERABLE);
    @ ensures (positionInList + offset < numberOfPreferences) ==>
    @   (\result == preferenceList[positionInList + offset]);
    @*/

  public /*@ pure @*/ int getNextPreference(int offset){
        if(positionInList + offset < numberOfPreferences){
          return (preferenceList[positionInList + offset]);
        }else{
          return NONTRANSFERABLE;
        }
  }
 
  /**
   * Transfer this ballot to the next preference candidate.
   * 
   * @design This method may be called multiple times during the same count
   * until the ballot is non-transferable or a continuing candidate ID is
   * found in the remainder of the preference list.
   * 
   * @param countNumber the count number at which the ballot was transfered.
   */    
  /*@ also public normal_behavior
    @   requires 0 <= positionInList;
    @   requires positionInList <= numberOfPreferences;
    @   requires countNumberAtLastTransfer <= countNumber;
    @   requires countNumber < MAXIMUM_ROUNDS_OF_COUNTING;
    @   assignable countNumberAtLastTransfer, positionInList, preferenceList[*], candidateID;
    @   ensures countNumberAtLastTransfer == countNumber;
    @   ensures \old(positionInList) <= positionInList;
    @   ensures (positionInList == \old(positionInList) + 1) ||
    @           (positionInList == numberOfPreferences);
    @*/
  public void transfer(int countNumber) {
 			if (countNumberAtLastTransfer <= countNumber) {
				countNumberAtLastTransfer = countNumber;
				if (positionInList != numberOfPreferences) {
					shiftPreferenceList();
					positionInList = positionInList + 1;
				}else if(positionInList == numberOfPreferences) {
					candidateID = NONTRANSFERABLE;
				}
			}
	}
  
  /**
	 * Corrects the candidate list after it has been changed.
	 * 
	 * @param shiftPosition
	 *            the position in the list from where the list must be
    *            
    * @note Dermot Cochran: The ballot information still needs to be preserved
    * when publishing the results.  Not all of the ballot can be publicly revealed;
    * only the current top preference and next preference so that the count can
    * independently checked at each round.
	 */
  /*@ private normal_behavior
    @ requires 0 < positionInList;
    @ requires positionInList < preferenceList.length;
    @ assignable preferenceList[*];
    @ ensures preferenceList[positionInList+1] == \old(preferenceList[positionInList]);
    @*/
  private /*@ helper @*/ void shiftPreferenceList() {
	  if( 0 < positionInList && positionInList < numberOfPreferences-1){
		int temp = preferenceList[positionInList+1];
		preferenceList[positionInList+1] = preferenceList[positionInList];
		preferenceList[positionInList] = temp;
	  }
	}
    
  /**
   * Get ballot ID number
   * 
   * @return ID number for this ballot
   */    
  /*@ also public normal_behavior
    @ ensures \result == ballotID;
    @*/
  public /*@ pure @*/ int getBallotID(){
    return ballotID;
  }
     
  /**
   * This method checks if this ballot paper is assigned to this candidate.
   * 
   * @design It is valid to use <code>NONTRANSFERABLE</code> as the ID value 
   * to be checked. This ballot paper can only be assigned to one candidate at 
   * a time; there is no concept of fractional transfer of votes in the Irish 
   * electoral system.
   * 
   * @param candidateIDToCheck The unique identifer for this candidate
   * 
   * @return <code>true</code> if this ballot paper is assigned to this 
   * candidate ID
   */    
  /*@ also public normal_behavior
    @ requires (0 < candidateIDToCheck) ||
    @   (candidateIDToCheck == NONTRANSFERABLE);
    @ ensures (\result == true) <==> (candidateID == candidateIDToCheck);
    @*/
  public /*@ pure @*/ boolean isAssignedTo(int candidateIDToCheck){
    if(0 < candidateIDToCheck || candidateIDToCheck == NONTRANSFERABLE){
      if(candidateID == candidateIDToCheck){
        return true;
      }
    }
    return false;
  }
    
  /**
   * Gets the remaining number of preferences.
   * 
   * @return The number of preferences remaining
   */    
  /*@ also public normal_behavior 
    @ requires positionInList <= numberOfPreferences;
    @ ensures \result == numberOfPreferences - positionInList;
    @*/
  public /*@ pure @*/ int remainingPreferences(){
    int number = 0;
    if(positionInList <= numberOfPreferences){
      number = (numberOfPreferences - positionInList);
      return number;
    }
    return 0;
  }
    
  /**
   * Compares with another ballot paper's secret random number.
   * 
   * @design It is intended to be able to compare random numbers without
   * revealing the exact value of the random number, so that the random
   * number cannot be manipulated in any way.
   * 
   * @param other 
   * Other ballot to compare to this ballot
   * 
   * @return <code>true</code> if other ballot has a lower random number                                                               
   */
  /*@ also public normal_behavior
    @ requires this.randomNumber != other.randomNumber;
    @ ensures (\result == true) <==> (this.randomNumber > other.randomNumber);
  */    
  public /*@ pure @*/ boolean isAfter(/*@ non_null @*/Ballot other){
    if(this.randomNumber > other.randomNumber ){
      return true;
    }
    return false;
  }
  
  /**
   * Simple unit test for the Ballot class
   */
  public void main () {
	Ballot ballot = new Ballot();
	int[] candidateIDList = {1,2,3,4,5,6,7};
	int listSize = candidateIDList.length;
	ballot.load(candidateIDList, listSize);
	//@ assert ballot.getFirstPreference() == 1;
	//@ assert ballot.remainingPreferences() == listSize - 1;
	//@ assert ballot.isAssignedTo(1);
	//@ assert !ballot.isAssignedTo(8);
  }

  
}