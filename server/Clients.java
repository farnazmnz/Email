package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Clients extends Thread {

	// ------user data
	private Users user;

	private DataOutputStream out;
	private DataInputStream in;
	private Server server;
	private String data;

	public Clients(Server server, Users user) {

		this.user = user;
		this.server = server;

	}

	public void setData(DataInputStream in, DataOutputStream out) {
		this.out = out;
		this.in = in;
	}

	@Override
	public void run() {
		super.run();

		try {
			while (true) {
				// ------------------object for input data

				data = in.readUTF();
				String order[] = data.split("&");
				// log
				Server.log.append("\n" + data);

				// ------------------------------------------------send message
				// order[0]->type
				if (order[0].equals("send")) {

					// (order[1]->to
					if (server.isUser(order[1])) {

						// -----add message to sender and reciver list
						new Manager().addMessage(user.getUser(), data);
						new Manager().addMessage(order[1], data);

						out.writeUTF("Sent:)");
					}
					// ------if message failed
					else
						out.writeUTF("There is no user:(");

				}
				// -----------------------------------------------------sends
				// order[0]->type
				else if (order[0].equals("sendes")) {
					Server.log.append("\n" + "(Request) Sendes From " + user.getUser());
					out.writeUTF(getSendes());

				}
				// -----------------------------------------------------Receivers
				// order[0]->type
				else if (order[0].equals("receives")) {
					Server.log.append("\n" + "(Request) Receives From " + user.getUser());
					out.writeUTF(getReceives());

				}
				// -----------------------------------------------------RChange
				// order[0]->type
				else if (order[0].equals("Rchange")) {
					// ------------------------------object for receive profile data
					Server.log.append("\n" + "(Request) RChange From " + user.getUser());
					out.writeUTF(user.getUser() + "&" + user.getPassword() + "&" + user.getName() + "&"
							+ user.getFamilyname());

				}
				// -----------------------------------------------------SChange
				// order[0]->type
				else if (order[0].equals("Schange")) {
					// ------set new profile data
					// order[1]->user name
					// order[2]->password
					// order[3]->name
					// order[4]->family name
					user.setPassword(order[2]);
					user.setName(order[3]);
					user.setFamilyname(order[4]);

					Server.log.append("\n" + "(Request) SChange From " + user.getUser());
					out.writeUTF("OK");

				}
				// -----------------------------------------------close
				// order[0]->type
				else if (order[0].equals("close")) {

					out.writeUTF("ok Closed.");
					server.log.append("\n" + "User " + user.getUser() + " Closed!");
					break;
				} else
					out.writeUTF("!!!!");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getSendes() {

		List<Message> M = new Manager().getListMessage(user.getUser());
		String result = "";

		for (int x = 0; x < M.size(); x++) {
			if (M.get(x).getSender().equals(this.user.getUser())) {

				result += M.get(x).getBody() + "&" + M.get(x).getReceiver() + "&" + M.get(x).getSender() + "&"
						+ M.get(x).getTime() + ",";

			}

		}
		if (result.isEmpty()) {
			result += "No Message!" + "&" + " " + "&" + " " + "&" +" " + ",";
			
		}
		return result;
	}

	public String getReceives() {
		List<Message> M = new Manager().getListMessage(user.getUser());
		String result = "";
		for (int x = 0; x < M.size(); x++) {
			if (M.get(x).getReceiver().equals(this.user.getUser())) {

				result += M.get(x).getBody() + "&" + M.get(x).getReceiver() + "&" + M.get(x).getSender() + "&"
						+ M.get(x).getTime() + ",";

			}
		}

		if (result.isEmpty()) {
			result += "No Message!" + "&" + " " + "&" + " " + "&" +" " + ",";
			
		}

		return result;
	}

}
