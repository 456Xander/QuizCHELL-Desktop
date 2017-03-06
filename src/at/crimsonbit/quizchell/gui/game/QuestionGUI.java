package at.crimsonbit.quizchell.gui.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
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

	private String[] shuffledAnswers;
	private QuestionPane questionPane;
	private Question question;
	private ScheduledThreadPoolExecutor exec;

	private FeedbackPane fbp;

	/**
	 * Constructs a new QuestionGUI with the Question q. <br>
	 * The Question is displayed in a {@link JTextArea} at the top and the
	 * Answers are choosable from 4 {@link MultiLineButton} each centered in an
	 * {@link AdjustableBorderPanel}. Additionally there is a JProgressBar in
	 * the bottom which acts as a Timer.
	 * 
	 * @param q
	 */
	public QuestionGUI() {
		exec = new ScheduledThreadPoolExecutor(0);
		setIconImage(Design.LOGO);
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
		return questionPane.wasCorrect();
	}

	private QuestionPane createQuestionPane() {
		questionPane = new QuestionPane(shuffledAnswers, question, exec);
		return questionPane;
	}

	/**
	 * Generates a new QuestionPanel from the Question q and asks it. It has the
	 * same effect as creating a new {@link QuestionGUI} with q as Question, but
	 * this way you do not need to dispose and recreate the Frame, just the
	 * ContentPane, which is a much faster operation and there are no
	 * closing/opening animations
	 * 
	 * @param q
	 */
	public void askQuestion(Question q) {
		question = q;
		shuffledAnswers = q.getAnswers();
		shuffleArray(shuffledAnswers);
		if (fbp != null)
			remove(fbp);
		add(createQuestionPane());
		revalidate();
		repaint();
	}

	public FeedbackPane getFeedback(boolean correct) {
		fbp = new FeedbackPane(correct, question.getAns1());
		remove(questionPane);
		add(fbp);
		fbp.setVisible(true);
//		revalidate();
		repaint();
		revalidate();
		return fbp;
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

}
