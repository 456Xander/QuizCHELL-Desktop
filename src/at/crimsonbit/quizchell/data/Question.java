package at.crimsonbit.quizchell.data;

import java.io.Serializable;

/**
 * A Class representing a Question
 * 
 * @author Alexander Daum
 *
 */
public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String ques, ans1, ans2, ans3, ans4;
	private int time;
	private QuestionSubject subject;
	
	/**
	 * Constructs a new Question
	 * 
	 * @param ques
	 * @param ans1
	 *            the Correct Answer
	 * @param ans2
	 * @param ans3
	 * @param ans4
	 */
	public Question(String ques, String ans1, String ans2, String ans3, String ans4, QuestionSubject subj) {
		this.ques = ques;
		this.ans1 = ans1;
		this.ans2 = ans2;
		this.ans3 = ans3;
		this.ans4 = ans4;
		this.subject = subj;
		this.time = 30000;
	}
	
	/**
	 * 
	 * @return The time in ms
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Sets the Time a user may need at maximum to time
	 * 
	 * @param time
	 *            the time in ms
	 * @return this instance for convienience
	 */
	public Question setTime(int time) {
		this.time = time;
		return this;
	}
	
	/**
	 * 
	 * @return The Question
	 */
	public String getQues() {
		return ques;
	}
	
	/**
	 * 
	 * @return The First Answer which is the Correct one
	 */
	public String getAns1() {
		return ans1;
	}
	
	/**
	 * 
	 * @return The Second Answer
	 */
	public String getAns2() {
		return ans2;
	}
	
	/**
	 * 
	 * @return The Third Answer
	 */
	public String getAns3() {
		return ans3;
	}
	
	/**
	 * 
	 * @return The Fourth Answer
	 */
	public String getAns4() {
		return ans4;
	}
	
	/**
	 * 
	 * @return All answers packed in an Array
	 */
	public String[] getAnswers() {
		return new String[] { ans1, ans2, ans3, ans4 };
	}
	
	@Override
	public String toString() {
		return getQues() + "? " + getAns1() + " oder " + getAns2() + " oder " + getAns3() + " oder " + getAns4();
	}

	public QuestionSubject getSubject() {
		return subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ans1 == null) ? 0 : ans1.hashCode());
		result = prime * result + ((ans2 == null) ? 0 : ans2.hashCode());
		result = prime * result + ((ans3 == null) ? 0 : ans3.hashCode());
		result = prime * result + ((ans4 == null) ? 0 : ans4.hashCode());
		result = prime * result + ((ques == null) ? 0 : ques.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + time;
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
		Question other = (Question) obj;
		if (ans1 == null) {
			if (other.ans1 != null)
				return false;
		} else if (!ans1.equals(other.ans1))
			return false;
		if (ans2 == null) {
			if (other.ans2 != null)
				return false;
		} else if (!ans2.equals(other.ans2))
			return false;
		if (ans3 == null) {
			if (other.ans3 != null)
				return false;
		} else if (!ans3.equals(other.ans3))
			return false;
		if (ans4 == null) {
			if (other.ans4 != null)
				return false;
		} else if (!ans4.equals(other.ans4))
			return false;
		if (ques == null) {
			if (other.ques != null)
				return false;
		} else if (!ques.equals(other.ques))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (time != other.time)
			return false;
		return true;
	}
}
