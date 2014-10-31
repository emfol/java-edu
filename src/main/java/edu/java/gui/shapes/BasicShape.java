package edu.java.gui.shapes;

import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class BasicShape extends Object implements Shape {

    private final GeneralPath path;

    public BasicShape() {
        super();
        this.path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
    }

    protected final GeneralPath getShapePath() {
        return this.path;
    }

    public final boolean contains(double x, double y) {
        return this.path.contains(x, y);
    }

    public final boolean contains(double x, double y, double w, double h) {
        return this.path.contains(x, y, w, h);
    }

    public final boolean contains(Point2D p) {
        return this.path.contains(p);
    }

    public final boolean contains(Rectangle2D r) {
        return this.path.contains(r);
    }

    public final Rectangle getBounds() {
        return this.path.getBounds();
    }

    public final Rectangle2D getBounds2D() {
        return this.path.getBounds2D();
    }

    public final PathIterator getPathIterator(AffineTransform at) {
        return this.path.getPathIterator(at);
    }

    public final PathIterator getPathIterator(AffineTransform at, double flatness) {
        return this.path.getPathIterator(at, flatness);
    }

    public final boolean intersects(double x, double y, double w, double h) {
        return this.path.intersects(x, y, w, h);
    }

    public final boolean intersects(Rectangle2D r) {
        return this.path.intersects(r);
    }

}
