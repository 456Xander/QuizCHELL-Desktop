package at.crimsonbit.quizchell.gui.general;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import layout.TableLayout;

/**
 * A {@link JPanel} which holdes a Logo and a text next to it.
 * 
 * @author Alexander Daum
 *
 */
public class LogoPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JLabel label;

    /**
     * Creates a new LogoPanel with specified Icon and Text
     * 
     * @param ico
     *            The icon
     * @param text
     *            The text
     */
    public LogoPanel(Icon ico, String text) {

	double f = TableLayout.FILL;
	double[][] size = new double[][] { { 25, f, 25 }, { f } };
	this.setLayout(new TableLayout(size));
	label = new JLabel(text, ico, JLabel.CENTER) {
	    @Override
	    protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 250);
		super.paintComponent(g2);
	    }
	};
	this.add(label, "1,0");
    }

    /**
     * Creates a new {@link LogoPanel} with specified Icon, Text, Font and
     * TextColor
     * 
     * @param ico
     *            The Icon
     * @param text
     *            The Text
     * @param font
     *            The Font
     * @param textColor
     *            The Color of the text
     */
    public LogoPanel(Icon ico, String text, Font font, Color textColor) {
	this(ico, text);
	label.setFont(font);
	label.setForeground(textColor);
    }

    /**
     * 
     * @param img
     * @param text
     */
    public LogoPanel(Image img, String text) {
	this(new ImageIcon(img), text);
    }

    public LogoPanel(URL url, String text) {
	this(new ImageIcon(url), text);
    }

    public LogoPanel(String path, String text) {
	this(imgFromPath(path), text);
    }

    public LogoPanel(Image img, String text, Font font, Color textColor) {
	this(new ImageIcon(img), text, font, textColor);
    }

    public LogoPanel(String path, String text, Font font, Color textColor) {
	this(imgFromPath(path), text, font, textColor);
    }

    /**
     * Creates an Image from an path String. If the Project is run in eclipse it
     * uses the File, if it is packed in a jar it uses
     * ClassLoader.getSystemResource() to get the Image from within the jar
     * 
     * @param path
     *            The relative path to the image
     * @return
     */
    public static BufferedImage imgFromPath(String path) {
	BufferedImage img = null;
	try {
	    URL url = ClassLoader.getSystemResource(path);
	    if (url == null) {
		File imgFile = new File(path);
		img = ImageIO.read(imgFile);
	    } else {
		img = ImageIO.read(url);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return img;
    }

    public void scale(int width, int height) {
	BufferedImage img = new BufferedImage(label.getIcon().getIconWidth(), label.getIcon().getIconHeight(),
		BufferedImage.TYPE_4BYTE_ABGR);
	label.getIcon().paintIcon(null, img.getGraphics(), 0, 0);
	BufferedImage scaled = Design.scaleImage(img, height, width);
	label.setIcon(new ImageIcon(scaled));
    }

}
