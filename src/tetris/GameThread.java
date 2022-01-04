package tetris;

public class GameThread extends Thread {

    private GameArea gameArea;
    private GameForm gameForm;
    private int score;
    private int level;
    private int scorePerLevel = 3;
    private int speedUpPerLevel = 100;

    private int pause = 500;

    public GameThread(GameArea gameArea, GameForm gameForm) {
        this.gameArea = gameArea;
        this.gameForm = gameForm;
        this.level = 1;

        gameForm.updateScore(score);
        gameForm.updateLevel(level);
    }

    @Override
    public void run() {

        while (true) {
            gameArea.spawnBlock();
            while (gameArea.moveBlockDown()) {
                try {
                    Thread.sleep(pause);
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
        }
    }
}
