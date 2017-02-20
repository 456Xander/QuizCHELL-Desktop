package at.crimsonbit.quizchell.test;

import static org.junit.Assert.*;

import org.junit.Test;

import at.crimsonbit.quizchell.QuizDelegate;
import at.crimsonbit.quizchell.data.Question;
import at.crimsonbit.quizchell.data.QuestionSubject;

public class TestSerialStore {

	@Test
	public void test() {
		QuizDelegate delegate = new QuizDelegate.Local();

		System.out.println(delegate.getRandomQuestion(
				new QuestionSubject[] { new QuestionSubject(QuestionSubject.Subject.BASIC_DEVICES, 1) }));
		delegate.addQuestion(
				new Question("ABS", "1", "2", "3", "4", new QuestionSubject(QuestionSubject.Subject.BASIC_DEVICES, 2)));

		delegate.flush();
	}

}
