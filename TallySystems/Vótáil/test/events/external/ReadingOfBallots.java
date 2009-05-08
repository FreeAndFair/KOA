package external;

import election.tally.BallotCounting;
import util.AbstractScenarioTest;

public class ReadingOfBallots extends AbstractScenarioTest {
	
	public void testReadingOfBallots() {
		ballotCounting.setup(electionParameters);
		assert ballotCounting.getStatus() == BallotCounting.PRELOAD;
		ballotCounting.load(ballotBox);
		assert ballotCounting.getStatus() == BallotCounting.PRECOUNT;
		//@ assert ballotCounting.totalVotes == ballotBox.size();
	}

}
