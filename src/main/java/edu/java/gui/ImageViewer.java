package edu.java.gui;

import java.awt.Component;
import java.awt.Window;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public final class ImageViewer extends WindowAdapter {

    private Frame frame;
    private ImageViewerComponent component;

    public ImageViewer() {
        super();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Window w = e.getWindow();
        w.setVisible(false);
        w.dispose();
    }

    public ImageViewerComponent getImageViewerComponent() {
        if (this.component == null) {
            this.component = new ImageViewerComponent();
        }
        return this.component;
    }

    public Frame getFrame() {

        Component cmp;

        if (this.frame == null) {
            cmp = this.getImageViewerComponent();
            if (cmp != null) {
                this.frame = new Frame("Window For Image Viewer Component");
                this.frame.addWindowListener(this);
                this.frame.add(cmp, BorderLayout.CENTER);
            }
        }

        return this.frame;

    }

    public static void main(String[] args) {

        ImageViewer viewer = new ImageViewer();

        Frame frm = viewer.getFrame();
        frm.setBounds(10, 10, 480, 320);
        frm.setVisible(true);

    }

}
