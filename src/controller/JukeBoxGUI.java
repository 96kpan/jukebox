/*
 *oct 8 -> 22:46
 */
package controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.JukeBox;
import model.Song;
import model.User;

public class JukeBoxGUI extends JFrame {

	public static final int width = 350;
	public static final int height = 250;
	private JukeBox jukeBox;
	private JLabel accountStatus;
	private String inputUserStr;
	private String inputPasswordStr;
	private Song inputSong;
	private int songIndex;
	private User currentUser;
	

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
		ButtonListener song1 = new ButtonListener();
		selectSongButton1.addActionListener(song1);

		//set button "Select Song 2"
		JButton selectSongButton2 = new JButton();
		selectSongButton2.setText("Select song 2");
		ButtonListener song2 = new ButtonListener();
		selectSongButton2.addActionListener(song2);
		
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
		inputUserStr = accountText.getText();
		JLabel password = new JLabel("Password");
		JPasswordField passwordText = new JPasswordField("", 10);
		inputPasswordStr = passwordText.getText();
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
		
		accountStatus = new JLabel();
		accountStatus.setText(currentUser.labelString());
		
	}

	private void initializeJukeBox() {
		jukeBox = new JukeBox();
		
		//check if the inputted values are correct
		//if true, update the values
		if(jukeBox.validate(inputUserStr, inputPasswordStr, inputSong)){
			currentUser = jukeBox.getUser();
		}
		else{
			JOptionPane.showMessageDialog(null, "Input fields are not correct");
		}
		

	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Select song 1")){
				inputSong = jukeBox.getSong(0);
				songIndex = 0;
				accountStatus.setText(currentUser.labelString());
			}
			else if(e.getActionCommand().equals("Select song 2")){
				inputSong = jukeBox.getSong(1);
				songIndex = 1;
				accountStatus.setText(currentUser.labelString());
			}
			
		}
		
	}

}
