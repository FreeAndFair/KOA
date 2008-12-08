package election.tally;
/**
 * Election results
 *
 */

public class ElectionResults {
//@ public invariant 0 <= numberElected;
	public long numberElected;

/*@ public invariant (\forall int i;
  @   0 < i && i < numberElected;
  @   0 < electedCandidateIDs[i] &&
  @   electedCandidateIDs[i] != Ballot.NONTRANSFERABLE);
  @ public invariant (\forall int i, j;
  @   0 <= i && i < numberElected &&
  @   0 <= j && j < numberElected && 
  @   i != j;
  @   electedCandidateIDs[i] != electedCandidateIDs[j]); 
  @*/
	public /*@ non_null @*/ long[] electedCandidateIDs;
	
//@ public invariant 0 <= totalNumberOfCounts;
	public long totalNumberOfCounts;
	
	public /*@ pure @*/ ElectionResults(){
		
	}
}
