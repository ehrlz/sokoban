package es.upm.pproject.sokoban.model;

public interface GameModel {

	//Methods
	
	//Getter of the actual level that has been played
	public int getActualLevel();
	
	//Setter of the actual level
	public void setActualLevel(int actualLevel);
	
	//Getter of the actual level score
	public int getLevelScore();
	
	//Setter of the actual level score
	public void setLevelScore(int levelScore);
	
	//Getter of the game score
	public int getGameScore();
	
	//Setter of the game score
	public void setGameScore(int gameScore);
}