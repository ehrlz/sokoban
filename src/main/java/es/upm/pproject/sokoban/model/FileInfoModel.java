package es.upm.pproject.sokoban.model;

import java.util.Deque;

import javax.xml.bind.annotation.XmlRootElement;

import es.upm.pproject.sokoban.model.MapModel.MapElement;

//class "FileInfoModel.java" is the root-element of the file being saved
@XmlRootElement(namespace = "es.upm.pproject.model")

public class FileInfoModel {

	// XmLElementWrapper generates a wrapper element around XML representation
	// @XmlElementWrapper(name = "Map data")
	// XmlElement sets the name of the entities
	// @XmlElement(name = "Map")
	private String mapLevelName;
	private MapElement[][] levelMap;
	private Deque<Integer> movementQueue;
	private Deque<Boolean> boxQueue;

	// @XmlElementWrapper(name = "Game data")
	// @XmlElement(name = "Game")
	private int actualLevel;
	private int levelScore;
	private int gameScore;

	public String getMapLevelName() {
		return mapLevelName;
	}

	public MapElement[][] getLevelMap() {
		return levelMap;
	}

	public int getActualLevel() {
		return actualLevel;
	}

	public int getLevelScore() {
		return levelScore;
	}

	public int getGameScore() {
		return gameScore;
	}

	public void setMapLevelName(String mapLevelName) {
		this.mapLevelName = mapLevelName;
	}

	public void setLevelMap(MapElement[][] levelMap) {
		this.levelMap = levelMap;
	}

	public void setActualLevel(int actualLevel) {
		this.actualLevel = actualLevel;
	}

	public void setLevelScore(int levelScore) {
		this.levelScore = levelScore;
	}

	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}

	public Deque<Integer> getMovementQueue() {
		return movementQueue;
	}

	public void setMovementQueue(Deque<Integer> movementQueue) {
		this.movementQueue = movementQueue;
	}
	
	public Deque<Boolean> getBoxQueue() {
		return boxQueue;
	}

	public void setBoxQueue(Deque<Boolean> boxQueue) {
		this.boxQueue = boxQueue;
	}
}
