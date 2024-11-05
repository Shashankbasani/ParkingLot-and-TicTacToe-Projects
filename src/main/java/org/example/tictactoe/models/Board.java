package org.example.tictactoe.models;

import org.example.tictactoe.enums.CellState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int size;   // N
    private List<List<Cell>> board; // physical board

    public Board(int dimension){
        this.size = dimension;
        initialiseBoard();
    }

    public void initialiseBoard(){
        this.board = new ArrayList<>();
        for(int i = 0; i < size; i++){
            List<Cell> rowsOfCells = new ArrayList<>();
            for(int j = 0; j < size; j++){
                rowsOfCells.add(new Cell(i, j));
            }
            board.add(rowsOfCells);
        }
    }

    public Cell getCell(int row, int col){
        return board.get(row).get(col);
    }

    public void updateCell(int row, int col, Player player){
        Cell cell = board.get(row).get(col);
        cell.update(player);
    }

    public Cell getFirstEmptyCell(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                Cell cell = getCell(i, j);
                if(cell.getCellState() == CellState.EMPTY){
                    return cell;
                }
            }
        }
        return null;
    }

    public Cell getRandomEmptyCell(){
        List<Cell> emptyCells = new ArrayList<>();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                Cell cell = getCell(i, j);
                if(cell.getCellState() == CellState.EMPTY){
                   emptyCells.add(cell);
                }
            }
        }

        int index = new Random().nextInt(emptyCells.size());
        return emptyCells.get(index);
    }

    public void displayBoard(){
//        System.out.println();
//        for(int i = 0; i < size; i++){
//            for(int j = 0; j < size; j++){
//                Cell cell = board.get(i).get(j);
//                if(cell.getCellState() == CellState.EMPTY){
//                    System.out.print("___ |");
//                } else {
//                    System.out.print(" " + cell.getPlayer().getSymbol().getaChar() + " ");
//                }
//            }
//            System.out.println();
//        }


        System.out.println();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                Cell cell = board.get(i).get(j);
                if(cell.getCellState() == CellState.EMPTY){
                    System.out.print("   ");
                } else {
                    System.out.print(" " + cell.getPlayer().getSymbol().getaChar() + " ");
                }

                if(j < size-1){
                    System.out.print("|");
                }
            }
            System.out.println();
            for(int j = 0; i < size-1 && j < size; j++){
                System.out.print("----");
            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }


}
