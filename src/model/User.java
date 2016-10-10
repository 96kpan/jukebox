/*
 *oct 9 -> 11:57pm
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
	private LocalDate date;
	private String time;
	
	public User(String name, String pw) {
		this.acc_name = name;
		this.password = pw;
		this.play_status = 0;
		this.total_sec = 86400;
		date = LocalDate.now();
		day = date.getDayOfYear();
		setTime();
	}
	
	private void setTime() {
		hour = total_sec / 3600;
		min = (total_sec % 3600) / 60;
		sec = total_sec % 60;
		time = String.format("%02d:%02d:%02d", hour, min, sec);
	}
	
	public int getStatus() {
		return play_status;
	}
	
	public String getName() {
		return acc_name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void incStatus() {
		this.play_status++;
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
	
	public int getSeconds(){
		return total_sec;
	}

	public int getDay() {
		return day;
	}
	
	public String getTime() {
		return time;
	}
	
	public void negateTime(int seconds) {
		total_sec -= seconds;
	}
	
	public void negateTime(Song s) {
		int songSeconds = s.getLength();
		total_sec -= songSeconds;
		setTime();
	}
	
	public String labelString(){
		return "Status: " + this.getStatus() + ", " + this.getTime();
	}
}