package internal;

import util.AbstractScenarioTest;
import election.tally.Candidate;

public class DistributionOfSurplus extends AbstractScenarioTest {

	public void testDistributionOfSurplus() {
	 
	int numberOfSeats = 4;
	electionParameters.setNumberOfSeats(numberOfSeats);
	
	// Generate candidates
	int numberOfCandidates = 2 + numberOfSeats;
	Candidate[] candidates = new Candidate[numberOfCandidates];
	for (int i = 0; i < numberOfCandidates; i++) {
		candidates[i] = new Candidate();
	}
	
	// Add enough votes to elect the first candidate
	int numberOfBallots = ballotCounting.getQuota() + 20;
	candidates[0].addVote(numberOfBallots, 1);
	
 	electionParameters.setCandidateList(candidates);
 	ballotCounting.distributeSurplus(candidates[0]);
	}
}
