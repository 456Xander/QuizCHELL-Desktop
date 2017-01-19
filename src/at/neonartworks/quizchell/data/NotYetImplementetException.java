package at.neonartworks.quizchell.data;

public class NotYetImplementetException extends RuntimeException {
	public static final int ERROR_NOT_YET_IMPLEMENTET = 404;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotYetImplementetException() {
		this("");
	}

	public NotYetImplementetException(String s) {
		super(s);
		System.err.println(this.getMessage());
		this.printStackTrace();
		System.exit(ERROR_NOT_YET_IMPLEMENTET);
	}
}
