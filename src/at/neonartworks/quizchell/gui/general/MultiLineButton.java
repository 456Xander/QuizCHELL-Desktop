package at.neonartworks.quizchell.gui.general;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class MultiLineButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;

	public MultiLineButton(String text) {
		super();
		textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setFocusable(false);
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MultiLineButton.this.fireActionPerformed(new ActionEvent(MultiLineButton.this,
						MultiLineButton.this.getDisplayedMnemonicIndex(), MultiLineButton.this.getText()));
			}
		});
		this.setLayout(new BorderLayout());
		this.add(textArea, BorderLayout.CENTER);
	}

	@Override
	public void setText(String text) {
		String oldValue = textArea.getText();
		textArea.setText(text);
		firePropertyChange(TEXT_CHANGED_PROPERTY, oldValue, text);
		if (text == null || oldValue == null || !text.equals(oldValue)) {
			revalidate();
			repaint();
		}
	}

	@Override
	public String getText() {
		if (textArea == null)
			return null;
		return textArea.getText();
	}

}
