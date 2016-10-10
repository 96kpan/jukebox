/*
 *oct 10 -> 11:54
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
	private int year;
	private LocalDate date;
	
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
	
	public boolean canPlay() {
		checkDay();
		if(play_status < 3)
			return true;
		return false;
	}
	
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
	
	public int getPlayStatus(){
		return this.play_status;
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
