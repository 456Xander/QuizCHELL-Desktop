package at.crimsonbit.quizchell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import at.crimsonbit.quizchell.data.Question;
import at.crimsonbit.quizchell.data.QuestionSubject;
import at.crimsonbit.quizchell.data.QuizCHELLProperties;

public abstract class QuizDelegate {

	public abstract void addQuestion(Question q);

	public abstract Question getRandomQuestion();

	public abstract Question getRandomQuestion(QuestionSubject[] subjects);

	public abstract void flush();

	public static class Local extends QuizDelegate {

		private Map<QuestionSubject, List<Question>> questions;
		private Random random;
		int ctr = -1;

		public Local() {
			questions = read();

			random = new Random();
		}

		@Override
		public void addQuestion(Question q) {
			List<Question> category = questions.get(q.getSubject());
			if (category == null) {
				category = new ArrayList<>();
				questions.put(q.getSubject(), category);
			}
			category.add(q);
		}

		@SuppressWarnings("unchecked")
		@Override
		public Question getRandomQuestion() {
			List<Question>[] categories = new List[0];
			categories = questions.values().toArray(categories);
			int category = random.nextInt(categories.length);
			List<Question> selectedCategory = categories[category];
			int index = random.nextInt(selectedCategory.size());
			return selectedCategory.get(index);
		}

		@Override
		public Question getRandomQuestion(QuestionSubject[] subjects) {
			List<List<Question>> categories = new ArrayList<>();
			for (QuestionSubject subj : subjects) {
				List<Question> cat = questions.get(subj);
				//If cat == null ==> no questions in this category
				if (cat != null)
					categories.add(cat);
			}
			if (categories.isEmpty()) {
				return null;
			}
			int category = random.nextInt(categories.size());
			List<Question> selectedCategory = categories.get(category);
			int index = random.nextInt(selectedCategory.size());
			return selectedCategory.get(index);
		}

		@Override
		public void flush() {
			String path = QuizCHELLProperties.getDataPath();
			path += "/localStore.dat";
			File store = new File(path);
			if (store.exists()) {
				store.delete();
			}
			try {
				store.createNewFile();
				ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(store));
				ous.writeObject(questions);
				ous.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@SuppressWarnings("unchecked")
		private Map<QuestionSubject, List<Question>> read() {
			String path = QuizCHELLProperties.getDataPath();
			path += "/localStore.dat";
			File store = new File(path);
			Map<QuestionSubject, List<Question>> toRead = null;
			try {
				ObjectInputStream ous = new ObjectInputStream(new FileInputStream(store));
				toRead = (Map<QuestionSubject, List<Question>>) ous.readObject();
				ous.close();
			} catch (FileNotFoundException e) {
				// Data File does not exist, create it and write empty HashMap
				try {
					store.createNewFile();
					ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(store));
					ous.writeObject(new HashMap<QuestionSubject, List<Question>>());
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			} catch (IOException e) {
				toRead = new HashMap<>();
				// System.err.println("File " + path + "Cannot be found");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return toRead;
		}

	}

	public static class SQL extends QuizDelegate {

		@Override
		public void addQuestion(Question q) {
			// TODO Auto-generated method stub

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

		@Override
		public void flush() {
			// TODO Auto-generated method stub

		}

	}
}
