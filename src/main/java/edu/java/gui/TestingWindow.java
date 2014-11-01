package edu.java.gui;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Window;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.PathIterator;

public final class TestingWindow extends WindowAdapter implements KeyListener {

    private ShapeViewerComponent shapeViewer;

    public TestingWindow() {
        super();
        this.shapeViewer = null;
    }

    // WindowAdapter overrides

    @Override
    public void windowClosing(WindowEvent e) {
        Window w = e.getWindow();
        w.setVisible(false);
        w.dispose();
    }

    // KeyListener implementations

    public void keyPressed(KeyEvent e) {
        // nope..
    }

    public void keyReleased(KeyEvent e) {
        if (this.shapeViewer != null) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S:
                    this.shapeViewer.setViewMode(ShapeViewerComponent.MODE_STRETCHED);
                    break;
                case KeyEvent.VK_C:
                    this.shapeViewer.setViewMode(ShapeViewerComponent.MODE_CENTRALIZED);
                    break;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        // nope...
    }

    public void start(Shape shape) {

        Frame frame;

        this.shapeViewer = new ShapeViewerComponent(shape);
        this.shapeViewer.setBackground(Color.BLACK);
        this.shapeViewer.setForeground(Color.GREEN);

        frame = new Frame("Testing Window");
        frame.addWindowListener(this);
        frame.addKeyListener(this);
        frame.add(this.shapeViewer, BorderLayout.CENTER);
        frame.setBounds(10, 10, 480, 320);
        frame.setVisible(true);

    }

    public static void printShapeInfo(Shape shape) {
        PathIterator iterator = shape.getPathIterator(null);
        if (iterator != null) {
            int i = 1;
            float[] coords = new float[6];
            System.out.printf("# Dumping instance of %s:\n", shape.getClass().getName());
            while (!iterator.isDone()) {
                switch (iterator.currentSegment(coords)) {
                    case PathIterator.SEG_CLOSE:
                        System.out.printf("  %02d. CLOSE...\n", i);
                        break;
                    case PathIterator.SEG_MOVETO:
                        System.out.printf(
                            "  %02d. MOVETO: (%.2f, %.2f)\n", i,
                            coords[0], coords[1]
                        );
                        break;
                    case PathIterator.SEG_LINETO:
                        System.out.printf(
                            "  %02d. LINETO: (%.2f, %.2f)\n", i,
                            coords[0], coords[1]
                        );
                        break;
                    case PathIterator.SEG_CUBICTO:
                        System.out.printf(
                            "  %02d. CUBICTO: (%.2f, %.2f), (%.2f, %.2f), (%.2f, %.2f)\n", i,
                            coords[0], coords[1],
                            coords[2], coords[3],
                            coords[4], coords[5]
                        );
                        break;
                    case PathIterator.SEG_QUADTO:
                        System.out.printf(
                            "  %02d. QUADTO: (%.2f, %.2f), (%.2f, %.2f)\n", i,
                            coords[0], coords[1],
                            coords[2], coords[3]
                        );
                        break;
                    default:
                        System.out.printf("  %02d. UNKNOWN SEGMENT...\n", i);
                        break;
                }
                iterator.next();
                ++i;
            }
        }
    }

    public static void main(String[] args) {

        TestingWindow app;
        Class cls;
        Shape shape;

        if (args.length > 0) {
            try {
                cls = Class.forName(args[0]);
                if (Shape.class.isAssignableFrom(cls)) {
                    shape = (Shape)cls.newInstance();
                    printShapeInfo(shape);
                    app = new TestingWindow();
                    app.start(shape);
                } else {
                    System.out.printf(
                        "%s is not a %s subclass...\n",
                        cls.getName(),
                        Shape.class.getName()
                    );
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found...");
            } catch (Exception e) {
                System.out.println("Unexpected exception...");
            }
        } else {
            System.out.println("Shape argument expected...");
        }

    }

}
