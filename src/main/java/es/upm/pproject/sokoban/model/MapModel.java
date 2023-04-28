package es.upm.pproject.sokoban.model;

import java.io.IOException;
import java.util.Deque;

public interface MapModel {

	// Enums
	public enum MapElement {
		EMPTY, WALL, WAREHOUSE_MAN, GOAL, BOX, BOX_ON_GOAL, MAN_ON_GOAL
	}
	// Methods

	// This method that returns the map element of the actual map
	public MapElement[][] getLevelMap();

	// This method set the map element
	public void setLevelMap(MapElement[][] map);

	// This method returns the dimensions of the actual map
	public Pair getMapDimensions();

	// This method set the dimensions of the actual map
	public void setMapDimensions(Pair p);

	// This method returns the name of the actual level
	public String getMapLevelName();

	// This method set the name of the actual level
	public void setMapLevelName(String s);

	// This method creates the new map element
	public boolean createLevelMap(int numLevel) throws IOException;

	// This method returns if the number of the level we are trying to create exists
	public boolean numberValidator(int numLevel);
	
	//This method returns true if all boxes are on the goals positions 
	public boolean levelFinished();
	
	//This method adds the previous movement to the movements done
	public void addMovementQueue(boolean box, int mov);
	
	//Getter that returns the movements done
	public Deque<Integer> getMovementQueue();
	
	//Getter that returns the movement that has moved boxes
	public Deque<Boolean> getBoxQueue();
	
	//Set a new queue of movements, this will be used in load a started game
	public void setMovementQueue(Deque<Integer> cola);
	
	//Set a new queue of boxes moved, this will be used in load a started game
	public void setBoxQueue(Deque<Boolean> cola);
	
	//This method return the last movement if exists
	public int getLastMovement();

	//This method return the last movement if exists
	public boolean getLastBox();



}