package at.crimsonbit.quizchell.gui.general;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

import layout.TableLayout;

/**
 * A Class which is used to center a Component and still allow it to grow while
 * having a minimum size
 * 
 * @author Alexander Daum
 *
 */
public class AdjustableBorderPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new AdjustableBorderPanel with Component c and Background
	 * bg.<br>
	 * The Component c is Centered within it and has a minimum Size of its
	 * preferred size and grows. The growth is as big in the component as in the
	 * border, meaning, that the extra width/height of the component is as big
	 * as the width of the border on both sides of it. <br>
	 * bg determines the Background Color which could also be set afterwards,
	 * but the possibility to add it in the Constructor can save a few lines,
	 * e.g. you can just add it
	 * like<code> add(new AdjustableBorderPanel(comp, Color.GREY));</code> and
	 * only create comp beforehand. This saves you two lines (Construction of
	 * the AdjustableBorderPanel and setting of the Background).
	 * 
	 * @param c
	 *            The Component put on this AdjustableBorderPanel
	 * @param bg
	 *            The Background Color
	 */
	public AdjustableBorderPanel(Component c, Color bg) {
		Dimension compSize = c.getPreferredSize();
		double f = TableLayout.FILL;
		double[][] size = new double[][] { { f, compSize.getWidth(), f, f, f }, { f, compSize.getHeight(), f, f, f } };
		setLayout(new TableLayout(size));
		add(c, "1,1,3,3");
		setBackground(bg);
	}

}
