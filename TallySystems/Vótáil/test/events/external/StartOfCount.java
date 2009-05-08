package external;

import util.AbstractScenarioTest;


public class StartOfCount extends AbstractScenarioTest {

	/**
	 * Test that the count process is started correctly.
	 */
	public void testStartOfCount () {
		ballotCounting.setup(electionParameters);
		ballotCounting.count();
		
	}
}
