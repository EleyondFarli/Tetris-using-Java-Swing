package tetris;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameForm extends javax.swing.JFrame {

    private GameArea gameArea;
    private int speed;

    public GameForm() {
        initComponents();

        gameArea = new GameArea(gameAreaPlaceholder, 10, 20);
        this.add(gameArea);

        initControls();
        speed = 2;
        startGame();
    }

    private void initControls() {
        InputMap inMap = this.getRootPane().getInputMap();
        ActionMap actMap = this.getRootPane().getActionMap();

        inMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        inMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
        inMap.put(KeyStroke.getKeyStroke("UP"), "rightRotation");
        inMap.put(KeyStroke.getKeyStroke("Z"), "leftRotation");
        inMap.put(KeyStroke.getKeyStroke("DOWN"), "softDrop");
        inMap.put(KeyStroke.getKeyStroke("SPACE"), "hardDrop");

        actMap.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockRight();
            }
        });
        actMap.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockLeft();
            }
        });
        actMap.put("rightRotation", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.rotateBlockRight();
            }
        });
        actMap.put("leftRotation", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.rotateBlockLeft();
            }
        });
        actMap.put("softDrop", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.softDropBlock();
            }
        });
        actMap.put("hardDrop", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.hardDropBlock();
            }
        });

    }

    public void startGame() {
        new GameThread(gameArea).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        gameAreaPlaceholder = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gameAreaPlaceholder.setBackground(new java.awt.Color(238, 238, 238));
        gameAreaPlaceholder.setBorder(javax.swing.BorderFactory.createLineBorder(null));
        gameAreaPlaceholder.setPreferredSize(new java.awt.Dimension(250, 500));

        javax.swing.GroupLayout gameAreaPlaceholderLayout = new javax.swing.GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
                gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 248, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
                gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 498, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(217, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GameForm().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel gameAreaPlaceholder;
    // End of variables declaration
}
