package tetris;

import java.awt.*;

public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int x, y;
    private int[][][] shapes;
    private int currentRotation;

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;

        initShapes();
    }

    private void initShapes() {
        shapes = new int[4][][];

        for (int i = 0; i < 4; ++i) {
            int rows = shape[0].length;
            int cols = shape.length;

            shapes[i] = new int[rows][cols];

            for (int y = 0; y < rows; ++y) {
                for (int x = 0; x < cols; ++x) {
                    shapes[i][y][x] = shape[cols - x - 1][y];
                }
            }
            shape = shapes[i];
        }

    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveDown() {
        ++y;
    }

    public void moveLeft() {
        --x;
    }

    public void moveRight() {
        ++x;
    }

    public void moveUp() {
        --y;
    }

    public void rotateRight() {
        ++currentRotation;
        if (currentRotation > 3) {
            currentRotation = 0;
        }
        shape = shapes[currentRotation];
    }

    public void rotateLeft() {
        --currentRotation;
        if (currentRotation < 0) {
            currentRotation = 3;
        }
        shape = shapes[currentRotation];
    }

    public void spawn(int gridWidth) {
        currentRotation = 0;
        shape = shapes[currentRotation];

        y = -getHeight();
        x = ( gridWidth - getWidth() ) / 2;
    }

    public int getBottomEdge() {
        return y + getHeight();
    }

    public int getLeftEdge() {
        return x;
    }

    public int getRightEdge() {
        return x + getWidth();
    }
}
