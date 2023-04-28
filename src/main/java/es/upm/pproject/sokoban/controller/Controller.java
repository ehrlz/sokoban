package es.upm.pproject.sokoban.controller;

import java.io.IOException;

public interface Controller {

	// Methods

	// This method starts the game, it is only executed one time
	public void start() throws IOException;

	// This method will be called when a new map is created to handle back and
	// front-end
	public void execGame() throws IOException;

	// This method is called when a movement is done and it updates the map and the
	// actual score level/game
	public void mov(int i);

	// This method will restart the actual level
	public void restart();

	// This method will start a new game
	public void newGame();
}
