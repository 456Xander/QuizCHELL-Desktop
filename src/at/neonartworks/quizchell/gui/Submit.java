package at.neonartworks.quizchell.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.java.swing.plaf.windows.WindowsBorders.DashedBorder;

import layout.TableLayout;

public class Submit extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel submitPane, panelBtnSubmit;
    private GhostTextArea question, ans1, ans2, ans3, ans4;
    private JButton btnSubmit;

    public Submit() {
	this.setTitle("Frage Einschicken");
	this.setContentPane(getSubmitPane());
	Dimension size = new Dimension(480, 360);
	this.setSize(size);
	this.setMinimumSize(size);
    }

    private JPanel getSubmitPane() {
	if (submitPane == null) {
	    submitPane = new JPanel();
	    submitPane.setBackground(Colors.DARK_GRAY_BLUE);
	    submitPane.setLayout(createLayout());
	    submitPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    submitPane.add(getQuestion(), "0,0, 1,0");
	    submitPane.add(getAns1(), "0,1");
	    submitPane.add(getAns2(), "1,1");
	    submitPane.add(getAns3(), "0,2");
	    submitPane.add(getAns4(), "1,2");
	    submitPane.add(getPanelBtnSubmit(), "0,3,1,3");
	}
	return submitPane;
    }

    private LayoutManager createLayout() {
	final double f = TableLayout.FILL;
	double rows[] = new double[] { f, f, f, f };
	double cols[] = new double[] { f, f };
	double size[][] = new double[][] { cols, rows };
	TableLayout layout = new TableLayout(size);
	return layout;
    }

    public GhostTextArea getQuestion() {
	if (question == null) {
	    question = new GhostTextArea("Frage", 512);
	    question.setBackground(Colors.LIGHTER_GRAY);
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
	    ans1.setBackground(Colors.PALE_GREEN);
	}
	return ans1;
    }

    public GhostTextArea getAns2() {
	if (ans2 == null) {
	    ans2 = new GhostTextArea("Antwort 2", 64);
	    ans2.setLineWrap(true);
	    ans2.setWrapStyleWord(true);
	    ans2.setBorder(BorderFactory.createLineBorder(submitPane.getBackground(), 10, false));
	    ans2.setBackground(Colors.PALE_RED);
	}
	return ans2;
    }
    
    public GhostTextArea getAns3() {
	if (ans3 == null) {
	    ans3 = new GhostTextArea("Antwort 3", 64);
	    ans3.setLineWrap(true);
	    ans3.setWrapStyleWord(true);
	    ans3.setBorder(BorderFactory.createLineBorder(submitPane.getBackground(), 10, false));
	    ans3.setBackground(Colors.PALE_RED);
	}
	return ans3;
    }

    public GhostTextArea getAns4() {
	if (ans4 == null) {
	    ans4 = new GhostTextArea("Antwort 4", 64);
	    ans4.setLineWrap(true);
	    ans4.setWrapStyleWord(true);
	    ans4.setBorder(BorderFactory.createLineBorder(submitPane.getBackground(), 10, false));
	    ans4.setBackground(Colors.PALE_RED);
	}
	return ans4;
    }
    
    public JButton getBtnSubmit(){
	if(btnSubmit == null){
	    btnSubmit = new JButton("Fertig");
	    btnSubmit.setPreferredSize(new Dimension(152, 50));
	    btnSubmit.setForeground(Color.BLACK);
	    btnSubmit.setBackground(Color.LIGHT_GRAY);
	    btnSubmit.addActionListener(e -> abschicken());
	}
	return btnSubmit;
    }
    
    public JPanel getPanelBtnSubmit(){
	if(panelBtnSubmit == null){
	    panelBtnSubmit = new JPanel();
	    panelBtnSubmit.setBackground(submitPane.getBackground());
	    
	    double f = TableLayout.FILL;
	    double[][] size = new double[][]{{f},{f}};
	    panelBtnSubmit.setLayout(new TableLayout(size));
	    panelBtnSubmit.add(getBtnSubmit(), "0,0,c,c");
	}
	return panelBtnSubmit;
    }
    
    private void abschicken(){
	
    }
}