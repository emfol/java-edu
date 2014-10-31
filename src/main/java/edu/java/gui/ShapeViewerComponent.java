package edu.java.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Component;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Path2D;

public class ShapeViewerComponent extends Component {

    public static final int MODE_STRETCHED = 0;
    public static final int MODE_CENTRALIZED = 1;

    private Shape shape;
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

        Rectangle2D shapeBounds = this.shape.getBounds2D();
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

        g.fill(matrix.createTransformedShape(this.shape));

    }

    private void drawCentralized(Graphics2D g) {

        Rectangle2D shapeBounds = this.shape.getBounds2D();
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

        g.fill(matrix.createTransformedShape(this.shape));

    }

    /*
     * Public Methods
     */

    public void setViewMode(int mode) {
        this.mode = mode;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2;
        int mode, width, height;

        if (this.shape != null) {
            width = this.getWidth();
            height = this.getHeight();
            if (width > 0 && height > 0) {
                this.width = (double)width;
                this.height = (double)height;
                mode = this.mode;
                g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2.clearRect(0, 0, width, height);
                switch (mode) {
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
