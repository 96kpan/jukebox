/*	Jukebox Iteration 1: The Model
 *	Authors: Katie Pan & Niven Francis
 *
 *	Section Leaders: Bree Collins & Cody Macdonald
 *	Due: 10/14/16
 *	
 *	Last Edited: 10/18 @ 16:46
 *
 *	Song.java-------------------------------
 *	|
 *	|	Song object contains all necessary
 *	|	fields for a song. Contains getters
 *	|	and setters as well as checks to see
 *	|	if it's a new calendar day.
 *	|
 *
 */

package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Song implements Serializable{
	private String title;
	private String fileName;
	private String artist;
	private int length; // seconds
	private int play_status;
	private int day;
	private int year;
	private LocalDate date;

	// Creates the song object using the values passed through the parameters
	public Song(String artist, String title, String fileName, int length) {
		this.artist = artist;
		this.title = title;
		this.fileName = fileName;
		this.length = length;
		this.play_status = 0;
		date = LocalDate.now();
		day = date.getDayOfYear();
		year = date.getYear();
	}

	// Checks to see if the song can be played
	// If the play_status < 3 or if it's a new calendar day
	public boolean canPlay() {
		checkDay();
		if (play_status < 3)
			return true;
		return false;
	}

	// Checks to see if it's a new calendar day. If it is, it resets play_status
	// to 0
	private void checkDay() {
		date = LocalDate.now();
		int temp = date.getDayOfYear();
		int temp2 = date.getYear();
		if (day == temp && temp2 == year)
			return;
		else {
			play_status = 0;
			day = temp;
		}
	}

	// Incrememnts the play_status by 1
	public void incStatus() {
		this.play_status++;
	}

	// Returns the title of the song
	public String getTitle() {
		return title;
	}

	// Returns the artist of the song
	public String getArtist() {
		return artist;
	}

	// Returns the length of the song
	public int getLength() {
		return length;
	}

	// Returns the filename of the song
	public String getFileName() {
		return fileName;
	}

	public String getTime() {
		int min = (length % 3600) / 60;
		int sec = length % 60;
		return String.format("%01d:%02d", min, sec);
	}
}
