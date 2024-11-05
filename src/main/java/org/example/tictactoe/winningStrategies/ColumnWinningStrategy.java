package org.example.tictactoe.winningStrategies;

import org.example.tictactoe.models.Board;
import org.example.tictactoe.models.Cell;
import org.example.tictactoe.models.Move;
import org.example.tictactoe.models.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{

    private Map<Integer, Map<Player, Integer>> countMap = new HashMap<>();

    public ColumnWinningStrategy(int boardSize, List<Player> playerList){
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

        int col = cell.getColumn();

        // O(1) to get the winner
        if(countMap.get(col).get(player) == board.getSize()){
            return true;
        }
        return false;
    }

    // O(1) to update the counter
    public void updateCounter(Move move){
        Cell cell = move.getCell();
        Player player = move.getPlayer();

        int col = cell.getColumn();

        int existingCount = countMap.get(col).get(player);
        int newCount = existingCount + 1;
        countMap.get(col).put(player, newCount);
    }

    @Override
    public void decrementCounter(Move move) {
        Cell cell = move.getCell();
        Player player = move.getPlayer();

        int col = cell.getColumn();

        int existingCount = countMap.get(col).get(player);
        int newCount = existingCount - 1;
        countMap.get(col).put(player, newCount);
    }
}
