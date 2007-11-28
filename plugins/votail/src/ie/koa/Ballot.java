package ie.koa;

/**
 * The Ballot class represents a ballot paper in an Irish election,
 * which uses the Proportional Representation Single Transfer Vote.
 * (PRSTV) system.
 * 
 * @see <a href="http://www.cev.ie/htm/tenders/pdf/1_2.pdf">
 * Department of Environment and Local Government, 
 * Count Requirements and Commentary on Count Rules,
 * section 3-14</a>
 */

//@ refine "Ballot.spec";
public class Ballot {
  public static final int MAX_POSSIBLE_BALLOTS = 150000;

/**
   * Candidate ID value to use for nontransferable ballot papers
   * 
   * @design A special candidate ID value is used to indicate
   * non-transferable votes i.e., when the list of preferences has
   * been exhausted and none of the continuing candidates are in the preference list, 
   * the ballot is deemed to be non-transferable
   * 
   * @see <a href="http://www.cev.ie/htm/tenders/pdf/1_2.pdf">
   * Department Of Environment and Local Government,
   * Count requirements and Commentaryan Count Rules,
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
  //	 @bug Patrick etc.-- can't be empty, hence non-null is used	
  protected /*@ spec_public non_null@*/ int[] preferenceList = new int [0];
	
  /** Total number of valid preferences on this ballot paper */
  //@ public invariant (0 == numberOfPreferences) | (0 < numberOfPreferences);
  // @bug kiniry - this should probably be an int because an array
  // length is an int and this field relates to preferenceList's length.
  // @design Patrick and Joe - Again, numberOfPreferences == 0 means an invalid numberOfPreferences and thus an invalid/uninitialised ballot.
  // @see #ballotID invariant
  protected /*@ spec_public @*/ int numberOfPreferences;
  
  /** Position within preference list */
  //@ public initially positionInList == 0;
  //@ public invariant 0 <= positionInList;
  //@ public invariant positionInList <= numberOfPreferences;
  //@ public constraint \old(positionInList) <= positionInList;
  protected /*@ spec_public @*/ int positionInList;

  /** Maximum possible numbers of counting rounds*/
  // @design Patrick must justify the length of this array.
  public static final int MAXIMUM_ROUNDS_OF_COUNTING = 50;
  
  /** Candidate ID to which the vote is assigned at the end of each count */
  //@ public invariant candidateIDAtCount.length == MAXIMUM_ROUNDS_OF_COUNTING;
  // @bug Patrick etc.	
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
    @   ensures ballotID == 0;
    @*/
  // @bug- Patrick: How to ensure ballotID > 0, when ballotID is assigned in load().
  // @design Patrick and Joe - Perhaps ballotID of 0 encodes an invalid ballot?
  public /*@ pure @*/ Ballot() {
	  
    numberOfPreferences = 0;
    countNumberAtLastTransfer = 0;
    positionInList = 0;
    candidateID = NONTRANSFERABLE; 
    ballotID = 0;
    randomNumber = UniqueNumber.getUniqueID(); 
    //@ set _randomNumber = randomNumber;
  }
    
  /**
   * Copy constructor
   * 
   * @return An exact copy of this ballot paper
   */    
  /*@ also public normal_behavior
    @ ensures this == \result;
    @ ensures (* the above postcondition is probably incorrect and should be an equals call *);
    @ ensures this.equals(\result);
    @*/
  public /*@ pure @*/ Ballot copy() {
    return this;
  }
    

  /**
   * Get the location of the ballot at each count
   * 
   * @param countNumber
   * @return The candidate ID or the NONTRANSFERABLE constant
   */    
  /*@ also public normal_behavior
    @ requires 0 <= countNumber;
    @ requires countNumber <= countNumberAtLastTransfer;
    @ ensures \result == candidateIDAtCount[countNumber];
    @*/
  public /*@ pure @*/ int getPreferenceAtCount(int countNumber) {
    if (0 <= countNumber && countNumber <= countNumberAtLastTransfer) {
      int number = candidateIDAtCount[countNumber];
      return number;
    } else {
      return 0;
    }
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
    int number = 0;
    if (0 < numberOfPreferences) {
      number = preferenceList[0];
    }
    if (number != NONTRANSFERABLE) {
      return number;
    }else{
      return 0;
    }

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
   * @param uniqueID
   *            The serial number of the ballot or equivalent
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
    @   requires (* @bug Patrick refined precondition from 0 <= listSize because there must be at least one pref in the list *);
    @   requires (\forall int i; 0 <= i && i < listSize;
    @     (candidateIDList[i]) != NONTRANSFERABLE);
    @   requires (\forall int i; 0 <= i && i < listSize;
    @     (candidateIDList[i]) > 0);
    // @bug: - Patrick: added candidateIDList[i]) > 0 as candidateID can never be zero
    @   requires candidateIDList.length == listSize;
    @   requires positionInList < listSize;
    // @bug - upperbound placed on positioninList to stop IndexTooBig error
    @   requires (* re-evaluate: preferenceList.length == listSize; *);
    @   requires (* @bug Patrick both list lengths precondition missing *);
    @   requires 0 < uniqueID;
    @   requires (* @bug Patrick and Joe - Nothing was said about uniqueID and load must make the ballot valid and a ballotID of 0 is invalid *);
    @   ensures numberOfPreferences == listSize;
    @   ensures ballotID == uniqueID;
    @   ensures preferenceList.length == listSize;
    @   ensures (\forall int i; 0 <= i && i < listSize;
    @     (preferenceList[i] == candidateIDList[i]));
    @*/
  // @bug- Patrick- candidateIDList must be non-null, and therefore is not empty
  // @review Patrick - Must figure out relationship between preferenceList's initial size and numberOfPreferences.
  public void load(/*@ non_null @*/ int[] candidateIDList, int listSize, int uniqueID){
    // numberOfPreferences == 0;
    // countNumberAtLastTransfer == 0;
    // positionInList == 0;
    // candidateID == NONTRANSFERABLE; 
    // ballotID == 0;
    numberOfPreferences = listSize;
    ballotID = UniqueNumber.getUniqueID();
    preferenceList = new int [listSize];
    for(int i = 0; i < listSize; i++){
      if (candidateIDList[i] != NONTRANSFERABLE && 0 < candidateIDList[i]) {
		preferenceList[i] = candidateIDList[i];
		candidateID = preferenceList[positionInList];
	  }
    }
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
    if(0 <= positionInList && positionInList <= numberOfPreferences){
      if(1 <= offset && preferenceList != null){
        if(positionInList + offset < numberOfPreferences){
          return (preferenceList[positionInList + offset]);
        }else{
          return NONTRANSFERABLE;
        }
      }
    }
    return 0;
  }
 
  /**
   * Transfer this ballot to the next preference candidate.
   * 
   * @design This method may be called multiple times during the same count
   * until the ballot is nontransferable or a continuing candidate ID is
   * found in the remainder of the preference list.
   * 
   * @param countNumber the count number at which the ballot was transfered.
   */    
  /*@ also public normal_behavior
    @   requires 0 <= positionInList;
    @   requires positionInList <= numberOfPreferences;
    @   requires countNumberAtLastTransfer <= countNumber;
    @   requires countNumber < MAXIMUM_ROUNDS_OF_COUNTING;
    @   requires (* @bug Patrick - Missing upperbound on countNumber *);
    @   assignable countNumberAtLastTransfer, positionInList, preferenceList[*], candidateID;
    @   ensures countNumberAtLastTransfer == countNumber;
    @   ensures \old(positionInList) <= positionInList;
    @   ensures (positionInList == \old(positionInList) + 1) ||
    @           (positionInList == numberOfPreferences);
    @*/
  public void transfer(int countNumber) {
		if (0 <= positionInList && positionInList <= numberOfPreferences) {
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
	}
  
  /**
	 * Corrects the candidate list after it has been changed.
	 * 
	 * @param shiftPosition
	 *            the position in the list from where the list must be
    *            
    * @note Dermot Cochran: The ballot information still needs to be preserved
    * when publishing the results.  Not all of the ballot can be publicy revealed;
    * only the current top preference and next preference so that the count can
    * independantly checked at each round.
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
		/*System.arraycopy(preferenceList, positionInList - 1, preferenceList,
				positionInList, 1);*/
				
		// System.arrayCopy(); vs. loop with very good invariant & variant
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
  

  
}