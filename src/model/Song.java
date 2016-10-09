/*
 * oct 8 -> 21:54
 */
package model;

import java.time.LocalDate;

public class Song {
	private String title;
	private String fileName;
	private String artist;
	private int length; // seconds
	private int play_status;
	private int day;
	private LocalDate date;
	
	public Song(String artist, String title, String fileName, int length) {
		this.artist = artist;
		this.title = title;
		this.fileName = fileName;
		this.length = length;
		this.play_status = 0;
		date = LocalDate.now();
		day = date.getDayOfYear();
	}
	
	public boolean canPlay() {
		checkDay();
		if(play_status < 3)
			return true;
		return false;
	}
	
	private void checkDay() {
		date = LocalDate.now();
		int temp = date.getDayOfYear();
		if(day == temp)
			return;
		else {
			play_status = 0;
			day = temp;
		}
	}
	
	public void incStatus() {
		this.play_status++;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int getLength() {
		return length;
	}
	
	public String getFileName() {
		return fileName;
	}
}
