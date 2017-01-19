package at.neonartworks.quizchell.gui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * A JTextArea which displays a light gray Text if it isn't focused and there is
 * not text in it. When there is no text when it looses focus the ghost text
 * will be displayed no matter what previously was in it.
 */
public class GhostTextArea extends JTextArea {

    private static final Color GHOST_COLOR = Color.GRAY;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String ghostText;

    private boolean ghost;
    private StringBuilder cache;

    /**
     * Constructs an empty GhostTextArea with specified ghostText to be shown
     * when the Component does not have focus and text in it
     * 
     * @param ghostText
     *            The light gray text to be shown when the Component does not
     *            have focus and no text in it
     */
    public GhostTextArea(String ghostText, int maxLen) {
	super();
	this.setDocument(new LimitDocument(maxLen));
	this.ghostText = ghostText;
	this.addFocusListener(new GhostTextListener());
	/*
	 * Wenn the GhostTextArea does have Focus at the beginning the ghost
	 * text should not be shown
	 */
	if (!this.hasFocus()) {
	    this.setText(ghostText);
	    this.setForeground(GHOST_COLOR);
	    ghost = true;
	}
    }

    private class GhostTextListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
	    if (ghost) {
		GhostTextArea.this.setText("");
		GhostTextArea.this.setForeground(Color.BLACK);
		ghost = false;
	    }
	}

	@Override
	public void focusLost(FocusEvent e) {
	    if (GhostTextArea.this.getText().equals("")) {
		GhostTextArea.this.setText(ghostText);
		GhostTextArea.this.setForeground(GHOST_COLOR);
		ghost = true;
	    }
	}

    }

    private class LimitDocument extends PlainDocument {
	
	private int maxLen;
	private LimitDocument(int maxLen) {
	    this.maxLen = maxLen;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	    if (str == null)
		return;

	    if (getLength() + str.length() <= maxLen)
		super.insertString(offs, str, a);
	}
    }

    public String getGhostText() {
	return ghostText;
    }

    public void setGhostText(String ghostText) {
	this.ghostText = ghostText;
    }

}
