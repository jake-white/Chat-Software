import java.awt.Container;

import javax.swing.*;

public class frame {
	public static serverSide panel = new serverSide();
	public static JFrame frame = new JFrame("Server");

	public static void main(String[] args) {
		frame.setSize(600, 400);
		Container pane = frame.getContentPane();
		pane.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.init();
		panel.check();
	}
}
