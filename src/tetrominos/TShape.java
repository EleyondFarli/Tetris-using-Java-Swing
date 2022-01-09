package tetrominos;

import tetris.TetrisBlock;

import java.awt.*;

public class TShape extends TetrisBlock {

    public TShape() {
        super(new int[][] { {0, 1, 0},
                            {1, 1, 1},
                            {0, 0, 0}
        }, Color.magenta, false);
    }
}
