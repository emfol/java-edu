package edu.java.gui.paths;

import java.awt.geom.Path2D;

public final class XPath extends Path2D.Float {

    public XPath() {
        super(Path2D.WIND_EVEN_ODD, 14);
        this.build();
    }

    private void build() {
        this.moveTo(0.0f, 1.0f);
        this.lineTo(1.0f, 0.0f);
        this.lineTo(2.0f, 1.0f);
        this.lineTo(3.0f, 0.0f);
        this.lineTo(4.0f, 1.0f);
        this.lineTo(3.0f, 2.0f);
        this.lineTo(4.0f, 3.0f);
        this.lineTo(3.0f, 4.0f);
        this.lineTo(2.0f, 3.0f);
        this.lineTo(1.0f, 4.0f);
        this.lineTo(0.0f, 3.0f);
        this.lineTo(1.0f, 2.0f);
        this.closePath();
    }

}
