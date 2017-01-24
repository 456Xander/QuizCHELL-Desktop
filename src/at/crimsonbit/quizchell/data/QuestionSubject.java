package at.crimsonbit.quizchell.data;

public class QuestionSubject {
    private final Subject subject;
    private final int year;

    public QuestionSubject(Subject subject, int year) {
	if (year > 5) {
	    throw new IllegalArgumentException(
		    "Tried to get QuestionSubject for year " + year + ", but there are only 5 years!");
	}
	this.subject = subject;
	this.year = year;
    }

    public Subject getSubject() {
	return subject;
    }

    public int getYear() {
	return year;
    }

    public static enum Subject {
	/*
	 * All Areas from which there are questions in QuizChell
	 */
	/**
	 * Basic electronic devices like resistors, capacitors, inductivities.
	 */
	BASIC_DEVICES,
	/**
	 * Digital electronic devices, like logical gates, Adder, and other
	 * digital circuits
	 */
	DIGITALS,
	/**
	 * Questions about programming, file systems, network and similar things
	 */
	INFORMATICS,
	/**
	 * Miscellaneous questions about the live in the HTL, teachers, jokes,
	 * etc.
	 */
	MISC_HTL

    }

}
