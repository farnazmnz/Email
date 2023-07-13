package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SingUp extends JFrame implements ActionListener {

	private JButton singup, back;
	private JTextField username, name, familyname, password1, password2;

	public SingUp() {
		this.setBounds(500, 10, 500, 500);
		this.setLayout(null);

		JLabel l1 = new JLabel("UserName:");
		l1.setBounds(175, 20, 100, 30);
		this.add(l1);

		// username
		username = new JTextField();
		username.setBounds(150, 50, 200, 40);
		this.add(username);

		l1 = new JLabel("Password:");
		l1.setBounds(150, 90, 100, 30);
		this.add(l1);

		// pass1
		password1 = new JTextField();
		password1.setBounds(150, 120, 200, 40);
		this.add(password1);

		l1 = new JLabel("Confirm Password:");
		l1.setBounds(150, 170, 200, 30);
		this.add(l1);

		// pass2
		password2 = new JTextField();
		password2.setBounds(150, 200, 200, 40);
		this.add(password2);

		l1 = new JLabel("Name:");
		l1.setBounds(150, 250, 200, 30);
		this.add(l1);

		// name
		name = new JTextField();
		name.setBounds(150, 280, 200, 40);
		this.add(name);

		l1 = new JLabel("Family Name:");
		l1.setBounds(150, 330, 200, 30);
		this.add(l1);

		// familyname
		familyname = new JTextField();
		familyname.setBounds(150, 360, 200, 40);
		this.add(familyname);

		// singup
		singup = new JButton("Ok");
		singup.setBounds(150, 410, 100, 30);
		this.add(singup);
		singup.addActionListener(this);

		// back
		back = new JButton("Back");
		back.setBounds(250, 410, 100, 30);
		this.add(back);
		back.addActionListener(this);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == singup) {

			String s1, s2, s3, s4, s5;
			s1 = username.getText();
			s2 = password1.getText();
			s3 = password2.getText();
			s4 = name.getText();
			s5 = familyname.getText();

			if (s2.equals(s3)) {

				if (s1.length() > 1 && s2.length() > 1 && s3.length() > 1 && s4.length() > 1 && s5.length() > 1) {

					try {
						Api api = new Api();
						// -------------request sing up method
						boolean check = api.SingUp(s1, s2, s4, s5);
						if (check) {
							JOptionPane.showMessageDialog(null, "Sucess :)");
							this.setVisible(false);
							this.dispose();	
						}
						else
							JOptionPane.showMessageDialog(null, "User is already exist :(");
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				} else
					JOptionPane.showMessageDialog(null, "Please fill in all the fields");

			} else
				JOptionPane.showMessageDialog(null, "Incorrect password!");

		} else if (e.getSource() == back) {

			this.setVisible(false);
			this.dispose();
		}
	}

}
