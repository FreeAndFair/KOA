package util;

import election.tally.Ballot;
import election.tally.BallotBox;
import election.tally.Candidate;
import election.tally.ElectionParameters;
import election.tally.dail.BallotCounting;
import junit.framework.TestCase;

public abstract class AbstractScenarioTest extends TestCase {

	protected /*@ spec_public non_null @*/ BallotCounting ballotCounting;
	protected /*@ spec_public non_null @*/ ElectionParameters electionParameters;
	protected /*@ spec_public non_null @*/ Candidate candidate;
	protected /*@ spec_public non_null @*/ BallotBox ballotBox;

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