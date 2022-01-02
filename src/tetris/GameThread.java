package tetris;

public class GameThread extends Thread {

    private GameArea gameArea;
    private GameForm gameForm;
    private int score;
    private int level;
    private int scorePerLevel = 3;
    private int speedUpPerLevel = 100;

    private int pause = 1000;

    public GameThread(GameArea gameArea, GameForm gameForm) {

        this.gameArea = gameArea;
        this.gameForm = gameForm;
        this.level = 1;
    }

    @Override
    public void run() {

        while (true) {
            gameArea.spawnBlock();
            while (gameArea.moveBlockDown()) {
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (gameArea.isBlockOutOfBounds()) {
                System.out.println("Game Over");
                break;
            }
            gameArea.moveBlockToBackground();
            score += gameArea.clearLines();
            gameForm.updateScore(score);

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
