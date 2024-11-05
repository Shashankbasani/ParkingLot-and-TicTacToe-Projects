package org.example.tictactoe.winningStrategies;

import org.example.tictactoe.models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{

    // Map of RowNumber, and each RowNumber stores
    // a Map of Player and their count of symbols in that particular row
    private Map<Integer, Map<Player, Integer>> countMap = new HashMap<>();

    public RowWinningStrategy(int boardSize, List<Player> playerList){
        for(int i = 0; i < boardSize; i++){
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

        if(countMap.get(row).get(player) == board.getSize()){
            return true;
        }
        return false;
    }

    public void updateCounter(Move move){
        Cell cell = move.getCell();
        Player player = move.getPlayer();

        int row = cell.getRow();

        int existingCount = countMap.get(row).get(player);
        int newCount = existingCount + 1;
        countMap.get(row).put(player, newCount);
    }

    @Override
    public void decrementCounter(Move move) {
        Cell cell = move.getCell();
        Player player = move.getPlayer();

        int row = cell.getRow();

        int existingCount = countMap.get(row).get(player);
        int newCount = existingCount - 1;
        countMap.get(row).put(player, newCount);
    }


}
