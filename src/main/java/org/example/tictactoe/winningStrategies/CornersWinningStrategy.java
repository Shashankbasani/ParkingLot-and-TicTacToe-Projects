package org.example.tictactoe.winningStrategies;

import org.example.tictactoe.models.Board;
import org.example.tictactoe.models.Move;

public class CornersWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Move lastMove) {
        // check all 4 corners
        return false;
    }

    public void updateCounter(Move move){

    }

    @Override
    public void decrementCounter(Move move) {

    }
}
