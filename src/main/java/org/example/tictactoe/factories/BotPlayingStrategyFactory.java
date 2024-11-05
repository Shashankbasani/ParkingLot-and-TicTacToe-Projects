package org.example.tictactoe.factories;

import org.example.tictactoe.botPlayingStratgies.BotPlayingStrategy;
import org.example.tictactoe.botPlayingStratgies.EasyPlayingStrategy;
import org.example.tictactoe.botPlayingStratgies.HardPlayingStrategy;
import org.example.tictactoe.botPlayingStratgies.MediumPlayingStrategy;
import org.example.tictactoe.enums.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel difficultyLevel) {
        if(difficultyLevel == BotDifficultyLevel.EASY){
            return new EasyPlayingStrategy();
        }
        if(difficultyLevel == BotDifficultyLevel.MEDIUM){
            return new MediumPlayingStrategy();
        }
        if(difficultyLevel == BotDifficultyLevel.HARD){
            return new HardPlayingStrategy();
        }
        return null;
    }

}
