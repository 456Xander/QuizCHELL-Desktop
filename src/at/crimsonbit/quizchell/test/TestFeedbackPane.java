package at.crimsonbit.quizchell.test;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;

import at.crimsonbit.quizchell.gui.game.FeedbackPane;
import at.crimsonbit.quizchell.gui.general.Design;

public class TestFeedbackPane {
	
	@Test
	public void test() {
		FeedbackPane fbp = new FeedbackPane(false, "Ich hab Angst");
		JFrame f = new JFrame();
		f.setMinimumSize(Design.MIN_SIZE);
		f.setContentPane(fbp);
		f.setVisible(true);
		fbp.doContinue();
	}
	
}
