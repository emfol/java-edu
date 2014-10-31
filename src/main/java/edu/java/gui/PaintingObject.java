package edu.java.gui;

import java.awt.image.ImageProducer;

public abstract class PaintingObject extends Object {

    private volatile int widthHint;
    private volatile int heightHint;

    public PaintingObject() {
        super();
    }

    public void sizeHint(int width, int height) {
        this.widthHint = width;
        this.heightHint = height;
    }

    public int getWidthHint() {
        return this.widthHint;
    }

    public int getHeightHint() {
        return this.heightHint;
    }

    public abstract void init();
    public abstract ImageProducer getSource();

}
