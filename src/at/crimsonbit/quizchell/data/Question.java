package at.crimsonbit.quizchell.data;

import java.io.Serializable;

/**
 * A Class representing a Question
 * 
 * @author alex
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
}
