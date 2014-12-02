

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class clientSide extends JPanel implements ActionListener {
    JButton button = new JButton("MESSAGE SERVER");
    public static String user, m;
    public PrintWriter out = null;
    static Socket socket = null;
    String hostName = "localhost", response;
    int portNumber = 8000;
    public Timer checker = new Timer(10, new timer());
    BufferedReader in;
    static JTextArea text = new JTextArea(1,30);
    JTextField port = new JTextField();
    JTextField username = new JTextField();
    JTextField msg = new JTextField();
    JLabel userL = new JLabel("Username");
    JScrollPane pane;
    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);

    public void init() {

JOptionPane.showInputDialog("Enter an IP", hostName);

    	this.setLayout(layout);
		pane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        try {
            socket = new Socket(hostName, portNumber);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		pane.setPreferredSize(new Dimension(400, 300));
		pane.setAutoscrolls(true);
        text.setEditable(false);
        this.add(userL);
        this.add(username);
        this.add(pane);
        this.add(msg);
        this.add(button);
        text.setVisible(true);
        username.setVisible(true);
		pane.setVisible(true);
        msg.setVisible(true);
        button.setVisible(true);
        msg.addActionListener(this);
        username.addActionListener(this);
        button.addActionListener(this);
        frame.frame.setVisible(true);
        try {
        	
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void check() {
            serverSync s;
            s = new serverSync(socket);
            Thread t = new Thread(s);
            t.start();
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == button) {
            out.println(username.getText() + ": " + msg.getText());
            System.out.println("sent!");

        }

    }

    public class timer implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {

        }

    }
}