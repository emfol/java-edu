package edu.java.gui;

import java.awt.Component;
import java.awt.Window;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public final class RawDrawingComponentWindow extends WindowAdapter {

    private Frame frame;

    public RawDrawingComponentWindow() {
        super();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Window w = e.getWindow();
        w.setVisible(false);
        w.dispose();
    }

    public Frame getFrame() {
        if (this.frame == null) {
            this.frame = new Frame("Window For Raw Drawing Component");
            this.frame.addWindowListener(this);
        }
        return this.frame;
    }

    public static void main(String[] args) {
        RawDrawingComponentWindow wcmp = new RawDrawingComponentWindow();

        Frame frm = wcmp.getFrame();
        frm.setBounds(10, 10, 480, 320);
        frm.setVisible(true);
    }

}
