package org.example.tictactoe.winningStrategies;

import org.example.tictactoe.models.Board;
import org.example.tictactoe.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move lastMove);
    void updateCounter(Move move);
    void decrementCounter(Move move);
}
