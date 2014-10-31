package edu.java.gui;

import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageProducer;
import javax.imageio.ImageIO;

public final class ImageViewerComponent extends Component
    implements Runnable {

    /*
     * Static
     */

    private static final Pattern IMG_FILE_PATTERN;

    static {
        IMG_FILE_PATTERN = Pattern.compile(
            "\\.(?:jpe?g|png|gif)$",
            Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE
        );
    }

    private static Pattern getImageFilePattern() {
        return IMG_FILE_PATTERN;
    }

    private static boolean isImageFilePath(String sourceString) {
        return (
            sourceString != null
            ? ((getImageFilePattern()).matcher(sourceString)).matches()
            : false
        );
    }

    /*
     * Instance Members
     */

    private volatile String sourceString;
    private volatile Image displayImage;

    /*
     * Constructors
     */

    public ImageViewerComponent(String sourceString) {
        super();
        this.sourceString = sourceString;
    }

    public ImageViewerComponent() {
        this(null);
    }

    /*
     * Private Methods
     */

    private Image getImageFromFileName(String sourceString) {
        Image image = null;
        try {
            image = ImageIO.read(new File(sourceString));
        } catch (IOException e) {
            System.err.printf(
                "I/O Error While Loading Image: %s\n",
                sourceString
            );
        }
        return image;
    }

    private Image getImageFromClassName(String sourceString) {

        Image img = null;
        Class cls;
        ImageProducer iPrd;
        PaintingObject pObj;

        try {
            cls = Class.forName(sourceString);
            if (PaintingObject.class.isAssignableFrom(cls)) {
                pObj = (PaintingObject)cls.newInstance();
                pObj.sizeHint(this.getWidth(), this.getHeight());
                pObj.init();
                iPrd = pObj.getSource();
                if (iPrd != null) {
                    img = this.createImage(prd);
                }
            } else {
                System.err.printf(
                    "Invalid Class: %s\n",
                    sourceString
                );
            }
        } catch (ClassNotFoundException e) {
            System.err.printf(
                "Class Not Found: %s\n",
                sourceString
            );
        } catch (Exception e) {
            System.err.printf(
                "Unknown Error During Class Load: %s (%s)\n",
                sourceString, e
            );
        }

        return img;

    }

    private Image getDisplayImage() {
        Image img = this.displayImage;
        if (img == null) {
            String src = this.getSource();
            if (src != null) {
                img = ImageViewerComponent.isImageFilePath(src)
                    ? this.getImageFromFileName(src)
                    : this.getImageFromClassName(src);
                this.displayImage = img;
            }
        }
        return img;
    }

    /*
     * Public Methods
     */

    public String getSource() {
        String src = this.sourceString;
        if (src == null) {
            src = System.getProperty("image");
        }
        return src;
    }

    public void setSource(String sourceString) {
        this.sourceString = sourceString;
        this.displayImage = null;
        // @todo check if isShowing call is necessary
        if (this.isShowing()) {
            this.repaint();
        }
    }

    public void drawNoImageSymbol(Graphics2D g) {
        // @todo draw anything here
    }

    @Override
    public void paint(Graphics g) {

        Image img;
        boolean empty = true;
        int cmpW, cmpH, imgW, imgH;

        cmpW = this.getWidth();
        cmpH = this.getHeight();

        if (cmpW > 0 && cmpH > 0) {
            img = this.getDisplayImage();
            if (img != null) {
                imgW = img.getWidth(this);
                imgH = img.getHeight(this);
                if (imgW > 0 && imgH > 0) {
                    g.drawImage(
                        img,
                        (cmpW - imgW) / 2,
                        (cmpH - imgH) / 2,
                        this
                    );
                    empty = false;
                }
            }
            if (empty) {
                this.drawNoImageSymbol((Graphics2D)g);
            }
        }

    }

}
