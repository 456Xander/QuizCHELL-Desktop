package at.neonartworks.quizchell.gui.general;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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

public class LogoPanel extends JPanel {
	private JLabel label;

	public LogoPanel(Icon ico, String text) {

		double f = TableLayout.FILL;
		double[][] size = new double[][] { { 25, f, 25 }, { f } };
		this.setLayout(new TableLayout(size));
		label = new JLabel(text, ico, JLabel.CENTER);
		this.add(label, "1,0");
	}

	public LogoPanel(Icon ico, String text, Font font, Color textColor) {
		this(ico, text);
		label.setFont(font);
		label.setForeground(textColor);
	}

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
		BufferedImage scaled = scaleImage(img, height, width);
		label.setIcon(new ImageIcon(scaled));
	}

	private BufferedImage scaleImage(BufferedImage img, int height, int width) {
		AffineTransform at = new AffineTransform();
		at.scale((double) width / img.getWidth(), (double) height / img.getHeight());
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);

		BufferedImage tmp = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		op.filter(img, tmp);
		return tmp;
	}

}
