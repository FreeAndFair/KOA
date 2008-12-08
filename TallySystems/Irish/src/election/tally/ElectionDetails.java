package election.tally;

/**
 * Data transfer structure for candidate ID details and number of seats
 *
 */

public class ElectionDetails {

/** Number of candidates for election in this constituency */
//@ public invariant 0 < numberOfCandidates;
// @ design- Patrick- changed to int as fields in ElectionAlgorithm are ints
	public int numberOfCandidates;
	
/** Number of seats to be filled in this election */
//@ public invariant 0 < numberOfSeatsInThisElection;
//@ public invariant numberOfSeatsInThisElection <= totalNumberOfSeats;
	public int numberOfSeatsInThisElection;
	
/** Number of seats in this constituency */
//@ public invariant 0 < totalNumberOfSeats;
	public int totalNumberOfSeats;
	
/** List of ID values for all candidates in this election */
/*@ public invariant (\forall int i;
  @   0 <= i && i < numberOfCandidates;
  @   0 < candidateIDs[i] &&
  @   candidateIDs[i] != Ballot.NONTRANSFERABLE); 
  @*/
// @constraint No duplicate candidate IDs are allowed. 	
/*@ public invariant (\forall int i, j;
  @   0 <= i && i < numberOfCandidates &&
  @   0 <= j && j < numberOfCandidates &&
  @   i != j;
  @   candidateIDs[i] != candidateIDs[j]); 
  @*/	
	public /*@ non_null @*/ long[] candidateIDs;
	
	public /*@ pure @*/ ElectionDetails(){
		
	}
}
