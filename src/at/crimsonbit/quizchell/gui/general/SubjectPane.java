package at.crimsonbit.quizchell.gui.general;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import at.crimsonbit.quizchell.data.QuestionSubject;
import layout.TableLayout;

public class SubjectPane extends JPanel {
	private JComboBox<QuestionSubject.Subject> subjects;
	private JSpinner year;
	
	public SubjectPane() {
		double[] cols = new double[] { TableLayout.FILL, 150, 50, TableLayout.FILL };
		double[] rows = new double[] { 15, TableLayout.FILL };
		TableLayout layout = new TableLayout(new double[][] { cols, rows });
		this.setLayout(layout);
		JLabel label = new JLabel("Fachbereich und Jahrgang", JLabel.CENTER);
		label.setForeground(Design.PALE_BLUE);
		this.add(label, "1,0,2,0");
		this.add(getBoxSubjects(), "1,1,c,c");
		this.add(getSpinnerYear(), "2,1,c,c");
	}
	
	public JComboBox<QuestionSubject.Subject> getBoxSubjects() {
		if (subjects == null) {
			subjects = new JComboBox<>(QuestionSubject.Subject.values());
			subjects.setBackground(Design.LIGHTER_GRAY);
		}
		return subjects;
	}
	
	public JSpinner getSpinnerYear() {
		if (year == null) {
			year = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
			year.setBackground(Design.LIGHTER_GRAY);
		}
		return year;
	}
	
	public QuestionSubject getSubject() {
		return new QuestionSubject((QuestionSubject.Subject) subjects.getSelectedItem(), (int) year.getValue());
	}
}
