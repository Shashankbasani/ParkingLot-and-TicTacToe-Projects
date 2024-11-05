package org.example.tictactoe;

import org.example.tictactoe.enums.BotDifficultyLevel;
import org.example.tictactoe.exceptions.BotCountException;
import org.example.tictactoe.exceptions.DimensionException;
import org.example.tictactoe.exceptions.DuplicateSymbolException;
import org.example.tictactoe.exceptions.PlayerCountException;
import org.example.tictactoe.models.*;
import org.example.tictactoe.winningStrategies.ColumnWinningStrategy;
import org.example.tictactoe.winningStrategies.DiagonalWinningStrategy;
import org.example.tictactoe.winningStrategies.RowWinningStrategy;
import org.example.tictactoe.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    // in the case of implementing games, you can take the blow approach.

    // main contains all the hardcoding that you need to do.
    // can contain the game logic as well.
    // if you get time, then move the logic to service class.
    public static void main(String[] args) throws PlayerCountException, DuplicateSymbolException, BotCountException, DimensionException {

        int boardSize = 3;

        List<Player> players = new ArrayList<>();
        players.add(new Player(1, "Saharsh", new Symbol('X')));
        players.add(new Bot(2, "Bot", new Symbol('B'), BotDifficultyLevel.MEDIUM));


        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy(boardSize, players));
        winningStrategies.add(new ColumnWinningStrategy(boardSize, players));
        winningStrategies.add(new DiagonalWinningStrategy(boardSize, players));

        Game game = Game.getbuilder()
                        .setBoardSize(boardSize)
                        .setPlayers(players)
                        .setWinningStrategies(winningStrategies)
                        .build();
        game.play();
    }
}

// we have covered
// 0. Overview
// 1. requirements
// 2. class diagrams -> code
// 3. schema -> structure your DB

// Agenda
// how to implement TicTacToe
// basic structure
// Game Builder

// how to actually play a game
// Bot difficulties
// Winning Strategies
// undo feature.

// keep the class till 9:30.