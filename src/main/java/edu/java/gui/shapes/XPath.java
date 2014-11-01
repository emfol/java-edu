package edu.java.gui.shapes;

import java.awt.geom.GeneralPath;

public final class XPath extends BasicShape {

    public XPath() {
        super();
        this.build();
    }

    private void build() {
        GeneralPath path = this.getShapePath();
        path.setWindingRule(GeneralPath.WIND_EVEN_ODD);
        path.moveTo(0.0f, 1.0f);
        path.lineTo(1.0f, 0.0f);
        path.lineTo(2.0f, 1.0f);
        path.lineTo(3.0f, 0.0f);
        path.lineTo(4.0f, 1.0f);
        path.lineTo(3.0f, 2.0f);
        path.lineTo(4.0f, 3.0f);
        path.lineTo(3.0f, 4.0f);
        path.lineTo(2.0f, 3.0f);
        path.lineTo(1.0f, 4.0f);
        path.lineTo(0.0f, 3.0f);
        path.lineTo(1.0f, 2.0f);
        path.closePath();
    }

}
