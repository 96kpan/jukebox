package controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.JukeBox;

public class JukeBoxGUI extends JFrame {

	public static final int width = 350;
	public static final int height = 350;

	public static void main(String[] args){
		JukeBoxGUI jukeboxGUI = new JukeBoxGUI();
		jukeboxGUI.setVisible(true);
	}

	public JukeBoxGUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);

		this.setLayout(new FlowLayout());

		this.setLocation(100, 40);
		this.setTitle("JukeBox");
		
		//song button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));
		
		//set button "Select Song 1"
		JButton selectSongButton1 = new JButton();
		selectSongButton1.setText("Select song 1");


		//set button "Select Song 2"
		JButton selectSongButton2 = new JButton();
		selectSongButton2.setText("Select song 2");
		
		buttonPanel.add(selectSongButton1);
		buttonPanel.add(selectSongButton2);
		
		this.add(buttonPanel);
		
		//Account information-- account name, password, signout button
		//and login button
		JPanel accountInfoBox = new JPanel();
		accountInfoBox.setLayout(new GridLayout(3, 2));
		accountInfoBox.setBackground(Color.white);
		JLabel accountName = new JLabel("Account Name");
		JTextField accountText = new JTextField("", 10);
		JLabel password = new JLabel("Password");
		JTextField passwordText = new JTextField("", 10);
		JButton signOutButton = new JButton();
		signOutButton.setText("Sign out");
		JButton loginButton = new JButton();
		loginButton.setText("Login");
		
		accountInfoBox.add(accountName);
		accountInfoBox.add(accountText);
		accountInfoBox.add(password);
		accountInfoBox.add(passwordText);
		accountInfoBox.add(signOutButton);
		accountInfoBox.add(loginButton);
		
		this.add(accountInfoBox);
		
		initializeJukeBox();

		
		

	}

	private void initializeJukeBox() {
		// TODO Auto-generated method stub

	}

}
