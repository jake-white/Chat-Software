import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JTextArea;
import javax.swing.Timer;

public class ClientConnection implements Runnable {
	public static Socket client;
	public static int i;
	public static boolean[] past_disconnect = new boolean[200];
	public static int lastOut;
	static disconnectCheck check = new disconnectCheck();
	public static Timer disconnectCheck = new Timer(30, check);
	BufferedReader[] in = new BufferedReader[200];

	public ClientConnection(Socket client, int i) {
		this.i = i;
		this.client = client;
	}

	public void run() {
		
		int thread_i = i;
		disconnectCheck.start();
		System.out.println("test");
		String line;
		PrintWriter[] out = new PrintWriter[200];
		while (true) {
			try {
				in[thread_i]= new BufferedReader(new InputStreamReader(
						client.getInputStream()));
				lastOut = i;
				line = in[thread_i].readLine();
				serverSide.text.append("\n" + line);
				for (int count = 0; count < 200; ++count) {
					if (serverSide.out[count] != null) {
						serverSide.out[count].println(line);
						System.out.println("sendin to " + count);
					}

				}
			} catch (Exception e) {
				if (!past_disconnect[lastOut]) {
					serverSide.text.append("\n" + "USER " + lastOut
							+ " SEEMS TO HAVE DISCONNECTED...");
					past_disconnect[lastOut] = true;
					serverSide.validi.add(i);
					break;
				}
			}
		}

	}

	public static class disconnectCheck implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

		}
	}
}
