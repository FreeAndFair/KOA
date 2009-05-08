package external;

import election.tally.BallotCounting;
import util.AbstractScenarioTest;


public class StartOfCount extends AbstractScenarioTest {

	/**
	 * Test that the count process is started correctly.
	 */
	public void testStartOfCount () {
		ballotCounting.setup(electionParameters);
		assert ballotCounting.getStatus() == BallotCounting.PRECOUNT;
		ballotCounting.count();
		assert ballotCounting.getStatus() == BallotCounting.FINISHED;
		
	}
}
