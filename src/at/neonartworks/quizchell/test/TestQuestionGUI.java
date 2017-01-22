package at.neonartworks.quizchell.test;

import static org.junit.Assert.*;

import org.junit.Test;

import at.neonartworks.quizchell.data.Question;
import at.neonartworks.quizchell.gui.game.QuestionGUI;
import at.neonartworks.quizchell.gui.game.Submit;

public class TestQuestionGUI {

	@Test
	public void test() throws InterruptedException {
		QuestionGUI subm = new QuestionGUI(new Question("Ist das ein Test", "Ja", "Nein", "Weiß nicht",
				"Hab Angst, aus Irgendeinem Grund versteh ich die Frage nicht oder").setTime(10000));
		subm.setVisible(true);
		System.out.println(subm.wasCorrect());
	}

}
