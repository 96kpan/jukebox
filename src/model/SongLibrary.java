package model;

import java.util.ArrayList;
import java.util.Queue;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class SongLibrary implements ListModel{
	
	private Queue<Song> songList;
	
	public SongLibrary(Queue<Song> queue){
		songList = queue;
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return songList.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return ((Object) songList).get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		//We need a dataListener because in the songQueue,
		//it constantly changes because we are adding elements/queueing elements
		
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		//We need a dataListener because in the songQueue,
		//it constantly changes because we are deleting elements/dequeueing elements
				
		
	}

}
