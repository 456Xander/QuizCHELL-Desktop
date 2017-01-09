package at.neonartworks.quizchell.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.junit.Test;

import at.neonartworks.quizchell.gui.GhostTextArea;

public class TestGhostTextArea {

	@Test
	public void test() throws InterruptedException {
		JFrame f = new JFrame();
		f.setSize(480, 360);
		Container contentPane = f.getContentPane();
		contentPane.setLayout(new BorderLayout());
		GhostTextArea a = new GhostTextArea("Hallo");
		a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(a, BorderLayout.NORTH);
		GhostTextArea b = new GhostTextArea("Klick mich");
		b.setLineWrap(true);
		b.setWrapStyleWord(true);
		b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(b, BorderLayout.CENTER);
		f.setVisible(true);
		Thread.sleep(1000000);
	}

}
