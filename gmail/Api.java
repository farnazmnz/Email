package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.Date;

public class Api {
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	private String name, pass;

	public Api() throws UnknownHostException, IOException {

		// socket
		socket = new Socket("127.0.0.1", 9090);

		// input output Socket
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());

	}

	public boolean Login(String name, String pass) throws IOException {
		this.name = name;
		this.pass = pass;

		// -------------------------------create object for login
		String type = "connect";
		String result = type + "&" + name + "&" + pass;

		out.writeUTF(result);
		String read = in.readUTF();

		// -----if login failed ->close socket
		if (read.equals("Password or Username is incorrect")) {
			socket.close();

			return false;
		}

		return true;
	}

	public boolean SingUp(String username, String password, String name, String familyname) throws IOException {

		// -------------------------------create object for singup
		String type = "singup";
		String result = "";
		result = type + "&" + username + "&" + password + "&" + name + "&" + familyname;

		out.writeUTF(result);
		String read = in.readUTF();

		// ---------if sing up success close socket
		if (read.equals("OK")) {
			socket.close();
			return true;
		}

		return false;
	}

	public String[] RChange() throws IOException {

		// --------------------------create object for receive profile data
		String type = "Rchange";
		out.writeUTF(type);
		String read = in.readUTF();

		return read.split("&");

	}

	public boolean SChange(String username, String password, String name, String familyname) throws IOException {

		// -----------------------------------create object for send new profile data
		String type = "Schange";
		String result = "";
		result = type + "&" + username + "&" + password + "&" + name + "&" + familyname;
		out.writeUTF(result);
		String read = in.readUTF();

		if (read.equals("OK")) {

			return true;
		}

		return false;
	}

	public String sendMessage(String username, String body) {

		// -------------------------------create object for send data
		String type = "send";
		String result = "";
		// -------------------create time
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm ");
		Date date = new Date(System.currentTimeMillis());

		result = type + "&" + username + "&" + name + "&" + body + "&" + format.format(date);

		try {
			out.writeUTF(result);

			return in.readUTF();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "null";
	}

	public String close() {
		// ---------------------close socket
		String type = "close";
		String result = "";
		result = type;

		try {
			out.writeUTF(result);

			return in.readUTF();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "null";
	}

	public String[] getSendes() throws IOException {
		// ---------------------------create object for get all sends data
		String type = "sendes";
		String result = "";
		result = type;
		String[] str = null;

		out.writeUTF(result);

		String reader = in.readUTF();
		String Data[] = reader.split(",");
		str = new String[Data.length];

		for (int i = 0; i < Data.length; i++) {

			String[] temp = Data[i].split("&");
			str[i] = "To:" + temp[1] + "\nMessage:\n" + temp[0] + "\nTime:\n" + temp[3];
			// body->0 sender->1 receiver->2 time->3

		}

		return str;

	}

	public String[] getReceives() throws IOException {
		// ----------------------------------create object for all received data
		String type = "receives";
		String result = "";
		result = type;
		String[] str = null;

		out.writeUTF(result);

		String reader = in.readUTF();
		String Data[] = reader.split(",");
		str = new String[Data.length];

		for (int i = 0; i < Data.length; i++) {

			String[] temp = Data[i].split("&");
			str[i] = "From:" + temp[2] + "\nMessage:\n" + temp[0] + "\nTime:\n" + temp[3];

			// body->0 sender->1 receiver->2 time->3
		}

		return str;
	}

}
