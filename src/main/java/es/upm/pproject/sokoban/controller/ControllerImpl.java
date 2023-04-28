package es.upm.pproject.sokoban.controller;

import java.io.IOException;

import es.upm.pproject.sokoban.model.GameModel;
import es.upm.pproject.sokoban.model.GameModelImpl;
import es.upm.pproject.sokoban.model.MapModel;
import es.upm.pproject.sokoban.model.MapModelImpl;
import es.upm.pproject.sokoban.model.MovementModel;
import es.upm.pproject.sokoban.model.MovementModelImpl;
import es.upm.pproject.sokoban.model.Pair;
import es.upm.pproject.sokoban.model.MapModel.MapElement;
import es.upm.pproject.sokoban.view.View;
import es.upm.pproject.sokoban.view.ViewImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerImpl implements Controller {

	private static final Logger logger = LoggerFactory.getLogger(ControllerImpl.class);

	private View view;
	private MapModel mapModel;
	private MovementModel movementModel;
	private GameModel gameModel;

	// Auxiliary controller for load and save
	private IOController ioController;

	// Constructor
	public ControllerImpl() {
		this.ioController = new IOControllerImpl();
		
		this.view = new ViewImpl(this, ioController);
		this.mapModel = new MapModelImpl();
		this.movementModel = new MovementModelImpl();
		this.gameModel = new GameModelImpl();

		ioController.setComponents(view, gameModel, mapModel);
	}

	// Public Methods
	public void start() throws IOException {
		execGame();
	}

	/*
	 * This method will be called when a new map is created to handle back and
	 * front-end
	 */
	public void execGame() throws IOException {
		int actualLevel = gameModel.getActualLevel();
		if (!mapModel.numberValidator(actualLevel)) {
			end();
		}
		if (mapModel.createLevelMap(actualLevel)) {
			logger.info("Map number {} created.", actualLevel);
			MapElement[][] mapLevel = mapModel.getLevelMap();
			Pair mapDimensions = mapModel.getMapDimensions();
			view.loadMap(mapLevel, mapDimensions, gameModel.getGameScore(), mapModel.getMapLevelName());
			gameModel.setLevelScore(0);
			view.show();
		} else {
			logger.error("Error loading map number {}.", actualLevel);
			// shows error on view
			view.errorLoadingMap();
			gameModel.setActualLevel(++actualLevel);
			execGame();
		}

	}

	public void mov(int i) {
		MapElement[][] matrix = null;
		switch (i) {
			case 1:
				matrix = movementModel.goUp(mapModel.getLevelMap());
				break;
			case 2:
				matrix = movementModel.goDown(mapModel.getLevelMap());
				break;
			case 3:
				matrix = movementModel.goLeft(mapModel.getLevelMap());
				break;
			case 4:
				matrix = movementModel.goRight(mapModel.getLevelMap());
				break;
			default:
				break;
		}
		if (i == 5) {
			if (gameModel.getLevelScore() > 0) {
				int mov=mapModel.getLastMovement();
				boolean box=mapModel.getLastBox();
				matrix = movementModel.undo(box,mov, mapModel.getLevelMap());
				mapModel.setLevelMap(matrix);
				gameModel.setGameScore(gameModel.getGameScore() - 1);
				gameModel.setLevelScore(gameModel.getLevelScore() - 1);
				view.updateMap(matrix);
				view.updateScore(gameModel.getLevelScore(), gameModel.getGameScore());
			}
		} else {
			if (movementModel.mapChanged()) {
				mapModel.addMovementQueue(movementModel.boxMoved(), i);
				mapModel.setLevelMap(matrix);
				gameModel.setGameScore(gameModel.getGameScore() + 1);
				gameModel.setLevelScore(gameModel.getLevelScore() + 1);
				view.updateMap(matrix);
				view.updateScore(gameModel.getLevelScore(), gameModel.getGameScore());
			}
		}
		try {
			checkLevelDone();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public void restart() {
		logger.info("Restart map!");
		int actualLevel = gameModel.getActualLevel();
		try {
			view.blockMov();
			gameModel.setGameScore(gameModel.getGameScore() - gameModel.getLevelScore());
			gameModel.setLevelScore(0);
			mapModel.createLevelMap(actualLevel);
			MapElement[][] mapLevel = mapModel.getLevelMap();
			Pair mapDimensions = mapModel.getMapDimensions();
			view.loadMap(mapLevel, mapDimensions, gameModel.getGameScore(), mapModel.getMapLevelName());
			view.show();
		} catch (IOException e) {
			logger.error("Error while restarting map.");
			logger.debug(e.getMessage());
		}
	}

	public void newGame() {
		logger.info("new game!");
		gameModel.setActualLevel(1);
		int actualLevel = gameModel.getActualLevel();
		try {
			view.blockMov();
			gameModel.setGameScore(0);
			gameModel.setLevelScore(0);
			mapModel.createLevelMap(actualLevel);
			MapElement[][] mapLevel = mapModel.getLevelMap();
			Pair mapDimensions = mapModel.getMapDimensions();
			view.loadMap(mapLevel, mapDimensions, gameModel.getGameScore(), mapModel.getMapLevelName());
			view.show();
		} catch (IOException e) {
			logger.error("Error while restarting map.");
			logger.debug(e.getMessage());
		}
	}

	// Private methods

	private void checkLevelDone() throws IOException {
		if (mapModel.levelFinished()) {
			logger.info("Level Completed!.");
			view.blockMov();
			int actualLevel = gameModel.getActualLevel();
			gameModel.setActualLevel(++actualLevel);
			execGame();
		}
	}

	private void end() {
		logger.info("All levels finished!");
		view.end(gameModel.getGameScore());
		System.exit(0);
	}
}
