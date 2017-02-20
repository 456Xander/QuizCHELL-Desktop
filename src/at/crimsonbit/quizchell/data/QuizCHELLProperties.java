package at.crimsonbit.quizchell.data;

public class QuizCHELLProperties {
	public static String getDataPath() {
		String os = System.getProperty("os.name");
		String path;
		if (os.contains("win")) {
			// Windows
			path = System.getenv("APPDATA");
			
		} else {
			// Linux
			path = System.getenv("HOME");
			
		}
		path += "/.quizCHELL";
		return path;
	}
}
