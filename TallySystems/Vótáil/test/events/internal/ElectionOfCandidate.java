package internal;

import election.tally.Candidate;
import util.AbstractScenarioTest;

public class ElectionOfCandidate extends AbstractScenarioTest {

	public void testElectionOfCandidate() {
 		assert candidate.getStatus() == Candidate.CONTINUING;
		candidate.declareElected();
 		assert candidate.getStatus() == Candidate.ELECTED;
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

}
