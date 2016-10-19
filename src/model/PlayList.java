package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class PlayList implements TableModel{
	
	private ArrayList<Song> playlistLibrary;
	private static final List<String> columnNames = Collections.unmodifiableList(Arrays.asList(new String[] { "Artist", "Title", "Length"}));

	public PlayList(ArrayList<Song> input){
		playlistLibrary = input;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.playlistLibrary.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return columnNames.get(columnIndex);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 0){ 
			//first column = artist
			return String.class;
		}
		else if(columnIndex == 1){ 
			//second column = title
			return String.class;
		}
		else if(columnIndex == 2){ 
			//third column = length
			return Integer.class;
		}
		else{
			//invalid input
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Song song = this.playlistLibrary.get(rowIndex);
		if(columnIndex == 0){ 
			//first column = artist
			return song.getArtist();
		}
		else if(columnIndex == 1){ 
			//second column = title
			return song.getTitle();
		}
		else if(columnIndex == 2){ 
			//third column = length
			return song.getLength();
		}
		else{
			//invalid input
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

}
