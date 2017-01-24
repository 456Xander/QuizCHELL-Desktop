package at.crimsonbit.quizchell.gui.general;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Stores Colors, Fonts, etc. which are used for Design of the QuizCHEL(L) Game
 * 
 * @author Alexander Daum
 *
 */
public class Design {
    public static final Color DARK_GRAY = new Color(60, 60, 60);
    public static final Color DARK_GRAY_BLUE = new Color(80, 80, 80);
    public static final Color PALE_RED = new Color(245, 200, 200);
    public static final Color PALE_GREEN = new Color(200, 245, 200);
    public static final Color PALE_BLUE = new Color(200, 200, 245);
    // Currently not used, but looks nice
    public static final Color PALE_ORANGE = new Color(245, 210, 200);
    public static final Color LIGHTER_GRAY = new Color(224, 224, 224);
    public static final Color LOGO_COLOR = new Color(160, 0, 24);

    public static final Font LOGO_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 40);

    public static final Dimension MIN_SIZE = new Dimension(480, 360);

    public static final int LOGO_HEIGHT = 64;

    public static final BufferedImage LOGO;
    static {
	BufferedImage tmp = null;
	try {
	    tmp = ImageIO.read(new File("src/resources/Logo.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	LOGO = scaleImage(tmp, 64, 64);
    }

    public static BufferedImage scaleImage(BufferedImage img, int height, int width) {
	if (img == null)
	    return null;
	AffineTransform at = new AffineTransform();
	at.scale((double) width / img.getWidth(), (double) height / img.getHeight());
	AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);

	BufferedImage tmp = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
	op.filter(img, tmp);
	return tmp;
    }
}
