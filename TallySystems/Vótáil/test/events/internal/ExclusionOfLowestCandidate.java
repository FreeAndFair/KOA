package internal;

import election.tally.Candidate;
import util.AbstractScenarioTest;

public class ExclusionOfLowestCandidate extends AbstractScenarioTest {

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
}
