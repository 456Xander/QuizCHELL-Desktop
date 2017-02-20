package at.crimsonbit.quizchell.gui.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import at.crimsonbit.quizchell.gui.general.AdjustableBorderPanel;
import at.crimsonbit.quizchell.gui.general.Design;
import at.crimsonbit.quizchell.gui.general.LogoPanel;
import layout.TableLayout;

/**
 * QuizError is a class used for error reporting to the end user. It opens a GUI
 * which looks like the other QuizCHELL GUIs. When this Class is used, it is
 * suggested, that {@link QuizError#waitFor()} is called, in order to give the
 * user time to read the error. This Method will pause the current thread until
 * the user clicks the OK Button.
 * 
 * @author Alexander Daum
 *
 */
public class QuizError extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel pane;
	private LogoPanel logo;
	private JTextArea errorArea;
	private JLabel labelError;
	private JButton btnOK;
	private boolean clicked = false;

	/**
	 * Creates a new {@link QuizError} with the text error
	 * 
	 * @param error
	 */
	public QuizError(String error) {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(getPane());
		setMinimumSize(Design.MIN_SIZE);
		getErrorArea().setText(error);
	}

	private JPanel getPane() {
		if (pane == null) {
			pane = new JPanel();
			pane.setBackground(Design.DARK_GRAY);
			pane.setLayout(createLayout());
			pane.add(getLogo(), "0,0,c,c");
			pane.add(getLabelError(), "0,1,c,c");
			pane.add(new AdjustableBorderPanel(getErrorArea(), pane.getBackground()), "0,2");
			pane.add(new AdjustableBorderPanel(getBtnOK(), pane.getBackground()), "0,3");
		}
		return pane;
	}

	private LayoutManager createLayout() {
		double f = TableLayout.FILL;
		double[] cols = new double[] { f };
		double[] rows = new double[] { Design.LOGO_HEIGHT, 50, f, f };
		double[][] size = new double[][] { cols, rows };
		return new TableLayout(size);
	}

	private LogoPanel getLogo() {
		if (logo == null) {
			logo = new LogoPanel("src/resources/Logo.png", "Quiz CHEL(L)", Design.LOGO_FONT, Design.LOGO_COLOR);
			logo.scale(Design.LOGO_HEIGHT, Design.LOGO_HEIGHT);
			logo.setBackground(pane.getBackground());
		}
		return logo;
	}

	private JTextArea getErrorArea() {
		if (errorArea == null) {
			errorArea = new JTextArea();
			errorArea.setBorder(new EmptyBorder(5, 5, 5, 5));
			errorArea.setEditable(false);
			errorArea.setLineWrap(true);
			errorArea.setWrapStyleWord(true);
			errorArea.setBackground(Color.GRAY);
		}
		return errorArea;
	}

	private JLabel getLabelError() {
		if (labelError == null) {
			labelError = new JLabel("Thats an Error");
			labelError.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
			labelError.setForeground(Design.LOGO_COLOR);
		}
		return labelError;
	}

	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			btnOK.setBackground(Design.LIGHTER_GRAY);
			btnOK.addActionListener(e -> onClick());
		}
		return btnOK;
	}

	/**
	 * Notifies the waiting thread, so that this error can be closed.
	 * Additionally all future wait requests will return immediately
	 */
	private void onClick() {
		synchronized (this) {
			clicked = true;
			this.notify();
		}
	}

	/**
	 * Stops the current Thread until the user presses the OK Button. If the OK
	 * Button was already clicked, this method will return immediately until
	 * reactivate() is issued
	 */
	public void waitFor() {
		synchronized (this) {
			while (!clicked) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Reactivates the wait method, such that a call of waitFor will wait, even
	 * if the OK button was pressed previously, at the next click it will be
	 * deactivated again
	 * 
	 * @return
	 */
	public boolean reactivate() {
		boolean tmp = clicked;
		clicked = true;
		return !tmp;
	}
}
