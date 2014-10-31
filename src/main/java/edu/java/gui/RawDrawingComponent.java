package edu.java.gui;

import java.awt.Component;
import java.awt.Image;

public final class RawDrawingComponent extends Component
    implements Runnable {

    private Image image;

    public RawDrawingComponent() {
        super();
    }

    private Image getComponentImage() {
        if (this.image == null) {
            // build image
        }
        return this.image;
    }

    @Override
    public void paint(Graphics g) {

        Image img;
        int cmpW, cmpH, imgW, imgH;

        cmpW = this.getWidth();
        cmpH = this.getHeight();

        if (cmpW > 0 && cmpH > 0) {
            img = this.getComponentImage();
            if (img != null) {
                imgW = img.getWidth();
                imgH = img.getHeight();
                g.drawImage(
                    img,
                    (cmpW - imgW) / 2,
                    (cmpH - imgH) / 2,
                    this
                );
            }
        }

    }

}
