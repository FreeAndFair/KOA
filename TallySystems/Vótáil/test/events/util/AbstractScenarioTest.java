package util;

import election.tally.Candidate;
import election.tally.ElectionParameters;
import election.tally.dail.BallotCounting;
import junit.framework.TestCase;

public abstract class AbstractScenarioTest extends TestCase {

	protected BallotCounting ballotCounting;
	protected ElectionParameters electionParameters;
	protected Candidate candidate;

	public AbstractScenarioTest() {
		super();
	}

	public AbstractScenarioTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ballotCounting = new BallotCounting();
		electionParameters = new ElectionParameters();
		electionParameters.totalNumberOfSeats = 4;
		electionParameters.numberOfSeatsInThisElection = 4;
		electionParameters.candidateIDs = new long[]{1,2,3};
		electionParameters.numberOfCandidates = 3;
		candidate = new Candidate();
	}

}