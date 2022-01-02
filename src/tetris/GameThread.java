package tetris;

public class GameThread extends Thread {

    private GameArea gameArea;

    public GameThread(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    @Override
    public void run() {

        while (true) {
            gameArea.spawnBlock();
            while (gameArea.moveBlockDown()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
