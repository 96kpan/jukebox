package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class SongQueue implements ListModel{
	
	private Queue<Song> songList;
	
	public SongQueue(Queue<Song> queue){
		songList = new LinkedList<Song>();
		songList.addAll(queue);
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return songList.size();
	}

	@Override
	public Song getElementAt(int index) {
		// TODO Auto-generated method stub
		System.out.println("getELemenet " + ((LinkedList<Song>) songList).get(index));
		return ((LinkedList<Song>) songList).get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		
	}
	
	public void addSong(Song s){
		songList.add(s);
	}

}
