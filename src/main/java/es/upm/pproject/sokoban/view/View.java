package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.model.Pair;
import es.upm.pproject.sokoban.model.MapModel.MapElement;

public interface View {

    // Atributes & getters

    // Methods
	
	// Iniitialize components of the view and makes it visible on screen
    public void initComponents(); 

    // show GUI on screen
    public void show(); 
    
    // unload listeners while loading new map
    public void blockMov(); 

    // Load data on screen
    public void loadMap(MapElement[][] map, Pair dim, int gameScore, String levelName); 

    // Update map data on screen
    public void updateMap(MapElement[][] map); 
    
    // Update score data on screen
    public void updateScore(int actualLvlScore, int totalLvlScore); 
    
    // Update name of the actual level
    public void updateNameLevel(String levelName);

    // Notifies user map has an error and it hasn't been loaded
    public void errorLoadingMap(); 
    
    // Notifies user map has passed the game
    public void end(int score); 
}
