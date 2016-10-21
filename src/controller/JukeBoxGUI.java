/*	Jukebox Iteration 1: The Model
 *	Authors: Katie Pan & Niven Francis
 *
 *	Section Leaders: Bree Collins & Cody Macdonald
 *	Due: 10/14/16
 *	
 *	Last Edited: 10/20 20:49
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.JukeBox;
import model.PlayList;
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
	private static JukeBoxGUI instance;
	private TableModel model;
	private JTable table;
	private DefaultListModel listModel;
	private JList list;
	private JScrollPane scrollPaneList;
	private Queue<Song> queue;

	// Creates a new instance of the GUI
	public static void main(String[] args){
		JukeBoxGUI jukeboxGUI = new JukeBoxGUI();
		instance = jukeboxGUI;
		jukeboxGUI.setVisible(true);
	}

	public void update() {
		if(listModel.getSize() != 0) {
			listModel.remove(0);
			queue.remove(0);
		}
		//System.out.println(queue.toString());
	}
	
	public Queue<Song> getQueue() {
		return queue;
	}

	// Creates the GUI for the Jukebox, including the frame and buttons
	private JukeBoxGUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);

		this.setLayout(new FlowLayout());
		WindowListener w = new Save();
		this.addWindowListener(w);

		this.setLocation(100, 40);
		this.setTitle("JukeBox");

		//song button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,3));

		//Account information-- account name, password, signout button
		//and login button
		JPanel accountInfoBox = new JPanel();
		accountInfoBox.setLayout(new GridLayout(4, 2));
		accountInfoBox.setBackground(Color.white);
		JLabel accountName = new JLabel("    Account Name");
		accountText = new JTextField("", 15);
		JLabel password = new JLabel("    Password");
		passwordText = new JPasswordField("", 15);
		JButton signOutButton = new JButton();
		signOutButton.setText("Sign out");
		ButtonListener signout = new ButtonListener();
		signOutButton.addActionListener(signout);
		JButton loginButton = new JButton();
		loginButton.setText("Login");
		ButtonListener login = new ButtonListener();
		loginButton.addActionListener(login);

		accountStatus = new JLabel();
		accountStatus.setText("    Status: Login first");

		accountInfoBox.add(accountName);
		accountInfoBox.add(accountText);
		accountInfoBox.add(password);
		accountInfoBox.add(passwordText);
		accountInfoBox.add(signOutButton);
		accountInfoBox.add(loginButton);
		accountInfoBox.add(accountStatus);

		initializeJukeBox();

		//adds the playlist queue onto the left side
		//of the gui
		playlistQueue = new JPanel();
		JLabel queueLabel = new JLabel("Play List (Song at top is playing)");
		playlistQueue.setBackground(Color.white);

		//this.listModel = new SongQueue(jukeBox.getQueue());
		
		listModel = new DefaultListModel<String>();
		
		this.list = new JList(listModel);
		
		scrollPaneList = new JScrollPane(this.list);
		playlistQueue.add(queueLabel);
		playlistLabel = new JLabel();
		playlistLabel.setText(jukeBox.toString()); //needs to be a jlist
		playlistLabel.add(scrollPaneList);
		playlistQueue.add(playlistLabel);

		//this jpanel includes the label of the playlist,
		//the playlist queue, and the account info box
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new GridLayout(3, 1, 20, 20));
		leftSide.add(queueLabel);
		leftSide.add(scrollPaneList, BorderLayout.CENTER);
		leftSide.add(accountInfoBox);

		//the arrowbutton JButton that will allow the user to 
		//add a song of their choice into the jukebox queue
		JButton arrowButton = new JButton();
		arrowButton.setText("<-");
		ButtonListener b = new ButtonListener();
		arrowButton.addActionListener(b);

		//RIGHT SIDE OF THE GUI
		//This side will include the songs with options of sorting
		JPanel rightSide = new JPanel();
		this.model = new PlayList(jukeBox.getSongList());
		this.table = new JTable(this.model);
		JScrollPane scrollPane = new JScrollPane(this.table);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(this.model);
		this.table.setRowSorter(rowSorter);
		// Add panel to frame
		rightSide.add(scrollPane, BorderLayout.CENTER);

		this.add(leftSide);
		this.add(arrowButton);
		this.add(rightSide);

	}

	public static JukeBoxGUI getInstance() {
		return instance;
	}

	// Creates a new instance of the JukeBox
	private void initializeJukeBox() {
		//singleton design pattern
		//jukeBox = new JukeBox();
		jukeBox = JukeBox.getInstance();
		queue = new LinkedList<Song>();
		//check if the inputted values are correct
		//if true, update the values

	}

	public JukeBox getJukeBox(){
		return jukeBox;
	}

	public DefaultListModel getListModel(){
		return this.listModel;
	}

	private class Save implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			JOptionPane optionPane = new JOptionPane();
			optionPane.setMessage("Start from earlier save?");
			int n = optionPane.showConfirmDialog(null, "Start from earlier save?", "Save State", JOptionPane.YES_NO_OPTION);

			list = new JList(listModel);			
			
			DefaultListModel temp = null;
			Queue<Song> temp_queue = null;
			boolean changed = false;
			
			if(n == optionPane.YES_OPTION){
				try {
					FileInputStream fis = new FileInputStream("jukebox_savedata");
					ObjectInputStream input = new ObjectInputStream(fis);
					jukeBox = (JukeBox) input.readObject();
					input.close();
					fis.close();
					
					fis = new FileInputStream("listmodel_savedata");
					input = new ObjectInputStream(fis);
					temp = (DefaultListModel) input.readObject();
					input.close();
					fis.close();
					changed = true;

					fis = new FileInputStream("queue_savedata");
					input = new ObjectInputStream(fis);
					temp_queue = (Queue<Song>) input.readObject();
					input.close();
					fis.close();
				} catch (Exception i) {
					jukeBox = JukeBox.getInstance();
				}
			}
			else{
				jukeBox = JukeBox.getInstance();
			}

			jukeBox.initializeJukeBox();
			if(changed) {
				for(int x = 0; x < temp.size(); x++) {
					listModel.addElement(temp.getElementAt(x));
					jukeBox.addSong(temp_queue.peek());
					temp_queue.remove();
				}
				//System.out.println(temp_queue.toString());
			}
			
			//System.out.println(JukeBox.getInstance().getQueue().toString());

			scrollPaneList = new JScrollPane(list);
			
			if (listModel.getSize()>0){
				//((PlayList) listModel).play(((PlayList) listModel).get(0));
				list.updateUI();
			}
			

		}

		@Override
		public void windowClosing(WindowEvent e) {
			JOptionPane jop = new JOptionPane();
			jop.setMessage("Save?");
			int n = jop.showConfirmDialog(null, "Save?", "Save State", JOptionPane.YES_NO_CANCEL_OPTION);

			if (n == jop.YES_OPTION) {
				try {
					FileOutputStream fos = new FileOutputStream("jukebox_savedata");
					ObjectOutputStream outFile = new ObjectOutputStream(fos);
					outFile.writeObject(jukeBox); //error line
					outFile.close();
					
					fos = new FileOutputStream("listmodel_savedata");
					outFile = new ObjectOutputStream(fos);
					outFile.writeObject(listModel); //error line
					outFile.close();
					
					fos = new FileOutputStream("queue_savedata");
					outFile = new ObjectOutputStream(fos);
					outFile.writeObject(queue); //error line
					outFile.close();
				} catch (IOException e1) {
					//System.out.println("Save failed");
					e1.printStackTrace();
				}
				System.exit(0);
			}
			if (n == jop.NO_OPTION)
				System.exit(0);

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}


	private class ButtonListener implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			//action listener for the button command
			//the button should allow the user to add a song into the queue

			if(e.getActionCommand().equals("<-")){
				int index = table.getSelectedRow();
				int correctedIndex = table.convertRowIndexToModel(index);
				inputSong = jukeBox.getSong(correctedIndex);
				if(inputUserStr.equals("") && inputPasswordStr.equals("") ){
					JOptionPane.showMessageDialog(null, "Input username and password please");
					return;
				}

				if(jukeBox.validate(inputUserStr, inputPasswordStr, inputSong)){
					jukeBox.playSong(inputSong);
					//System.out.println("size " + jukeBox.getQueue().size());
					//System.out.println(inputSong.getTitle());
					accountStatus.setText(currentUser.labelString());
					//playlistLabel.setText(jukeBox.toString());
					listModel.addElement(inputSong.getTime() + " " + inputSong.getTitle() + " by " + inputSong.getArtist());
					list.setModel(listModel);
					list.updateUI();
				}
				else{
					JOptionPane.showMessageDialog(null, "Cannot play song");
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