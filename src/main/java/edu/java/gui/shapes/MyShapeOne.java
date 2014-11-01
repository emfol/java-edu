package edu.java.gui.shapes;

import java.awt.geom.GeneralPath;

public final class MyShapeOne extends BasicShape {

    public MyShapeOne() {
        super();
        this.build();
    }

    private void build() {
        GeneralPath path = this.getShapePath();
        path.setWindingRule(GeneralPath.WIND_EVEN_ODD);
        path.moveTo(0.0f, 0.5f);
        path.lineTo(0.5f, 0.0f);
        path.lineTo(2.5f, 0.0f);
        path.lineTo(3.0f, 0.5f);
        path.lineTo(2.5f, 1.0f);
        path.lineTo(0.5f, 1.0f);
        path.closePath();
    }

}
