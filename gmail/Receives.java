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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class Receives extends JFrame implements ActionListener{

	
	
	private Api api;
    static JList list; 

	
	public Receives(Api api) {
		this.api=api;
		
		this.setVisible(true);
		this.setBounds(200, 10, 500, 500);
		this.setLayout(null);

		 //create a panel 
        JPanel p =new JPanel();
        p.setSize(500, 500);
        p.setOpaque(true);
        p.setBackground(Color.BLACK);
        this.add(p); 

        //create list 
        try {
			list= new JList(api.getReceives());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
          
        
        
        JScrollPane scrollPane = new JScrollPane(list); 

        //add list to panel 
        p.add(scrollPane); 
       
        list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					String text=list.getSelectedValue().toString();
					String str="";
					for (int i = 0; i < text.length(); i++) {
						if(text.charAt(i)=='\\')
							str+="\n";
						else
							str+=text.charAt(i);
					}
					JOptionPane.showMessageDialog(null,str);
				}
			}
		});
   
	
		this.setVisible(true);
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

	
		}
		
	}
	
	


	

	

