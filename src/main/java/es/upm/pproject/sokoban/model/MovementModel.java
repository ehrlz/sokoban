package es.upm.pproject.sokoban.model;
import es.upm.pproject.sokoban.model.MapModel.MapElement;

public interface MovementModel{

	//Methods
	//This method update the map if it is possible and moves the warehouse man one step up
	//And returns the map updated
	public MapElement[][] goUp(MapElement[][] map);
	
	//This method update the map if it is possible and moves the warehouse man one step down
	//And returns the map updated
	public MapElement[][] goDown(MapElement[][] map);
	
	//This method update the map if it is possible and moves the warehouse man one step left
	//And returns the map updated
	public MapElement[][] goLeft(MapElement[][] map);
	
	//This method update the map if it is possible and moves the warehouse man one step right
	//And returns the map updated
	public MapElement[][] goRight(MapElement[][] map);
	
	//This method applies the reverse movement
	//And returns the map updated
	public MapElement[][] undo(boolean box,int mov,MapElement[][] map);
	
	//This method returns true if the movement done has moved a box
	public boolean boxMoved();
	
	//This method returns true if the last movement done was valid and the map has changed
	public boolean mapChanged();
}
