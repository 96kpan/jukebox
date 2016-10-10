/*
 *oct 9 -> 11pm
 */
package model;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
/**
 * Play one audio file from the songfiles directory.
 * There is no listener for the end of song event.
 * 
 * @author Rick Mercer
 */
import songplayer.SongPlayer;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class JukeBox {

	private ArrayList<Song> songList;
	private ArrayList<User> userList;
	private boolean IS_VALIDATED;
	private User thisUser;
	private Song thisSong;
	private Queue<Song> songQueue;

	
	// Creates the new JukeBox
	public JukeBox() {
		initializeJukeBox();
		//	test();
	}

	// Initializes a new songQueue and creates the song list and user list
	private void initializeJukeBox() {
		songQueue = new LinkedList<Song>();
		setSongList();
		setUserList();
		IS_VALIDATED = false;
	}

	// Plays the song passed through the parameter and increments status of thisUser and thisSong
	public void playSong(Song song) {
		EndOfSongListener waitForSongEnd = new WaitingForSongToEnd();
		SongPlayer.playFile(waitForSongEnd, song.getFileName());
		thisUser.incStatus();
		thisSong.incStatus();
	}

	// Get song prior to validation
	// Gets the song based on index from the songList
	public Song getSong(int i) {
		thisSong = songList.get(i);
		return thisSong;
	}

	// Returns songList array
	public ArrayList getSongList(){
		return this.songList;
	}
	
	// Returns userList array
	public ArrayList getUserList(){
		return this.userList;
	}

	// Sets song prior to validation
	public void setSong(Song song) {
		thisSong = song;
	}
	
	// Returns current user
	public User getUser(){
		return this.thisUser;
	}

	// Current user login info -> login screen
	// Will validate user and information to see if user can play the song
	public boolean validate(String user, String password, Song song) {
		if(user.equals("Chris")) 
			if(password.equals("1")){
				thisUser = userList.get(0);
				validateUser();
				return true;
			}
		if(user.equals("Devon")) 
			if(password.equals("22")) {
				thisUser = userList.get(1);
				validateUser();
				return true;
			}
		if(user.equals("River")) 
			if(password.equals("333")){
				thisUser = userList.get(2);
				validateUser();
				return true;
			}
		if(user.equals("Ryan")) 
			if(password.equals("4444")){
				thisUser = userList.get(3);
				validateUser();
				return true;
			}
		return false;
	}

	// Checks if the user can play the song
	private void validateUser() {
		if(thisUser.canPlay() && thisUser.getSeconds() >= thisSong.getLength() && thisSong.canPlay()){
			thisUser.negateTime(thisSong.getLength());
			IS_VALIDATED = true;
			songQueue.add(thisSong);
			return;
		}

		this.IS_VALIDATED = false;

	}

	// Sets the songList array to contain all possible songs
	private void setSongList() {
		songList = new ArrayList<Song>();
		songList.add(new Song("Kevin MacLeod", "Danse Macabre", "./songfiles/DanseMacabreViolinHook.mp3", 34));
		songList.add(new Song("FreePlay Music", "Determined Tumbao", "./songfiles/DeterminedTumbao.mp3", 20));
		songList.add(new Song("Sun Microsystems", "Flute", "./songfiles/flute.aif", 5));
		songList.add(new Song("Kevin MacLeod", "Loping Sting", "./songfiles/LopingSting.mp3", 4));
		songList.add(new Song("Unknown", "Space Music", "./songfiles/spacemusinc.au", 6));
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

	// Testing purposes only
	private void test() {
		User Niven = new User("Niven", "1");
		System.out.println(Niven.getTime());

	}

	/**
	 * This inner class allows us to have an callback function that receive a
	 * songFinishedPlaying message whenever an audio file has been played.
	 */
	private class WaitingForSongToEnd implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			songQueue.remove();
		}
	}
}


