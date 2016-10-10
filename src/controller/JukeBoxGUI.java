/*
 *oct 9 -> 11:27pm
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
	private JTextField accountText;
	private JPasswordField passwordText;
	private String inputUserStr = "";
	private String inputPasswordStr = "";
	private JLabel password;
	private Song inputSong;
	private int songIndex = -1;
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
		accountInfoBox.setLayout(new GridLayout(4, 2));
		accountInfoBox.setBackground(Color.white);
		JLabel accountName = new JLabel("Account Name");
		accountText = new JTextField("", 10);
		JLabel password = new JLabel("Password");
		passwordText = new JPasswordField("", 10);
		JButton signOutButton = new JButton();
		signOutButton.setText("Sign out");
		ButtonListener signout = new ButtonListener();
		signOutButton.addActionListener(signout);
		JButton loginButton = new JButton();
		loginButton.setText("Login");
		ButtonListener login = new ButtonListener();
		loginButton.addActionListener(login);

		accountStatus = new JLabel();
		accountStatus.setText("");

		accountInfoBox.add(accountName);
		accountInfoBox.add(accountText);
		accountInfoBox.add(password);
		accountInfoBox.add(passwordText);
		accountInfoBox.add(signOutButton);
		accountInfoBox.add(loginButton);
		accountInfoBox.add(accountStatus);

		this.add(accountInfoBox);

		initializeJukeBox();
	}

	private void initializeJukeBox() {
		jukeBox = new JukeBox();

		//check if the inputted values are correct
		//if true, update the values
		
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Select song 1")){
				inputSong = jukeBox.getSong(3);
				if(jukeBox.validate(inputUserStr, inputPasswordStr, inputSong)){
					currentUser = jukeBox.getUser();
					jukeBox.playSong(inputSong);
					accountStatus.setText(currentUser.labelString());
				}
				else{
					JOptionPane.showMessageDialog(null, "Incorrect username/password. Try again");
				}
				
			}
			else if(e.getActionCommand().equals("Select song 2")){
				inputSong = jukeBox.getSong(6);
				if(jukeBox.validate(inputUserStr, inputPasswordStr, inputSong)){
					currentUser = jukeBox.getUser();
					jukeBox.playSong(inputSong);
					accountStatus.setText(currentUser.labelString());
				}
				else{
					JOptionPane.showMessageDialog(null, "Incorrect username/password. Try again");
				}
			}
			else if(e.getActionCommand().equals("Sign out")){
				resetUser();
			}
			//if the use press the login button, we want to validate, sign in and start changing
			//the toString of accountStatus
			else if(e.getActionCommand().equals("Login")){
				//validate the parameters are all entered, else
				//we display a popup warning message
				inputPasswordStr = passwordText.getText();
				inputUserStr = accountText.getText();
			
				if(inputUserStr.equals("") || inputPasswordStr.equals("")){
					// Select song is clicked when no one is logged in
					JOptionPane.showMessageDialog(null, "Not all input fields are entered");
				}else{
					inputSong = jukeBox.getSong(0);
					if(jukeBox.validate(inputUserStr, inputPasswordStr, inputSong)){
						currentUser = jukeBox.getUser();
						accountStatus.setText(currentUser.labelString());
					}
					
				}
			}
		}

		private void resetUser() {
			accountStatus.setText("");;
			accountText.setText("");
			passwordText.setText("");
			inputUserStr = "";
			inputPasswordStr = "";
			inputSong = null;
			songIndex = -1;
			currentUser = null;

		}

	}

}
