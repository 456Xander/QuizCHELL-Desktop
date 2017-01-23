package at.neonartworks.quizchell.gui.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import at.neonartworks.quizchell.data.Question;
import at.neonartworks.quizchell.gui.general.Design;
import at.neonartworks.quizchell.gui.general.GhostTextArea;
import at.neonartworks.quizchell.gui.general.LogoPanel;
import layout.TableLayout;

public class Submit extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel submitPane, panelBtnSubmit;
    private LogoPanel panelLogo;
    private GhostTextArea question, ans1, ans2, ans3, ans4;
    private Question dataQuestion = null;
    private JButton btnSubmit;
    private final int logoHeight = 64;

    public Submit() {
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("Frage Einschicken");
	this.setContentPane(getSubmitPane());
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
	    submitPane.add(getTxtQuestion(), "0,1, 1,1");
	    submitPane.add(getAns1(), "0,2");
	    submitPane.add(getAns2(), "1,2");
	    submitPane.add(getAns3(), "0,3");
	    submitPane.add(getAns4(), "1,3");
	    submitPane.add(getPanelBtnSubmit(), "0,4,1,4");
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

    public GhostTextArea getTxtQuestion() {
	if (question == null) {
	    question = new GhostTextArea("Frage", 1024);
	    question.setBackground(Design.LIGHTER_GRAY);
	    question.setLineWrap(true);
	    question.setWrapStyleWord(true);
	    question.setBorder(BorderFactory.createLineBorder(submitPane.getBackground(), 10, false));
	}
	return question;
    }

    public GhostTextArea getAns1() {
	if (ans1 == null) {
	    ans1 = new GhostTextArea("Richtige Antwort", 64);
	    ans1.setLineWrap(true);
	    ans1.setWrapStyleWord(true);
	    ans1.setBorder(BorderFactory.createLineBorder(submitPane.getBackground(), 10, false));
	    ans1.setBackground(Design.PALE_GREEN);
	}
	return ans1;
    }

    public GhostTextArea getAns2() {
	if (ans2 == null) {
	    ans2 = new GhostTextArea("Antwort 2", 64);
	    ans2.setLineWrap(true);
	    ans2.setWrapStyleWord(true);
	    ans2.setBorder(BorderFactory.createLineBorder(submitPane.getBackground(), 10, false));
	    ans2.setBackground(Design.PALE_RED);
	}
	return ans2;
    }

    public GhostTextArea getAns3() {
	if (ans3 == null) {
	    ans3 = new GhostTextArea("Antwort 3", 64);
	    ans3.setLineWrap(true);
	    ans3.setWrapStyleWord(true);
	    ans3.setBorder(BorderFactory.createLineBorder(submitPane.getBackground(), 10, false));
	    ans3.setBackground(Design.PALE_RED);
	}
	return ans3;
    }

    public GhostTextArea getAns4() {
	if (ans4 == null) {
	    ans4 = new GhostTextArea("Antwort 4", 64);
	    ans4.setLineWrap(true);
	    ans4.setWrapStyleWord(true);
	    ans4.setBorder(BorderFactory.createLineBorder(submitPane.getBackground(), 10, false));
	    ans4.setBackground(Design.PALE_RED);
	}
	return ans4;
    }

    public JButton getBtnSubmit() {
	if (btnSubmit == null) {
	    btnSubmit = new JButton("Fertig");
	    btnSubmit.setPreferredSize(new Dimension(152, 50));
	    btnSubmit.setForeground(Color.BLACK);
	    btnSubmit.setBackground(Color.LIGHT_GRAY);
	    btnSubmit.addActionListener(new SubmitListener());
	}
	return btnSubmit;
    }

    public JPanel getPanelBtnSubmit() {
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

    public LogoPanel getPanelLogo() {
	if (panelLogo == null) {
	    panelLogo = new LogoPanel("src/resources/Logo.png", "Quiz CHEL(L)", Design.LOGO_FONT, Design.LOGO_GREEN);
	    panelLogo.scale(logoHeight, logoHeight);
	    panelLogo.setBackground(submitPane.getBackground());
	}
	return panelLogo;
    }

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
	return new Question(question.getText(), ans1.getText(), ans2.getText(), ans3.getText(), ans4.getText());
    }

    private class SubmitListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    try {
		dataQuestion = new Question(question.getText(), ans1.getText(), ans2.getText(), ans3.getText(),
			ans4.getText());
		synchronized (Submit.this) {
		    Submit.this.notify();
		}
	    } catch (NullPointerException ex) {
		dataQuestion = null;
	    }

	}

    }

}