package tetrominos;

import tetris.TetrisBlock;

import java.awt.*;

public class OShape extends TetrisBlock {

    public OShape() {
        super(new int[][] { {0, 1, 1},
                            {0, 1, 1},
                            {0, 0, 0}
        }, Color.yellow, false);
    }
}
