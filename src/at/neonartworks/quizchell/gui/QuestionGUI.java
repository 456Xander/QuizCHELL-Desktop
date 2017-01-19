package at.neonartworks.quizchell.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import at.neonartworks.quizchell.data.Question;
import layout.TableLayout;

public class QuestionGUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JButton btnAns1, btnAns2, btnAns3, btnAns4;
    private JProgressBar time;
    private JTextArea txtQuest;
    private Question question;

    public QuestionGUI() {
	Question q = readQuestion();
	this.setContentPane(getpContentPane());
    }

    private JPanel getpContentPane() {
	if (contentPane == null) {
	    contentPane = new JPanel();
	    contentPane.setLayout(createLayout());
	}
	return contentPane;
    }

    private TableLayout createLayout() {
	double f = TableLayout.FILL;
	double[] rows = new double[]{};
	double[] cols = new double[]{};
	double[][] size = new double[][] { cols, rows };
	return new TableLayout(size);
    }

    private Question readQuestion() {
	// TODO implement SQL Client
	return null;
    }

}
