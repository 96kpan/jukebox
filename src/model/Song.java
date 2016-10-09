package model;

public class Song {
	String title;
	String fileName;
	String artist;
	int length; // seconds
	
	public Song(String artist, String title, String fileName, int length) {
		this.artist = artist;
		this.title = title;
		this.fileName = fileName;
		this.length = length;
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
}
