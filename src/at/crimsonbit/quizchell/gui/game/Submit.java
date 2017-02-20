package at.crimsonbit.quizchell.gui.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import at.crimsonbit.quizchell.data.Question;
import at.crimsonbit.quizchell.data.QuestionSubject;
import at.crimsonbit.quizchell.gui.general.AdjustableBorderPanel;
import at.crimsonbit.quizchell.gui.general.Design;
import at.crimsonbit.quizchell.gui.general.GhostTextArea;
import at.crimsonbit.quizchell.gui.general.LogoPanel;
import at.crimsonbit.quizchell.gui.general.SubjectPane;
import layout.TableLayout;

/**
 * The GUI for submitting, thus adding, a new Question. It consists of 5
 * TextAreas, one for the question Text and 4 for the answers. All those
 * TextAreas are {@link GhostTextArea}, which are limited to 64 characters for
 * answers and 1024 characters for the question. The GhostTextAreas also have a
 * ghost text. TheBackground of the answers shows if it is the correct one, or a
 * false answer. A green Background indicates the correct answer, whilst a red
 * background indicates a wrong one. <br>
 * To use this class, the method {@link Submit#getQuestion()} has to be called,
 * look into its documentation for further details. <br>
 * There are also some features for convenience of the user, like e.g. the
 * enter/return key jumps to the next field which is not filled, and, if all
 * textAreas contain text, it submits the question.
 * 
 * @author Alexander Daum
 *
 */
public class Submit extends JFrame {

	private static final int ANS_LENGHT = 64;

	private static final long serialVersionUID = 1L;

	private JPanel submitPane, panelBtnSubmit;
	private SubjectPane subjectPane;
	private LogoPanel panelLogo;
	private GhostTextArea question, ans1, ans2, ans3, ans4;
	private Question dataQuestion = null;
	private JButton btnSubmit;
	private Dimension ansSize;
	/**
	 * An Array of all textAreas
	 */
	private GhostTextArea[] textArs;
	private final int logoHeight = ANS_LENGHT;

	public Submit() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Frage Einschicken");
		this.setIconImage(Design.LOGO);
		ansSize = new Dimension(150, 50);
		this.setContentPane(getSubmitPane());
		textArs = new GhostTextArea[] { question, ans1, ans2, ans3, ans4 };
		Dimension size = Design.MIN_SIZE;
		this.setSize(size);
		this.setMinimumSize(size);
	}

	private JPanel getSubmitPane() {
		if (submitPane == null) {
			submitPane = new JPanel();
			submitPane.setBackground(Design.DARK_GRAY);
			submitPane.setLayout(createLayout());
			submitPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			submitPane.add(getPanelLogo(), "0,0, 1,0");
			submitPane.add(new AdjustableBorderPanel(getTxtQuestion(), submitPane.getBackground()), "0,1, 1,1");
			submitPane.add(new AdjustableBorderPanel(getAns1(), submitPane.getBackground()), "0,2");
			submitPane.add(new AdjustableBorderPanel(getAns2(), submitPane.getBackground()), "1,2");
			submitPane.add(new AdjustableBorderPanel(getAns3(), submitPane.getBackground()), "0,3");
			submitPane.add(new AdjustableBorderPanel(getAns4(), submitPane.getBackground()), "1,3");
			submitPane.add(getPanelBtnSubmit(), "1,4,1,4");
			submitPane.add(getSubjectPane(), "0,4");
		}
		return submitPane;
	}

	private LayoutManager createLayout() {
		final double f = TableLayout.FILL;
		double rows[] = new double[] { logoHeight, f, f, f, 60 };
		double cols[] = new double[] { f, f };
		double size[][] = new double[][] { cols, rows };
		TableLayout layout = new TableLayout(size);
		return layout;
	}

	private GhostTextArea getTxtQuestion() {
		if (question == null) {
			question = new GhostTextArea("Frage", 1024);
			question.setBackground(Design.LIGHTER_GRAY);
			question.setLineWrap(true);
			question.setPreferredSize(new Dimension(380, 50));
			question.setWrapStyleWord(true);
			question.addKeyListener(new JumpToNextEmpty(0));
		}
		return question;
	}

	private GhostTextArea getAns1() {
		if (ans1 == null) {
			ans1 = new GhostTextArea("Richtige Antwort", ANS_LENGHT);
			ans1.setLineWrap(true);
			ans1.setWrapStyleWord(true);
			ans1.setBackground(Design.PALE_GREEN);
			ans1.addKeyListener(new JumpToNextEmpty(1));
			ans1.setPreferredSize(ansSize);
		}
		return ans1;
	}

	private GhostTextArea getAns2() {
		if (ans2 == null) {
			ans2 = new GhostTextArea("Antwort 2", ANS_LENGHT);
			ans2.setLineWrap(true);
			ans2.setWrapStyleWord(true);
			ans2.setBackground(Design.PALE_RED);
			ans2.addKeyListener(new JumpToNextEmpty(2));
			ans2.setPreferredSize(ansSize);
		}
		return ans2;
	}

	private GhostTextArea getAns3() {
		if (ans3 == null) {
			ans3 = new GhostTextArea("Antwort 3", ANS_LENGHT);
			ans3.setLineWrap(true);
			ans3.setWrapStyleWord(true);
			ans3.setBackground(Design.PALE_RED);
			ans3.addKeyListener(new JumpToNextEmpty(3));
			ans3.setPreferredSize(ansSize);
		}
		return ans3;
	}

	private GhostTextArea getAns4() {
		if (ans4 == null) {
			ans4 = new GhostTextArea("Antwort 4", ANS_LENGHT);
			ans4.setLineWrap(true);
			ans4.setWrapStyleWord(true);
			ans4.setBackground(Design.PALE_RED);
			ans4.addKeyListener(new JumpToNextEmpty(4));
			ans4.setPreferredSize(ansSize);
		}
		return ans4;
	}

	private JButton getBtnSubmit() {
		if (btnSubmit == null) {
			btnSubmit = new JButton("Fertig");
			btnSubmit.setPreferredSize(new Dimension(152, 50));
			btnSubmit.setForeground(Color.BLACK);
			btnSubmit.setBackground(Color.LIGHT_GRAY);
			btnSubmit.addActionListener(new SubmitListener());
		}
		return btnSubmit;
	}

	private JPanel getPanelBtnSubmit() {
		if (panelBtnSubmit == null) {
			panelBtnSubmit = new JPanel();
			panelBtnSubmit.setBackground(submitPane.getBackground());

			double f = TableLayout.FILL;
			double[][] size = new double[][] { { f }, { f } };
			panelBtnSubmit.setLayout(new TableLayout(size));
			panelBtnSubmit.add(getBtnSubmit(), "0,0,c,c");
		}
		return panelBtnSubmit;
	}

	private LogoPanel getPanelLogo() {
		if (panelLogo == null) {
			panelLogo = new LogoPanel(Design.LOGO, "Quiz CHEL(L)", Design.LOGO_FONT, Design.LOGO_COLOR);
			panelLogo.scale(logoHeight, logoHeight);
			panelLogo.setBackground(submitPane.getBackground());
		}
		return panelLogo;
	}

	private JPanel getSubjectPane() {
		if (subjectPane == null) {
			subjectPane = new SubjectPane();
			subjectPane.setBackground(submitPane.getBackground());

		}
		return subjectPane;
	}

	/**
	 * In any implementation this method has to be called, to get the question,
	 * that the user wants to submit. It pauses the current thread until the
	 * user pressed the submit button and filled some text into all TextAreas.
	 * As soon as the user submits his/her question, this method returns it.
	 * 
	 * @return The question entered by the user
	 */
	public Question getQuestion() {
		while (dataQuestion == null) {
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return dataQuestion;
	}

	/**
	 * A method to submit the question entered by the user. It notifies the
	 * object, on which {@link Submit#getQuestion()} is waiting, and makes it
	 * return the question
	 */
	private void doSubmit() {
		try {
			QuestionSubject subject = subjectPane.getSubject();
			dataQuestion = new Question(question.getText(), ans1.getText(), ans2.getText(), ans3.getText(),
					ans4.getText(), subject);
			synchronized (this) {
				this.notify();
			}
		} catch (NullPointerException ex) {
			dataQuestion = null;
		}

	}

	/**
	 * An Implementation of ActionListener, which checks if all TextAreas are
	 * filled, and if they are calls {@link Submit#doSubmit()}. If any textArea
	 * is still empty, it sets the color of its Foreground Color to Red
	 * 
	 * @author Alexander Daum
	 *
	 */
	private class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean reallySubmit = true;
			for (GhostTextArea ar : textArs) {
				if (ar.isGhost()) {
					ar.setForeground(Color.RED);
					reallySubmit = false;
				}
			}
			if (reallySubmit)
				doSubmit();
		}

	}

	/**
	 * An Implementation of KeyListener, which on click of the enter/return key
	 * jumps to the next empty textArea in {@link Submit#textArs}. If all
	 * textAreas contain non Ghost text, the listener submits the question
	 * 
	 * @author Alexander Daum
	 *
	 */
	private class JumpToNextEmpty implements KeyListener {
		private int instanceIndex;

		public JumpToNextEmpty(int index) {
			instanceIndex = index;
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				for (int i = instanceIndex + 1; i < textArs.length; i++) {
					if (textArs[i].isGhost() || textArs[i].getText().equals("")) {
						textArs[i].grabFocus();
						return;
					}
				}
				for (int i = 0; i <= instanceIndex; i++) {
					if (textArs[i].isGhost() || textArs[i].getText().equals("")) {
						textArs[i].grabFocus();
						return;
					}
				}
				doSubmit();
			}
		}

	}

}