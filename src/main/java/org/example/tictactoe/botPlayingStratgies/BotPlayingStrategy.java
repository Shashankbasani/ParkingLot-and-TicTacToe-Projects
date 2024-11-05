package org.example.tictactoe.botPlayingStratgies;

import org.example.tictactoe.models.Board;
import org.example.tictactoe.models.Bot;
import org.example.tictactoe.models.Move;

public interface BotPlayingStrategy {
    Move makeBotMove(Bot bot, Board board);
}
