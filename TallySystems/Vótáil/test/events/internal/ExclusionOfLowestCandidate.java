package internal;

import election.tally.Ballot;
import election.tally.BallotBox;
import election.tally.BallotCounting;
import election.tally.Candidate;
import election.tally.ElectionParameters;
import election.tally.dail.DailBallotCounting;
import util.AbstractScenarioTest;

public class ExclusionOfLowestCandidate extends TestCase {

	protected BallotCounting ballotCounting;
	protected ElectionParameters parameters;
	protected Candidate candidate;
	protected BallotBox ballotBox;

	public final void testExclusionOfLowestCandidate() {
		int numberOfSeats = 4;
		electionParameters.setNumberOfSeats(numberOfSeats);
		
		// Generate candidates
		int numberOfCandidates = 2 + numberOfSeats;
		Candidate[] candidates = new Candidate[numberOfCandidates];
		for (int i = 0; i < numberOfCandidates; i++) {
			candidates[i] = new Candidate();
			candidates[i].addVote(i*1000, 1);
		}
				
	 	electionParameters.setCandidateList(candidates);
	 	ballotCounting.setup(electionParameters);
	 	
	 	// Eliminate the lowest candidate
	 	int numberToEliminate = 1;
	 	Candidate[] candidatesToEliminate = {candidates[0]};
	 	ballotCounting.eliminateCandidates(candidatesToEliminate, numberToEliminate);
	 	assert(candidates[0].getStatus() == Candidate.ELIMINATED);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ballotCounting = new DailBallotCounting();
		parameters = new ElectionParameters();
		parameters.totalNumberOfSeats = 4;
		parameters.numberOfSeatsInThisElection = 4;
		parameters.candidateIDs = new long[]{1,2,3};
		parameters.numberOfCandidates = 3;
		candidate = new Candidate();
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
