package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Start extends JFrame implements ActionListener{

	private JTextField user;
	private JPasswordField pass;
	private JButton login,singup;

	public Start() {

		this.setBounds(300, 10, 500, 500);
		this.setLayout(null);

		// lable
		JLabel l1 = new JLabel("UserName:");
		l1.setBounds(150, 80, 100, 20);
		this.add(l1);

		// user
		user = new JTextField();
		user.setBounds(150, 100, 200, 40);
		this.add(user);

		// lable
		JLabel l2 = new JLabel("Password:");
		l2.setBounds(150, 160, 100, 20);
		this.add(l2);

		// pass
		pass = new JPasswordField();
		pass.setBounds(150, 180, 200, 40);
		this.add(pass);
		
		//login
	    login=new JButton("Singin");
	    login.setFocusable(false);
	    login.setBounds(200, 230, 100, 40);
		this.add(login);
		//action
		login.addActionListener(this);
		
		//singup
	    singup=new JButton("Singup");
	    singup.setFocusable(false);
	    singup.setBounds(200, 280, 100, 40);
		this.add(singup);
		//singup
		singup.addActionListener(this);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		
		new Start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==login) {
			if(user.getText().length()<1||pass.getText().length()<1) {
				JOptionPane.showMessageDialog(null, "Enter Currect User Or Password!");
			}
			else {
				try {
					Api api=new Api();
					if(api.Login(user.getText(), pass.getText())) {
						new Main(api,user.getText());
						setVisible(false);
						dispose();
					}
					else {
				JOptionPane.showMessageDialog(null, "User or Password Is InCorrect!");

					}

				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
			}
		}//end if
		
		else if(e.getSource()==singup) {
			new SingUp();
		}
		
	}

}
