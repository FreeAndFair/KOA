package election.tally;

/**
 * The Candidate object records the number of votes received during
 * each round of counting.  Votes can only be added to the candidate's
 * stack while the candidate has a status of <code>CONTINUING</code>.
 *
 * @design The JML annotations of the Candidate class do not reference
 *   the private members of the class and therefore cannot enforce the
 *   internal consistency of data in the Candidate object.  It is the
 *   calling object, of type {@link ElectionAlgorithm}, which must
 *   ensure that votes added to one candidate do not exceed votes
 *   transfered away from another.
 * 
 * @author Dermot Cochran
 * @copyright Systems Research Group and University College Dublin, Ireland.
 * @reviewer Joe Kiniry
 * 
 * @see <a href="http://www.cev.ie/htm/tenders/pdf/1_2.pdf">Department of 
 * Environment and Local Government, Count Requirements and Commentary on Count 
 * Rules, sections 3-14</a>
 *
 * @review kiniry 8 Feb 2006 - Note that the first bit of a Javadoc
 * comment for a method *must* be a sentence that ends in a period.
 * Javadoc treats such structures in a special way.
 *
 * @review kiniry 8 Feb 2006 - Your heavyweight (behavioral) specs
 * should have a visibility that matches that of the method
 * (typically).  Thus, your public methods below should have public
 * behavioral specs.  This is why your protected fields must be made
 * spec_public---exactly so that they can be mentioned in public
 * specs.
 */

public class Candidate  {

  /** Identifier for the candidate */
  /**
   * @design kiniry 8 Feb 2006 - I presume this invariant should
   * mirror that of Ballot's id stuff.
   */
  //@ public invariant 0 <= candidateID;
  //@ public invariant (state != UNASSIGNED) ==> 0 < candidateID;
  /*@ public invariant (\forall Candidate a, b; 
    @   a != null && b != null && a.state != UNASSIGNED && b.state != UNASSIGNED;
    @   (a.candidateID == b.candidateID) <==> (a == b));
    @ public constraint (state != UNASSIGNED) ==>
    @   candidateID == \old (candidateID);
    @*/
  protected /*@ spec_public @*/ int candidateID;

  /** Number of votes added at each count */
  /*@ public invariant (\forall int i; 0 <= i && i < MAXCOUNT;
    @   0 <= votesAdded[i]);
    @*/
  /*@ public initially (\forall int i; 0 <= i && i < MAXCOUNT;
    @   votesAdded[i] == 0);
    @*/
  protected /*@ spec_public non_null @*/ int[] votesAdded;
  
  /** Number of votes removed at each count */
  /*@ public invariant (\forall int i; 0 <= i && i < MAXCOUNT;
    @   0 <= votesRemoved[i]);
    @*/
  /*@ public initially (\forall int i; 0 <= i && i < MAXCOUNT;
    @   votesRemoved[i] == 0);
    @*/
  protected /*@ spec_public non_null @*/ int[] votesRemoved;
	
  /** The status of the candidate at the latest count */
  /*@ public invariant state == ELECTED || state == ELIMINATED || 
    @                  state == CONTINUING || state == UNASSIGNED;
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
  //@ public constraint \old(lastSetAddedCountNumber) <= lastSetAddedCountNumber;
  //@ public invariant lastSetAddedCountNumber <= lastCountNumber;
  protected /*@ spec_public @*/ int lastSetAddedCountNumber;
  
  /** Unique random number used to simulate drawing of lots between candidates */
  /*@ public invariant (\forall Candidate a, b; 
    @   a != null && b != null && a.state != UNASSIGNED && b.state != UNASSIGNED;
    @   (a.randomNumber == b.randomNumber) <==> (a == b));
    @ public constraint (state != UNASSIGNED) ==> 
    @   randomNumber == \old (randomNumber);
    @*/
  protected /*@ spec_public @*/ int randomNumber;
  
  /** State value for a candidate neither elected nor eliminated yet. */
  public static final byte CONTINUING;

  /**
   * State value for a candidate deemed to have been elected either by
   * having a quota or being the highest continuing candidate for the
   * last remaining seat.
   */
  public static final byte ELECTED;

  /**
   * State value for a candidiate excluded from election as being one
   * of the lowest continuing candidates at the end of a round of counting.
   */
  public static final byte ELIMINATED;
  
  /** State value for a candidate object without a valid ID */
  public static final byte UNASSIGNED;
  
  /** State value for a candidate defeated at the last round of election e.g. the
   * second highest remaining candidate when the last seat is being filled
   */
  public static final byte DEFEATED;

  /** Maximum possible number of counts 
   * @design An assumption is made that the maximum number of counts will never 
   * exceed one hundred.  This depends on the number of candidates.  In a five seat 
   * constituency the deposit saving threshold would be five percent of the vote; 
   * this implies an average of twenty candidates or four per seat.  If we assume 
   * five major parties then there could be five candidates per seat, or a total of 
   * twenty five, plus some independent candidates, maybe about thirty candidates in 
   * total.  Also, it is possible for several candidates to be eliminated in the
   * same count if their combined votes are low enough.
   */
  public static final int MAXCOUNT;
  
  /**
   * Gets number of votes added or removed in this round count.
   * 
   * @param count This count number
   * @return A positive number if the candidate received transfers or
   *   a negative number if the candidate's surplus was distributed or
   *   the candidate was eliminated and votes transfered to another
   */
  /*@ public normal_behavior
    @   requires state != UNASSIGNED;
    @   requires 0 <= count;
    @   requires count <= lastCountNumber;
    @   requires count < MAXCOUNT;
    @   ensures \result == votesAdded[count] - votesRemoved[count];
    @*/
  public /*@ pure @*/ int getVoteAtCount (int count);

  
  /**
   * Total vote received by this candidate less transfers to other
   * candidates.
   *
   * @return Net total of votes received
   */
  /*@ public normal_behavior
    @   requires state != UNASSIGNED;
    @   ensures \result == (\sum int i; 0 <= i && i <= lastCountNumber;
    @     ((votesAdded[i]) - (votesRemoved[i])));
    @*/
  public /*@ pure @*/ int getTotalVote ();

  /**
   * Original number of votes recieved by this candidate before
   * transfers due to elimination or distribution of surplus votes.
   *
   * @return Gross total of votes received
   */
  /*@ public normal_behavior
    @   requires state != UNASSIGNED;
    @   ensures \result == (\sum int i; 0 <= i && i <= lastCountNumber;
    @     (votesAdded[i]));
    @*/
  public /*@ pure @*/ int getOriginalVote();
  
  /**
   * Get status at the current round of counting; {@link #ELECTED},
   * {@link #ELIMINATED} or {@link #CONTINUING} or {@link #UNASSIGNED}
   *
   * @return State value for this candidate
   */
  /*@ public normal_behavior
    @   ensures \result == state;
    @*/
  public /*@ pure @*/ byte getStatus();
  
  /**
   * Get the unique ID of this candidate.
   *
   * @return The candidate ID number
   */
  /*@ public normal_behavior
    @   requires state != UNASSIGNED;
    @   ensures \result == candidateID;
    @*/
  public /*@ pure @*/ int getCandidateID();
  
  /**
   * This is the default constructor method for a <code>Candidate</code> object.
   */
  /*@ public normal_behavior
    @   ensures state == UNASSIGNED;
    @*/
  public Candidate();
  
  /**
   * Add a number of votes to the candidate's ballot stack.
   * 
   * @design This method cannot be called twice for the same candidate
   *   in the same round of counting.
   *  
   * @param numberOfVotes Number of votes to add
   * @param count The round of counting at which the votes were added
   */  
  /*@ public normal_behavior
    @   requires state == CONTINUING;
    @   requires lastCountNumber < count;
    @   requires votesAdded[count] == 0;
    @   assignable lastCountNumber, votesAdded[count], lastSetAddedCountNumber;
    @   ensures votesAdded[count] == numberOfVotes;
    @   ensures lastCountNumber == count;
    @   ensures lastSetAddedCountNumber == count;
    @*/
  public void addVote (int numberOfVotes,  int count);
  
  /**
   * Removes a number of votes from a candidate's ballot stack.
   * 
   * @design This method cannot be called twice for the same candidate
   *   in the same round of counting.
   *  
   * @param numberOfVotes Number of votes to remove from this candidate
   * @param count The round of counting at which the votes were removed
   */  
  /*@ public normal_behavior
    @   requires state == ELIMINATED || state == ELECTED;
    @   requires lastCountNumber < count;
    @   requires votesRemoved[count] == 0;
    @   assignable lastCountNumber, votesRemoved[count];
    @   ensures votesRemoved[count] == numberOfVotes;
    @   ensures lastCountNumber == count;
    @*/
  public void removeVote (int numberOfVotes,  int count);
  
  /** Sets the candidate ID 
   * @param candidateIDToAssign Identification number for this candidate 
   * @design The candidate ID must be assigned by the client object because the 
   * client object must know who the candidate is */
  /*@ public normal_behavior
    @   requires state == UNASSIGNED;
    @   requires 0 < candidateIDToAssign;
    @   requires (\forall Candidate other; other != null;
    @   other.candidateID != candidateIDToAssign);
    @   assignable state, candidateID;
    @   ensures candidateID == candidateIDToAssign;   
    @   ensures state == CONTINUING; 
    @*/
  public void setCandidateID (int candidateIDToAssign);
  
  /** Declares the candidate to be elected */
  /*@ public normal_behavior
    @   requires state == CONTINUING;
    @   assignable state;
    @   ensures state == ELECTED;
    @*/
  public void declareElected();
  
  /** Declares the candidate to be eliminated */
  /*@   public normal_behavior
    @   requires state == CONTINUING;
    @   assignable state;
    @   ensures state == ELIMINATED;
    @*/
  public void declareEliminated() ;
  
  /** 
   * Gets number of votes in last set of votes added
   * 
   * @design In the first round of counting this is the same as
   * the number of first preferences, otherwise it is the most
   * set of votes received.  The last set of votes received 
   * are the only votes considered when a surplus is being distributed.
   * 
   * @return The number of votes in the last set added
   */
  /*@ public normal_behavior 
    @   ensures \result == votesAdded[lastSetAddedCountNumber];
    @*/
  public /*@ pure @*/ int getNumberOfVotesInLastSet();
  
  /** 
   * Gets the count number at which the last set of votes was added.
   * 
   * @design This is needed to check which ballots are in the last set added
   * 
   * @return The last count number at which votes were added
   */
  /*@ public normal_behavior
    @   ensures \result == lastSetAddedCountNumber;
    @*/
  public /*@ pure @*/ int getLastSetAddedCountNumber();
  
  /**
   * Compares with another candidate's secret random number.
   * 
   * @design It is intended to be able to compare random numbers without
   * revealing the exact value of the random number, so that the random
   * number cannot be manipulated in any way.
   * 
   * @param other
   *   Other candidate to compare with this candidate
   *   
   * @return <code>true</code> if other candidate has lower random number
   */
  /*@ public normal_behavior
    @   requires state != UNASSIGNED;
    @   ensures (\result == true) <==> (this.randomNumber > other.randomNumber);
    @*/
  public /*@ pure @*/ boolean isAfter(Candidate other);
}
