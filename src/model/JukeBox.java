/*	Jukebox Iteration 1: The Model
 *	Authors: Katie Pan & Niven Francis
 *
 *	Section Leaders: Bree Collins & Cody Macdonald
 *	Due: 10/14/16
 *	
 *	Last Edited: 10/19 8:15
 *
 *	JukeBox.java-------------------------------
 *	|
 *	|	Contains all the logic for handling
 *	|	songs, users, the queue, and validating
 *	|	if a song/user can play
 *	|
 *
 */

package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import controller.JukeBoxGUI;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class JukeBox implements Serializable{

	private ArrayList<Song> songList;
	private ArrayList<User> userList;
	private User thisUser;
	private Song thisSong;
	private Queue<Song> songQueue;
	private boolean currentPlaying;
	private JukeBoxGUI gui;
	private static JukeBox instance = new JukeBox();
	private String playList;
	private SongQueue newSongQueue;
	private JList list;

	// Creates the new JukeBox
	private JukeBox() {
		initializeJukeBox();
	}
	
	public static JukeBox getInstance() {
		return instance;
	}
	
	// Initializes a new songQueue and creates the song list and user list
	private void initializeJukeBox() {
		
		songQueue = new LinkedList<Song>();
		newSongQueue = new SongQueue(songQueue);
		
		//System.out.println(songQueue.toString());
		setSongList();
		setUserList();
		currentPlaying = false;
	}

	// Plays the song passed through the parameter and increments status of thisUser and thisSong
	public void playSong(Song song) {
		songQueue.add(song);
		//System.out.println(song.getTitle() + " added to queue");
		//System.out.println(toString());
		thisUser.incStatus();
		thisSong.incStatus();
		thisUser.negateTime(song);
		playQueue();
	}

	// Plays the start of the song queue
	private void playQueue() {
		EndOfSongListener waitForSongEnd = new WaitingForSongToEnd();
		if(!currentPlaying) {
			SongPlayer.playFile(waitForSongEnd, songQueue.peek().getFileName());
			
			currentPlaying = true;
		}
	}
	
	public Queue<Song> getQueue(){
		return this.songQueue;
	}

	// Get song prior to validation
	// Gets the song based on index from the songList
	public Song getSong(int i) {
		thisSong = songList.get(i);
		return thisSong;
	}

	// Returns current user
	public User getUser(){
		return this.thisUser;
	}

	// Current user login info -> login screen
	// Will validate user and information to see if user can play the song
	public boolean validate(String user, String password) {
		for(int x = 0; x < userList.size(); x++) {
			if(user.equals(userList.get(x).getName())) {
				if(password.equals(userList.get(x).getPassword())) {
					thisUser = userList.get(x);
					return true;
				}
			}
		}
		return false;
	}

	// Current user login info -> login screen
	// Will validate user and information to see if user can play the song
	public boolean validate(String user, String password, Song song) {

		if(thisUser.canPlay() && thisUser.getSeconds() >= thisSong.getLength() && thisSong.canPlay())
			return true;

		return false;
	}

	// Sets the songList array to contain all possible songs
	private void setSongList() {
		songList = new ArrayList<Song>();
		songList.add(new Song("Kevin MacLeod", "Danse Macabre", "./songfiles/DanseMacabreViolinHook.mp3", 34));
		songList.add(new Song("FreePlay Music", "Determined Tumbao", "./songfiles/DeterminedTumbao.mp3", 20));
		songList.add(new Song("Sun Microsystems", "Flute", "./songfiles/flute.aif", 5));
		songList.add(new Song("Kevin MacLeod", "Loping Sting", "./songfiles/LopingSting.mp3", 4));
		songList.add(new Song("Unknown", "Space Music", "./songfiles/spacemusic.au", 6));
		songList.add(new Song("FreePlay Music", "Swing Cheese", "./songfiles/SwingCheese.mp3", 15));
		songList.add(new Song("Microsoft", "Tada", "./songfiles/tada.wav", 2));
		songList.add(new Song("Kevin MacLeod", "The Curtain Rises", "./songfiles/TheCurtainRises.mp3", 28));
		songList.add(new Song("Pierre Langer", "Untameable Fire", "./songfiles/UntameableFire.mp3", 282));
	}
	
	public ArrayList<Song> getSongList(){
		return songList;
	}
 
	// Sets the userList array to contain all possible users
	private void setUserList() {
		userList = new ArrayList<User>();
		userList.add(new User("Chris", "1"));
		userList.add(new User("Devon", "22"));
		userList.add(new User("River", "333"));
		userList.add(new User("Ryan", "4444"));
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("<html>");
		
		Queue<Song> temp = new LinkedList<Song>();
		temp.addAll(songQueue);
		for(Song s : temp){
			result.append(s.getTime() + " " + s.getTitle() + " by " + s.getArtist() + "<br>");
		}

		result.append("</html>");
		//System.out.println(result.toString());
		playList = result.toString();
		return result.toString();
	}

	/**
	 * This inner class allows us to have an callback function that receive a
	 * songFinishedPlaying message whenever an audio file has been played.
	 */
	private class WaitingForSongToEnd implements EndOfSongListener {

		// Prints a message, waits 1 second, and plays the next song if possible once song finishes playing
		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			//System.out.println("Finished " + eosEvent.fileName() + ", " + eosEvent.finishedDate() + ", "
			//		+ eosEvent.finishedTime());
			//System.out.println(JukeBox.getInstance().toString());
		
			JukeBox object = JukeBox.getInstance();
			JukeBoxGUI gui = JukeBoxGUI.getInstance();
			
			//list.setModel(gui.getListModel());
			//list.updateUI();
			
			try {
				//System.out.println("here");
				Thread.sleep(1000);
				currentPlaying = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			songQueue.remove();
			

			
			
			//System.out.println(object.toString());
			gui.update();
			if(!songQueue.isEmpty()) {
				//System.out.println("here4");
				playQueue();
			}
			
		}
	}
	
}