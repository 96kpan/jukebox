/*	Jukebox Iteration 1: The Model
 *	Authors: Katie Pan & Niven Francis
 *
 *	Section Leaders: Bree Collins & Cody Macdonald
 *	Due: 10/14/16
 *	
 *	Last Edited: 10/18 4:48pm
 *
 *	JukeBoxGUI.java-------------------------------
 *	|
 *	|	Creates the GUI for JukeBox. Users can
 *	|	login and play/add songs to the queue
 *	|	here.
 *	|
 *
 */

package controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int width = 1500;
	public static final int height = 1500;
	private JukeBox jukeBox;
	private JLabel accountStatus;
	private JTextField accountText;
	private JPasswordField passwordText;
	private String inputUserStr = "";
	private String inputPasswordStr = "";
	private Song inputSong;
	private User currentUser;
	private JPanel playlistQueue;
	private JLabel playlistLabel;


	// Creates a new instance of the GUI
	public static void main(String[] args){
		JukeBoxGUI jukeboxGUI = new JukeBoxGUI();
		jukeboxGUI.setVisible(true);
	}
	
	public void update() {
	//	System.out.println(jukeBox.toString());
	//	System.out.println();
		playlistLabel.setText(jukeBox.toString());
	}

	// Creates the GUI for the Jukebox, including the frame and buttons
	private JukeBoxGUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);

		this.setLayout(new FlowLayout());

		this.setLocation(100, 40);
		this.setTitle("JukeBox");

		//song button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,3));

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
		accountStatus.setText("Status: Login first");

		accountInfoBox.add(accountName);
		accountInfoBox.add(accountText);
		accountInfoBox.add(password);
		accountInfoBox.add(passwordText);
		accountInfoBox.add(signOutButton);
		accountInfoBox.add(loginButton);
		accountInfoBox.add(accountStatus);
		
		initializeJukeBox();
		
		playlistQueue = new JPanel();
		playlistQueue.setBackground(Color.white);
		playlistLabel = new JLabel();
		playlistLabel.setText(jukeBox.toString());
		playlistQueue.add(playlistLabel);
		
		
		this.add(playlistQueue);
		this.add(accountInfoBox);
		
	}
	
	public static JukeBoxGUI getInstance(){
		return new JukeBoxGUI();
	}

	// Creates a new instance of the JukeBox
	private void initializeJukeBox() {
		//singleton design pattern
		jukeBox = JukeBox.getInstance();

		//check if the inputted values are correct
		//if true, update the values

	}
	
	private class ButtonListener implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Select song 1")){
				inputSong = jukeBox.getSong(4);
				if(jukeBox.validate(inputUserStr, inputPasswordStr, inputSong)){
					jukeBox.playSong(inputSong);
					accountStatus.setText(currentUser.labelString());
					playlistLabel.setText(jukeBox.toString());
				}
				else{
					JOptionPane.showMessageDialog(null, "Cannot play song 1");
				}

			}
			else if(e.getActionCommand().equals("Select song 2")){
				inputSong = jukeBox.getSong(8);
				if(jukeBox.validate(inputUserStr, inputPasswordStr, inputSong)){
					jukeBox.playSong(inputSong);
					accountStatus.setText(currentUser.labelString());
					playlistLabel.setText(jukeBox.toString());
				}
				else{
					JOptionPane.showMessageDialog(null, "Cannot play song 2");
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
				inputSong = jukeBox.getSong(0);
				if(!jukeBox.validate(inputUserStr, inputPasswordStr)){
					// Select song is clicked when no one is logged in
					JOptionPane.showMessageDialog(null, "Username/passwords are wrong");
					resetUser();
				}else{
					if(jukeBox.validate(inputUserStr, inputPasswordStr)){
						currentUser = jukeBox.getUser();
						accountStatus.setText(currentUser.labelString());
						
					}
				}
			}
		}

		// Resets the user text boxes and string displays
		private void resetUser() {
			accountStatus.setText("Status: Login first");;
			accountText.setText("");
			passwordText.setText("");
			inputUserStr = "";
			inputPasswordStr = "";
			inputSong = null;
			currentUser = null;

		}

	}
}
