package org.example.tictactoe.botPlayingStratgies;

import org.example.tictactoe.models.Board;
import org.example.tictactoe.models.Bot;
import org.example.tictactoe.models.Cell;
import org.example.tictactoe.models.Move;

public class EasyPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move makeBotMove( Bot bot, Board board) {
        Cell emptyCell = board.getFirstEmptyCell();
        return new Move(emptyCell, bot);
    }
}
