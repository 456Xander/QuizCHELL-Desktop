package at.crimsonbit.quizchell.gui.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import at.crimsonbit.quizchell.gui.general.Design;
import at.crimsonbit.quizchell.gui.general.LogoPanel;
import layout.TableLayout;

public class FeedbackPane extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int STATE_WAITING = -1;
	private static final int STATE_NEXT = 0;
	private static final int STATE_END = 1;
	
	private JButton btnNextQuestion, btnEndGame;
	private JLabel lblText;
	private LogoPanel logoPanel;
	private int state = -1;
	private boolean correct;
	private String correctAnswer;
	
	public FeedbackPane(boolean correct, String correctAnswer) {
		setLayout(createLayout());
		this.correct = correct;
		this.correctAnswer = correctAnswer;
		setBackground(Design.DARK_GRAY);
		add(getLogoPanel(), "0,0,7,0");
		add(getLblText(), "2,2,5,2");
		add(getBtnEndGame(), "5,5");
		add(getBtnNextQuestion(), "2,5");
	}
	
	private LayoutManager createLayout() {
		double f = TableLayout.FILL;
		double[] cols = new double[] { 0.1, 25, f, 25, 0.1, f, 25, 0.1 };
		double[] rows = new double[] { Design.LOGO_HEIGHT, 20, f, 20, 0.1, 0.3, 0.2 };
		double[][] size = new double[][] { cols, rows };
		return new TableLayout(size);
	}
	
	private JButton getBtnNextQuestion() {
		if (btnNextQuestion == null) {
			btnNextQuestion = new JButton("Neue Frage");
			btnNextQuestion.setBackground(Color.LIGHT_GRAY);
			btnNextQuestion.addActionListener(new FeedBackBtnListener(STATE_NEXT));
		}
		return btnNextQuestion;
	}
	
	private JButton getBtnEndGame() {
		if (btnEndGame == null) {
			btnEndGame = new JButton("Spiel beenden");
			btnEndGame.setBackground(Color.LIGHT_GRAY);
			btnEndGame.addActionListener(new FeedBackBtnListener(STATE_END));
		}
		return btnEndGame;
	}
	
	private JLabel getLblText() {
		if (lblText == null) {
			lblText = new JLabel(getFeedbackText());
			lblText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
			lblText.setHorizontalTextPosition(JLabel.CENTER);
			lblText.setForeground(Design.LIGHTER_GRAY);
		}
		return lblText;
	}
	
	private String getFeedbackText() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body>");
		if (correct) {
			sb.append("Die Antwort war richtig");
		} else {
			sb.append("Falsch. Die richtige Antwort wäre: <br>");
			sb.append(correctAnswer);
		}
		sb.append("</body></html>");
		return sb.toString();
	}
	
	private LogoPanel getLogoPanel() {
		if (logoPanel == null) {
			logoPanel = new LogoPanel(Design.LOGO, "Quiz CHEL(L)", Design.LOGO_FONT, Design.LOGO_COLOR);
			logoPanel.scale(Design.LOGO_HEIGHT, Design.LOGO_HEIGHT);
			logoPanel.setBackground(getBackground());
		}
		return logoPanel;
	}
	
	public boolean doContinue() {
		synchronized (this) {
			while (state == STATE_WAITING) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return state == STATE_NEXT;
	}
	
	private class FeedBackBtnListener implements ActionListener {
		private int state;
		
		public FeedBackBtnListener(int state) {
			this.state = state;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			FeedbackPane.this.state = this.state;
			synchronized (FeedbackPane.this) {
				FeedbackPane.this.notify();
			}
		}
		
	}
	
}
