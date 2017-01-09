package at.neonartworks.quizchell.gui;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import layout.TableLayout;

public class TMP_Submit extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel submitPane;
	private GhostTextArea question, ans1, ans2, ans3, ans4;

	public TMP_Submit() {
		this.setContentPane(getSubmitPane());
		Dimension size = new Dimension(480, 360);
		this.setSize(size);
		this.setMinimumSize(size);
	}

	private JPanel getSubmitPane() {
		if (submitPane == null) {
			submitPane = new JPanel();
			submitPane.setLayout(createLayout());
			submitPane.add(getQuestion(), "0,0, 2,0");
			submitPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		}
		return submitPane;
	}

	private LayoutManager createLayout() {
		final double f = TableLayout.FILL;
		double rows[] = new double[] { f, f, f, f, f };
		double cols[] = new double[] { f, f, f };
		double size[][] = new double[][] { cols, rows };
		TableLayout layout = new TableLayout(size);
		return layout;
	}

	public GhostTextArea getQuestion() {
		if (question == null) {
			question = new GhostTextArea("Frage");
			question.setLineWrap(true);
			question.setWrapStyleWord(true);
			question.setBorder(new EmptyBorder(25, 25, 25, 25));
		}
		return question;
	}

	public GhostTextArea getAns1() {
		if (ans1 == null) {
			ans1 = new GhostTextArea("Frage");
			ans1.setLineWrap(true);
			ans1.setWrapStyleWord(true);
			ans1.setBorder(new EmptyBorder(25, 25, 25, 25));
		}
		return ans1;
	}

	public GhostTextArea getAns2() {
		if (ans2 == null) {
			ans2 = new GhostTextArea("Frage");
			ans2.setLineWrap(true);
			ans2.setWrapStyleWord(true);
			ans2.setBorder(new EmptyBorder(25, 25, 25, 25));
		}
		return ans2;
	}

	public GhostTextArea getAns3() {
		if (ans3 == null) {
			ans3 = new GhostTextArea("Frage");
			ans3.setLineWrap(true);
			ans3.setWrapStyleWord(true);
			ans3.setBorder(new EmptyBorder(25, 25, 25, 25));
		}
		return ans3;
	}

	public GhostTextArea getAns4() {
		if (ans4 == null) {
			ans4 = new GhostTextArea("Frage");
			ans4.setLineWrap(true);
			ans4.setWrapStyleWord(true);
			ans4.setBorder(new EmptyBorder(25, 25, 25, 25));
		}
		return ans4;
	}
}
