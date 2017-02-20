package at.crimsonbit.quizchell.gui.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import at.crimsonbit.quizchell.data.GameType;
import at.crimsonbit.quizchell.data.QuestionSubject;
import at.crimsonbit.quizchell.gui.general.AdjustableBorderPanel;
import at.crimsonbit.quizchell.gui.general.Design;
import at.crimsonbit.quizchell.gui.general.LogoPanel;
import at.crimsonbit.quizchell.gui.general.SubjectPane;
import layout.TableLayout;

/**
 * The MainScreen is the part of QuizCHELL, which lets the user choose what
 * he/she wants to do. Therefore there are some buttons to select actions and a
 * {@link SubjectPane} to let the user choose the type of Question. The
 * SubjectPane will later be replaced by a proper configuration in the settings,
 * where multiple categories can be selected.
 * 
 * @author Alexander Daum
 *
 */
public class MainScreen extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel mainPane;
	private SubjectPane subjectPane;
	private JButton single, multi, submit, addFriend;
	private final Dimension btnSize;
	private LogoPanel panelLogo;
	private GameType type = null;

	/**
	 * Creates a new {@link MainScreen}
	 */
	public MainScreen() {
		btnSize = new Dimension(200, 20);
		setTitle("Quiz CHEL(L)");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(getMainPane());
		setMinimumSize(Design.MIN_SIZE);
		setIconImage(Design.LOGO);
		pack();
	}

	private TableLayout createLayout() {
		double f = TableLayout.FILL;
		double[] cols = new double[] { f, f };
		double[] rows = new double[] { Design.LOGO_HEIGHT, f, f, f };
		double[][] size = new double[][] { cols, rows };
		return new TableLayout(size);
	}

	private JPanel getMainPane() {
		if (mainPane == null) {
			mainPane = new JPanel();
			mainPane.setBackground(Design.DARK_GRAY);
			mainPane.setLayout(createLayout());
			mainPane.add(getPanelLogo(), "0,0,1,c");
			mainPane.add(new AdjustableBorderPanel(getSingle(), mainPane.getBackground()), "0,1,0,c");
			mainPane.add(new AdjustableBorderPanel(getMulti(), mainPane.getBackground()), "1,1,1,c");
			mainPane.add(new AdjustableBorderPanel(getAddFriend(), mainPane.getBackground()), "0,2,0,c");
			mainPane.add(new AdjustableBorderPanel(getSubmit(), mainPane.getBackground()), "1,2,1,c");
			mainPane.add(getSubjectPane(), "0,3,1,c");
		}
		return mainPane;
	}

	private JButton getSingle() {
		if (single == null) {
			single = new JButton("<html><body>Zuf&auml;llige Frage");
			single.setBackground(Color.LIGHT_GRAY);
			single.setPreferredSize(btnSize);
			single.addActionListener(new BtnListener(GameType.SINGLEPLAYER));
		}
		return single;
	}

	private JButton getMulti() {
		if (multi == null) {
			multi = new JButton("Mehrspieler");
			multi.setBackground(Color.LIGHT_GRAY);
			multi.setPreferredSize(btnSize);
			multi.addActionListener(new BtnListener(GameType.MULTIPLAYER));
		}
		return multi;
	}

	private JButton getSubmit() {
		if (submit == null) {
			submit = new JButton("Frage einsenden");
			submit.setBackground(Color.LIGHT_GRAY);
			submit.setPreferredSize(btnSize);
			submit.addActionListener(new BtnListener(GameType.SUBMIT));
		}
		return submit;
	}

	private JButton getAddFriend() {
		if (addFriend == null) {
			addFriend = new JButton("<html><body>Freund hinzuf&uuml;gen");
			addFriend.setBackground(Color.LIGHT_GRAY);
			addFriend.setPreferredSize(btnSize);
			addFriend.addActionListener(new BtnListener(GameType.ADDFRIEND));
		}
		return addFriend;
	}

	private LogoPanel getPanelLogo() {
		if (panelLogo == null) {
			panelLogo = new LogoPanel("src/resources/Logo.png", "Quiz CHEL(L)", Design.LOGO_FONT, Design.LOGO_COLOR);
			panelLogo.scale(Design.LOGO_HEIGHT, Design.LOGO_HEIGHT);
			panelLogo.setBackground(mainPane.getBackground());
		}
		return panelLogo;
	}

	private JPanel getSubjectPane() {
		if (subjectPane == null) {
			subjectPane = new SubjectPane();
			subjectPane.setBackground(mainPane.getBackground());
		}
		return subjectPane;
	}

	/**
	 * GetSelectedGame is the suggested Method to get information on which
	 * selection the user made. When called, the Method waits until the user
	 * clicks one of the buttons and then returns. While the program is waiting,
	 * the thread which called this method is paused and will continue when this
	 * method returns. When the user has already clicked a button when this
	 * method is called, it returns the selection instantly. <br>
	 * It is suggested, that the using program hides or disposes this MainScreen
	 * after getSelectedGame has returned, because otherwise the user could
	 * wonder why this is still open.
	 * 
	 * @return
	 */
	public GameType getSelectedGame() {
		while (type == null) {
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return type;
	}

	/**
	 * An Implementation of ActionListener for the Buttons which should return a
	 * GameType to {@linkplain MainScreen#getSelectedGame()}. Every button
	 * adding this listener should represent the selection of the GameMode which
	 * it provides in the Constructor {@link BtnListener#BtnListener(GameType)}
	 * 
	 * @author Alexander Daum
	 *
	 */
	private class BtnListener implements ActionListener {
		private GameType ty;

		/**
		 * Creates a new {@link BtnListener} which will return the GameType t.
		 * Any button which adds this Listener should represent the choice of
		 * GameMode t
		 * 
		 * @param t
		 *            The GameType that will be returned
		 */
		public BtnListener(GameType t) {
			ty = t;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			MainScreen.this.type = ty;
			synchronized (MainScreen.this) {
				MainScreen.this.notify();
			}
		}

	}

	/**
	 * Returns an Array of {@link QuestionSubject}, that the user has chosen to
	 * play. This method sould always be called <b>after</b>
	 * {@link MainScreen#getSelectedSubjects()}, because the user can change the
	 * selected subjects until this frame is either hidden or disposed. Because
	 * of this the method where a {@link MainScreen} is used, it should hide or
	 * dispose after getSelectedSubjects is completed
	 * 
	 * @return An Array with the selected QuestionSubjects
	 */
	public QuestionSubject[] getSelectedSubjects() {
		return new QuestionSubject[] { subjectPane.getSubject() };
	}

}
