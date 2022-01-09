package tetris;

import javax.swing.*;

public class StartupForm extends javax.swing.JFrame {

    private javax.swing.JButton leaderboardButton;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton startButton;

    public StartupForm() {
        initComponents();
    }

    private void initComponents() {

        startButton = new javax.swing.JButton();
        leaderboardButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        startButton.setText("Start Game");
        startButton.addActionListener(this::startButtonActionPerformed);

        leaderboardButton.setText("Leaderboard");
        leaderboardButton.addActionListener(evt -> leaderboardButtonActionPerformed(evt));

        quitButton.setText("Quit");
        quitButton.addActionListener(evt -> quitButtonActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(leaderboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                        .addComponent(quitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(106, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(167, Short.MAX_VALUE)
                                .addComponent(startButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(leaderboardButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quitButton)
                                .addGap(44, 44, 44))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        Tetris.start();
    }

    private void leaderboardButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        Tetris.showLeaderboard();
    }

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new StartupForm().setVisible(true));
    }
}
