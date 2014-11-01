package edu.java.gui.shapes;

import java.awt.geom.GeneralPath;
import java.awt.geom.Ellipse2D;

public final class MyCircleShape extends BasicShape {

    private static final float SQRT2 = (float)Math.sqrt(2.0);

    public MyCircleShape() {
        super();
        this.build();
    }

    private void build() {
        Ellipse2D.Float ellipse = new Ellipse2D.Float();
        GeneralPath path = this.getShapePath();
        float a, b;
        path.setWindingRule(GeneralPath.WIND_EVEN_ODD);
        // outer circle
        a = 0.0f;
        b = 4.0f;
        ellipse.setFrame(a, a, b, b);
        path.append(ellipse, false);
        // inner circle
        a = SQRT2;
        b = 4.0f - 2.0f * SQRT2;
        ellipse.setFrame(a, a, b, b);
        path.append(ellipse, false);
    }

}
