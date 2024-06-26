import java.util.AbstractSet;
import java.util.Date;


public class MMOthelloPlayer extends OthelloPlayer implements MiniMax{
	private int depthLimit;
	private int generatedNodes = 0;
	private int staticEvaluationsComputed = 0;
	
	public MMOthelloPlayer(String name) {
		super(name);
		depthLimit = 5;
	}
	public MMOthelloPlayer(String name, int _depthLimit) {
		super(name);
		depthLimit = _depthLimit;
	}

	@Override
	public Square getMove(GameState currentState, Date deadline) {
		Square root = currentState.getPreviousMove();
		Square nextMove = miniMax(root, 0, currentState,true);
		if (nextMove.getCol() >= 0 && nextMove.getRow() >= 0) {
            currentState = currentState.applyMove(nextMove);
        }
		return nextMove;
	}
	public Square miniMax(Square move,int depth, GameState currentState,boolean isMax) {
		AbstractSet<Square> possibleMoves = currentState.getValidMoves();
		if(depth == depthLimit || possibleMoves.size() == 0) {
			return move;
		}
		
		if (isMax) {
			int v = Integer.MIN_VALUE;
			Square maxMove = null;
			for(Square _move: possibleMoves) {
				Square nextMove = miniMax(_move, depth + 1, currentState.applyMove(_move), false);
				if(staticEvaluator(currentState.applyMove(_move)) > v) {
					maxMove = _move;
					v = staticEvaluator(currentState.applyMove(_move));
				}
			}
			return maxMove;
		}else {
			int v = Integer.MAX_VALUE;
			Square minMove = null;
			for(Square _move: possibleMoves) {
				Square nextMove = miniMax(_move, depth + 1, currentState.applyMove(_move), false);
				if(staticEvaluator(currentState.applyMove(_move)) > v) {
					minMove = _move;
					v = staticEvaluator(currentState.applyMove(_move));
				}
			}
			return minMove;
        }
	}
	
	
	
	 /**
     * The static evaluation function for your search.  This function
     * must be used by your MiniMax and AlphaBeta algorithms for all
     * static evaluations.  It is separated out so that it can be easily
     * altered for grading purposes.
     * 
     * @param state the state to be evaluated.
     * @return an integer score for the value of the state to the max player.
     */
	@Override
	public int staticEvaluator(GameState state) {
		staticEvaluationsComputed++;
		// check current state of the board
		// Apply all moves and calculate scores
		// Assign scores to possible nodes
		
		//Study strategy to play Othello:
		/*p 
		 * Using actual game score for minimax is bad
		 * Avoid the space next to the corners and the side! - Mark a low score for those spaces 
		 * Avoid at all cost B1, G1, B7, G7
		 * Mark a high score for the spaces in the corners and on the side - Especially corners: Mark it for 99? 
		 * Corner: Using Static Values
		 * A  B  C  D  E  F  G  H
		 * 99  50 50 50 50 50 50  99
		 * 50 -99 20 20 20 20 -99 50				
		 * 50  20 40 40 40 40 20  50
		 * 50  20 40 PS PS 40 20  50
		 * 50  20 40 PS PS 40 20  50
		 * 50  20 40 40 40 40 20  50
		 * 50 -99 20 20 20 20 -99 50
		 * 99  50 50 50 50 50 50  99
		 * result
		 * Mobility: 
		 * The more moves you have the better the reverse is applied for the opponent:
		 * result = youValidMoves - opponentValidMoves
		 * 
		 */
		
		 return state.getScore(state.getCurrentPlayer());
	}
	 /**
     * Get the number of static evaluations that were
     * performed during the search.
     * 
     * @return the number of static evaluations performed.
     */
	@Override
	public int getNodesGenerated() {
		return generatedNodes;
	}
	 /**
     * Get the number of static evaluations that were
     * performed during the search.
     * 
     * @return the number of static evaluations performed.
     */
	@Override
	public int getStaticEvaluations() {
		return staticEvaluationsComputed;
	}
	 /**
     * Get the average branching factor of the nodes that
     * were expanded during the search.  This is to be computed
     * based upon the actual number of children for each node.
     * 
     * @return the average branching factor.
     */
	@Override
	public double getAveBranchingFactor() {
		// TODO Auto-generated method stub
		return 0;
	}
	 /**
     * Get the effective branching factor of the nodes that were
     * expanded during the search.  This is to be computed based
     * upon the number of children that are explored in the search.
     * Without alpha/beta pruning this number will be equal to the 
     * average branching factor. With alpha/beta pruning it may be
     * significantly smaller.
     * 
     * @return the effective branching factor.
     */
	@Override
	public double getEffectiveBranchingFactor() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
