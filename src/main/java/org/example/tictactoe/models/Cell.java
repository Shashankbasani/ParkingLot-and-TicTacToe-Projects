package org.example.tictactoe.models;


import org.example.tictactoe.enums.CellState;

public class Cell {
    private int row;
    private int column;
    private CellState cellState;
    private Player player;  // which player has used this cell.

    public Cell(int row, int column){
        this.row = row;
        this.column = column;
        this.cellState = CellState.EMPTY;
    }

    public void update(Player player){
        this.cellState = CellState.FILLED;
        this.player = player;
    }

    public void clear(){
        this.cellState = CellState.EMPTY;
        this.player = null;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
