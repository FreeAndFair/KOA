/**
 * Votail Cuntais - Irish PR-STV ballot counting system
 * 
 * Copyright (c) 2009 Dermot Cochran
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 * Ballot counting for elections to D‡il ƒireann
 */
package election.tally.dail;

import election.tally.ElectionAlgorithm;
 
/**
 * @author Dermot Cochran
 *
 */
public class BallotCounting extends ElectionAlgorithm {

	/**
	 * Inner class for state machine
	 */
	public class BallotCountingMachine implements election.util.StateMachine {
		
		// States within the ballot counting machine
		public static final int READY_TO_COUNT = 1;
		public static final int NO_SEATS_FILLED_YET = 2;
		public static final int CANDIDATES_HAVE_QUOTA = 3;
		public static final int CANDIDATE_ELECTED = 4;
		public static final int NO_SURPLUS_AVAILABLE = 5;
		public static final int SURPLUS_AVAILABLE = 6;
		public static final int READY_TO_CALCULATE_ROUNDING_TRANSFERS = 7;
		public static final int READY_TO_ALLOCATE_SURPLUS = 8;
		public static final int READY_TO_ADJUST_NUMBER_OF_TRANSFERS = 9;
		public static final int READY_TO_MOVE_BALLOTS = 10;
		public static final int CANDIDATE_EXCLUDED = 11;
		public static final int READY_FOR_NEXT_ROUND_OF_COUNTING = 12;
		public static final int LAST_SEAT_BEING_FILLED = 13;
		public static final int MORE_CONTINUING_CANDIDATES_THAN_REMAINING_SEATS = 14;
		public static final int ONE_OR_MORE_SEATS_REMAINING = 15;
		public static final int ALL_SEATS_FILLED = 16;
		public static final int END_OF_COUNT = 17;
		public static final int ONE_CONTINUING_CANDIDATE_PER_REMAINING_SEAT = 18;
		
		// Initial state
		public BallotCountingMachine() {
			state = READY_TO_COUNT;
		}

		//@ public invariant isPossibleState (state);
		//@ public constraint isTransition(\old(state), state);
 		private int state;
 		
 		//@ ensures \result == state;
 		public int getState() {
			return state;
		}

 		//@ ensures newState == state;
		public void changeState(int newState) {
			state = newState;
		}

		/**
		 * Set of possible states
		 */
		public boolean isPossibleState(int value) {
 			return ((READY_TO_COUNT == value) ||
 					(NO_SEATS_FILLED_YET == value) ||
 					(CANDIDATES_HAVE_QUOTA == value) ||
 					(CANDIDATE_ELECTED == value) ||
 					(NO_SURPLUS_AVAILABLE == value) ||
 					(SURPLUS_AVAILABLE == value) ||
 					(READY_TO_CALCULATE_ROUNDING_TRANSFERS == value) ||
 					(READY_TO_ALLOCATE_SURPLUS == value) ||
 					(READY_TO_ADJUST_NUMBER_OF_TRANSFERS == value) ||
 					(READY_TO_MOVE_BALLOTS == value) ||
 					(CANDIDATE_EXCLUDED == value) ||
 					(READY_FOR_NEXT_ROUND_OF_COUNTING == value) ||
 					(LAST_SEAT_BEING_FILLED == value) ||
 					(MORE_CONTINUING_CANDIDATES_THAN_REMAINING_SEATS == value) ||
 					(ONE_OR_MORE_SEATS_REMAINING == value) ||
 					(ALL_SEATS_FILLED == value) ||
 					(END_OF_COUNT == value) ||
 					(ONE_CONTINUING_CANDIDATE_PER_REMAINING_SEAT == value));
		}
		
		/**
		 * Set of valid transitions from state machine diagram
		 */
		public boolean isTransition(int fromState, int toState) {
			
			// Self transitions are allowed
			if (toState == fromState) {
				return true;
			}
			
			// No transitions into the initial state
			else if (READY_TO_COUNT == toState) {
				return false;
			}
			
			// No transitions away from final state
			else if (END_OF_COUNT == fromState) {
				return false;
			}
			
			// Transition: Calculate Quota
			else if ((READY_TO_COUNT == fromState) && (NO_SEATS_FILLED_YET == toState)) {
				return true;
			}
			
			// Transition: Find Highest Continuing Candidate with Quota
			else if (((NO_SEATS_FILLED_YET == fromState) || 
					(CANDIDATES_HAVE_QUOTA == fromState) ||
					(MORE_CONTINUING_CANDIDATES_THAN_REMAINING_SEATS == fromState)) &&
				((CANDIDATE_ELECTED == toState) ||	
					(NO_SURPLUS_AVAILABLE == toState))) {
					return true;
				}
			
			// Transition: Calculate Surplus
			else if ((CANDIDATE_ELECTED == fromState) &&
			   ((CANDIDATES_HAVE_QUOTA == toState) ||
					   (SURPLUS_AVAILABLE == toState) ||
					   (NO_SURPLUS_AVAILABLE == toState))) {
				   return true;
			   }
			
			// Transition: Calculate Transfer Factor
			else if ((SURPLUS_AVAILABLE == fromState) && 
					(READY_TO_ALLOCATE_SURPLUS == toState)) {
				return true;
			}
			
			// Transition: Calculate Non-Fractional Transfers
			else if ((READY_TO_ALLOCATE_SURPLUS == fromState) &&
					(READY_TO_CALCULATE_ROUNDING_TRANSFERS == toState)) {
				return true;
			}
			
			// Transition: Calculate Fractional Differences
			else if ((READY_TO_CALCULATE_ROUNDING_TRANSFERS == fromState) &&
					(READY_TO_ADJUST_NUMBER_OF_TRANSFERS == toState)) {
				return true;
			}
			
			// Transition: Calculate Adjusted Number of Transfers
			else if ((READY_TO_ADJUST_NUMBER_OF_TRANSFERS == fromState) &&
					(READY_TO_MOVE_BALLOTS == toState)) {
				return true;
			}
			
			// Transition: Calculate Transfers
			else if ((CANDIDATE_EXCLUDED == fromState) &&
					(READY_TO_MOVE_BALLOTS == toState)) {
				return true;
			}
			
			// Transition: Move the Ballots
			else if ((READY_TO_MOVE_BALLOTS == fromState) && 
					(READY_FOR_NEXT_ROUND_OF_COUNTING == toState)) {
				return true;
			}
			
			// Transition: Select Lowest Continuing Candidates for Exclusion
			else if (((NO_SURPLUS_AVAILABLE == fromState) ||
					(LAST_SEAT_BEING_FILLED == fromState)) &&
					(CANDIDATE_EXCLUDED == toState)) {
				return true;
			}
			
			// Transition: Count Continuing Candidates
			else if ((ONE_OR_MORE_SEATS_REMAINING == fromState) &&
					((LAST_SEAT_BEING_FILLED == toState) ||
					(MORE_CONTINUING_CANDIDATES_THAN_REMAINING_SEATS == toState) ||
					(ONE_CONTINUING_CANDIDATE_PER_REMAINING_SEAT == toState))) {
				return true;
			}
			
			// Transition: Check Remaining Seats
			else if ((READY_FOR_NEXT_ROUND_OF_COUNTING == fromState) &&
					((ONE_OR_MORE_SEATS_REMAINING == toState) ||
					(ALL_SEATS_FILLED == toState))) {
				return true;
			}
			
			// Transition: Declare Remaining Candidates Elected
			else if ((ONE_CONTINUING_CANDIDATE_PER_REMAINING_SEAT == fromState) &&
					(ALL_SEATS_FILLED == toState)) {
				return true;
			}
			
			// Transition: Close the Count
			else if ((ALL_SEATS_FILLED == fromState) &&
					(END_OF_COUNT == toState)) {
				return true;
			}
			
			// No other state transitions are possible
			return false;
		}
		
	}

}
