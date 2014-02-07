import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class serverSide extends JPanel {
	public static JTextArea text = new JTextArea(1, 30);
	public static JScrollPane pane;
	public static String hostname = "localhost";
	public static Socket clientsocket = new Socket();
	public static Socket[] clients = new Socket[200];
	public static PrintWriter[] out = new PrintWriter[200];
	public static BufferedReader in;
	public ServerSocket socket;
	public boolean first = true;
	public Timer checker = new Timer(30, new timer());
	public static String nextmsg;
	public static int i = 0;
	static List<Integer> validi = new ArrayList<Integer>();

	public void init() {
		for(int i = 0; i < 200; ++ i)
		{
			validi.add(i);
		}
		try {
			socket = new ServerSocket(5623);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		pane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(pane, BorderLayout.CENTER);
		pane.setPreferredSize(new Dimension(400, 300));
		pane.setAutoscrolls(true);
		text.setWrapStyleWord(true);
		pane.setVisible(true);
		text.setEditable(false);
		text.setText("Bam, started server at " + hostname);
		clients[i] = new Socket();
	}

	public void check() {
		
		while(true)
		{
		System.out.println("Waiting for client response...");
		ClientConnection c = null;
		try {
			clients[validi.get(0)] = socket.accept();
			text.append("\n NEW USER " + validi.get(0) + " SEEMS TO HAVE CONNECTED...");
			try {
				out[validi.get(0)] = new PrintWriter(
						clients[validi.get(0)].getOutputStream(), true);
			} catch (IOException e1) {
			}
			c = new ClientConnection(clients[validi.get(0)], validi.get(0));
			Thread t = new Thread(c);
			Collection<Integer> collect = new ArrayList<Integer>();
			collect.clear();
			collect.add(validi.get(0));
			validi.removeAll(collect);
			System.out.println(validi);
			t.start();
		} catch (IOException e) {
		}
		}
	}

	public class timer implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
		}

	}

}
