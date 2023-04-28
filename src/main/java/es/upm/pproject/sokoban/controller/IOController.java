package es.upm.pproject.sokoban.controller;

import java.io.File;
import es.upm.pproject.sokoban.model.GameModel;
import es.upm.pproject.sokoban.model.MapModel;
import es.upm.pproject.sokoban.view.View;

public interface IOController {
	// This methods sets the components to work with
	public void setComponents(View view, GameModel gameModel, MapModel mapModel);

	// This method will save the actual game
	public void save(String file);

	// This method will load the game from a file
	public void load(File file);
}
