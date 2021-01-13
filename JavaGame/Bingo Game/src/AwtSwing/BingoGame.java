package AwtSwing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BingoGame {

	static class MyFrame extends JFrame{
		public MyFrame(String title) {
			super(title);
			this.setLayout(new BorderLayout());
			this.setSize(400,500);
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
	}
	
	public static void main(String[] args) {
		new MyFrame("Bingo Game");

	}

}
