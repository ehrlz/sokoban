package es.upm.pproject.sokoban.controller;

import java.io.File;
import java.io.StringWriter;
import java.util.Deque;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.upm.pproject.sokoban.model.FileInfoModel;
import es.upm.pproject.sokoban.model.GameModel;
import es.upm.pproject.sokoban.model.MapModel;
import es.upm.pproject.sokoban.model.Pair;
import es.upm.pproject.sokoban.view.View;
import es.upm.pproject.sokoban.model.MapModel.MapElement;

public class IOControllerImpl implements IOController {

	private static final Logger logger = LoggerFactory.getLogger(IOControllerImpl.class);

	private FileInfoModel fileInfoModel;

	private GameModel gameModel;
	private MapModel mapModel;
	private View view;

	public IOControllerImpl() {
		fileInfoModel = new FileInfoModel();
	}

	public void setComponents(View view, GameModel gameModel, MapModel mapModel) {
		this.gameModel = gameModel;
		this.mapModel = mapModel;
		this.view = view;
	}

	public void save(String file) {
		fileInfoModel.setLevelMap(mapModel.getLevelMap());
		fileInfoModel.setMapLevelName(mapModel.getMapLevelName());

		fileInfoModel.setActualLevel(gameModel.getActualLevel());
		fileInfoModel.setGameScore(gameModel.getGameScore());
		fileInfoModel.setLevelScore(gameModel.getLevelScore());

		fileInfoModel.setMovementQueue(mapModel.getMovementQueue());
		fileInfoModel.setBoxQueue(mapModel.getBoxQueue());

		saveGame(file);
	}

	public void load(File file) {
		loadGame(file);
		mapModel.setLevelMap(fileInfoModel.getLevelMap());

		String actualLevelName = fileInfoModel.getMapLevelName();
		mapModel.setMapLevelName(actualLevelName);

		// Obtain map dimensions
		MapElement[][] actualMap = fileInfoModel.getLevelMap();
		int rowsActualLvl = actualMap.length;
		int columnsActualLvl = actualMap[0].length;
		mapModel.setMapDimensions(new Pair(rowsActualLvl, columnsActualLvl));

		int actualLevelScore = fileInfoModel.getLevelScore();
		int totalLevelScore = fileInfoModel.getGameScore();
		gameModel.setActualLevel(fileInfoModel.getActualLevel());
		gameModel.setGameScore(totalLevelScore);
		gameModel.setLevelScore(actualLevelScore);

		// if there isn't queue in save, set empty on model
		Deque<Integer> movQueue;
		Deque<Boolean> boxQueue;
		if(fileInfoModel.getMovementQueue()==null) {
			movQueue = new LinkedList<>();
		}
		else {
			movQueue = fileInfoModel.getMovementQueue();
		}
		
		if(fileInfoModel.getBoxQueue()==null) {
			boxQueue = new LinkedList<>();
		}
		else {
			boxQueue = fileInfoModel.getBoxQueue();
		}
		
		mapModel.setMovementQueue(movQueue);
		mapModel.setBoxQueue(boxQueue);


		// map update from load file
		view.loadMap(actualMap, new Pair(rowsActualLvl, columnsActualLvl), totalLevelScore, actualLevelName);
		view.updateScore(actualLevelScore, totalLevelScore);
		view.blockMov();
		view.show();
	}

	private void saveGame(String fileName) {
		JAXBContext context;
		Marshaller m;

		logger.info("Saving the actual game into {}.", fileName);
		try {
			context = JAXBContext.newInstance(FileInfoModel.class);

			m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to logger
			StringWriter stringMarshalling = new StringWriter();
			m.marshal(fileInfoModel, stringMarshalling);
			logger.debug("Saved data: {}", stringMarshalling);

			// Write to File
			m.marshal(fileInfoModel, new File(fileName + ".xml"));
		} catch (JAXBException e) {
			logger.error("An error occurred while saving the game!");
			logger.debug(e.getMessage());
		}
	}

	private void loadGame(File file) {
		JAXBContext context;
		Unmarshaller unmarshaller;
		logger.info("Loading the actual game from {}.", file.getName());
		try {
			context = JAXBContext.newInstance(FileInfoModel.class);
			unmarshaller = context.createUnmarshaller();

			fileInfoModel = (FileInfoModel) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			logger.error("An error occurred while saving the game!");
			logger.debug(e.getMessage());
		}
	}
}
