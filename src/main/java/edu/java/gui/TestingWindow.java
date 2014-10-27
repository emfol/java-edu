package edu.java.gui;

import java.lang.String;
import java.lang.Class;
import java.lang.System;
import java.lang.Exception;
import java.lang.ClassNotFoundException;

import java.awt.Color;
import java.awt.Window;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.geom.PathIterator;
import java.awt.geom.Path2D;

public final class TestingWindow extends WindowAdapter implements KeyListener {

    private PathViewerComponent pathViewer;

    public TestingWindow() {
        super();
        this.pathViewer = null;
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
        if (this.pathViewer != null) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S:
                    this.pathViewer.setViewMode(PathViewerComponent.MODE_STRETCHED);
                    break;
                case KeyEvent.VK_C:
                    this.pathViewer.setViewMode(PathViewerComponent.MODE_CENTRALIZED);
                    break;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        // nope...
    }

    public void start(Path2D path) {

        Frame frame;

        this.pathViewer = new PathViewerComponent(path);
        this.pathViewer.setBackground(Color.BLACK);
        this.pathViewer.setForeground(Color.GREEN);

        frame = new Frame("Testing Window");
        frame.addWindowListener(this);
        frame.addKeyListener(this);
        frame.add(this.pathViewer, BorderLayout.CENTER);
        frame.setBounds(10, 10, 480, 320);
        frame.setVisible(true);

    }

    public static void printPath2DInfo(Path2D path) {
        PathIterator iterator = path.getPathIterator(null);
        if (iterator != null) {
            float[] coords = new float[6];
            System.out.printf("Dumping instance of %s:\n", path.getClass().getName());
            while (!iterator.isDone()) {
                switch (iterator.currentSegment(coords)) {
                    case PathIterator.SEG_CLOSE:
                        System.out.println("+ CLOSE...");
                        break;
                    case PathIterator.SEG_MOVETO:
                        System.out.printf(
                            "+ MOVETO: (%.2f, %.2f)\n",
                            coords[0], coords[1]
                        );
                        break;
                    case PathIterator.SEG_LINETO:
                        System.out.printf(
                            "+ LINETO: (%.2f, %.2f)\n",
                            coords[0], coords[1]
                        );
                        break;
                    case PathIterator.SEG_CUBICTO:
                        System.out.printf(
                            "+ CUBICTO: (%.2f, %.2f), (%.2f, %.2f), (%.2f, %.2f)\n",
                            coords[0], coords[1],
                            coords[2], coords[3],
                            coords[4], coords[5]
                        );
                        break;
                    case PathIterator.SEG_QUADTO:
                        System.out.printf(
                            "+ QUADTO: (%.2f, %.2f), (%.2f, %.2f)\n",
                            coords[0], coords[1],
                            coords[2], coords[3]
                        );
                        break;
                    default:
                        System.out.println("+ UNKNOWN SEGMENT...");
                        break;
                }
                iterator.next();
            }
        }
    }

    public static void main(String[] args) {

        TestingWindow app;
        Class cls;
        Path2D path;

        if (args.length > 0) {
            try {
                cls = Class.forName(args[0]);
                if (Path2D.class.isAssignableFrom(cls)) {
                    path = (Path2D)cls.newInstance();
                    printPath2DInfo(path);
                    app = new TestingWindow();
                    app.start(path);
                } else {
                    System.out.printf(
                        "%s is not a %s subclass...\n",
                        cls.getName(),
                        Path2D.class.getName()
                    );
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found...");
            } catch (Exception e) {
                System.out.println("Unexpected exception...");
            }
        } else {
            System.out.println("Path2D argument expected...");
        }

    }

}
