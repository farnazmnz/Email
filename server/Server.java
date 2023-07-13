package main;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class Server extends JFrame {

	static DataOutputStream out;
	static DataInputStream in;

	public static JTextArea log;
	static JPanel pannel;
	String data;
	

	static List<Users> users=new ArrayList<Users>();
	public Server() {
		

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 10, 700, 700);
		this.setBackground(Color.black);
		this.setLayout(null);
		this.setVisible(true);

		
		log = new JTextArea("");
		log.setOpaque(true);
		log.setSize(500, 500);
		JScrollPane scroll = new JScrollPane(log);
		scroll.setBounds(100, 100, 500, 500);
		
		this.add(scroll);

		
		try {
			ServerSocket server = new ServerSocket(9090);
			
			
			log.append("\n" + "Server Created");
			
			
			//------------------------------------------------------waiting for clients
            while(true) {
			Socket socket = server.accept();
			//-----new client
			log.append("\n" + "new Clinet ...");
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
		
			//--------object for read data 
		    data=in.readUTF();
		    String order[]=data.split("&");
		    //-----------------------------------------------------------login
		    //order[0]->type
		    if(order[0].equals("connect")) {
		    	//order[1]->name
		    	//order[2]->password
		    	String name=order[1];
		    	String password=order[2];
		    	//log
		    	log.append("\n"+"Request Name:"+name+" Password "+password);
		    	
		    	
		    	
		    	//------------------log in success
		    	Users user=check(name, password);
		    	
		    	if(user!=null) {
		    	log.append("\n"+"new Login Name:"+name+" Password "+password);
		    	out.writeUTF("You are logined...");
		    	Clients c=new Clients(this,user);
		    	c.setData(in, out);
		    	c.start();

		    	log.append("\n"+"start");
		         
		    	
		    	}
		    	//------------------log in failed
		    	else {
			    	out.writeUTF("Password or Username is incorrect");
			    	socket.close();

		    	}
		    	
		    }
		    //-----------------------------------------------------------sing up
		    //order[0]->type

		    else if(order[0].equals("singup")) {
		    	log.append("\n"+"(Request) new Singup UserName:"+order[1]);

		    	if(Singup(data)) {
			    	log.append("\n"+"(Submited) new Singup UserName:"+order[1]);
		    		out.writeUTF("OK");
		    		socket.close();
		    		
		    	}
		    	else {
			    	log.append("\n"+"(Failed) new Singup UserName:"+order[1]);
		    		out.writeUTF("User is olready exist!");
		    		socket.close();

		    	}
		    }
		    else {
			//write
			out.writeUTF("!!!");
		    }
		    
		    
		}//end while

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		users=new Manager().getUsers();
		new Server();

	}
	//---------------check pass and username
	public static Users check(String name,String pass) {
		
		for (int i = 0; i < users.size(); i++) {

			if(users.get(i).getUser().equals(name)&&users.get(i).getPassword().equals(pass)) {
				return users.get(i);
			}
		}
		return null;
	}
	//-----if user exits
	public static boolean isUser(String user) {
		
		
		for (int i = 0; i <users.size(); i++) {

			if(users.get(i).getUser().equals(user)) {
				return true;
			}
		}
		return false;
	}
	
	//--------------------sing up new user
	public static boolean Singup(String s) {
	

		String order[]=s.split("&");
		
		if(!isUser(order[1])) {
			
		Users u=new Users(order[1], order[2]);
	    u.setName(order[3]);
	    u.setFamilyname(order[4]);
		users.add(u);
		//----write new user to USERS.txt
		new Manager().addUser(s);
		
		
		
		
		
		File newFile=new File("data/"+order[1]+".txt");
		if(!newFile.exists()) {
        try {
			newFile.createNewFile();
		
		} catch (IOException e) {

			e.printStackTrace();
		}		
		}
		
		
		return true;
		}
		
		return false;
	}

}
