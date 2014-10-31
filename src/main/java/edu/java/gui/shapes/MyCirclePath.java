package edu.java.gui.shapes;

import java.awt.geom.Path2D;
import java.awt.geom.Ellipse2D;

public final class MyCirclePath extends Path2D.Float {

    public MyCirclePath() {
        super();
        this.build();
    }

    private void build() {
        this.append(new Ellipse2D.Float(0.0f, 0.0f, 1.0f, 1.0f), false);
    }

}
