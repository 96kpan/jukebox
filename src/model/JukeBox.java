package model;

public class JukeBox {

	private Song[] songList;
	private User[] userList;
	private boolean IS_VALIDATED;
	private User thisUser;
	private Song thisSong;

	public JukeBox() {
		initializeJukeBox();
		test();
	}

	private void initializeJukeBox() {
		setSongList();
		setUserList();
		IS_VALIDATED = false;


	}

	//current user login info -> login screen
	public void validate(String user, String password, Song song) {
		if(user.equals("Chris")) 
			if(password.equals("1")){
				thisUser = userList[0];
				validateUser(song);
			}
		if(user.equals("Devon")) 
			if(password.equals("22")) {
				thisUser = userList[1];
				validateUser(song);
			}
		if(user.equals("River")) 
			if(password.equals("333")){
				thisUser = userList[2];
				validateUser(song);
			}
		if(user.equals("Ryan")) 
			if(password.equals("4444")){
				thisUser = userList[3];
				validateUser(song);
			}
	}

	//checks if 
	private void validateUser(Song chosenSong) {
		if(thisUser.canPlay() && thisUser.getSeconds() >= chosenSong.getLength()){
			thisUser.negateTime(chosenSong.getLength());
			IS_VALIDATED = true;
			return;
		}
		
		this.IS_VALIDATED = false;
		
	}

	// Sets the songList array to contain all possible songs

	private void setSongList() {
		songList = new Song[9];
		songList[0] = new Song("Kevin MacLeod", "Danse Macabre", "./songfiles/DanseMacabreViolinHook.mp3", 34);
		songList[1] = new Song("FreePlay Music", "Determined Tumbao", "DeterminedTumbao.mp3", 20);
		songList[2] = new Song("Sun Microsystems", "Flute", "flute.aif", 5);
		songList[3] = new Song("Kevin MacLeod", "Loping Sting", "LopingSting.mp3", 4);
		songList[4] = new Song("Unknown", "Space Music", "spacemusinc.au", 6);
		songList[5] = new Song("FreePlay Music", "Swing Cheese", "SwingCheese.mp3", 15);
		songList[6] = new Song("Microsof", "Tada", "tada.wav", 2);
		songList[7] = new Song("Kevin MacLeod", "The Curtain Rises", "TheCurtainRises.mp3", 28);
		songList[8] = new Song("Pierre Langer", "Untameable Fire", "UntameableFire.mp3", 282);
	}

	private void setUserList() {
		userList = new User[4];
		userList[0] = new User("Chris", "1");
		userList[1] = new User("Devon", "22");
		userList[2] = new User("River", "333");
		userList[3] = new User("Ryan", "4444");
	}

	// Testing purposes only
	private void test() {
		User Niven = new User("Niven", "1");
		System.out.println(Niven.getTime());

	}
}