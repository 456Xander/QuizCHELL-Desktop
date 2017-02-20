package at.crimsonbit.quizchell.test;

import org.junit.Test;

import at.crimsonbit.quizchell.gui.game.QuizError;

public class TestError {

	@Test
	public void test() {
		QuizError err = new QuizError("Whooops, Schaut aus als wenn der Server nicht erreichbar wäre");
		err.waitFor();
	}

}
