package at.crimsonbit.quizchell;

import at.crimsonbit.quizchell.data.Question;
import at.crimsonbit.quizchell.data.QuestionSubject;

public abstract class QuizDelegate {
	
	public abstract void addQuestion(Question q);
	
	public abstract Question getNextQuestion();
	
	public abstract Question getQuestionIn(int skipQuestions);
	
	public abstract Question getRandomQuestion();
	
	public abstract Question getRandomQuestion(QuestionSubject[] subjects);
	
	public static class Local extends QuizDelegate {
		
		@Override
		public void addQuestion(Question q) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public Question getNextQuestion() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Question getQuestionIn(int skipQuestions) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Question getRandomQuestion() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Question getRandomQuestion(QuestionSubject[] subjects) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public static class SQL extends QuizDelegate {
		
		@Override
		public void addQuestion(Question q) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public Question getNextQuestion() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Question getQuestionIn(int skipQuestions) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Question getRandomQuestion() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Question getRandomQuestion(QuestionSubject[] subjects) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
