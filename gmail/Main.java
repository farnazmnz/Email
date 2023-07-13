package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame implements ActionListener{

	private JButton exite,sendes,receives,change,sendMessage;
	private static Api api;
	public Main(Api api,String name) {
		super(name);
		
		this.api=api;
		this.setBounds(100, 100, 500,500);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//send button
		sendMessage=new JButton("Send new email");
		sendMessage.setFocusable(false);
		sendMessage.setBounds(200, 50, 150, 40);
		this.add(sendMessage);
		sendMessage.addActionListener(this);
		
		//receive button
		receives=new JButton("Inbox");
		receives.setFocusable(false);
		receives.setBounds(200, 100, 150, 40);
		this.add(receives);
		receives.addActionListener(this);
		
		//sends button
		sendes=new JButton("Outbox");
		sendes.setFocusable(false);
		sendes.setBounds(200, 150, 150, 40);
		this.add(sendes);
		sendes.addActionListener(this);

		//change button
		change=new JButton("Edit Profile");
		change.setFocusable(false);
		change.setBounds(200, 200, 150, 40);
		this.add(change);
		change.addActionListener(this);

		//exit button
		exite=new JButton("Exit");
		exite.setFocusable(false);
		exite.setBounds(200, 250, 150, 40);
		this.add(exite);
		exite.addActionListener(this);

		
		this.setVisible(true);
	}
	
	


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==exite) {
		    api.close();	
			System.exit(0);

		}
		
		else if(e.getSource()==sendMessage) {
			
			new SendMessage(api);
		}
		else if(e.getSource()==receives) {
			new Receives(api);
			
		}
		else if(e.getSource()==sendes) {
			new Sendes(api);
			
		}
		else if(e.getSource()==change) {
			new Change(api);
			
		}
		
		
		
		
	}

}
