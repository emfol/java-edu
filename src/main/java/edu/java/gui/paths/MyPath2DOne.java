package edu.java.gui.paths;

import java.awt.geom.Path2D;

public class MyPath2DOne extends Path2D.Float {

    public MyPath2DOne() {
        super(Path2D.WIND_EVEN_ODD, 7);
        this.moveTo(0.0f, 0.5f);
        this.lineTo(0.5f, 0.0f);
        this.lineTo(2.5f, 0.0f);
        this.lineTo(3.0f, 0.5f);
        this.lineTo(2.5f, 1.0f);
        this.lineTo(0.5f, 1.0f);
        this.closePath();
    }

}
