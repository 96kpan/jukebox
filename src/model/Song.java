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
}
