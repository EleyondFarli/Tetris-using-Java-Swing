package tetris;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {

    private int gridRows;
    private int gridColumns;
    private int gridCellSize;

    private TetrisBlock block;

    public GameArea(JPanel placeholder, int columns, int rows) {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());

        gridColumns = columns;
        gridRows = rows;
        gridCellSize = this.getBounds().width / gridColumns;

        spawnBlock();
    }

    private void drawBlock(Graphics g) {
        int height = block.getHeight();
        int width = block.getWidth();
        int[][] shape = block.getShape();
        Color blockColor = block.getColor();

        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                if (shape[row][col] == 1) {
                    int x = ( block.getX() + col ) * gridCellSize;
                    int y = ( block.getY() + row ) * gridCellSize;

                    g.setColor(blockColor);
                    g.fillRect(x, y, gridCellSize, gridCellSize);
                    g.setColor(Color.black);
                    g.drawRect(x, y, gridCellSize, gridCellSize);
                }
            }
        }
    }

    public void moveBlockDown() {
        block.moveDown();
        repaint();
    }

    public void spawnBlock() {
        block = new TetrisBlock(new int[][]{ { 1, 0 }, { 1, 0 }, { 1, 1 } }, Color.orange);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        for (int y = 0; y < gridRows; ++y) {
//            for (int x = 0; x < gridColumns; ++x) {
//                g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
//            }
//        }

        drawBlock(g);
    }
}
