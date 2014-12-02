
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class serverSync implements Runnable {
	Socket socket;
	public clientSide cS = new clientSide();

	public serverSync(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		while (true) {

			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				clientSide.text.append("\n" + in.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}