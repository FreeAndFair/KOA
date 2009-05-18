package internal;

import junit.framework.TestCase;
import election.tally.BallotBox;
import election.tally.Candidate;
import election.tally.ElectionParameters;
import election.tally.dail.DailBallotCounting;

public class DistributionOfSurplus extends TestCase {
	
	protected DailBallotCounting ballotCounting;
	protected ElectionParameters parameters;
	protected Candidate candidate;
	protected BallotBox ballotBox;
	
	/**
	 * Create the test data needed
	 */
	protected void setUp() throws Exception {
		super.setUp();
		ballotCounting = new DailBallotCounting();
		parameters = new ElectionParameters();
		parameters.totalNumberOfSeats = 4;
		parameters.numberOfSeatsInThisElection = 4;
		parameters.candidateIDs = new long[]{1,2,3};
		parameters.numberOfCandidates = parameters.totalNumberOfSeats + 2;
		
		// Generate sample candidates
	 	Candidate[] candidates = new Candidate[parameters.numberOfCandidates];
		for (int i = 0; i < parameters.numberOfCandidates; i++) {
			candidates[i] = new Candidate();
			candidates[i].addVote(i*1000, 1);
		}
	 	parameters.setCandidateList(candidates);	
	}

	public void testDistributionOfSurplus() {
	 
	 	ballotCounting.setup(parameters);
	 	ballotCounting.distributeSurplus(ballotCounting.findHighestCandidate());
	}
}
