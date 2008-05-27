package ie.koa;

/** Data transfer structure for set of all valid votes */
//@ refine "BallotBox.spec";
public class BallotBox {
	
/**
 * List of valid ballot papers
 * 
 * @constraint No two ballots IDs in a ballot box are the same
 */
/*@ public invariant (\forall int i, j;
  @   0 <= i && i <numberOfBallots &&
  @   0 <= j && j <numberOfBallots &&
  @   i != j;
  @   ballots[i].ballotID != ballots[j].ballotID);
  @*/	
	public /*@ non_null @*/ Ballot[] ballots;
	
/**
 * @return the number of ballots in this ballot box
 */	
/*@ also 
  @ public normal_behavior
  @ ensures 0 <= \result;
  @*/
   public /*@ pure @*/ long size(){
		return numberOfBallots;
	}
	
//@ public invariant 0 <= numberOfBallots;
//@ public initially numberOfBallots == 0;
//@ public constraint numberOfBallots >= \old(numberOfBallots);
	public int numberOfBallots;
	
	public /*@ pure @*/ BallotBox(){
		numberOfBallots = 0;
	}
}
