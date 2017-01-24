package at.crimsonbit.quizchell;

import javax.swing.JOptionPane;

import at.crimsonbit.quizchell.data.GameType;
import at.crimsonbit.quizchell.data.NotYetImplementetException;
import at.crimsonbit.quizchell.data.Question;
import at.crimsonbit.quizchell.data.QuestionSubject;
import at.crimsonbit.quizchell.gui.game.MainScreen;
import at.crimsonbit.quizchell.gui.game.QuestionGUI;
import at.crimsonbit.quizchell.gui.game.Submit;

public class Main {
    public static void main(String[] args) {
	MainScreen mainScreen = new MainScreen();
	mainScreen.setVisible(true);
	GameType game = mainScreen.getSelectedGame();
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
	Question q = submit.getQuestion();
	// TODO SQL Access
	throw new NotYetImplementetException("SQL Access is not implemented yet");
    }

    /**
     * Loads a Question from the SQL and asks it. There is no Rating
     */
    public static void casualQuestion(QuestionSubject[] subjects) {
	Question q = null;
	q = fetchFromSQL(subjects);
	QuestionGUI qGui = new QuestionGUI(q);
	boolean answeredCorrect = qGui.wasCorrect();
	JOptionPane.showMessageDialog(null, "Antwort: " + (answeredCorrect ? "richtig" : "falsch"));
    }
    /**
     * Loads a random Question from the SQL and returns it
     * @return a random Question
     */
    public static Question fetchFromSQL(QuestionSubject[] subjects){
	throw new NotYetImplementetException("SQL Access is not implemented yet");
    }
}
