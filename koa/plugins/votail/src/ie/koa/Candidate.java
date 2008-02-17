package ie.koa;

/** 
 * The Candidate object records the number of votes recieved during
 * each round of counting. Votes can only be added to the candidate's
 * stack while the candidate has a status of <code>CONTINUING</code>.
 * 
 * @see <a href="http://www.cev.ie/htm/tenders/pdf/1_2.pdf">
 * Department of Environment and Local Government, 
 * Count Requirements and Commentary on Count Rules,
 * section 3-14</a>
 */

//@ refine "Candidate.spec";
public class Candidate {
public static final int MAX_CANDIDATES = 1000;

/** Identifier for the candidate */
// @bon All candidates' identifiers are non-negative.
//@ public invariant 0 <= candidateID;
//@ public invariant (state != UNASSIGNED) ==> 0 < candidateID;
/*@ public invariant (\forall Candidate a, b;
  @   a != null && b != null &&
  @   a.state != UNASSIGNED && b.state != UNASSIGNED;
  @   (a.candidateID == b.candidateID) <==> (a == b));
  @*/
// @bug patrick-  added old(state)==state as it allows the variable to be initiliased
/*@ public constraint ((state != UNASSIGNED) && (\old(state) == state)) ==>
  @   candidateID == \old(candidateID);
  @*/
	protected /*@ spec_public @*/ int candidateID;
	
/** Number of votes added at each count */
/*@ public invariant (\forall int i; 0 < i && i < MAXCOUNT;
  @ 0 <= votesAdded[i]);
  @*/
/*@ public initially (\forall int i; 0 < i && i < MAXCOUNT;
  @ votesAdded[i] == 0);
  @*/	
//@ public invariant votesAdded.length == MAXCOUNT;
// @bug Patrick and Joe etc.
  protected /*@ spec_public non_null @*/ int[] votesAdded;
	
/** Number of votes removed at each count */
/*@ public invariant (\forall int i; 0 < i && i < MAXCOUNT;
  @                                  0 <= votesRemoved[i]);
  @*/
/*@ public initially (\forall int i; 0 < i && i < MAXCOUNT;
  @                                  votesRemoved[i] == 0);
  @*/	
//@ public invariant votesRemoved.length == MAXCOUNT;
// @bug Patrick and Joe See the above discussion of the new invariant
// for votesAdded.
  protected /*@ spec_public non_null @*/ int[] votesRemoved;

//@ public invariant votesAdded != votesRemoved;
//@ public invariant votesRemoved.owner != null;
//@ public invariant votesAdded.owner != null;
//@ public invariant votesRemoved != votesAdded;
//@ public invariant votesRemoved.owner == votesAdded.owner;
//@ public invariant \typeof(votesRemoved.owner) <: Candidate.class;
  
// @design @bug Patrick and Joe 
// @bug Patrick and Joe - There seems to be a bug in this variable as  
// votesRemoved points to the same integer array as votesAdded. 
// As this should not happen, invariants are created so votesRemoved cannot 
// have the same memory as votesAdded and that they both cannot be null.
// Also to help solve this problem for each incidence of Candidate, this Candidate   
// is made as the owner of both of the integer arrays. Also to back up the invariant
// integer arrays must be owned by the Candidate class.  
	
/** The status of the candidate at the latest count */
/*@ public invariant state == ELECTED || state == ELIMINATED ||
  @   state == CONTINUING || state == UNASSIGNED;
  @ public initially state == UNASSIGNED;
  @*/
	protected /*@ spec_public @*/ byte state;
	
/** The number of rounds of counting so far */
//@ public invariant 0 <= lastCountNumber;
//@ public initially lastCountNumber == 0;
//@ public constraint \old(lastCountNumber) <= lastCountNumber;
//@ public invariant lastCountNumber < MAXCOUNT;
	protected /*@ spec_public @*/ int lastCountNumber;
	
/** The count number at which the last set of votes were added */
//@ public invariant 0 <= lastSetAddedCountNumber;
//@ public initially lastSetAddedCountNumber == 0;	
/*@ public constraint 
  @   \old(lastSetAddedCountNumber) <= lastSetAddedCountNumber;
  @*/
// @bug Patrick and Joe - There seems to be a bug in this constraint
// assertion, as it must hold for all methods (pure or otherwise), and
// the JML Reference Manual indicates that constraints generally are
// reflexive and transitive, and '<' is not reflexive.
//@ public invariant lastSetAddedCountNumber <= lastCountNumber;
	protected /*@ spec_public @*/ int lastSetAddedCountNumber;
	
/**
 * Unique random number used to simulate drawing of lots between candidates.
 * @review Patrick - Write about implicit semantics of "random" in this spec.
 */
/*@ public invariant (\forall Candidate a, b;
  @   a != null && b != null &&
  @   a.state != UNASSIGNED && b.state != UNASSIGNED;
  @   (a.randomNumber == b.randomNumber) <==> (a == b));
  @ public constraint (state != UNASSIGNED) ==>
  @   randomNumber == \old(randomNumber); 	
  @*/
  protected /*@ spec_public @*/ int randomNumber;
  //@ ghost int _randomNumber;
	
/** State value for a candidate neither elected nor eliminated yet */
	public static final byte CONTINUING = 0;
	
/**
 * State value for a candidate deemed to have been elected either by
 * having a quota or being the highest continuing candidate for the
 * last remaining seat.  
 */	
	public static final byte ELECTED = 1;
	
/**
 * State value for a candidate excluded from election as being one
 * of the lowest continuing candidates at the end of a round of counting.  
 */	
	public static final byte ELIMINATED = 2;	

/** State value for a candidate object without a valid ID */	
	public static final byte UNASSIGNED = 3;
	
/**
 * State value for a candidate defeated at the last round of the election
 * e.g. the second highest remaining candidate when the last seat is 
 * being filled  
 */	
	public static final byte DEFEATED = 4;	

/**
 * Maximum possible number of counts
 * 
 * @design This value is not set by the legislation; it is chosen so that
 * fixed length arrays can be used in the specification.  
 */	
	public static final int MAXCOUNT = 100;
	
	/**
	 * Total number of votes this candidate has at any time 
	 * 
	 * @design This variable was added as to provide easy access from other classes.
	 */
	public int total;

	
/**
 * Gets number of votes added or removed in this round count.
 * 
 * @param count This count number
 * @return A positive number if the candidate recieved transfers or 
 * a negative number if the candidate's surplus was distributed or 
 * the candidate was eliminated and votes transfered to another. 
 */	
/*@ also
  @   public normal_behavior
  @   requires state != UNASSIGNED;
  @   requires 0 <= count;
  @   requires count <= lastCountNumber;
  @   requires count < MAXCOUNT;
  @   ensures \result == votesAdded[count] - votesRemoved[count];
  @*/
// @bon How many votes have been added or removed in this count round?
	public /*@ pure @*/ int getVoteAtCount(int count){
		int sum = 0;
		if(state != UNASSIGNED){
			if(0 <= count && count <= lastCountNumber){
				sum = (votesAdded[count] - votesRemoved[count]);
			}
			return sum;
		} else {
		return 0;
		}
	}
	
/**
 * Total vote recieved by this candidate less transfers to 
 * other candidates.
 * 
 * @return Net total of votes recieved
 */	
/*@ also
  @   public normal_behavior
  @   requires state != UNASSIGNED;
  @   ensures \result == (\sum int i; 0 <= i && i <= lastCountNumber;
  @     ((votesAdded[i]) - (votesRemoved[i])));
  @*/
// @verify Patrick and Joe - ESC/Java2 cannot check assertions that
// contain the \sum operator, so we will test this instead for
// validation.
	public /*@ pure @*/ int getTotalVote() {
		int i = 0;
		int totalVote = 0;
		if (state != UNASSIGNED) {
			while (0 <= i && i <= lastCountNumber) {
				totalVote += (votesAdded[i] - votesRemoved[i]);
				i++;
			}
			return totalVote;
		} else {
			return 0;
		}
	} //@ nowarn Post;
  	
/**
 * Original number of votes recieved by this candidate before
 * transfers due to elimination or distribution of surplus votes.
 * 
 * @return Gross total of votes recieved 
 */	
/*@ also
  @   public normal_behavior
  @   requires state != UNASSIGNED;
  @   ensures \result == (\sum int i; 0 <= i && i <=lastCountNumber;
  @     votesAdded[i]); 
  @*/
// @verify Patrick and Joe - ESC/Java2 cannot check assertions that
// contain the \sum operator, so we will test this instead for
// validation.
// @TODO Dermot - or use a ghost variable to check the total
	public /*@ pure @*/ int getOriginalVote() {
		int total = 0;
		int i = 0;
		if (state != UNASSIGNED) {
			while (0 <= i && i <= lastCountNumber) {
				total = votesAdded[i];
				i++;
			}
			return total;
		} else {
			return 0;
		}

	} //@ nowarn Post;
	
/**
 * Get status at the current round of counting; {@link #ELECTED}, 
 * {@link #ELIMATED} or {@link #CONTINUING} or {@link #UNASSIGNED}
 * 
 *  @return State value for this candidate
 */
/*@ also public normal_behavior
  @   ensures \result == state;
  @*/	
	public /*@ pure @*/ byte getStatus(){
		return state;
	}
	
/**
 * Get the unique ID of this candidate.
 * 
 * @return The candidate ID number
 */
/*@ also
  @   public normal_behavior
  @   requires state != UNASSIGNED;
  @   ensures \result == candidateID;
  @*/	
	public /*@ pure @*/ int getCandidateID() {
		if (state != UNASSIGNED) {
			return candidateID;
		} else {
			return 0;
		}

	}
	
/**
 * This is the default constructor method for a <code>Candidate</code>
 */	
/*@	also
  @   public normal_behavior
  @   requires state != UNASSIGNED;
  @*/
  public Candidate(){
    state = UNASSIGNED;
    votesAdded = new int [MAXCOUNT];
    //@ set votesAdded.owner = this;
    votesRemoved = new int [MAXCOUNT];
    //@ set votesRemoved.owner = this;
    randomNumber = UniqueNumber.getUniqueID(); 
    //@ set _randomNumber = randomNumber;
  }

/**
 * Add a number of votes to the candidate's ballot stack.
 * 
 * @design This method cannot be called twice for the same candidate
 * in the same round of counting.
 * 
 * @param numberOfVotes Number of votes to add
 * @param count The round of counting at which the votes were added
 */	
/*@ also 
  @   public normal_behavior
  @   requires state == CONTINUING;
  @   requires lastCountNumber < count;
  @   requires votesAdded[count] == 0;
  @   requires 0 < count & count < MAXCOUNT;
  @   requires (* new bounds precondition added by Patrick and Joe on 25 Jan 2007 *);
  @   requires 0 <= numberOfVotes;
  @   requires (* @bug Patrick and Joe missing precondition bounding numberOfVotes to conform to invariant *);
  @   assignable lastCountNumber, votesAdded[count], lastSetAddedCountNumber;
  @   ensures votesAdded[count] == numberOfVotes;
  @   ensures lastCountNumber == count;
  @   ensures lastSetAddedCountNumber == count;
  @*/
  public void addVote(int numberOfVotes, int count){
    if(state == CONTINUING){
      if(lastCountNumber < count && votesAdded[count] == 0){
        votesAdded[count] = numberOfVotes;
        lastCountNumber = count;
        lastSetAddedCountNumber = count;
      }
    }
  }

/**
 * Removes a number of votes from a candidates ballot stack.
 * 
 * @design This method cannot be called twice for the same candidate
 * in the same round of counting.
 * 
 * @param numberOfVotes Number of votes to remove from this candidate
 * @param count The round of counting at which the votes were removed 
 */	
/*@ also
  @   public normal_behavior
  @   requires state == ELIMINATED || state == ELECTED;
  @   requires lastCountNumber < count;
  @   requires votesRemoved[count] == 0;
  @   requires 0 < count & count < MAXCOUNT;
  @   requires (* new bounds precondition added by Patrick and Joe on 25 Jan 2007 *);
  @   requires 0 <= numberOfVotes;
  @   requires (* @bug Patrick and Joe missing precondition bounding numberOfVotes to conform to invariant *);
  @   assignable lastCountNumber, votesRemoved[count];
  @   ensures votesRemoved[count] == numberOfVotes;
  @   ensures lastCountNumber == count;
  @*/
// @review Patrick and Joe - Patrick will review postcondition and code.
  public void removeVote(int numberOfVotes, int count){
    if(state == ELIMINATED || state == ELECTED){
      if(lastCountNumber < count && votesRemoved[count] == 0){
        votesRemoved[count] = numberOfVotes;
        lastCountNumber = count;
      }
    }
  }	
	
/** 
 * Sets the candidate ID
 * 
 * @param candidateIDToAssign Identification number for this candidate
 * 
 * @design The candidate ID must be assigned by the client object because 
 * the client object must know who the candidate is.
 */
/*@ also
  @   public normal_behavior
  @   requires state == UNASSIGNED;
  @   requires 0 < candidateIDToAssign;
  @   requires (\forall Candidate other; other != null;
  @     other.candidateID != candidateIDToAssign);
  @   assignable state, candidateID;
  @   ensures candidateID == candidateIDToAssign;
  @   ensures state == CONTINUING;
  @*/
// @bug - Patrick:	Constraint states that candidate ID must equal the old Candidate ID
	public void setCandidateID(int candidateIDToAssign){
        if(state == UNASSIGNED && 0 < candidateIDToAssign){
        	candidateID = candidateIDToAssign;
        	state = CONTINUING;
        }
		
	}
	
/** Declares the candidate to be elected */
/*@ also public normal_behavior
  @   requires state == CONTINUING;
  @   assignable state;
  @   ensures state == ELECTED;
  @*/
	public void declareElected(){
		if(state == CONTINUING){
			state = ELECTED;
		}
	}
	
/** Declares the candidate to be eliminated */
/*@ also public normal_behavior
  @   requires state == CONTINUING;
  @   assignable state;
  @   ensures state == ELIMINATED;
  @*/
	public void declareEliminated(){
		if(state == CONTINUING){
			state = ELIMINATED;
		}
	}
	
/**
 * Gets number of votes in last set of votes added
 * 
 * @design In the first round of counting this is the same as
 * the number of first preferences, otherewise it is the most
 * set of votes recieved. The last set of votes recieved are
 * the only votes considered when a surplus is being distributed.
 * 
 * @return The number of votes in the last set added 
 */	
/*@ also public normal_behavior
  @   ensures \result == votesAdded[lastSetAddedCountNumber];
  @*/
	public /*@ pure @*/ int getNumberOfVotesInLastSet(){
		int number = votesAdded[lastSetAddedCountNumber];
		return number;
	}

/**
 * Gets the count number at which the last set of votes was added.
 * 
 * @design This is needed to check which ballots are in the last 
 * set added
 * 
 * @see requirement 19, section 7, item 2, page 19
 * 
 * @return The last count number at which votes were added
 */	
/*@ also public normal_behavior
  @   ensures \result == lastSetAddedCountNumber;
  @*/
	public /*@ pure @*/ int getLastSetAddedCountNumber(){
		return lastSetAddedCountNumber;
	}	
	
/**
 * Compares with another candidate's secret random number.
 * 
 * @design It is intended to be able to compare random numbers without
 * revealing the exact value of the random number, so that the random
 * number cannot be manipulated in any way.
 * 
 * @param other other candidate to compare with this candidate
 * 
 * @return <code>true</true> if other candidate has lower random number
 */	
/*@ also
  @ public normal_behavior
  @ requires state != UNASSIGNED;
  @ ensures (\result == true) <==>
  @   (this.randomNumber > other.randomNumber);
  @*/
	public /*@ pure @*/ boolean isAfter(/*@ non_null @*/ Candidate other){
		if(state != UNASSIGNED){
			if(this.randomNumber > other.randomNumber){
				return true;
			}
		}
		return false;
	}
}