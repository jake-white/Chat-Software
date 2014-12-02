import javax.swing.*;

public class frame {
	public static JFrame frame = new JFrame("Client");
	public static clientSide client = new clientSide();

	public static void main(String[] args) {
		frame.setSize(600, 400);
		frame.getContentPane().add(client);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.init();
		client.check();
		client.checker.start();
	}
}