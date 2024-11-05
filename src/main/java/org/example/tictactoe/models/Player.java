package org.example.tictactoe.models;



import org.example.tictactoe.enums.CellState;
import org.example.tictactoe.enums.PlayerType;

import java.util.Scanner;


public class Player {
    private int id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    public Player(int id, String name, Symbol symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = PlayerType.HUMAN;
    }


    // given this board, make the move.
    public Move makeMove(Board board){
        System.out.println("Its " + this.name + "'s turn now");
        int row, column;

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Row");
            row = scanner.nextInt();

            System.out.println("Enter Column");
            column = scanner.nextInt();

            // validate the inputs
            // till the time inputs are not correct, repeat
            // once correct inputs given, break the loop

            if(validateInputs(board, row, column)){
                break;
            }
        }

        // board is updated
        board.updateCell(row, column, this);

        Move move = new Move(board.getCell(row, column), this);
        return move;
    }

    private boolean validateInputs(Board board, int row, int column) {
        if(row < 0 || row >= board.getSize() || column < 0 || column >= board.getSize()){
            System.out.println("Please give inputs within boundary");
            return false;
        }

        Cell cell = board.getCell(row, column);
        if(cell.getCellState() == CellState.FILLED){
            System.out.println("Please enter position of empty cell");
            return false;
        }

        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
