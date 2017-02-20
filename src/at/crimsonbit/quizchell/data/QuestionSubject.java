package at.crimsonbit.quizchell.data;

import java.io.Serializable;

public class QuestionSubject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionSubject other = (QuestionSubject) obj;
		if (subject != other.subject)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	public static enum Subject implements Serializable {
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
