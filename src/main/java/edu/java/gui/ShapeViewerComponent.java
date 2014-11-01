package edu.java.gui;

import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Component;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class ShapeViewerComponent extends Component {

    public static final int MODE_STRETCHED = 0;
    public static final int MODE_CENTRALIZED = 1;

    private volatile Shape shape;
    private volatile int mode;
    private double width;
    private double height;

    public ShapeViewerComponent(Shape shape) {
        super();
        this.shape = shape;
        this.mode = MODE_STRETCHED;
    }

    public ShapeViewerComponent() {
        this(null);
    }

    /*
     * Private Methods
     */

    private void drawStretched(Graphics2D g) {

        Shape tSh, oSh = this.shape;
        Rectangle2D shapeBounds = oSh.getBounds2D();
        AffineTransform matrix = new AffineTransform();
        double frameWidth = this.width * 0.75, frameHeight = this.height * 0.75;

        matrix.translate(
            (this.width - frameWidth) * 0.5,
            (this.height - frameHeight) * 0.5
        );
        matrix.scale(
            frameWidth / shapeBounds.getWidth(),
            frameHeight / shapeBounds.getHeight()
        );

        tSh = matrix.createTransformedShape(oSh);

        g.fill(tSh);

    }

    private void drawCentralized(Graphics2D g) {

        Shape tSh, oSh = this.shape;
        Rectangle2D shapeBounds = oSh.getBounds2D();
        AffineTransform matrix = new AffineTransform();
        double scale, shapeWidth, shapeHeight, frameWidth, frameHeight;

        frameWidth = this.width * 0.75;
        frameHeight = this.height * 0.75;
        shapeWidth = shapeBounds.getWidth();
        shapeHeight = shapeBounds.getHeight();

        if (shapeHeight / shapeWidth < frameHeight / frameWidth) {
            scale = frameWidth / shapeWidth;
        } else {
            scale = frameHeight / shapeHeight;
        }

        matrix.translate(
            (this.width - (shapeWidth * scale)) * 0.5,
            (this.height - (shapeHeight * scale)) * 0.5
        );
        matrix.scale(scale, scale);

        tSh = matrix.createTransformedShape(oSh);

        g.fill(tSh);

    }

    /*
     * Public Methods
     */

    public void setShape(Shape shape) {
        this.shape = shape;
        this.repaint();
    }

    public void setViewMode(int mode) {
        this.mode = mode;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2;
        int m, w, h;

        if (this.shape != null) {
            w = this.getWidth();
            h = this.getHeight();
            if (w > 0 && h > 0) {
                this.width = (double)w;
                this.height = (double)h;
                m = this.mode;
                g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2.clearRect(0, 0, w, h);
                switch (m) {
                    case MODE_STRETCHED:
                        this.drawStretched(g2);
                        break;
                    case MODE_CENTRALIZED:
                        this.drawCentralized(g2);
                        break;
                }
            }
        }

    }

}
