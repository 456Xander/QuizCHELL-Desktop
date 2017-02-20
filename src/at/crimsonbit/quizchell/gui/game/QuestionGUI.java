package at.crimsonbit.quizchell.gui.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import at.alex.multilinebutton.MultiLineButton;
import at.crimsonbit.quizchell.data.Question;
import at.crimsonbit.quizchell.gui.general.AdjustableBorderPanel;
import at.crimsonbit.quizchell.gui.general.Design;
import at.crimsonbit.quizchell.gui.general.LogoPanel;
import layout.TableLayout;

/**
 * The Question is displayed in a {@link JTextArea} at the top and the Answers
 * are selectable from 4 {@link MultiLineButton} each centered in an
 * {@link AdjustableBorderPanel}. Additionally there is a JProgressBar in the
 * bottom which acts as a Timer.
 * 
 * @author Alexander Daum
 *
 */
public class QuestionGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private final int logoHeight = 64;
	private final int timeBarHeight = 32;
	private JPanel contentPane;
	private LogoPanel panelLogo;
	private MultiLineButton btnAns1, btnAns2, btnAns3, btnAns4;
	private JProgressBar time;
	private JTextArea txtQuestion;
	private Question question;
	private String[] shuffledAnswers;
	private ScheduledThreadPoolExecutor exec;
	/**
	 * A String storing the clicked Answer
	 */
	private String clickedAnswer = null;

	/**
	 * Constructs a new QuestionGUI with the Question q. <br>
	 * The Question is displayed in a {@link JTextArea} at the top and the
	 * Answers are choosable from 4 {@link MultiLineButton} each centered in an
	 * {@link AdjustableBorderPanel}. Additionally there is a JProgressBar in
	 * the bottom which acts as a Timer.
	 * 
	 * @param q
	 */
	public QuestionGUI(Question q) {

		this.question = q;
		shuffledAnswers = q.getAnswers();
		shuffleArray(shuffledAnswers);
		setIconImage(Design.LOGO);
		this.exec = new ScheduledThreadPoolExecutor(0);
		this.setContentPane(getpContentPane());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(Design.MIN_SIZE);
		setVisible(true);

	}

	/**
	 * This method pauses the current thread, and waits for the user to select
	 * an answer. It returns when either the user has selected an answer or the
	 * timer ran out. When the user selected an answer this method will return
	 * whether it was the correct answer, if the time ran out, it will always
	 * return false. Latter behavior is achieved by setting the selected answer
	 * to an empty string, which will, hopefully, never be the correct answer.
	 * 
	 * @return whether the selected answer was correct, or false if no answer
	 *         was selected in time
	 */
	public boolean wasCorrect() {
		// A loop should be used according to Javadoc
		while (clickedAnswer == null) {
			// Acquire the lock
			synchronized (this) {
				try {
					/*
					 * Waits until notify is called. notify is called in the
					 * Click Event of all Answer Buttons, or when the timer
					 * ends.
					 */
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		// Close the Frame and return if the answer was true
		this.dispose();
		return question.getAns1().equals(clickedAnswer);
	}

	/**
	 * Creates the contentPane
	 * 
	 * @return
	 */
	private JPanel getpContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(10, 25, 5, 25));
			contentPane.setLayout(createLayout());
			contentPane.setBackground(Design.DARK_GRAY);

			// Add the Components
			contentPane.add(getPanelLogo(), "0,0,2,0");
			contentPane.add(getTxtQuestion(), "0,1,2,1");
			contentPane.add(new AdjustableBorderPanel(getBtnAns1(), contentPane.getBackground()), "0,3");
			contentPane.add(new AdjustableBorderPanel(getBtnAns2(), contentPane.getBackground()), "2,3");
			contentPane.add(new AdjustableBorderPanel(getBtnAns3(), contentPane.getBackground()), "0,4");
			contentPane.add(new AdjustableBorderPanel(getBtnAns4(), contentPane.getBackground()), "2,4");
			contentPane.add(getTime(), "0,5,2,5");
		}
		return contentPane;
	}

	/**
	 * Creates the Layout
	 * 
	 * @return
	 */
	private TableLayout createLayout() {
		double f = TableLayout.FILL;
		double[] rows = new double[] { logoHeight, f, 25, 0.35, 0.35, timeBarHeight };
		double[] cols = new double[] { 0.4, f, 0.4 };
		double[][] size = new double[][] { cols, rows };
		return new TableLayout(size);
	}

	/**
	 * Creates the logo Panel
	 * 
	 * @return
	 */
	private LogoPanel getPanelLogo() {
		if (panelLogo == null) {
			panelLogo = new LogoPanel("src/resources/Logo.png", "Quiz CHEL(L)", Design.LOGO_FONT, Design.LOGO_COLOR);
			panelLogo.setBackground(contentPane.getBackground());
			panelLogo.scale(logoHeight, logoHeight);
		}
		return panelLogo;
	}

	/**
	 * Creates the Button for Answer 1
	 * 
	 * @return
	 */
	private MultiLineButton getBtnAns1() {
		if (btnAns1 == null) {
			btnAns1 = new MultiLineButton(shuffledAnswers[0], Color.LIGHT_GRAY);
			btnAns1.setPreferredSize(new Dimension(172, 65));
			btnAns1.setFont(null);
			btnAns1.addActionListener(new AnswerBtnListener(shuffledAnswers[0]));
		}
		return btnAns1;
	}

	/**
	 * Creates the Button for Answer 2
	 * 
	 * @return
	 */
	private MultiLineButton getBtnAns2() {
		if (btnAns2 == null) {
			btnAns2 = new MultiLineButton(shuffledAnswers[1], Color.LIGHT_GRAY);
			btnAns2.setPreferredSize(new Dimension(172, 65));
			btnAns2.setFont(null);
			btnAns2.addActionListener(new AnswerBtnListener(shuffledAnswers[1]));
		}
		return btnAns2;
	}

	/**
	 * Creates the Button for Answer 3
	 * 
	 * @return
	 */
	private MultiLineButton getBtnAns3() {
		if (btnAns3 == null) {
			btnAns3 = new MultiLineButton(shuffledAnswers[2], Color.LIGHT_GRAY);
			btnAns3.setPreferredSize(new Dimension(172, 65));
			btnAns3.setFont(null);
			btnAns3.addActionListener(new AnswerBtnListener(shuffledAnswers[2]));
		}
		return btnAns3;
	}

	/**
	 * Creates the Button for Answer 4
	 * 
	 * @return
	 */
	private MultiLineButton getBtnAns4() {
		if (btnAns4 == null) {
			btnAns4 = new MultiLineButton(shuffledAnswers[3], Color.LIGHT_GRAY);
			btnAns4.setPreferredSize(new Dimension(172, 65));
			btnAns4.setFont(null);
			btnAns4.addActionListener(new AnswerBtnListener(shuffledAnswers[3]));
		}
		return btnAns4;
	}

	/**
	 * Creates the Question Text Field
	 * 
	 * @return
	 */
	private JTextArea getTxtQuestion() {
		if (txtQuestion == null) {
			txtQuestion = new JTextArea();
			txtQuestion.setLineWrap(true);
			txtQuestion.setWrapStyleWord(true);
			txtQuestion.setText(question.getQues());
			txtQuestion.setBackground(Design.LIGHTER_GRAY);
		}
		return txtQuestion;
	}

	private JProgressBar getTime() {
		if (time == null) {
			time = new JProgressBar(0, question.getTime());
			time.setValue(question.getTime());
			Runnable task = new Runnable() {

				@Override
				public void run() {
					int newValue = time.getValue() - 10;
					if (newValue < 0) {
						end();
					}
					time.setValue(newValue);
					repaint();
				}
			};
			exec.scheduleAtFixedRate(task, 10, 10, TimeUnit.MILLISECONDS);
		}
		return time;
	}

	private void end() {
		clickedAnswer = "";
		synchronized (this) {
			notify();
		}
	}

	/**
	 * Shuffles an Array of any Type T. To do this it randomly switches
	 * Elements. The result is again written in the parameter array
	 * 
	 * @param array
	 *            the array to be shuffled
	 */
	private static <T> void shuffleArray(T[] array) {
		int index;
		T temp;
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}

	/**
	 * An Implementation of ActionListener for the Answer buttons on a
	 * {@link QuestionGUI}. When an ActionEvent is fired, the wasCorrect method
	 * will return if the Answer given in the Constructor
	 * {@link AnswerBtnListener#AnswerBtnListener(String)} was correct.
	 * 
	 * @author Alexander Daum
	 *
	 */
	private class AnswerBtnListener implements ActionListener {
		private String ans;

		/**
		 * Creates a new {@link AnswerBtnListener}. The String ans is the answer
		 * that should be selected when an ActionEvent is fired.
		 * 
		 * @param ans
		 */
		public AnswerBtnListener(String ans) {
			this.ans = ans;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			clickedAnswer = ans;
			synchronized (QuestionGUI.this) {
				QuestionGUI.this.notify();
			}
		}

	}

}
