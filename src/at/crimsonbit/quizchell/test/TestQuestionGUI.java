package at.crimsonbit.quizchell.test;

import static org.junit.Assert.*;

import org.junit.Test;

import at.crimsonbit.quizchell.data.Question;
import at.crimsonbit.quizchell.data.QuestionSubject;
import at.crimsonbit.quizchell.gui.game.QuestionGUI;
import at.crimsonbit.quizchell.gui.game.Submit;

public class TestQuestionGUI {

    @Test
    public void test() throws InterruptedException {
	QuestionGUI subm = new QuestionGUI(new Question("Ist das ein Test", "Ja", "Nein", "Weiß nicht",
		"Hab Angst, aus Irgendeinem Grund versteh ich die Frage nicht oder",
		new QuestionSubject(QuestionSubject.Subject.MISC_HTL, 1)).setTime(30000));
	subm.setVisible(true);
	System.out.println(subm.wasCorrect());
    }

}
