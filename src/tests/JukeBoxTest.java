/*	Jukebox Iteration 2: The View
 *	Authors: Katie Pan & Niven Francis
 *
 *	Section Leaders: Bree Collins & Cody Macdonald
 *	Due: 10/21/16
 *	
 *	Last Edited: 10/20 22:15
 *
 *	JukeBoxTest.java-------------------------------
 *	|
 *	|	Tests certain parts of all the classes in	
 *	|	the model package to give 90.1% EclEmma
 *	}	coverage.
 *	|
 *
 */

package tests;

import model.JukeBox;
import model.Song;
import model.User;

import static org.junit.Assert.*;

import org.junit.Test;

public class JukeBoxTest {
	@Test
	public void test() {
		JukeBox jukeBox = JukeBox.getInstance();
		User user = new User("Chris", "1");
		assertTrue(jukeBox.validate(user.getName(), user.getPassword()));
		assertFalse(jukeBox.validate(user.getName(), "22"));
		Song inputSong = jukeBox.getSong(4);
		if(jukeBox.validate(user.getName(), user.getPassword(), inputSong)){
			assertEquals("25:00:00",jukeBox.getUser().getTime());
			assertEquals("Status: 0 played, 25:00:00", jukeBox.getUser().labelString());
			assertEquals("Unknown", jukeBox.getSong(4).getArtist());
			jukeBox.playSong(inputSong);
			jukeBox.playSong(inputSong);
			jukeBox.playSong(inputSong);
			assertFalse(jukeBox.getSong(4).canPlay());
			assertEquals(3, jukeBox.getUser().getStatus());
			assertFalse(jukeBox.getUser().canPlay());
			assertFalse(jukeBox.validate(jukeBox.getUser().getName(), "22", inputSong));
		}
		assertEquals(user.getName(), jukeBox.getUser().getName());
	}
}
