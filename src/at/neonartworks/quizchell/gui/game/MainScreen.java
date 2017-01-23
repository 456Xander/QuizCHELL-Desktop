package at.neonartworks.quizchell.gui.game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import at.neonartworks.quizchell.gui.general.AdjustableBorderPanel;
import at.neonartworks.quizchell.gui.general.Design;
import layout.TableLayout;

public class MainScreen extends JFrame {
	private JPanel mainPane;
	private JButton single, multi, submit, addFriend;
	private final Dimension btnSize;

	public MainScreen() {
		btnSize = new Dimension(200, 20);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(getMainPane());
		setMinimumSize(Design.MIN_SIZE);
		pack();
	}

	private TableLayout createLayout() {
		double f = TableLayout.FILL;
		double[] cols = new double[] {f,f};
		double[] rows = new double[] { f, f, f, f };
		double[][] size = new double[][] { cols, rows };
		return new TableLayout(size);
	}

	private JPanel getMainPane() {
		if (mainPane == null) {
			mainPane = new JPanel();
			mainPane.setBackground(Design.DARK_GRAY);
			mainPane.setLayout(createLayout());
			mainPane.add(new AdjustableBorderPanel(getSingle(), mainPane.getBackground()), "0,0,1,c");
			mainPane.add(new AdjustableBorderPanel(getMulti(), mainPane.getBackground()), "0,1,1,c");
			mainPane.add(new AdjustableBorderPanel(getAddFriend(), mainPane.getBackground()), "0,2,1,c");
			mainPane.add(new AdjustableBorderPanel(getSubmit(), mainPane.getBackground()), "0,3,1,c");
		}
		return mainPane;
	}

	private JButton getSingle() {
		if (single == null) {
			single = new JButton("Zuf&auml;llige Frage");
			single.setBackground(Color.LIGHT_GRAY);
			single.setPreferredSize(btnSize);
		}
		return single;
	}

	private JButton getMulti() {
		if (multi == null) {
			multi = new JButton("Mehrspieler");
			multi.setBackground(Color.LIGHT_GRAY);
			multi.setPreferredSize(btnSize);
		}
		return multi;
	}

	private JButton getSubmit() {
		if (submit == null) {
			submit = new JButton("Frage einsenden");
			submit.setBackground(Color.LIGHT_GRAY);
			submit.setPreferredSize(btnSize);
		}
		return submit;
	}

	private JButton getAddFriend() {
		if (addFriend == null) {
			addFriend = new JButton("Freund hinzuf&uuml;gen");
			addFriend.setBackground(Color.LIGHT_GRAY);
			addFriend.setPreferredSize(btnSize);
		}
		return addFriend;
	}

}
