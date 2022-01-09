package tetris;

import java.util.*;

public class GameThread extends Thread {

    private GameArea gameArea;
    private GameForm gameForm;
    private int score;
    private int level;
    private int scorePerLevel = 3;
    private int speedUpPerLevel = 100;
    private boolean isSoftdropping = false;

    private int pause = 500;
    private int softDropPause = 300;

    private int[] bagOf7Pieces;

    public GameThread(GameArea gameArea, GameForm gameForm) {
        this.gameArea = gameArea;
        this.gameForm = gameForm;
        this.level = 1;

        bagOf7Pieces = new int[7];
        gameForm.updateScore(score);
        gameForm.updateLevel(level);
    }

    public void setSoftdropping(boolean softdropping) {
        isSoftdropping = softdropping;
    }

    @Override
    public void run() {
        int pieceNumber = 0;
        int blockType;

        while (true) {
            if (pieceNumber == 0) {
                bagOf7Pieces = generate7Bag();
            }
            blockType = bagOf7Pieces[pieceNumber];
            gameArea.spawnBlock(blockType);
            while (gameArea.moveBlockDown()) {
                try {
                    if (isSoftdropping) {
                       Thread.sleep(softDropPause);
                       isSoftdropping = false;
                    } else {
                        Thread.sleep(pause);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Thread stopped");
                    return;
                }
            }

            if (gameArea.isBlockOutOfBounds()) {
                Tetris.gameOver(score);
                break;
            }

            gameArea.moveBlockToBackground();

            int scoreToAdd = gameArea.clearLines();
            if (scoreToAdd > 0) {
                score += scoreToAdd;
                gameForm.updateScore(score);
            }

            int newLevel = score / scorePerLevel + 1;
            if (newLevel > level) {
                level = newLevel;
                gameForm.updateLevel(level);
                if (pause > speedUpPerLevel) {
                    pause -= speedUpPerLevel;
                }
            }
            pieceNumber = (pieceNumber + 1) % 7;
            Tetris.playFall();
        }
    }

    private int[] generate7Bag() {
        int[] newBag = { 0, 1, 2, 3, 4, 5, 6 };
        Random rand = new Random();

		for (int i = 0; i < newBag.length; i++) {
            int randomIndexToSwap = rand.nextInt(newBag.length);
            int temp = newBag[randomIndexToSwap];
            newBag[randomIndexToSwap] = newBag[i];
            newBag[i] = temp;
        }

        return newBag;
    }


}
