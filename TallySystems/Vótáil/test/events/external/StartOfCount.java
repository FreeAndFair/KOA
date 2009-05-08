package external;

import election.tally.ElectionParameters;
import election.tally.dail.BallotCounting;
import junit.framework.TestCase;

public class StartOfCount extends TestCase {

	private /*@ spec_public non_null @*/ BallotCounting ballotCounting;
	private /*@ spec_public non_null @*/ ElectionParameters electionParameters;

	/**
	 * Test that the count process is started correctly.
	 */
	public void testStartOfCount () {
		ballotCounting.setup(electionParameters);
		ballotCounting.count();
		
	}

	protected void setUp() throws Exception {
 		super.setUp();
 		ballotCounting = new BallotCounting();
 		electionParameters = new ElectionParameters();
 		electionParameters.totalNumberOfSeats = 4;
 		electionParameters.numberOfSeatsInThisElection = 4;
 		electionParameters.candidateIDs = new long[]{1,2,3};
 		electionParameters.numberOfCandidates = 3;
	}
}
