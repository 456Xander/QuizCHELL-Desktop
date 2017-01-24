package at.crimsonbit.quizchell.test;

import org.junit.Test;

import at.crimsonbit.quizchell.gui.game.MainScreen;

public class TestMain {

	@Test
	public void test() throws InterruptedException {
		MainScreen m = new MainScreen();
		m.setVisible(true);
		System.out.println(m.getSelectedGame());
	}

}
