package at.crimsonbit.quizchell.data;

/**
 * GameType is an enum, that is used as return by the mainScreen to show which
 * Game or feature has been selected. For more detail see the documentation of
 * each enum constant.
 * 
 * @author Alexander Daum
 *
 */
public enum GameType {
	/**
	 * Indicates single player game mode. There are just casual questions, no
	 * rating. You get a random question from the delegate of the Quiz Instance
	 * and have to answer it. Afterwards you see if you were correct or not.
	 * Perfect for training
	 */
	SINGLEPLAYER,
	/**
	 * Multi player is the main game mode of the game. Two players play against
	 * each other and get some Questions. In the end the player who answered
	 * more questions correct wins the game
	 */
	MULTIPLAYER,
	/**
	 * Add Friend indicates, that the friends menu should be shown. Friends can
	 * always start games against each other
	 */
	ADDFRIEND,
	SUBMIT;
}
