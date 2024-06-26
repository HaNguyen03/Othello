// package edu.drexel.cs.ai.othello;

import java.util.Date;
//import java.util.ArrayList;
//import java.security.SecureRandom;

/**
 * An interface for having a human play othello through the
 * {@link UserInterface user interface}.
 * 

 */
public final class HumanP2PPlayer extends OthelloPlayer {
    Square nextMove;

    /**
     * Creates a new agent that plays according to human input.
     */
    public HumanP2PPlayer(String name) {
        super(name);
        nextMove = null;
    }

    /**
     * Callback function for receiving the next move from the UI.
     */
    public void handleUIInput(Square square) {
        nextMove = square;
    }

    /**
     * Returns the next move as input by the human from the UI. Note that this
     * function will block until the UI makes a call to
     * {@link #handleUIInput(Square)} with the next move. Also, the
     * HumanOthelloPlayer agent will always have an infinite deadline.
     */
    public Square getMove(GameState currentState, Date deadline) {
        while (nextMove == null) {
            /* wait for the UI to send us the next move */
            try {
                Thread.yield();
                Thread.sleep(10);
            }
            catch (Exception e) {
            }
        }
        Square next = nextMove;
        nextMove = null;
        return next;
    }
}
