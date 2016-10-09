package model;

public class JukeBox {
	private Song[] songList;
	private boolean IS_VALIDATED;
	
	public JukeBox() {
		initializeJukeBox();
		test();
	}
	
	private void initializeJukeBox() {
		setSongList();
		IS_VALIDATED = false;
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
	
	// Testing purposes only
	private void test() {
		User Niven = new User("Niven", "1");
		System.out.println(Niven.getTime());
		
	}
}
