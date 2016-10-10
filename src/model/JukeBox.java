/*	Jukebox Iteration 1: The Model
 *	Authors: Katie Pan & Niven Francis
 *
 *	Section Leaders: & Cody Macdonald
 *	Due: 10/14/16
 *	
 *	Last Edited: 10/10 @ 12:09
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class JukeBox {

	private ArrayList<Song> songList;
	private ArrayList<User> userList;
	private User thisUser;
	private Song thisSong;
	private Queue<Song> songQueue;
	private boolean currentPlaying;

	
	// Creates the new JukeBox
	public JukeBox() {
		initializeJukeBox();
	}

	// Initializes a new songQueue and creates the song list and user list
	private void initializeJukeBox() {
		songQueue = new LinkedList<Song>();
		System.out.println(songQueue.toString());
		setSongList();
		setUserList();
		currentPlaying = false;
	}

	// Plays the song passed through the parameter and increments status of thisUser and thisSong
	public void playSong(Song song) {
		songQueue.add(song);
		System.out.println(song.getTitle() + " added to queue");
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
	public boolean validate(String user, String password, Song song) {
		for(int x = 0; x < userList.size(); x++) {
			if(user.equals(userList.get(x).getName())) {
				if(password.equals(userList.get(x).getPassword())) {
					thisUser = userList.get(x);
					if(thisUser.canPlay() && thisUser.getSeconds() >= thisSong.getLength() && thisSong.canPlay())
						return true;
				}
			}
		}
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

	// Sets the userList array to contain all possible users
	private void setUserList() {
		userList = new ArrayList<User>();
		userList.add(new User("Chris", "1"));
		userList.add(new User("Devon", "22"));
		userList.add(new User("River", "333"));
		userList.add(new User("Ryan", "4444"));
	}

	/**
	 * This inner class allows us to have an callback function that receive a
	 * songFinishedPlaying message whenever an audio file has been played.
	 */
	private class WaitingForSongToEnd implements EndOfSongListener {

		// Prints a message, waits 1 second, and plays the next song if possible once song finishes playing
		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			System.out.println("Finished " + eosEvent.fileName() + ", " + eosEvent.finishedDate() + ", "
					+ eosEvent.finishedTime());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentPlaying = false;
			songQueue.remove();
			if(!songQueue.isEmpty()) {
				playQueue();
			}
		}
	}
}