/*	Jukebox Iteration 1: The Model
 *	Authors: Katie Pan & Niven Francis
 *
 *	Section Leaders: & Cody Macdonald
 *	Due: 10/14/16
 *	
 *	Last Edited: 10/10 @ 12:09
 *
 *	User.java-------------------------------
 *	|
 *	|	User object contains all necessary
 *	|	fields for a user. Contains getters
 *	|	and setters as well as checks to see
 *	|	if it's a new calendar day.
 *	|
 *
 */

package model;
import java.time.LocalDate;

public class User {
	private String acc_name;
	private String password;
	private int play_status;
	
	private int hour;
	private int min;
	private int sec;
	private int total_sec;
	private int day;
	private int year;
	private LocalDate date;
	private String time;
	
	// Creates a new User based on the parameters given
	public User(String name, String pw) {
		this.acc_name = name;
		this.password = pw;
		this.play_status = 0;
		this.total_sec = 90000;
		date = LocalDate.now();
		day = date.getDayOfYear();
		year = date.getYear();
		setTime();
	}
	
	// Sets the time string correctly depending on total_sec
	private void setTime() {
		hour = total_sec / 3600;
		min = (total_sec % 3600) / 60;
		sec = total_sec % 60;
		time = String.format("%02d:%02d:%02d", hour, min, sec);
	}
	
	// Returns how many times a user has played a song
	public int getStatus() {
		return play_status;
	}
	
	// Returns the account name of the user
	public String getName() {
		return acc_name;
	}
	
	// Returns the password of the user
	public String getPassword() {
		return password;
	}
	
	// Increments the play_status by 1
	public void incStatus() {
		this.play_status++;
	}
	
	// Checks to see if the user can play a song
	// If the play_Status is < 3 and if it's a new celandar day
	public boolean canPlay() {
		checkDay();
		if(play_status < 3)
			return true;
		return false;
	}

	// If it's a new calendar day, it resets play_status to 0
	private void checkDay() {
		date = LocalDate.now();
		int temp = date.getDayOfYear();
		int temp2 = date.getYear();
		if(day == temp && temp2 == year)
			return;
		else {
			play_status = 0;
			day = temp;
		}
	}
	
	// Returns the total seconds remaining for the user
	public int getSeconds(){
		return total_sec;
	}
	
	// Returns the time string
	public String getTime() {
		return time;
	}
	
	// Negates the total_sec by the song length passed through the parameter
	public void negateTime(Song s) {
		int songSeconds = s.getLength();
		total_sec -= songSeconds;
		setTime();
	}
	
	// Returns the label string used for the GUI
	public String labelString(){
		return "Status: " + this.getStatus() + " played, " + this.getTime();
	}
}