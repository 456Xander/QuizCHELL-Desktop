package at.neonartworks.quizchell.data;

public class Question {
	private final String ques, ans1, ans2, ans3, ans4;

	public Question(String ques, String ans1, String ans2, String ans3, String ans4) {
		this.ques = ques;
		this.ans1 = ans1;
		this.ans2 = ans2;
		this.ans3 = ans3;
		this.ans4 = ans4;
	}

	public String getQues() {
		return ques;
	}

	public String getAns1() {
		return ans1;
	}

	public String getAns2() {
		return ans2;
	}

	public String getAns3() {
		return ans3;
	}

	public String getAns4() {
		return ans4;
	}

	public String[] getAnswers() {
		return new String[] { ans1, ans2, ans3, ans4 };
	}
}
