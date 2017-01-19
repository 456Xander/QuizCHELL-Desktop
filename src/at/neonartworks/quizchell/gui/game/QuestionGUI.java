package at.neonartworks.quizchell.gui.game;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import at.neonartworks.quizchell.data.Question;
import at.neonartworks.quizchell.gui.general.Design;
import at.neonartworks.quizchell.gui.general.LogoPanel;
import at.neonartworks.quizchell.gui.general.MultiLineButton;
import layout.TableLayout;

public class QuestionGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int logoHeight = 64;
	private final int timeBarHeight = 32;
	private JPanel contentPane;
	private LogoPanel panelLogo;
	private JButton btnAns1, btnAns2, btnAns3, btnAns4;
	private JProgressBar time;
	private JTextArea txtQuestion;
	private Question question;
	private String[] shuffledAnswers;

	public QuestionGUI(Question q) {

		this.question = q;
		shuffledAnswers = q.getAnswers();
		shuffleArray(shuffledAnswers);

		this.setContentPane(getpContentPane());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(480, 360));
	}

	private JPanel getpContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(10, 25, 5, 25));
			contentPane.setLayout(createLayout());
			contentPane.setBackground(Design.DARK_GRAY);

			contentPane.add(getPanelLogo(), "0,0,2,0");
			contentPane.add(getTxtQuestion(), "0,1,2,1");
			contentPane.add(getBtnAns1(), "0,3");
			contentPane.add(getBtnAns2(), "2,3");
			contentPane.add(getBtnAns3(), "0,4");
			contentPane.add(getBtnAns4(), "2,4");
		}
		return contentPane;
	}

	private TableLayout createLayout() {
		double f = TableLayout.FILL;
		double[] rows = new double[] { logoHeight, f, 25, 0.35, 0.35, timeBarHeight };
		double[] cols = new double[] { 0.4, f, 0.4 };
		double[][] size = new double[][] { cols, rows };
		return new TableLayout(size);
	}

	private LogoPanel getPanelLogo() {
		if (panelLogo == null) {
			panelLogo = new LogoPanel("src/resources/Logo.png", "Quiz CHEL(L)", Design.LOGO_FONT, Design.LOGO_GREEN);
			panelLogo.setBackground(contentPane.getBackground());
			panelLogo.scale(logoHeight, logoHeight);
		}
		return panelLogo;
	}

	private JButton getBtnAns1() {
		if (btnAns1 == null) {
			btnAns1 = new MultiLineButton(shuffledAnswers[0]);
			btnAns1.setPreferredSize(new Dimension(152, 50));
			btnAns1.setBorder(new LineBorder(contentPane.getBackground(), 10));
			btnAns1.setBackground(Color.LIGHT_GRAY);
			btnAns1.setFont(null);
		}
		return btnAns1;
	}

	private JButton getBtnAns2() {
		if (btnAns2 == null) {
			btnAns2 = new MultiLineButton(shuffledAnswers[1]);
			btnAns2.setPreferredSize(new Dimension(152, 50));
			btnAns2.setBorder(new LineBorder(contentPane.getBackground(), 10));
			btnAns2.setBackground(Color.LIGHT_GRAY);
			btnAns2.setFont(null);
		}
		return btnAns2;
	}

	private JButton getBtnAns3() {
		if (btnAns3 == null) {
			btnAns3 = new MultiLineButton(shuffledAnswers[2]);
			btnAns3.setPreferredSize(new Dimension(152, 50));
			btnAns3.setBorder(new LineBorder(contentPane.getBackground(), 10));
			btnAns3.setBackground(Color.LIGHT_GRAY);
			btnAns3.setFont(null);
		}
		return btnAns3;
	}

	private JButton getBtnAns4() {
		if (btnAns4 == null) {
			btnAns4 = new MultiLineButton(shuffledAnswers[3]);
			btnAns4.setPreferredSize(new Dimension(152, 50));
			btnAns4.setBorder(new LineBorder(contentPane.getBackground(), 10));
			btnAns4.setBackground(Color.LIGHT_GRAY);
			btnAns4.setFont(null);
		}
		return btnAns4;
	}

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

	private static void shuffleArray(String[] array) {
		int index;
		String temp;
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}

}
