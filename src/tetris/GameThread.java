package tetris;

public class GameThread extends Thread {

    private GameArea gameArea;

    public GameThread(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    @Override
    public void run() {

        while (true) {
            try {
                gameArea.moveBlockDown();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
