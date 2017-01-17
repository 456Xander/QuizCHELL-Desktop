package at.neonartworks.quizchell.test;

import static org.junit.Assert.*;

import org.junit.Test;

import at.neonartworks.quizchell.gui.Submit;

public class TestTMP_Submit {

	@Test
	public void test() throws InterruptedException {
		Submit subm = new Submit();
		subm.setVisible(true);
		Thread.sleep(Long.MAX_VALUE);
	}

}
