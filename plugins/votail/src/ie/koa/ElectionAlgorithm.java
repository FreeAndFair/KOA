package ie.koa;


/**
 * Vote counting algorithm for elections to Dail Eireann.
 * 
 * This Java package <code>ie.koa</code> is designed to be used as part of the 
 * KOA remote voting system, but does not assume anything about the KOA system
 * other than that it takes care of the remote voting process, supplies a valid
 * set of ballots and candidate IDs to be counted by this algorithm and takes 
 * care of system level issues such as security, authentication and data storage.
 * 
 * 
 * @design This JML specification and associated Java code is intended 
 * to be verifiable using the Extended Static Checking for Java
 * version 2 tool (ESCJava2). 
 * 
 * @see <a href="http://www.irishstatuebook.ie/1992_23.html">Part XIX of the 
 * Electoral Act, 1992</a>
 * @see <a href="http://www.cev.ie/htm/tenders/pdf/1_2.pdf">Department of
 * Environment and Local Government: Count Requirements and Commentary on Count
 * Rules, sections 3-16</a>
 * @see <a href="http://www.secure.ucd.ie/products/opensource/KOA/">The KOA Remote
 * Voting System</a> 
 * @see <a href="http://www.secure.ucd.ie/products/opensource/ESCJava2/">ESCJava/2
 * Homepage</a>
 * @see <a href="http://www.jmlspecs.org/">JML Homepage</a>  
 */
//@ refine "ElectionAlgorithm.spec";
public class ElectionAlgorithm {
	/**
	* Abstract State Machine for Election Algorithm.
	*/
	/*@ public model byte state;
	@ public invariant EMPTY < SETUP;
	@ public invariant SETUP < PRELOAD;
	@ public invariant PRELOAD < LOADING;
	@ public invariant LOADING < PRECOUNT;
	@ public invariant PRECOUNT < COUNTING;
	@ public invariant COUNTING < FINISHED;
	@ public invariant FINISHED < REPORT;
	@ public initially state == EMPTY;
	@ public constraint \old (state) <= state;
	@ public invariant (state == EMPTY) || (state == SETUP) || 
	@   (state == PRELOAD) ||
	@   (state == LOADING) || (state == PRECOUNT) || 
	@   (state == COUNTING) ||
	@   (state == FINISHED) || (state == REPORT);
	@*/
	
	protected /*@ spec_public @*/ byte status; //@ in state;
   //@ public represents state <- status;
	/*@
	  @ public invariant status == EMPTY ||status == SETUP || status == PRELOAD ||
	  @   status == LOADING || status == PRECOUNT || status == COUNTING ||
	  @   status == FINISHED ||status == REPORT;
	  @*/
	
	/** List of decisions made */
	/*@ public model non_null ie.koa.Decision[] decisionsMade;
	  @ public invariant 
	  @   (\forall int i; 0 <= i && i < numberOfDecisions;
	  @   decisionsMade[i].decisionTaken != Decision.NODECISION &&
	  @   decisionsMade[i].atCountNumber < countNumber &&
	  @   (\exists int k; 0 <= k && k < totalCandidates;
	  @     decisionsMade[i].candidateID == 
	  @     candidateList[k].getCandidateID()));
	  @ protected invariant numberOfDecisions < decisions.length;
	  @*/
	
	protected Decision[] decisions;
   //@ protected represents decisionsMade <- decisions;

	/** Number of decisions made */
	/*@ public model int numberOfDecisions;
	  @ public initially numberOfDecisions == 0;
	  @ public invariant 0 <= numberOfDecisions;
	  @ public constraint 
	  @   \old (numberOfDecisions) <= numberOfDecisions;
	  @ public constraint (state != COUNTING) ==>
	  @   numberOfDecisions == \old (numberOfDecisions);
	  @*/
	//@ represents numberOfDecisions <- decisions.length;
	
	/** List of details for each candidate.
	* @constraint There are no duplicates in the list of candidate 
	* IDs and, once the counting starts, there must be a ballot paper 
	* associated with each vote held by a candidate.
	*/
   //@ public model non_null Candidate[] candidateList;
	/*@ public invariant (state == PRELOAD || state == LOADING || 
	  @   state == PRECOUNT)
	  @ ==>
	  @ (\forall int i; 0 <= i && i < totalCandidates;
	  @   candidateList[i].getStatus() == Candidate.CONTINUING &&
	  @   candidateList[i].getTotalVote() == 0 &&
	  @   candidateList[i].getOriginalVote() == 0);
	  @
	  @ public invariant (state == PRELOAD || state == LOADING ||   
	  @   state == PRECOUNT ||
	  @   state == COUNTING || state == FINISHED || state == REPORT)
	  @ ==>
	  @   (\forall int i, j;
	  @     0 <= i && i < totalCandidates && 
	  @     i < j && j < totalCandidates;
	  @     candidateList[i].getCandidateID() != 
	  @     candidateList[j].getCandidateID());
	  @
	  @ public invariant (state == PRELOAD || state == LOADING || 
	  @   state == PRECOUNT ||
	  @   state == COUNTING || state == FINISHED || state == REPORT)
	  @ ==>
	  @   (\forall int i;
	  @     0 <= i && i < totalCandidates;
	  @     candidateList[i].getCandidateID() != 
	  @     Ballot.NONTRANSFERABLE);
	  @
	  @ protected invariant (state == COUNTING || state == FINISHED || 
	  @   state == REPORT)
	  @ ==> 
	  @  (\forall int i; 0 <= i && i < totalCandidates;
	  @    candidateList[i].getTotalVote() ==
	  @    getNumberOfVotes (candidateList[i].getCandidateID()));
	  @*/

	/** List of candidates for election */
	protected Candidate[] candidates;
   //@ protected represents candidateList <- candidates;
	

	/** List of contents of each ballot paper that will be counted. */
   //@ public model non_null Ballot[] ballotsToCount;
	/*@ protected invariant (state >= PRECOUNT)
	  @ ==>
	  @   (\forall int i, j;
	  @     0 <= i && i < totalVotes && i < j && j < totalVotes;
	  @     ballotsToCount[i].getBallotID() != ballotsToCount[j].getBallotID());
	  @*/
	protected Ballot[] ballots;
   //@ protected represents ballotsToCount <- ballots;
	
	/** Total number of candidates for election */
   //@ public model int totalCandidates;
   //@ public invariant 0 <= totalCandidates;
   //@ public invariant totalCandidates <= candidateList.length;
	/*@ public constraint (state >= LOADING) ==>
	  @ totalCandidates == \old (totalCandidates);
	  @ protected invariant (state == FINISHED) ==> totalCandidates ==
	  @   numberElected + numberEliminated;
	  @ public invariant (state == COUNTING) ==> 1 <= totalCandidates;
	  @*/
	protected int totalNumberOfCandidates;
   //@ protected represents totalCandidates <- totalNumberOfCandidates;
	
	/** Number of candidates elected so far */
   //@ public model int numberElected;
   //@ protected invariant 0 <= numberElected;
   //@ protected invariant numberElected <= seats;
	/*@ protected invariant (state <= PRECOUNT) ==> numberElected == 0;
	  @ protected invariant (COUNTING <= state && state <= REPORT)
	  @ ==> 
	  @   numberElected == (\num_of int i; 0 <= i && i < totalCandidates;
	  @   isElected(candidateList[i]));
	  @ protected invariant (state == FINISHED || state == REPORT) ==>
	  @   numberElected == seats;
	  @ protected constraint (state == COUNTING) ==>
	  @   \old(numberElected) <= numberElected;
	  @*/
	/** Number of candidates elected so far */
	protected int numberOfCandidatesElected;
   //@ protected represents numberElected <- numberOfCandidatesElected;
	
	/** Number of candidates excluded from election so far */
   //@ protected model int numberEliminated;
   //@ protected invariant 0 <= numberEliminated;
   //@ protected invariant numberEliminated <= totalCandidates - seats;
	/*@ protected invariant (state == COUNTING || state == FINISHED || 
	  @   state == REPORT)
	  @ ==> 
	  @  numberEliminated == (\num_of int i; 0 <= i && i < totalCandidates;
	  @  candidateList[i].getStatus() == Candidate.ELIMINATED);
	  @*/
	/** Number of candidates excluded from election so far */
	protected int numberOfCandidatesEliminated;
   //@ protected represents numberEliminated <- numberOfCandidatesEliminated;

	/** Number of seats to be filled in this election */
   //@ public model int seats;
   //@ public invariant 0 <= seats;
   //@ public invariant seats <= totalSeats;
	/*@ public constraint (state == LOADING || state == COUNTING) ==>
	  @   (seats == \old (seats));
	  @ public invariant (state == COUNTING) ==> (1 <= seats);
	  @*/
	/** Number of seats in this election */
	protected int numberOfSeats;
   //@ protected represents seats <- numberOfSeats;
	
	/** Total number of seats in this constituency
	 * @design The constitution and laws of Ireland do not allow less than three or
	 * more than five seats in each Dail constituency, but this could change in
	 * future and is not an essential part of the specification.
	 */
   //@ public model int totalSeats;
   //@ public invariant 0 <= totalSeats;
	/*@ public constraint (state == LOADING || state == COUNTING) ==>
	  @   totalSeats == \old (totalSeats);
	  @*/
	/** Number of seats in this constituency */
	protected int totalNumberOfSeats;
  //@ protected represents totalSeats <- totalNumberOfSeats;

	/** Total number of valid votes in this election */
   //@ public model int totalVotes;
   //@ public invariant 0 <= totalVotes;
   //@ protected invariant totalVotes <= ballotsToCount.length;
	/*@ public invariant (state == EMPTY || state == SETUP || 
	  @   state == PRELOAD)
	  @ ==> 
	  @   totalVotes == 0;
	  @ public constraint (state == LOADING)
	  @ ==> 
	  @   \old (totalVotes) <= totalVotes;
	  @ public constraint (state == PRECOUNT || state == COUNTING ||
	  @   state == FINISHED || state == REPORT)
	  @ ==> 
	  @  totalVotes == \old (totalVotes);
	  @*/
	
   /** Total number of valid ballot papers */
	protected int totalNumberOfVotes;
   
   /** 
    * Article 16 of the constitution of the Republic or Ireland specifies 
    * a maximum of 30,000 people per seat, and the current electoral laws specify a
    * maximum of five seats per constituency, so the maximum possible
    * number of of voters is 150,000. 
    */
	final static int MAXVOTES = 999999;
   
   //@ protected represents totalVotes <- totalNumberOfVotes;
	// @bug- Patrick- Long in spec, int in code...
	// @design- totalNumberOfVotes was changed to int to comply to above bug
	
	/** Number of votes so far which did not have a transfer to
	 * a continuing candidate */
   //@ public model int nonTransferableVotes;
   //@ public invariant 0 <= nonTransferableVotes;
   //@ public invariant nonTransferableVotes <= totalVotes;
	/*@ protected invariant (state == COUNTING || state == FINISHED || 
	  @   state == REPORT)
	  @ ==> nonTransferableVotes == 
	  @   (\num_of int i; 0 <= i && i < totalVotes;
	  @     ballotsToCount[i].candidateID == Ballot.NONTRANSFERABLE);
	  @*/
	/** Number of votes so far which did not have a transfer to
	 * a continuing candidate */
	protected int totalofNonTransferableVotes;
   //@ protected represents nonTransferableVotes <- totalofNonTransferableVotes;

	/** Minimum number of votes needed to guarantee election */
   //@ public model int quota;
   //@ public invariant 0 <= quota;
   //@ public invariant quota <= totalVotes;
	/*@ public invariant (state == COUNTING) ==>
	  @   quota == 1 + (totalVotes / (seats + 1));
	  @*/
	/** Minimum number of votes needed to guarantee election */
	protected int numberOfVotesRequired;
   //@ protected represents quota <- numberOfVotesRequired;

	/** Minimum number of votes needed to save deposit unless elected */
   //@ public model int depositSavingThreshold;
   //@ public invariant 0 <= depositSavingThreshold;
   //@ public invariant depositSavingThreshold <= totalVotes;
	/*@ public invariant (state == COUNTING) 
	  @ ==> depositSavingThreshold ==
	  @   (((totalVotes / (totalSeats + 1)) + 1) / 4) + 1;
	  @*/
	/** Number of votes required to be deemed elected */
	protected int savingThreshold;
   //@ protected represents depositSavingThreshold <- savingThreshold;

	/** Number of rounds of counting so far */
   //@ public model int countNumber;
   //@ public initially countNumber == 0;
   //@ public invariant 0 <= countNumber;
   //@ public invariant countNumber < Candidate.MAXCOUNT;
	/*@ public constraint (state == COUNTING) ==> 
	  @   \old(countNumber) <= countNumber;
	  @
	  @ public constraint (state == COUNTING) ==>
	  @   countNumber <= \old (countNumber) + 1;
	  @*/
	/** Number of rounds of counting */
	protected int countNumberValue;
   //@ protected represents countNumber <- countNumberValue;

	/** Number of candidates with surplus votes */
   //@ public model int numberOfSurpluses;
   //@ public invariant 0 <= numberOfSurpluses;
   //@ protected invariant numberOfSurpluses <= numberElected;
	/** Number of candidates with surplus votes */
	protected int totalNumberOfSurpluses;
   //@ protected represents numberOfSurpluses <- totalNumberOfSurpluses;

	/** Total number of undistributed surplus votes */
   //@ public model int sumOfSurpluses;
   //@ public invariant 0 <= sumOfSurpluses;
   //@ public invariant sumOfSurpluses <= totalVotes;
	/*@ invariant (state == COUNTING) 
	  @ ==> sumOfSurpluses == (\sum int i; 
	  @   0 <= i && i < totalCandidates; getSurplus(candidateList[i]));
	  @*/
	/** Number of candidates with surplus votes */
	protected int totalSumOfSurpluses;
   //@ protected represents sumOfSurpluses <- totalSumOfSurpluses;

	
   //@ public model int remainingSeats;
   //@ public invariant 0 <= remainingSeats;
   //@ public invariant remainingSeats <= seats;
	/*@ public invariant (state <= PRECOUNT) ==> 
	  @   remainingSeats == seats;
	  @ public invariant (state == FINISHED || state == REPORT) ==>
	  @   remainingSeats == 0;
	  @ protected invariant (state == COUNTING) ==>
	  @   remainingSeats == (seats - numberElected);
	  @*/
	
	protected int totalRemainingSeats;
   //@ protected represents remainingSeats <- totalRemainingSeats;

	/** Number of candidates neither elected nor excluded from election */
   //@ public model int numberOfContinuingCandidates;
   //@ public invariant 0 <= numberOfContinuingCandidates;
	/*@ public invariant 
	  @   numberOfContinuingCandidates <= totalCandidates;
	  @
	  @ public invariant (state == FINISHED) ==>
	  @   numberOfContinuingCandidates == 0;
	  @ invariant (state == COUNTING) ==>  
	  @   numberOfContinuingCandidates ==
	  @   (totalCandidates - numberElected) - numberEliminated;
	  @*/
	/** Number of candidates neither elected nor excluded from election */
	protected int totalNumberOfContinuingCandidates;
   //@ protected represents numberOfContinuingCandidates <- totalNumberOfContinuingCandidates;
	
	/** There must be at least one continuing candidate for each remaining seat
	 * @see requirement 11, section 4, item 4, page 16
	 */
	/*@ invariant (state == COUNTING) ==>
	  @   remainingSeats <= numberOfContinuingCandidates;
	  @*/

	/** The lowest non-zero number of votes held by a continuing candidate */
   //@ public model int lowestContinuingVote;
   //@ public invariant 0 < lowestContinuingVote;
	/*@ public invariant
	  @   (\exists int k; 0 <= k && k < totalCandidates;
	  @     candidateList[k].getStatus() == Candidate.CONTINUING &&
	  @     0 < candidateList[k].getTotalVote())
	  @ ==> lowestContinuingVote ==
	  @   (\min int i; 0 <= i && i < totalCandidates &&
	  @     candidateList[i].getStatus() == Candidate.CONTINUING &&
	  @     0 < candidateList[i].getTotalVote();
	  @     candidateList[i].getTotalVote());
	  @*/
	/** Lowest continuing vote */
	protected int lowestVote;
   //@ protected represents lowestContinuingVote <- lowestVote;

	/** The second lowest non-zero number of votes held by a continuing candidate */
   //@ public model int nextHighestVote;
   //@ public invariant lowestContinuingVote < nextHighestVote;
	/*@ public invariant
	  @   (\exists int k; 0 <= k && k < totalCandidates;
	  @     candidateList[k].getStatus() == Candidate.CONTINUING &&
	  @     lowestContinuingVote < candidateList[k].getTotalVote())
	  @ ==> nextHighestVote ==
	  @   (\min int i; 0 <= i && i < totalCandidates &&
	  @     candidateList[i].getStatus() == Candidate.CONTINUING &&
	  @     lowestContinuingVote < candidateList[i].getTotalVote();
	  @     candidateList[i].getTotalVote());
	  @*/
	/** The second lowest non-zero number of votes held by a continuing
	                          candidate */
	protected int nextHighest;
   //@ protected represents nextHighestVote <- nextHighest;

	/** The highest number of votes held by a continuing candidate */
   //@ public model int highestContinuingVote;
   //@ public invariant highestContinuingVote < quota;
	/*@ public invariant (0 < numberOfContinuingCandidates)
	  @ ==> highestContinuingVote ==
	  @   (\max int i; 0 < i && i < totalCandidates &&
	  @     candidateList[i].getStatus() == Candidate.CONTINUING;
	  @     candidateList[i].getTotalVote());
	  @*/
	/** The highest number of votes held by a continuing candidate */
	protected int highestContinuing;
   //@ protected represents highestContinuingVote <- highestContinuing;

	/** Highest available surplus for distribution */
   //@ public model int highestSurplus;
   //@ public invariant 0 <= highestSurplus;
   //@ public invariant highestSurplus <= sumOfSurpluses;
	/*@ invariant (state == COUNTING) ==> highestSurplus ==
	  @   (\max int i; 0 < i && i < totalCandidates; 
	  @   getSurplus(candidateList[i]));
	  @*/
	/** The highest number of votes held by a continuing candidate */
	protected int highestAvailableSurplus;
   //@ protected represents highestSurplus <- highestAvailableSurplus;

	/** Sum of continuing votes other than the highest */
   //@ public model int sumOfOtherContinuingVotes;
   //@ public invariant 0 <= sumOfOtherContinuingVotes;
   //@ public invariant sumOfOtherContinuingVotes <= totalVotes;
	/*@ invariant (state == COUNTING) ==> sumOfOtherContinuingVotes ==
	  @ (\sum int i; 0 <= i && i < totalCandidates &&
	  @ candidateList[i].getStatus() == Candidate.CONTINUING
	  @ && candidateList[i].getTotalVote() < highestContinuingVote;
	  @ candidateList[i].getTotalVote());
	  @*/
	/** The highest number of votes held by a continuing candidate */
	protected int totalSumOfOtherContinuingVotes;
   //@ protected represents sumOfOtherContinuingVotes <- totalSumOfOtherContinuingVotes;

	/** Number of candidates with equal highest continuing votes */
   //@ public model int numberOfEqualHighestContinuing;
   //@ public invariant 0 <= numberOfEqualHighestContinuing;
	/*@ public invariant
	  @ numberOfEqualHighestContinuing <= numberOfContinuingCandidates;
	  @ public invariant (state == COUNTING) ==> numberOfEqualHighestContinuing ==
	  @ (\num_of int i; 0 <= i && i < totalCandidates &&
	  @ candidateList[i].getStatus() == Candidate.CONTINUING;
	  @ candidateList[i].getTotalVote() == highestContinuingVote);
	  @*/
	/** Number of candidates with equal highest continuing votes */
	protected int totalNumberOfEqualHighestContinuing;
   //@ protected represents numberOfEqualHighestContinuing <- totalNumberOfEqualHighestContinuing;
	
	/** Number of candidates with equal lowest non-zero votes */
   //@ public model int numberOfEqualLowestContinuing;
   //@ public invariant 0 <= numberOfEqualLowestContinuing;
	/*@ public invariant
	  @ numberOfEqualLowestContinuing <= numberOfContinuingCandidates;
	  @ public invariant (state == COUNTING) ==> numberOfEqualLowestContinuing ==
	  @ (\num_of int i; 0 <= i && i < totalCandidates &&
	  @ candidateList[i].getStatus() == Candidate.CONTINUING;
	  @ candidateList[i].getTotalVote() == lowestContinuingVote);
	  @*/
	/**  Number of candidates with equal lowest non-zero votes */
	protected int totalNumberOfEqualLowestContinuing;
   //@ protected represents numberOfEqualLowestContinuing <- totalNumberOfEqualLowestContinuing;
	
	
/**
 * @design The election count algorithm is modeled as an abstract state
 * machine with states and transistions between those states:
 * 
 *  <p> The normal path is:
 *  <p> Empty --> SETUP --> PRELOAD --> LOADING -->
 *  PRECOUNT --> COUNTING --> FINISHED --> REPORT
 *   
 */	

/** Start state */
public static final byte EMPTY = 0;
/** Set up candidate list */
public static final byte SETUP = 1;
/** Ready to load ballots */
public static final byte PRELOAD = 2;
/** Load all valid ballots */
public static final byte LOADING = 3;
/** Ready to count votes */
public static final byte PRECOUNT = 4;
/** Count the votes */
public static final byte COUNTING = 5;
/** Finished counting */
public static final byte FINISHED = 6;
/** Declare election result */
public static final byte REPORT = 7;


/**
 * Default Constructor.
 */
/*@ also
  @   protected normal_behavior
  @   ensures state == EMPTY;
  @   ensures countNumber == 0;
  @   ensures numberElected == 0;
  @   ensures numberEliminated == 0;
  @*/
public /*@ pure @*/ ElectionAlgorithm(){
	status = EMPTY;
	countNumberValue = 0;
	numberOfCandidatesElected = 0;
	numberOfCandidatesEliminated = 0;
}



/**
 * Determine if the candidate has enough votes to be elected.
 * 
 * @param candidate The candidate in question
 * @return True if the candidate has at least a quota of votes
 * @see http://www.cev.ie/htm/tenders/pdf/1_1.pdf, page 79, paragraph 120(2)
 */
/*@ also
  @   protected normal_behavior
  @   requires candidate != null;
  @   requires countNumber >= 1;
  @   requires state == COUNTING;
  @   ensures \result == (candidate.getTotalVote() >= quota);
  @*/
protected /*@ pure @*/ boolean hasQuota(Candidate candidate){
	if(candidate != null && countNumberValue >= 1 && status == COUNTING){
		if(candidate.getTotalVote() >= numberOfVotesRequired){
			return true;
		}
	}
	return false;
}

/**
 * Determine if the candidate was elected in any previous round
 * 
 * @param candidate  
 * The candidate record
 * 
 * @return True if the candidate has already been elected
 */
/*@ also
  @   protected normal_behavior
  @   requires candidate != null;
  @   requires countNumber >= 1;
  @   requires state == COUNTING;
  @   ensures (\result == true) <==>
  @   (candidate.getStatus() == Candidate.ELECTED || hasQuota(candidate));
  @*/
protected /*@ pure @*/ boolean isElected(Candidate candidate){
	if(candidate != null && countNumberValue >= 1 && status == COUNTING){
		if(candidate.getStatus() == Candidate.ELECTED || hasQuota(candidate)){
			return true;
		}
	}
	return false;
}

/**
 * Determine how many surplus votes a candidate has.
 * 
 * @design The surplus is the maximum number of votes available for transfer
 * @param candidate The candidate record
 * @return The undistributed surplus for that candidate, or zero if the 
 * candidate has less than a quota of votes
 * @see http://www.cev.ie/htm/tenders/pdf/1_1.pdf, page 79, paragraph 120(2)
 */
/*@ also
  @   protected normal_behavior
  @   requires candidate != null;
  @   requires countNumber > 1;
  @   ensures (hasQuota(candidate) == true) ==> \result ==
  @   (candidate.getTotalVote() - quota);
  @   ensures (hasQuota(candidate) == false) ==> \result == 0;
  @   ensures \result >= 0;
  @*/
protected /*@ pure @*/ int getSurplus(Candidate candidate){
	if(candidate != null && countNumberValue > 1){
		if(hasQuota(candidate)){
			return (candidate.getTotalVote() - numberOfVotesRequired);
		}
	}
	return 0;
}

/**
 * @design The deposit saving threshold is one plus one quarter of the full quota
 * @design This needs to be checked just before the candidate is eliminated
 * @see http://www.cev.ie/htm/tenders/pdf/1_2.pdf, section 3 page 13, section 4 page 17 and section 14
 * @param candidate The candidate for which to check
 * @return true if candidate has enough votes to save deposit
 */
/*@ also
  @   protected normal_behavior
  @   requires (state == COUNTING) || (state == FINISHED) || (state == REPORT);
  @   ensures \result == (candidate.getOriginalVote() >= depositSavingThreshold) ||
  @   (isElected (candidate) == true);
  @*/
protected /*@ pure @*/ boolean isDepositSaved(Candidate candidate){
	if(status == COUNTING || status == FINISHED || status == REPORT){
		if(candidate.getOriginalVote() >= savingThreshold){
			return true;
		}else if(isElected (candidate)){
			return true;
		}
	}
	return false;
}

/**
 * Distribution of highest surplus
 * 
 * @param candidateWithSurplus
 * @design At most one surplus may be distributed in each round
 * @see http://www.cev.ie/htm/tenders/pdf/1_2.pdf, section 12, page 47
 * 
 * @note ESC/Java2 cannot check assertions that contain the \sum operator
 */
/*@ also
  @   protected normal_behavior
  @   requires getSurplus (candidateWithSurplus) > 0;
  @   requires state == COUNTING;
  @   requires numberOfContinuingCandidates > remainingSeats;
  @   requires (numberOfContinuingCandidates > remainingSeats + 1) ||
  @   (sumOfSurpluses + lowestContinuingVote > nextHighestVote) ||
  @   (numberOfEqualLowestContinuing > 1);
  @   requires remainingSeats > 0;
  @   requires (remainingSeats > 1) ||
  @   ((highestContinuingVote < sumOfOtherContinuingVotes + sumOfSurpluses) &&
  @   (numberOfEqualHighestContinuing == 1));
  @   requires getSurplus (candidateWithSurplus) == highestSurplus;
  @   requires (sumOfSurpluses + highestContinuingVote >= quota) ||
  @   (sumOfSurpluses + lowestContinuingVote > nextHighestVote) ||
  @   (numberOfEqualLowestContinuing > 1) ||
  @   ((sumOfSurpluses + lowestContinuingVote >= depositSavingThreshold) &&
  @   (lowestContinuingVote < depositSavingThreshold));
  @   ensures getSurplus (candidateWithSurplus) == 0;
  @   ensures countNumber == \old (countNumber) + 1;
  @   ensures (state == COUNTING) || (state == FINISHED);
  @   ensures totalVotes == nonTransferableVotes +
  @   (\sum int i; 0 <= i && i < totalCandidates;
  @   candidateList[i].getTotalVote());
  @*/
	protected void distributeSurplus(Candidate candidateWithSurplus) {
		int add = 0;
		if (status == COUNTING && getSurplus(candidateWithSurplus) == highestAvailableSurplus) {
			if ((totalNumberOfContinuingCandidates > totalRemainingSeats + 1)|| (totalSumOfSurpluses + lowestVote > nextHighest)|| (totalNumberOfEqualLowestContinuing > 1)) {
				if ((totalRemainingSeats > 1)|| ((highestContinuing < totalSumOfOtherContinuingVotes + totalSumOfSurpluses) && (totalNumberOfEqualHighestContinuing == 1))) {
					if ((totalSumOfSurpluses + highestContinuing >= numberOfVotesRequired)|| (totalSumOfSurpluses + lowestVote > nextHighest) || (totalNumberOfEqualLowestContinuing > 1) || ((totalSumOfSurpluses + lowestVote >= savingThreshold) && (lowestVote < savingThreshold))) {
						countNumberValue = countNumberValue + 1;
						candidateWithSurplus.total = numberOfVotesRequired;
						for(int i=0; i<totalNumberOfCandidates;i++){
							add += candidates[i].getTotalVote();
						}
						totalNumberOfVotes = totalofNonTransferableVotes + add;
					}
				}
			}
		}
	}

/**
 * Elimination of a candidate and transfer of votes.
 * 
 * @param candidatesToEliminate One or more candidates to be excluded from the 
 * election in this count
 * @param numberToEliminate Number of candidates to eliminate in this count
 */
/*@ also
  @   protected normal_behavior
  @   requires 1 <= numberToEliminate;
  @   requires numberToEliminate <= numberOfContinuingCandidates;
  @   requires (\forall int i;
  @   0 <= i && i < numberToEliminate;
  @   candidatesToEliminate[i].getTotalVote() == 0 ||
  @   depositSavingThreshold <= candidatesToEliminate[i].getTotalVote() ||
  @   candidatesToEliminate[i].getTotalVote() +
  @   sumOfSurpluses + (\sum int j;
  @   0 <= j && j != i && j < numberToEliminate;
  @   candidatesToEliminate[i].getTotalVote()) < depositSavingThreshold);
  @   requires (\forall int i;
  @   0 <= i && i < numberToEliminate;
  @   candidatesToEliminate[i].getStatus() == Candidate.CONTINUING);
  @   requires sumOfSurpluses + (\sum int i;
  @   0 <= i && i < numberToEliminate;
  @   candidatesToEliminate[i].getTotalVote()) < quota;
  @   requires remainingSeats < numberOfContinuingCandidates;
  @   requires (state == COUNTING);
  @   ensures (\forall int i;
  @   0 <= i && i < numberToEliminate;
  @   candidatesToEliminate[i].getStatus() == Candidate.ELIMINATED &&
  @   candidatesToEliminate[i].getTotalVote() == 0);
  @   ensures numberEliminated == \old (numberEliminated) + numberToEliminate;
  @   ensures remainingSeats <= numberOfContinuingCandidates;
  @   ensures numberElected <= seats;
  @   ensures \old(lowestContinuingVote) <= lowestContinuingVote;
  @*/
protected void eliminateCandidates(Candidate[] candidatesToEliminate, int numberToEliminate){
	// @assert false;
}

/**
 * Declare results
 * 
 * @return This election result
 */
/*@ also
  @   protected normal_behavior
  @   requires state == FINISHED;
  @   assignable state;
  @   ensures state == REPORT;
  @*/
public ElectionResults report(){
	if(status == FINISHED){
		status = REPORT;
	}
	return null;
}

/**
 * Start the counting.
 */
/*@ also
  @   protected normal_behavior
  @   requires remainingSeats == seats;
  @   requires numberElected == 0;
  @   requires numberEliminated == 0;
  @   requires nonTransferableVotes == 0;
  @*/
/** @see requirement 1, section 3, item 2, page 12 */
//@ requires state == PRECOUNT;
/*@
  @ requires numberOfContinuingCandidates == totalCandidates;
  @ requires countNumber == 0;
  @ ensures state == FINISHED;
  @ assignable state, countNumber, numberElected, numberEliminated,
  @ numberOfContinuingCandidates, remainingSeats, 
  @ numberOfSurpluses, candidateList,
  @ sumOfSurpluses, highestContinuingVote, lowestContinuingVote,
  @ nextHighestVote, nonTransferableVotes, ballotsToCount,
  @ numberOfContinuingCandidates;
  @ ensures remainingSeats == 0;
  @ ensures numberElected == seats;
  @ ensures numberEliminated == totalCandidates - numberElected;
  @ ensures numberOfContinuingCandidates == 0;
  @*/
public void count(){
	 //@ assert false;
}

/**
 * Load candidate details and number of seats.
 * 
 * @param electionDetails The candidate details and number of seats
 */
/*@ also
  @   protected normal_behavior
  @   requires state == EMPTY;
  @   ensures state == PRELOAD;
  @   ensures totalCandidates == electionDetails.numberOfCandidates;
  @   ensures seats == electionDetails.numberOfSeatsInThisElection;
  @   ensures totalSeats == electionDetails.totalNumberOfSeats;
  @   assignable state, seats, totalSeats, totalCandidates, candidateList, totalNumberOfSeats, totalNumberOfCandidates, numberOfSeats;
  @*/
public void setup(ElectionDetails electionDetails){
	if(status == EMPTY){
		status = PRELOAD;
		totalNumberOfCandidates = electionDetails.numberOfCandidates;
		numberOfSeats = electionDetails.numberOfSeatsInThisElection;
		totalNumberOfSeats = electionDetails.totalNumberOfSeats;
	}
}

/**
 * Load all valid vote details.
 * @param ballotBox The complete set of valid votes
 * @design All ballot papers must be assigned to a valid candidate ID
 */
/*@ also
  @   protected normal_behavior
  @   requires state == PRELOAD;
  @   assignable state, totalVotes, ballotsToCount, quota,depositSavingThreshold;
  @   ensures state == PRECOUNT;
  @   ensures totalVotes == ballotBox.numberOfBallots;
  @   ensures (\forall int i; 0 <= i && i < totalVotes;
  @   (\exists int j; 0 <= j && j < totalCandidates;
  @   ballotsToCount[j].isAssignedTo(candidateList[i].getCandidateID())));
  @*/
public void load(BallotBox ballotBox) {
	//@ assert false;	 
}

/**
 * Gets the current number of votes for this candidate ID.
 * 
 * @design This method can also be used to check the number of 
 * nontransferable votes
 * 
 * @param candidateID Candidate ID for which to check the votes
 * @return Number of votes currently assigned to this candidate 
 */
/*@ also 
  @   protected normal_behavior
  @   requires (state == COUNTING || state == FINISHED || state == REPORT);
  @   requires 0 < candidateID || candidateID == Ballot.NONTRANSFERABLE;
  @   ensures \result == (\num_of int j; 0 <= j && j < totalVotes;
  @   ballotsToCount[j].isAssignedTo(candidateID));
  @*/
protected /*@ pure @*/ int getNumberOfVotes(int candidateID){
	int num = 0;
	if (status == COUNTING || status == FINISHED || status == REPORT) {
			if (0 < candidateID || candidateID == Ballot.NONTRANSFERABLE) {
				for (int j = 0; j < totalNumberOfVotes; j++) {
					if (ballots[j].isAssignedTo(candidateID)) {
						num++;
					}
				}
			}
		}
	return num;
}

/**
 * Gets the potential number of transfers from one candidate to another.
 * 
 * @design This method is needed to get the proportions to use when 
 * transferring surplus votes
 * 
 * @param fromCandidate Candidate from which to check the transfers
 * @param toCandidateID Candidate ID to check for reciept of transferred votes
 * @return Number of votes potentially transferable from this candidate to that candidate
 */
/*@ also
  @   protected normal_behavior
  @   requires (state == COUNTING);
  @   requires 0 < toCandidateID;
  @   requires toCandidateID != Ballot.NONTRANSFERABLE;
  @   ensures \result== (\num_of int j; 0 <= j && j < totalVotes;
  @   (ballotsToCount[j].isAssignedTo(fromCandidate.getCandidateID())) &&
  @   (ballotsToCount[j].getCountNumberAtLastTransfer() ==
  @   fromCandidate.getLastSetAddedCountNumber()) &&
  @   (getNextContinuingPreference(ballotsToCount[j]) == toCandidateID));
  @*/
	protected /*@ pure @*/ int getPotentialTransfers(Candidate fromCandidate,int toCandidateID) {
		int num = 0;
		if (status == COUNTING && 0 < toCandidateID	&& toCandidateID != Ballot.NONTRANSFERABLE) {
			for (int j = 0; j < totalNumberOfVotes; j++) {
				if (ballots[j].isAssignedTo(fromCandidate.getCandidateID()) == (getNextContinuingPreference(ballots[j]) == toCandidateID)) {
					if (ballots[j].getCountNumberAtLastTransfer() == fromCandidate.getLastSetAddedCountNumber()) {
						num++;
					}
				}
			}
		}
		return num;
	}

/**
 * Gets the status of the algorithm in progress.
 * 
 * @return The state variable value {@link #EMPTY}, {@link #SETUP},
 * {@link #PRELOAD}, {@link #LOADING}, {@link #PRECOUNT},
 * {@link #COUNTING}, {@link #FINISHED} or {@link #REPORT}
 */
/*@ also
  @   protected normal_behavior
  @   ensures \result == state;
  @*/
public /*@ pure @*/ byte getStatus(){
	return status;
}

/**
 * Gets the next preference continuing candidate.
 * 
 * @param ballot Ballot paper from which to get the next preference
 * 
 * @return Candidate if next continuing candidate or NONTRANSFERABLE
 */
/*@ also 
  @   protected normal_behavior
  @   requires state == COUNTING;
  @   ensures (\result == Ballot.NONTRANSFERABLE) <=!=>
  @   (\exists int k; 1 <= k && k <= ballot.remainingPreferences();
  @   (\result == ballot.getNextPreference(k)) &&
  @   (\forall int i; 1 <= i && i < k;
  @   isContinuingCandidateID(ballot.getNextPreference(i)) == false));
  @*/
	protected /*@ pure @*/ int getNextContinuingPreference(Ballot ballot) {
		if (status == COUNTING) {
			for (int k = 1; k < ballot.remainingPreferences(); k++) {
				for (int i = 1; i < k; i++) {
					if (isContinuingCandidateID(ballot.getNextPreference(i)) == false) {
						return ballot.getNextPreference(k);
					}
				}
			}
		}
		return Ballot.NONTRANSFERABLE;
	}

/**
 * Determine if a candidate ID beints to a continuing candidate.
 * 
 * @param candidateID
 * The ID of candidate for which to check the status
 * 
 * @return <code>true</code> if this candidate ID matches that of a 
 * continuing candidate
 */
/*@ also
  @   public normal_behavior
  @   requires 0 < candidateID;
  @   ensures \result == (\exists int i;
  @   0 <= i && i < totalCandidates;
  @   candidateID == candidateList[i].getCandidateID() &&
  @   candidateList[i].getStatus() == Candidate.CONTINUING);
  @*/
	public /*@ pure @*/ boolean isContinuingCandidateID(int candidateID) {
		for (int i = 0; i < totalNumberOfCandidates; i++) {
			if (candidateID == candidates[i].getCandidateID() && candidates[i].getStatus() == Candidate.CONTINUING) {
                return true;
			}
		}
		return false;
	}

/**
 * Determine actual number of votes to transfer to this candidate
 * 
 * @design The number of votes in a surplus transferred is in proportion to
 * the number of transfers available throughout the candidate ballot stack
 * 
 * @param fromCandidate Candidate from which to count the transfers
 * @param toCandidate Continuing candidate eligible to recieve votes
 * @return Number of votes available for transfer 
 */
/*@ also
  @   protected normal_behavior
  @   requires (state == COUNTING);
  @   requires (fromCandidate.getStatus() == Candidate.ELECTED) ||
  @   (fromCandidate.getStatus() == Candidate.ELIMINATED);
  @   requires toCandidate.getStatus() == Candidate.CONTINUING;
  @   ensures ((fromCandidate.getStatus() == Candidate.ELECTED) &&
  @   (getSurplus(fromCandidate) < getTotalTransferableVotes(fromCandidate)))
  @   ==>
  @   (\result ==
  @   (getSurplus (fromCandidate) *
  @   getPotentialTransfers (fromCandidate,
  @   toCandidate.getCandidateID()) /
  @   getTotalTransferableVotes (fromCandidate)));
  @   ensures ((fromCandidate.getStatus() == Candidate.ELIMINATED) ||
  @   (getTotalTransferableVotes(fromCandidate) <= getSurplus(fromCandidate)))
  @   ==>
  @   (\result ==
  @   (\num_of int j; 0 <= j && j < totalVotes;
  @   ballotsToCount[j].isAssignedTo(fromCandidate.getCandidateID()) &&
  @   getNextContinuingPreference(ballotsToCount[j]) ==
  @   toCandidate.getCandidateID()));
  @*/
	protected /*@ pure @*/ int getActualTransfers(Candidate fromCandidate, Candidate toCandidate) {
		int numberOfVotes =0;
		if (status == COUNTING && toCandidate.getStatus() == Candidate.CONTINUING) {
			if (fromCandidate.getStatus() == Candidate.ELECTED && (getSurplus(fromCandidate) < getTotalTransferableVotes(fromCandidate))) {
				numberOfVotes=(getSurplus (fromCandidate) * getPotentialTransfers (fromCandidate,toCandidate.getCandidateID()) / getTotalTransferableVotes (fromCandidate));
			    return numberOfVotes;
			}
         else
			if (fromCandidate.getStatus() == Candidate.ELIMINATED || (getTotalTransferableVotes(fromCandidate) <= getSurplus(fromCandidate))) {
				for(int j =0; j < totalNumberOfVotes; j++){
					if(ballots[j].isAssignedTo(fromCandidate.getCandidateID()) && getNextContinuingPreference(ballots[j]) == toCandidate.getCandidateID()){
						numberOfVotes++;
					}
				}
			}
		}
      return numberOfVotes;
	}

/**
 * Determine the rounded value of a fractional transfer.
 * 
 * @design This depends on the shortfall and the relative size of the other
 * fractional transfers.
 * 
 * @param fromCandidate
 * Elected candidate from which to distribute surplus
 * 
 * @param toCandidate 
 * Continuing candidate potentially eligble to recieve transfers
 * 
 * @return <code>1</code> if the fractional vote is to be rounded up
 * <code>0</code> if the fractional vote is to be rounded down
 */
/*@ also 
  @   protected normal_behavior
  @   requires state == COUNTING;
  @   requires isElected (fromCandidate);
  @   requires toCandidate.getStatus() == ie.koa.Candidate.CONTINUING;
  @   requires getSurplus(fromCandidate) < getTotalTransferableVotes(fromCandidate);
  @   ensures (getCandidateOrderByHighestRemainder (fromCandidate,toCandidate) <=
  @   getTransferShortfall (fromCandidate))
  @   ==> \result == 1;
  @   ensures (getCandidateOrderByHighestRemainder (fromCandidate,toCandidate) >
  @   getTransferShortfall (fromCandidate))
  @   ==> \result == 0;
  @*/
protected /*@ pure @*/ int getRoundedFractionalValue(/*@ non_null @*/ Candidate fromCandidate, /*@ non_null @*/ Candidate toCandidate){
	if(status == COUNTING && isElected (fromCandidate) && toCandidate.getStatus() == Candidate.CONTINUING && getSurplus(fromCandidate) < getTotalTransferableVotes(fromCandidate)){
		if(getCandidateOrderByHighestRemainder (fromCandidate,toCandidate) <= getTransferShortfall (fromCandidate)){
			return 1;
		}else if(getCandidateOrderByHighestRemainder (fromCandidate,toCandidate) > getTransferShortfall (fromCandidate)){
			return 0;
		}
	}
	return 0;
}

/**
 * Determine shortfall between sum of transfers rounded down and the size 
 * of surplus
 * 
 * @param fromCandidate
 * Elected candidate from which to distribute surplus 
 * 
 * @return The shortfall between the sum of the transfers and the size
 * of surplus
 */
protected /*@ pure @*/ int getTransferShortfall(/*@ non_null @*/ Candidate fromCandidate){
	int temp =0;
	if(status == COUNTING && isElected (fromCandidate) && getSurplus(fromCandidate) < getTotalTransferableVotes(fromCandidate)){
		for(int i=0;i<totalNumberOfCandidates;i++){
			if(candidates[i].getStatus() == Candidate.CONTINUING){
				temp += getActualTransfers (fromCandidate, candidates[i]);
			}
		}
	}
	return 0;
}

/**
 * Draw lots to choose between the two continuing candidates
 * 
 * @design This needs to be random but consistent, so that same 
 * result is always given for the same pair of candidates.
 * 
 * @param firstCandidate
 * The first of the candidates to be selected from
 * 
 * @param secondCandidate
 * The second of the candidates to be selected from
 * 
 * @return The candidate ID of the chosen candidate
 */
/*@ also 
  @   protected normal_behavior
  @   requires state == COUNTING;
  @   requires firstCandidate.getStatus() == Candidate.CONTINUING;
  @   requires secondCandidate.getStatus() == Candidate.CONTINUING;
  @   requires firstCandidate.randomNumber != secondCandidate.randomNumber;
  @   ensures (\result == firstCandidate.candidateID) <==>
  @   (firstCandidate.randomNumber < secondCandidate.randomNumber);
  @   ensures (\result == secondCandidate.candidateID) <=!=>
  @   (\result == firstCandidate.candidateID);
  @*/
protected /*@ pure @*/ int randomSelection(/*@ non_null @*/ Candidate firstCandidate, /*@ non_null @*/ Candidate secondCandidate){
	if(status == COUNTING && firstCandidate.getStatus() == Candidate.CONTINUING && secondCandidate.getStatus() == Candidate.CONTINUING){
		if(firstCandidate.randomNumber < secondCandidate.randomNumber){
			return firstCandidate.candidateID;
		}else {
			return secondCandidate.candidateID;
		}
	}
	return 0;
}

/**
 * List each ballot ID in order by random number used to show how the votes 
 * have been mixed and numbered.
 * 
 * @param ballot Ballot for which to get the order of
 * @return Oreder of this ballot in numbered list of ballots
 */
/*@ also
  @   protected normal_behavior 
  @   requires state == REPORT;
  @   ensures 1 <= \result;
  @   ensures \result <= ballotsToCount.length;
  @   ensures (\forall Ballot a, b; a != null && b != null;
  @   (getOrder (a) < getOrder (b)) <==> (b.isAfter(a)));
  @*/
protected /*@ pure @*/ int getOrder(Ballot ballot){
		if (status == REPORT) {
           return ballots.length;
		}
		return 0;
	}

/**
 * List each candidate Id in order by random number to show how lots would have 
 * been chosen
 * 
 * @param candidate Candidate for which to get the order of
 * @return Order of this candidate for use when the lots are chosen
 */
/*@ also
  @   protected normal_behavior
  @   requires state == REPORT;
  @   ensures 1 <= \result;
  @   ensures \result <= candidateList.length;
  @   ensures (\forall Candidate c, d; c != null && d != null;
  @   (getOrder (c) < getOrder (d)) <==> (d.isAfter(c)));
  @*/
protected /*@ pure @*/ int getOrder(Candidate candidate){
	if(status == REPORT){
		return candidates.length;
	}
	return 0;
}

/**
 * Determine the individuals remainder after integer division by the
 * transfer factor for surpluses
 * 
 * @design This can all be done with integer arithmetic; no need to
 * use floating point numbers, which could introduce rounding errors.
 * 
 * @param fromCandidate Elected candidate from which to count to transfers
 * @param tocandidate Continuing candidate eligible to recieve votes
 * 
 * @return The size of the quotient remainder 
 */
/*@ also
  @   protected normal_behavior
  @   requires state == COUNTING;
  @   requires isElected (fromCandidate);
  @   requires toCandidate.getStatus() == ie.koa.Candidate.CONTINUING;
  @   requires getSurplus(fromCandidate) < 
  @   getTotalTransferableVotes(fromCandidate);
  @   requires 0 <= getTransferShortfall (fromCandidate);
  @   ensures \result ==
  @   getPotentialTransfers(fromCandidate, toCandidate.getCandidateID()) -
  @   ((getActualTransfers(fromCandidate, toCandidate) *
  @   getTotalTransferableVotes (fromCandidate)) /
  @   getSurplus(fromCandidate));
  @*/
protected /*@ pure @*/ int getTransferRemainder(/*@ non_null @*/ Candidate fromCandidate, /*@ non_null @*/ Candidate toCandidate){
	int temp = 0;
	if(status == COUNTING && isElected (fromCandidate) && toCandidate.getStatus() == Candidate.CONTINUING){
		if(getSurplus(fromCandidate) < getTotalTransferableVotes(fromCandidate) && 0 <= getTransferShortfall (fromCandidate)){
		    temp = getPotentialTransfers(fromCandidate, toCandidate.getCandidateID()) - ((getActualTransfers(fromCandidate, toCandidate) * getTotalTransferableVotes (fromCandidate)) / getSurplus(fromCandidate));
		}
	}
	return temp;
}

/**
 * Determine if one continuing candidate is higher than another, for the
 * purpose of resolving remainders of transfer quotients.
 * 
 * @design This is determined by finding the earliest round of counting in
 * which these candidates had unequal votes. If both candidates are equal at
 * all counts then random numbers are used to draw lots.
 * 
 * @see <a href="http://www.cev.ie/htm/tenders/pdf/1_2.pdf">Department of
 * Environment and Local Government, Count Requirements and Commentary on
 * Count Rules, section 7, page 25</a> 
 *
 * @param firstCandidate
 * The first of the candidates to be compared
 * 
 * @param secondCandidate
 * The second of the candidates to be compared
 *  
 * @return <code>true</code> if the first candidate is deemed to have recieved more
 * votes than the second 
 */
/*@ also
  @   protected normal_behavior
  @   requires firstCandidate.getStatus() == Candidate.CONTINUING;
  @   requires secondCandidate.getStatus() == Candidate.CONTINUING;
  @   ensures \result == (\exists int i; 0 <= i && i < countNumber;
  @   (firstCandidate.getVoteAtCount(i) > secondCandidate.getVoteAtCount(i)) &&
  @   (\forall int j; 0 <= j && j < i;
  @   firstCandidate.getVoteAtCount(j) == secondCandidate.getVoteAtCount(j))) ||
  @   ((randomSelection (firstCandidate, secondCandidate) ==
  @   firstCandidate.getCandidateID()) &&
  @   (\forall int k; 0 <= k && k < countNumber;
  @   firstCandidate.getVoteAtCount(k) == secondCandidate.getVoteAtCount(k)));
  @*/
	protected /*@ pure @*/ boolean isHigherThan(Candidate firstCandidate, Candidate secondCandidate) {
		if (firstCandidate.getStatus() == Candidate.CONTINUING
				&& secondCandidate.getStatus() == Candidate.CONTINUING) {
			for (int i = 0; i < countNumberValue; i++) {
				if (firstCandidate.getVoteAtCount(i) > secondCandidate.getVoteAtCount(i)) {
					for (int j = 0; j < i; j++) {
						if(firstCandidate.getVoteAtCount(j) == secondCandidate.getVoteAtCount(j) && randomSelection (firstCandidate, secondCandidate) == firstCandidate.getCandidateID()){
							for (int k = 0; k < countNumberValue; k++) {
								if(firstCandidate.getVoteAtCount(k) == secondCandidate.getVoteAtCount(k)){
									return true;
								}
															}
						}

					}
				}
			}
		}
		return false;
	}

/**
 * Determine the number of continuing candidates with a higher remainder in
 * their transfer quotient, or deemed to have a higher remainder.
 * 
 * @design There must be a strict ordering of candidates for the purpose of 
 * allocating the transfer shortfall. If two or more candidates have equal
 * remainders then use the number of transfers they are about to recieve and if
 * the number of transfers are equal then look at the number of votes all ready 
 * recieved.
 * 
 * @param fromCandidate 
 * Elected candidate from which to distribute surplus
 * 
 * @param toCandidate 
 * Continuing candidate potentially eligible to recieve transfers
 * 
 * @return The number of continuing candidates with a higher quotient remainder
 * than this candidate 
 */
/*@ also
  @   protected normal_behavior
  @   requires state == COUNTING;
  @   requires isElected (fromCandidate);
  @   requires toCandidate.getStatus() == ie.koa.Candidate.CONTINUING;
  @   requires getSurplus(fromCandidate) < getTotalTransferableVotes(fromCandidate);
  @   ensures \result == (\num_of int i; i <= 0 && i < totalCandidates &&
  @   candidateList[i].getCandidateID() != toCandidate.getCandidateID()&&
  @   candidateList[i].getStatus() == ie.koa.Candidate.CONTINUING;
  @   (getTransferRemainder(fromCandidate, candidateList[i]) >
  @   getTransferRemainder(fromCandidate, toCandidate)) ||
  @   ((getTransferRemainder(fromCandidate, candidateList[i]) ==
  @   getTransferRemainder(fromCandidate, toCandidate)) &&
  @   (getActualTransfers(fromCandidate, candidateList[i]) >
  @   getActualTransfers(fromCandidate, toCandidate))) ||
  @   ((((getTransferRemainder(fromCandidate, candidateList[i]) ==
  @   getTransferRemainder(fromCandidate, toCandidate)) &&
  @   (getActualTransfers(fromCandidate, candidateList[i]) ==
  @   getActualTransfers(fromCandidate, toCandidate)))) &&
  @   isHigherThan (candidateList[i], toCandidate)));
  @*/
protected /*@ pure @*/ int getCandidateOrderByHighestRemainder(Candidate fromCandidate, Candidate toCandidate){
	int num = 0;
	if(status == COUNTING && isElected (fromCandidate)){
		if(toCandidate.getStatus() == Candidate.CONTINUING && toCandidate.getStatus() == Candidate.CONTINUING){
			for(int i=0; i<totalNumberOfCandidates; i++){
				if(candidates[i].getCandidateID() != toCandidate.getCandidateID()&& candidates[i].getStatus() == Candidate.CONTINUING){
					if(getTransferRemainder(fromCandidate, candidates[i]) > getTransferRemainder(fromCandidate, toCandidate)){
						num++;
					}
					if(getTransferRemainder(fromCandidate, candidates[i]) == getTransferRemainder(fromCandidate, toCandidate) &&	getActualTransfers(fromCandidate, candidates[i]) > getActualTransfers(fromCandidate, toCandidate)){
						num++;
					}
					if(getTransferRemainder(fromCandidate, candidates[i]) == getTransferRemainder(fromCandidate, toCandidate) && getActualTransfers(fromCandidate, candidates[i]) == getActualTransfers(fromCandidate, toCandidate) && isHigherThan (candidates[i], toCandidate)){
						num++;
					}
					
				}
			}
		}
		
	}
	return 0;
}

/**
 * Get the maximum number of votes transferable to continuing candidates.
 * 
 * @param fromCandidate Candidate ID fromwhich to check the transfers
 * 
 * @return Number of votes potentially transferable from this candidate
 */
/*@ also
  @   protected normal_behavior
  @   requires (state == COUNTING);
  @   ensures \result == (\sum int i; 0 <= i && i < totalCandidates;
  @   getPotentialTransfers (fromCandidate, candidateList[i].getCandidateID()));
  @*/
protected /*@ pure @*/ int getTotalTransferableVotes(/*@ non_null @*/ Candidate fromCandidate){
    int numberOfTransfers = 0;
	if(status == COUNTING){
		for(int i = 0; i < totalNumberOfCandidates; i++){
			numberOfTransfers += getPotentialTransfers (fromCandidate, candidates[i].getCandidateID());
		}
	}
	return numberOfTransfers;
} 

/**
 * Transfer votes from one candidate to another.
 * 
 * @param fromCandidate Elected or excluded candidate
 * @param toCandidate Continuing candidate
 * @param numberOfVotes Number of votes to be transferred
 */
/*@ also
  @   protected normal_behavior
  @   requires fromCandidate.getStatus() != Candidate.CONTINUING;
  @   requires toCandidate.getStatus() == Candidate.CONTINUING;
  @   requires numberOfVotes == getActualTransfers (fromCandidate,toCandidate) +
  @   getRoundedFractionalValue (fromCandidate, toCandidate);
  @   ensures fromCandidate.getTotalVote() ==
  @   \old (fromCandidate.getTotalVote()) - numberOfVotes;
  @   ensures toCandidate.getTotalVote() ==
  @   \old (toCandidate.getTotalVote()) + numberOfVotes;
  @*/
protected /*@ pure @*/ void transferVotes(/*@ non_null @*/ Candidate fromCandidate, /*@ non_null @*/ Candidate toCandidate, int numberOfVotes){
 	 //@ assert false;
}

/**
 * Update list of decision events
 */
/*@ also
  @   protected normal_behavior
  @   requires state == COUNTING;
  @   ensures (\forall int i; 0 <= i && i < totalCandidates;
  @   isElected (candidateList[i]) ==> (\exists int k;
  @   0 <= k && k < numberOfDecisions;
  @   (decisionsMade[k].candidateID == candidateList[i].getCandidateID()) &&
  @   ((decisionsMade[k].decisionTaken == Decision.ELECTBYQUOTA) ||
  @   (decisionsMade[k].decisionTaken == Decision.DEEMELECTED))) &&
  @   (\forall int n; 0 <= n && n < numberOfDecisions;
  @   (decisionsMade[n].candidateID == candidateList[i].getCandidateID())
  @   ==> (decisionsMade[n].decisionTaken != Decision.EXCLUDE)));
  @*/
	protected void updateDecisions() {
		if (status == COUNTING) {
			for (int n = 0; n < totalNumberOfCandidates; n++) {
				if (decisions[n].decisionTaken != Decision.EXCLUDE) {
                   break;
				}
			}
			for (int i = 0; i < totalNumberOfCandidates; i++) {
				for (int k = 0; k < totalNumberOfCandidates; k++) {
					if (decisions[k].candidateID == candidates[i].getCandidateID()&& decisions[k].decisionTaken == Decision.ELECTBYQUOTA) {
						isElected(candidates[i]);
					}
				}
			}
		}
	}
   
   /**
    * Main method - not implemented yet
    */
   void main() {
      //@ assert false;
   }
}