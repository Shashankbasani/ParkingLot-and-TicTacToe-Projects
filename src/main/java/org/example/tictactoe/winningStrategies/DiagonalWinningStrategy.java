package org.example.tictactoe.winningStrategies;

import org.example.tictactoe.models.Board;
import org.example.tictactoe.models.Cell;
import org.example.tictactoe.models.Move;
import org.example.tictactoe.models.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private int boardSize;
    private Map<Integer, Map<Player, Integer>> countMap = new HashMap<>();

    public DiagonalWinningStrategy(int boardSize, List<Player> playerList){
        this.boardSize = boardSize;
        for(int i = 0; i < 2; i++){
            countMap.put(i, new HashMap<>());

            for(Player player: playerList){
                countMap.get(i).put(player, 0);
            }
        }
    }

    @Override
    public boolean checkWinner(Board board, Move lastMove) {
        Cell cell = lastMove.getCell();
        Player player = lastMove.getPlayer();

        int row = cell.getRow();
        int col = cell.getColumn();

        if(row == col){
            if(countMap.get(0).get(player) == boardSize){
                return true;
            }
        }

        if(row + col == boardSize-1){
            if(countMap.get(1).get(player) == boardSize){
                return true;
            }
        }

        return false;
    }

    public void updateCounter(Move move){
        Cell cell = move.getCell();
        Player player = move.getPlayer();

        int row = cell.getRow();
        int col = cell.getColumn();

        if(row == col){
            int existingCount = countMap.get(0).get(player);
            int newCount = existingCount + 1;
            countMap.get(0).put(player, newCount);
        }
        if(row + col == boardSize - 1){
            int existingCount = countMap.get(1).get(player);
            int newCount = existingCount + 1;
            countMap.get(1).put(player, newCount);
        }
    }

    @Override
    public void decrementCounter(Move move) {
        Cell cell = move.getCell();
        Player player = move.getPlayer();

        int row = cell.getRow();
        int col = cell.getColumn();

        if(row == col){
            int existingCount = countMap.get(0).get(player);
            int newCount = existingCount - 1;
            countMap.get(0).put(player, newCount);
        }
        if(row + col == boardSize - 1){
            int existingCount = countMap.get(1).get(player);
            int newCount = existingCount - 1;
            countMap.get(1).put(player, newCount);
        }
    }
}
