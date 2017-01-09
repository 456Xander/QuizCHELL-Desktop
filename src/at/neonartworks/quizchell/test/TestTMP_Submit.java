package at.neonartworks.quizchell.test;

import static org.junit.Assert.*;

import org.junit.Test;

import at.neonartworks.quizchell.gui.TMP_Submit;

public class TestTMP_Submit {

	@Test
	public void test() throws InterruptedException {
		TMP_Submit subm = new TMP_Submit();
		subm.setVisible(true);
		Thread.sleep(Long.MAX_VALUE);
	}

}
