package org.example.tictactoe.models;

// A move is basically: player has chosen this cell
public class Move {
    private Cell cell;
    private Player player;

    public Move(Cell cell, Player player){
        this.cell = cell;
        this.player = player;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
