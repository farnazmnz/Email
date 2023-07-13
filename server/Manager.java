package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Manager {

	public Manager() {

	}

	//-------------------------------------------get all clients message (send,receive)s
	public List<Message> getListMessage(String name) {

		List<Message> list = new ArrayList<Message>();
		File file = new File("data/" + name + ".txt");
		if (!file.exists())
			return list;

		try {
			//-----read file (client).txt
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str;
			try {
				while ((str = reader.readLine()) != null) {
					if (str.length() > 1) {
						//----add to list
						String data[]=str.split("&");
						Message m = new Message(data[3], data[1], data[2]);
						m.setTime(data[4]);
						list.add(m);

					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

	//------------------------------add new Message to list clients message
	public void addMessage(String name, String message) {

		File file = new File("data/" + name + ".txt");
		if (!file.exists())
			return;

		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(message + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// --------------------add new user to list USERS
	public void addUser(String str) {

		File file = new File("data/USERS.txt");
		if (!file.exists())
			return;

		try {
			//-----write in file USERS.txt
			FileWriter writer = new FileWriter(file, true);
			writer.write(str+ "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//----------------------------------get all users in server
	public List<Users> getUsers() {

		List<Users> list = new ArrayList<Users>();
		File file = new File("data/USERS.txt");
		if (!file.exists())
			return list;

		try {
			// -----read file USERS.txt
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str;
			try {
				while ((str = reader.readLine()) != null) {
					if (str.length() > 1) {
						String data[]=str.split("&");

						// ------add to list
						Users u = new Users(data[1], data[2]);
						u.setName(data[3]);
						u.setFamilyname(data[4]);
						list.add(u);

					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

}
