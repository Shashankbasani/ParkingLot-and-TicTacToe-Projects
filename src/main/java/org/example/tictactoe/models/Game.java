package org.example.tictactoe.models;

import org.example.tictactoe.enums.CellState;
import org.example.tictactoe.enums.GameState;
import org.example.tictactoe.enums.PlayerType;
import org.example.tictactoe.exceptions.BotCountException;
import org.example.tictactoe.exceptions.DimensionException;
import org.example.tictactoe.exceptions.DuplicateSymbolException;
import org.example.tictactoe.exceptions.PlayerCountException;
import org.example.tictactoe.winningStrategies.WinningStrategy;

import java.util.*;


public class Game {
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private List<Move> movesList;   // this is used for undo feature
    private int currentPlayerIndex;
    private Player winner;  // after game completes, you have a winner;
    private List<WinningStrategy> winningStrategies;

    // before we make a game object, we need to check
    // check players = n-1
    // bot count should be 1 only.
    // winning condition should not be empty

    public Game(Builder builder){
        this.board = new Board(builder.boardSize);
        this.players = builder.players;
        this.winningStrategies = builder.winningStrategies;

        this.gameState = GameState.NOT_STARTED;
        this.movesList = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.winner = null;
    }

    public void displayBoard(){
        this.board.displayBoard();
    }

    public void play(){
        gameState = GameState.IN_PROGRESS;
        displayBoard();
        while(gameState != GameState.COMPLETED || gameState != GameState.DRAW){
            // keep taking player choices
            Player currentPlayer = players.get(currentPlayerIndex);
            Move move = currentPlayer.makeMove(board);
            movesList.add(move);
            displayBoard();

            for(WinningStrategy strategy : winningStrategies){
                strategy.updateCounter(move);
            }

            if(checkIfPlayerHasWon(board, move)){
                gameState = GameState.COMPLETED;
                winner = currentPlayer;
                break;
            }

            checkForUndo(currentPlayer, move);
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        displayBoard();
        if(gameState == GameState.COMPLETED){
            System.out.println("Winner is " + winner.getName());
        }
        if(gameState == GameState.DRAW){
            System.out.println("Game is a Draw!");
        }
    }

    private boolean checkIfPlayerHasWon(Board board, Move lastMove){
        for(WinningStrategy strategy : winningStrategies){
            if(strategy.checkWinner(board, lastMove)){
                return true;
            }
        }
        return false;
    }

    public void checkForUndo(Player player, Move move){
        if(player.getPlayerType() == PlayerType.BOT){
            return;
        }
        System.out.println("Do you want to undo ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("y")){
            performUndo(player, move);
        }
    }

    public void performUndo(Player player, Move move){
        Cell cell = move.getCell();
        cell.clear();

        for(WinningStrategy strategy : winningStrategies){
            strategy.decrementCounter(move);
        }

        movesList.remove(movesList.size()-1);

        displayBoard();
        player.makeMove(board);
        movesList.add(move);
    }

    // the game says, you want to get a Game object
    // take this GameBuilder with you.
    // use this GameBuilder to create a game object.
    // This GameBuilder will contain validations that gives you a valid game object
    // or an exception if any parameter is wrong.

    public static Builder getbuilder(){
        return new Builder();
    }

    public static class Builder {
        private int boardSize;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setBoardSize(int s){
            boardSize = s;
            return this;
        }

        public Builder setPlayers(List<Player> playerList){
            players = playerList;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> strategies){
            winningStrategies = strategies;
            return this;
        }

        private void checkBoardSize() throws DimensionException {
            if(boardSize < 3){
                throw new DimensionException("board size should be atleast 3 or more than 3");
            }
        }

        private void checkPlayerCount() throws PlayerCountException {
            if(players.size() != boardSize - 1){
                throw new PlayerCountException("Players should be n-1");
            }
        }

        private void checkUniqueSymbols() throws DuplicateSymbolException {
            Set<Character> uniqueSymbols = new HashSet<>();
            for(Player player : players){
                if(uniqueSymbols.contains(player.getSymbol().getaChar())){
                    throw new DuplicateSymbolException("Duplicate symbol");
                }
                uniqueSymbols.add(player.getSymbol().getaChar());
            }
        }

        private void checkBotCount() throws BotCountException {
            int botCount = 0;
            for(Player player : players){
                if(player.getPlayerType() == PlayerType.BOT){
                    botCount++;
                }
            }
            if(botCount > 1){
                throw new BotCountException("Bot count should be at most 1");
            }
        }

        public Game build() throws DimensionException, PlayerCountException, BotCountException, DuplicateSymbolException {
            checkBoardSize();
            checkPlayerCount();
            checkBotCount();
            checkUniqueSymbols();

            return new Game(this);
        }
    }
}
