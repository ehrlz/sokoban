package es.upm.pproject.sokoban.model;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement(name = "Map")
// order of appearance
@XmlType(propOrder = { "mapLevelName", "levelMap", "movementQueue","boxQueue" })

public class MapModelImpl implements MapModel {

	private static final Logger logger = LoggerFactory.getLogger(MapModelImpl.class);

	// Atributes & getters

	private MapElement[][] levelMap;

	public MapElement[][] getLevelMap() {
		return levelMap;
	}

	public void setLevelMap(MapElement[][] map) {
		levelMap = map;
	}

	private Pair mapDimensions;

	public Pair getMapDimensions() {
		return mapDimensions;
	}

	public void setMapDimensions(Pair p) {
		mapDimensions = p;
	}

	private String mapLevelName;

	public String getMapLevelName() {
		return mapLevelName;
	}

	public void setMapLevelName(String s) {
		mapLevelName = s;
	}

	// Constructor
	public MapModelImpl() {
		mapLevelName = "";
	}

	// Public Methods
	public boolean createLevelMap(int numLevel) throws IOException {
		Path pathLevel = Paths.get("levelFiles", "level_" + numLevel + ".txt");

		int numBox = 0;
		int numGoal = 0;
		int numWarehouseMan = 0;
		MapElement[][] mapRes = new MapElement[0][0];

		FileReader fr = new FileReader(pathLevel.toString());
		BufferedReader bufReader = new BufferedReader(fr);

		try {
			String line;
			mapLevelName = bufReader.readLine();

			// nRows <white_space> nColumns <new_line>
			line = bufReader.readLine();
			int nRows = Character.getNumericValue(line.charAt(0));
			int nColumns = Character.getNumericValue(line.charAt(2));

			mapDimensions = new Pair(nRows, nColumns);
			mapRes = new MapElement[nRows][nColumns];

			// character readen from the txt level file
        	for (int i = 0; i < nRows; i++) {
        	    line = bufReader.readLine();
        	    for (int j = 0; j < nColumns; j++) {
        	        char c = line.charAt(j);
        	        switch (c) {
        	            case '+':
        	                mapRes[i][j] = MapElement.WALL;
        	                break;
        	            case '.':
        	                mapRes[i][j] = MapElement.EMPTY;
        	                break;
        	            case '*':
        	                numGoal++;
        	                mapRes[i][j] = MapElement.GOAL;
        	                break;
        	            case '#':
        	                numBox++;
        	                mapRes[i][j] = MapElement.BOX;
        	                break;
        	            case 'W':
        	                numWarehouseMan++;
        	                mapRes[i][j] = MapElement.WAREHOUSE_MAN;
        	                break;
        	            default:
        	                break;
        	        }
        	    }
        	}

		} catch (IOException e) {
			logger.error("Error while parsing the map of level number {}.", numLevel);
		} finally {
			bufReader.close();
		}

		if (mapValidator(numBox, numGoal, numWarehouseMan)) {
			levelMap = mapRes;
			movementQueue = new LinkedList<>();
			boxQueue=new LinkedList<>();
			return true;
		} else {
			return false;
		}
	}

	public boolean numberValidator(int numLevel) {
		Path pathLevel = Paths.get("levelFiles", "level_" + numLevel + ".txt");
		try {
			FileReader a = new FileReader(pathLevel.toString());
			BufferedReader bufReader = new BufferedReader(a);
			bufReader.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	// If there is a box that is not on a game position, it must not finish
	public boolean levelFinished() {
		boolean res = true;
		for (int i = 0; i < mapDimensions.getElement0() && res; i++) {
			for (int j = 0; j < mapDimensions.getElement1() && res; j++) {
				if (levelMap[i][j] == MapElement.BOX) {
					res = false;
				}
			}
		}
		return res;
	}

	private Deque<Integer> movementQueue;
	private Deque<Boolean> boxQueue;

	public void addMovementQueue(boolean box,int mov) {
		movementQueue.add(mov);
		boxQueue.add(box);
	}

	// Getter that returns the movements done
	public Deque<Integer> getMovementQueue() {
		return movementQueue;
	}
	
	// Getter that returns the boxes moved
	public Deque<Boolean> getBoxQueue() {
		return boxQueue;
	}

	// Getter that returns the movements done
	public void setMovementQueue(Deque<Integer> cola) {
		this.movementQueue = cola;
	}
	
	// Getter that returns the movements done
	public void setBoxQueue(Deque<Boolean> cola) {
		this.boxQueue = cola;
	}

	// This method undo the last movement
	public int getLastMovement() {
		return movementQueue.removeLast();
	}
	
	// This method undo the last boxe moved
	public boolean getLastBox() {
		return boxQueue.removeLast();
	}
	/////////////////////////////////////////

	// Private Methods
	private boolean mapValidator(int numBox, int numGoal, int numWarehouseMan) {
		boolean res = true;

		/*
		 * (1) there is at least one box and one goal
		 * (2) there is only one warehouse man
		 * (3) there is the same number of boxes and goal positions
		 */
		if ((numBox < 1 && numGoal < 1) || numWarehouseMan != 1 || numBox != numGoal) {
			res = false;
		}

		return res;
	}
}
