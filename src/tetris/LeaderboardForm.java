package tetris;

import javax.naming.ldap.SortKey;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class LeaderboardForm extends javax.swing.JFrame {

    private DefaultTableModel tableModel;

    private String leaderboardFile = "highscores";

    private TableRowSorter<TableModel> tableSorter;

    public LeaderboardForm() {
        initComponents();
        initTableData();
        initTableSorter();
    }

    private void initTableData() {
        Vector< String > columnIdentifiers = new Vector<>();
        columnIdentifiers.add("Player");
        columnIdentifiers.add("Score");

        tableModel = (DefaultTableModel) leaderboardTable.getModel();

        try {
            FileInputStream fileStream = new FileInputStream(leaderboardFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileStream);

            tableModel.setDataVector((Vector) inputStream.readObject(), columnIdentifiers);
            fileStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Could not load leaderboard file");
        }
    }

    private void initTableSorter() {
        tableSorter = new TableRowSorter<>(tableModel);
        leaderboardTable.setRowSorter(tableSorter);

        ArrayList< TableRowSorter.SortKey > keys = new ArrayList<>();
        keys.add(new TableRowSorter.SortKey(1, SortOrder.DESCENDING));
        tableSorter.setSortKeys(keys);
    }

    private void saveLeaderboard() {
        try {
            FileOutputStream fileStream = new FileOutputStream(leaderboardFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);

            outputStream.writeObject(tableModel.getDataVector());
            outputStream.close();
            fileStream.close();
        } catch(Exception e) {
            System.out.println("could not save leaderboard");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        mainMenuButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaderboardTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        mainMenuButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        mainMenuButton.setText("Main Menu");
        mainMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mainMenuButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuButtonActionPerformed(evt);
            }
        });

        leaderboardTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Player", "Score"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(leaderboardTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void mainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        Tetris.showMainMenu();
    }

    public void addPlayer(String playerName, int score) {
        tableModel.addRow(new Object[] {playerName, score});
        tableSorter.sort();
        saveLeaderboard();
        this.setVisible(true);
    }

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeaderboardForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable leaderboardTable;
    private javax.swing.JButton mainMenuButton;
    // End of variables declaration
}
