package at.neonartworks.quizchell.test;

import static org.junit.Assert.*;

import org.junit.Test;

import at.neonartworks.quizchell.gui.game.Submit;

public class TestSubmit {

	@Test
	public void test() throws InterruptedException {
		Submit subm = new Submit();
		subm.setVisible(true);
		Thread.sleep(Long.MAX_VALUE);
	}

}
