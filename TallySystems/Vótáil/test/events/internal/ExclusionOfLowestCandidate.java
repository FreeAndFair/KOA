package internal;

import junit.framework.TestCase;
import election.tally.Ballot;
import election.tally.BallotBox;
import election.tally.BallotCounting;
import election.tally.Candidate;
import election.tally.ElectionParameters;
import election.tally.dail.DailBallotCounting;

public class ExclusionOfLowestCandidate extends TestCase {

	protected BallotCounting ballotCounting;
	protected ElectionParameters parameters;

	public final void testExclusionOfLowestCandidate() {
		 
 	 	ballotCounting.setup(parameters);
	 	
	 	// Eliminate the lowest candidate
 	 	Candidate candidate = ballotCounting.findLowestCandidate();
	 	ballotCounting.eliminateCandidate(candidate);
 	}

	protected void setUp() throws Exception {
		super.setUp();
		ballotCounting = new DailBallotCounting();
		parameters = new ElectionParameters();
		parameters.totalNumberOfSeats = 4;
		parameters.numberOfSeatsInThisElection = 4;
		// Generate candidates
		int numberOfCandidates = 2 + parameters.numberOfSeatsInThisElection;
		Candidate[] candidates = new Candidate[numberOfCandidates];
		for (int i = 0; i < numberOfCandidates; i++) {
			candidates[i] = new Candidate();
			candidates[i].addVote(i*1000, 1);
		}
		ballotBox = new BallotBox();
		ballotBox.numberOfBallots = 2;
		Ballot ballot1 = new Ballot();
		int[] list1 = {1};
		ballot1.load(list1 , list1.length);
		Ballot ballot2 = new Ballot();
		int[] list2 = {3,2};
		ballot2.load(list2 , list2.length);
		ballotBox.ballots =  new Ballot[] {ballot1,ballot2};
	}
}
