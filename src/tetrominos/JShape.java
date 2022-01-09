package tetrominos;

import tetris.TetrisBlock;

import java.awt.*;

public class JShape extends TetrisBlock {

    public JShape() {
        super(new int[][] { {1, 0, 0},
                            {1, 1, 1},
                            {0, 0, 0}
        }, Color.blue, false);
    }
}
