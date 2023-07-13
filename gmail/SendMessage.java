package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class SendMessage extends JFrame implements ActionListener{

	 DataOutputStream out;
	 DataInputStream in;
	public  JTextArea body;
	public  JPanel panel;
	public JButton send,back;
	public JTextField to;
	
	private Api api;

	
	public SendMessage(Api api) {
		this.api=api;
		
		
		this.setVisible(true);
		this.setBounds(200, 10, 500, 500);
		this.setVisible(true);
		this.setLayout(null);

	
		//to
		to=new JTextField();
		to.setBounds(100,10,300,40);
		this.add(to);
		
		//body
		body = new JTextArea("");
		body.setOpaque(true);
		body.setSize(300, 300);
		JScrollPane scroll = new JScrollPane(body);
		scroll.setBounds(100, 60, 300, 250);
		
		this.add(scroll);
		//send 
		send=new JButton("Send");
		send.setFocusable(false);
		send.setBounds(200,350,100,40);
		this.add(send);
		send.addActionListener(this);
		//back
		back=new JButton("Exit");
		back.setFocusable(false);
		back.setBounds(200,400,100,40);
		this.add(back);
		back.addActionListener(this);

	
		

		
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==send) {
			//------request send method
			String text=body.getText();
			String str="";
			for (int i = 0; i < text.length(); i++) {
				if(text.charAt(i)=='\n')
					str+="\\";
				else
					str+=text.charAt(i);
			}
		String result=(	api.sendMessage(to.getText(), str));
		JOptionPane.showMessageDialog(null, result);
		
	
		}
		else if(e.getSource()==back) {
			this.setVisible(false);
			dispose();
			
		}
		
		
	}
	
	


	

	
}
