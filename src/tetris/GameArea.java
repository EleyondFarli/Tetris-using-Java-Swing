package tetris;

import tetrominos.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameArea extends JPanel {

    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] background;

    private TetrisBlock block;

    private TetrisBlock[] blocks;

    public GameArea(JPanel placeholder, int columns, int rows) {
//        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());

        gridColumns = columns;
        gridRows = rows;
        gridCellSize = this.getBounds().width / gridColumns;

        initBackGroundArray();

        blocks = new TetrisBlock[]{ new IShape(),
                new JShape(),
                new LShape(),
                new OShape(),
                new SShape(),
                new TShape(),
                new ZShape()
        };
    }

    public void initBackGroundArray()
    {
        background = new Color[gridRows][gridColumns];
    }

    public int clearLines() {
        boolean lineFilled;
        int linesCleared = 0;
        for (int row = gridRows - 1; row >= 0; --row) {
            lineFilled = true;
            for (int col = 0; col < gridColumns; ++col) {
                if (background[row][col] == null) {
                    lineFilled = false;
                    break;
                }
            }

            if (lineFilled) {
                clearLine(row);
                shiftDown(row);
                clearLine(0);
                ++linesCleared;

                ++row;
                repaint();
            }
        }
        if (linesCleared > 0) {
            Tetris.playClearLine();
        }
        return linesCleared;
    }

    private void clearLine(int row) {
        for (int col = 0; col < gridColumns; ++col) {
            background[row][col] = null;
        }
    }

    private void shiftDown(int row) {
        for (int r = row; r > 0; --r) {
            if (gridColumns >= 0) System.arraycopy(background[r - 1], 0, background[r], 0, gridColumns);
        }
    }


    public void moveBlockToBackground() {
        int[][] shape = block.getShape();
        int height = block.getHeight();
        int width = block.getWidth();

        int xPos = block.getX();
        int yPos = block.getY();

        Color color = block.getColor();

        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                if (shape[row][col] == 1) {
                    background[row + yPos][col + xPos] = color;
                }
            }
        }
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

                    drawGridSquare(x, y, g, blockColor);
                }
            }
        }
    }

    private void drawBackground(Graphics g) {
        Color color;
        for (int row = 0; row < gridRows; ++row) {
            for (int col = 0; col < gridColumns; ++col) {
                color = background[row][col];
                if (color != null) {
                    int x = col * gridCellSize;
                    int y = row * gridCellSize;

                    drawGridSquare(x, y, g, color);
                }
            }
        }
    }

    private void drawGridSquare(int x, int y, Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }

    public boolean isBlockOutOfBounds() {
        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int col = 0; col < width; ++col) {
            for (int row = 0; row < height; ++row) {
                if (shape[row][col] == 1) {
                    int y = row + block.getY();
                    if (y < 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean moveBlockDown() {
        if (block == null) return false;
        if (!checkBottom()) {
            return false;
        }

        block.moveDown();
        repaint();
        return true;
    }

    public void moveBlockRight() {
        if (block == null) return;
        if (!checkRight()) {
            return;
        }

        block.moveRight();
        repaint();
    }

    public void moveBlockLeft() {
        //TODO: Implement kick table
        if (block == null) return;
        if (!checkLeft()) {
            return;
        }

        block.moveLeft();
        repaint();
    }

    public void rotateBlockRight() {
        //TODO: Implement kick table
        if (block == null) return;

        if (block.getLeftEdge() < 0) {
            block.setX(0);
        }

        if (block.getRightEdge() >= gridColumns) {
            block.setX(gridColumns - block.getWidth());
        }

        if (block.getBottomEdge() >= gridRows) {
            block.setY(gridRows - block.getHeight());
        }

        block.rotateRight();
        repaint();
    }

    public void rotateBlockLeft() {
        if (block == null) return;
        block.rotateLeft();
        repaint();
    }

    public void hardDropBlock() {
        if (block == null) return;
        while (checkBottom()) {
            block.moveDown();
        }
    }

    public void softDropBlock() {
        //TODO : implement in GameThread using softDropSpeed
        if (block == null) return;
        if (checkBottom()) {
            block.moveDown();
        }
        repaint();
    }

    private boolean checkBottom() {
        if (block.getBottomEdge() >= gridRows) {
            return false;
        }

        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int col = 0; col < width; ++col) {
            for (int row = height - 1; row >= 0; --row) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;

                    if (y < 0) {
                        break;
                    }

                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    private boolean checkLeft() {
        if (block.getLeftEdge() < 0) {
            return false;
        }

        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();

                    if (x < 0) {
                        break;
                    }

                    if (y >= 0 && y < getWidth()) {
                        if (background[y][x] != null) {
                            return false;
                        }
                    }
                    break;
                }
            }
        }

        return true;
    }

    private boolean checkRight() {
        if (block.getRightEdge() >= gridColumns) {
            return false;
        }

        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int row = 0; row < height; ++row) {
            for (int col = width - 1; col >= 0; --col) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();

                    if (x >= width) {
                        break;
                    }

                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    public void spawnBlock(int blockNr) {
        // TODO: Implement 7-bag randomizer system
        Random rand = new Random();
//        block = blocks[rand.nextInt(blocks.length)];
        block = blocks[blockNr];
        block.spawn(gridColumns);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        for (int y = 0; y < gridRows; ++y) {
//            for (int x = 0; x < gridColumns; ++x) {
//                g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
//            }
//        }

        drawBackground(g);
        drawBlock(g);
    }
}
