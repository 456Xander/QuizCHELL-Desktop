package at.crimsonbit.quizchell.test;

import static org.junit.Assert.*;

import org.junit.Test;

import at.crimsonbit.quizchell.gui.game.Submit;

public class TestSubmit {

	@Test
	public void test() throws InterruptedException {
		Submit subm = new Submit();
		subm.setVisible(true);
		System.out.println(subm.getQuestion());
	}

}
