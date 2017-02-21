package at.crimsonbit.quizchell.gui.general;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import at.crimsonbit.quizchell.gui.game.Submit;

/**
 * Stores Colors, Fonts, etc. which are used for Design of the QuizCHEL(L) Game
 * 
 * @author Alexander Daum
 *
 */
public class Design {
	/**
	 * A really dark gray color, used for Background in all QuizCHELL Classes
	 */
	public static final Color DARK_GRAY = new Color(60, 60, 60);
	/**
	 * The Background of the wrong answers in {@link Submit}
	 */
	public static final Color PALE_RED = new Color(245, 200, 200);
	/**
	 * The Background of the correct answer in {@link Submit}
	 */
	public static final Color PALE_GREEN = new Color(200, 245, 200);
	/**
	 * A pale blue used in the {@link SubjectPane}
	 */
	public static final Color PALE_BLUE = new Color(200, 200, 245);
	// Currently not used, but looks nice
	public static final Color PALE_ORANGE = new Color(245, 210, 200);
	/**
	 * A very light gray used as Background for some Buttons
	 */
	public static final Color LIGHTER_GRAY = new Color(224, 224, 224);
	/**
	 * The Crimson Red in the LOGO (Hex: #990018)
	 */
	public static final Color LOGO_COLOR = new Color(160, 0, 24);
	/**
	 * The Font for the {@link LogoPanel}
	 */
	public static final Font LOGO_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 40);
	/**
	 * The Minimum Size of any QuizCHELL Frames
	 */
	public static final Dimension MIN_SIZE = new Dimension(480, 360);
	/**
	 * The height of the Logo in pixels
	 */
	public static final int LOGO_HEIGHT = 64;
	/**
	 * The Logo Image, which will be loaded in a static block
	 */
	public static final BufferedImage LOGO;
	static {
		BufferedImage tmp = null;
		try {
			URL in = Design.class.getResource("/resources/Logo.png");
			if (in == null) {
				in = new File("src/resources/Logo.png").toURI().toURL();
			}
			tmp = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LOGO = scaleImage(tmp, LOGO_HEIGHT, LOGO_HEIGHT);
	}
	
	/**
	 * Scales an Image to height and width. It uses AffineTransforms with
	 * Bilinear filtering.
	 * 
	 * @param img
	 *            The Image which should be scaled
	 * @param height
	 *            The new height
	 * @param width
	 *            the new width
	 * @return A new BufferedImage which looks like the original, but is scaled
	 */
	public static BufferedImage scaleImage(BufferedImage img, int height, int width) {
		if (img == null)
			return null;
		AffineTransform at = new AffineTransform();
		at.scale((double) width / img.getWidth(), (double) height / img.getHeight());
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		
		BufferedImage tmp = new BufferedImage(width, height, img.getType());
		op.filter(img, tmp);
		return tmp;
	}
}
