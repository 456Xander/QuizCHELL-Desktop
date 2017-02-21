package at.crimsonbit.quizchell.gui.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

public class QuestionPane extends JPanel {
	
	private final int logoHeight = 64;
	private final int timeBarHeight = 32;
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
		return question.getAns1().equals(clickedAnswer);
	}
	
	/**
	 * Creates the contentPane
	 * 
	 * @return
	 */
	public QuestionPane(String[] answers, Question q, ScheduledThreadPoolExecutor exec) {
		this.shuffledAnswers = answers;
		this.question = q;
		this.exec = exec;
		this.setBorder(new EmptyBorder(10, 25, 5, 25));
		this.setLayout(createLayout());
		this.setBackground(Design.DARK_GRAY);
		
		// Add the Components
		this.add(getPanelLogo(), "0,0,2,0");
		this.add(getTxtQuestion(), "0,1,2,1");
		this.add(new AdjustableBorderPanel(getBtnAns1(), this.getBackground()), "0,3");
		this.add(new AdjustableBorderPanel(getBtnAns2(), this.getBackground()), "2,3");
		this.add(new AdjustableBorderPanel(getBtnAns3(), this.getBackground()), "0,4");
		this.add(new AdjustableBorderPanel(getBtnAns4(), this.getBackground()), "2,4");
		this.add(getTime(), "0,5,2,5");
		
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
			panelLogo.setBackground(this.getBackground());
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
			synchronized (QuestionPane.this) {
				QuestionPane.this.notify();
			}
		}
		
	}
}
