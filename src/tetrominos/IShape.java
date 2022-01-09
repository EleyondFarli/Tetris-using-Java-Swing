package tetrominos;

import tetris.TetrisBlock;

import java.awt.*;

public class IShape extends TetrisBlock {

    public IShape() {
        super(new int[][]{
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 1, 1, 1, 1 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                }, Color.cyan, true);
    }

    @Override
    public void rotateRight() {
        super.rotateRight();

        if (this.getWidth() == 1) {
            this.setX(this.getX() + 1);
            this.setY(this.getY() - 1);
        } else {
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }

    @Override
    public void rotateLeft() {
        super.rotateLeft();
        if (this.getWidth() == 1) {
            this.setX(this.getX() + 1);
            this.setY(this.getY() - 1);
        } else {
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }
}
