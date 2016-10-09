package model;

public class User {
	private String acc_name;
	private String password;
	private int play_status;
	
	private int hour;
	private int min;
	private int sec;
	private int total_sec;
	private String time;
	
	public User(String name, String pw) {
		this.acc_name = name;
		this.password = pw;
		this.play_status = 0;
		this.total_sec = 86400;
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
		if(play_status < 4)
			return true;
		return false;
	}
	
	public String getTime() {
		return time;
	}
}
