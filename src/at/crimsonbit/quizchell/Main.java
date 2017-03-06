package at.crimsonbit.quizchell;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JOptionPane;

import at.crimsonbit.quizchell.data.GameType;
import at.crimsonbit.quizchell.data.NotYetImplementetException;
import at.crimsonbit.quizchell.data.Question;
import at.crimsonbit.quizchell.data.QuestionSubject;
import at.crimsonbit.quizchell.data.QuizCHELLProperties;
import at.crimsonbit.quizchell.gui.game.FeedbackPane;
import at.crimsonbit.quizchell.gui.game.MainScreen;
import at.crimsonbit.quizchell.gui.game.QuestionGUI;
import at.crimsonbit.quizchell.gui.game.QuizError;
import at.crimsonbit.quizchell.gui.game.Submit;

/**
 * The Main class of QuizCHELL, it manages the flow of the game.
 * 
 * @author Alexander Daum
 *
 */
public class Main {
	/**
	 * The QuizDelegate for this Game. {@link QuizDelegate.Local} although is
	 * only thought for testing purposes and should not be used in any released
	 * version, because many functions of the Game might not work with it, such
	 * as Multiplayer or Friends. <br>
	 * 
	 */
	protected static QuizDelegate delegate = new QuizDelegate.Local();

	public static void main(String[] args) {

		// Create data folder if it does not exist
		String path = QuizCHELLProperties.getDataPath();
		File dataFolder = new File(path + "/");
		if (!dataFolder.exists()) {
			try {
				Files.createDirectory(dataFolder.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		MainScreen mainScreen = new MainScreen();
		mainScreen.setVisible(true);
		GameType game = mainScreen.getSelectedGame();
		mainScreen.setVisible(false);
		// Switch the possible cases
		switch (game) {
		case ADDFRIEND:
			throw new NotYetImplementetException("Friend system is not yet implemented");
			// break;
		case MULTIPLAYER:
			throw new NotYetImplementetException("Multiplayer is not yet implemented");
			// break;
		case SINGLEPLAYER:
			casualQuestion(mainScreen.getSelectedSubjects());
			break;
		case SUBMIT:
			submit();
			break;
		}
		mainScreen.dispose();
	}

	/**
	 * Starts a Submit GUI and submits the question to the SQL
	 */
	public static void submit() {
		Submit submit = new Submit();
		submit.setVisible(true);
		Question q = submit.getQuestion();
		submit.dispose();
		delegate.addQuestion(q);
		delegate.flush();
	}

	/**
	 * Loads a Question from the {@link Main#delegate} and asks it. The Question
	 * will not be counted anywhere.
	 */
	public static void casualQuestion(QuestionSubject[] subjects) {
		QuestionGUI qGui = new QuestionGUI();
		FeedbackPane fbp;
		
		do {
			Question q = null;
			q = delegate.getRandomQuestion(subjects);
			if (q == null) {
				QuizError err = new QuizError("Wir konnten keine Fragen in einer deiner Kategorien finden");
				err.waitFor();
				System.exit(0);
			}
			qGui.askQuestion(q);
			boolean answeredCorrect = qGui.wasCorrect();
			fbp = qGui.getFeedback(answeredCorrect);
		} while (fbp.doContinue());
		System.exit(0);
	}

}
