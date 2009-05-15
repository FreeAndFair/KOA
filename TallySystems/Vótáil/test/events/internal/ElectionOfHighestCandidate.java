package internal;

import election.tally.Candidate;
import util.AbstractScenarioTest;

public class ElectionOfHighestCandidate extends AbstractScenarioTest {

	public void testElectionOfCandidate() {
 		assert candidate.getStatus() == Candidate.CONTINUING;
		candidate.declareElected();
 		assert candidate.getStatus() == Candidate.ELECTED;
	}

}
