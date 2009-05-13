package internal;

import election.tally.Ballot;
import election.tally.BallotBox;
import election.tally.Candidate;
import election.tally.dail.BallotCounting;
import util.AbstractScenarioTest;

public class DistributionOfSurplus extends AbstractScenarioTest {

	public void testDistributionOfSurplus() {
	 
	int numberOfSeats = 4;
	electionParameters.setNumberOfSeats(numberOfSeats);
	int numberOfCandidates = 2 + numberOfSeats;
	Candidate[] candidates = new Candidate[numberOfCandidates];
	for (int i = 0; i < numberOfCandidates; i++) {
		candidates[i] = new Candidate();
	}
	
	int numberOfBallots = 59000;
	
	candidates[0].addVote(ballotCounting.getQuota(), 1);
	candidates[1] = new Candidate();
	electionParameters.setCandidateList(candidates);
 	Ballot ballotForFirstCandidate = new Ballot();
 	ballotCounting.distributeSurplus(candidates[0]);
	}
}
