package org.example.tictactoe.models;


import org.example.tictactoe.botPlayingStratgies.BotPlayingStrategy;
import org.example.tictactoe.botPlayingStratgies.EasyPlayingStrategy;
import org.example.tictactoe.botPlayingStratgies.HardPlayingStrategy;
import org.example.tictactoe.botPlayingStratgies.MediumPlayingStrategy;
import org.example.tictactoe.enums.BotDifficultyLevel;
import org.example.tictactoe.enums.PlayerType;
import org.example.tictactoe.factories.BotPlayingStrategyFactory;

public class Bot extends Player {

    private BotDifficultyLevel difficultyLevel;
    // bot has 5 attributes in total
    // 4 are from parent -> player

    public Bot(int id, String name, Symbol symbol, BotDifficultyLevel difficultyLevel) {
        super(id, name, symbol);
        this.difficultyLevel = difficultyLevel;
        this.setPlayerType(PlayerType.BOT);
    }

    @Override
    public Move makeMove(Board board) {
        System.out.println("Bot is thinking.....");
        BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(difficultyLevel);
        Move move = botPlayingStrategy.makeBotMove(this, board);
        Cell cell = move.getCell();
        board.updateCell(cell.getRow(), cell.getColumn(), this);
        return move;
    }

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
