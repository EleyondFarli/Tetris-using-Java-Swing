package tetrominos;

import tetris.TetrisBlock;

import java.awt.*;

public class LShape extends TetrisBlock {

    public LShape() {
        super(new int[][] { {0, 0, 1},
                            {1, 1, 1},
                            {0, 0, 0}
        }, Color.orange, false);
    }
}
