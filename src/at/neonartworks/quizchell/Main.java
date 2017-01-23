package at.neonartworks.quizchell;

import javax.swing.JOptionPane;

import at.neonartworks.quizchell.data.GameType;
import at.neonartworks.quizchell.data.NotYetImplementetException;
import at.neonartworks.quizchell.data.Question;
import at.neonartworks.quizchell.gui.game.MainScreen;
import at.neonartworks.quizchell.gui.game.QuestionGUI;
import at.neonartworks.quizchell.gui.game.Submit;

public class Main {
    public static void main(String[] args) {
	MainScreen mainScreen = new MainScreen();
	GameType game = mainScreen.getSelectedGame();
	switch (game) {
	case ADDFRIEND:
	    throw new NotYetImplementetException("Friend system is not yet implemented");
	    // break;
	case MULTIPLAYER:
	    throw new NotYetImplementetException("Multiplayer is not yet implemented");
	    // break;
	case SINGLEPLAYER:
	    casualQuestion();
	    break;
	case SUBMIT:
	    submit();
	    break;
	}
    }

    public static void submit() {
	Submit submit = new Submit();
	Question q = submit.getQuestion();
	// TODO SQL Access
	throw new NotYetImplementetException("SQL Access is not implemented yet");
    }

    public static void casualQuestion() {
	Question q = null;
	// TODO read Question from SQL
	QuestionGUI qGui = new QuestionGUI(q);
	boolean answeredCorrect = qGui.wasCorrect();
	JOptionPane.showMessageDialog(null, "Antwort: " + (answeredCorrect ? "richtig" : "falsch"));
    }
}
