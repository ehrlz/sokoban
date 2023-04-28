package es.upm.pproject.sokoban.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Game")
// order of appearance
@XmlType(propOrder = { "actualLevel", "levelScore", "gameScore" })

public class GameModelImpl implements GameModel {

	private int actualLevel;

	public int getActualLevel() {
		return actualLevel;
	}

	public void setActualLevel(int actualLevel) {
		this.actualLevel = actualLevel;
	}

	private int levelScore;

	public int getLevelScore() {
		return levelScore;
	}

	public void setLevelScore(int levelScore) {
		this.levelScore = levelScore;
	}

	private int gameScore;

	public int getGameScore() {
		return gameScore;
	}

	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}

	public GameModelImpl() {
		this.actualLevel = 1;
		this.gameScore = 0;
		this.levelScore = 0;
	}

}
