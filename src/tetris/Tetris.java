package tetris;

import javax.swing.*;

public class Tetris {

    private static GameForm gameForm;
    private static StartupForm startupForm;
    private static LeaderboardForm leaderboardForm;

    public static void start() {
        gameForm.setVisible(true);
        gameForm.startGame();
    }

    public static void showLeaderboard() {
        leaderboardForm.setVisible(true);
    }

    public static void showMainMenu() {
        startupForm.setVisible(true);
    }

    public static void gameOver(int score) {
        String playerName = JOptionPane.showInputDialog("Game Over!\nPlease enter your name below:");
        gameForm.setVisible(false);
        leaderboardForm.addPlayer(playerName, score);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gameForm = new GameForm();
                startupForm = new StartupForm();
                leaderboardForm = new LeaderboardForm();

                startupForm.setVisible(true);
            }
        });
    }
}