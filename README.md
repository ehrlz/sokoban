# sokoban
Sokoban is a jigsaw puzzle in which the goal is to move boxes from their original place to a goal position in a small warehouse. 

## Sokoban.java
Initializes the logger and the controller at the start of the game.

## ControllerImpl.java
Master class of the program. It controls the game loop (phases, end, movement, etc...). Creates the rest of components and manages them (except save-load operations). Calls models to select and load maps, calls view to show the situation on screen (loading new map or updating it for represent movement). Attends KeyBoardDetecter calls, and calls MovementModel to update the situation and calls back view to update the screen.

### Public methods:
- public void start() throws IOException;
    - This method starts the game, it is only executed one time.
- public void execGame() throws IOException;
    - This method will be called when a new map is created to handle back and front-end.
- public void mov(int i);
    - Updates the map and the actual score level/game when a movement is detected
- public void newGame();
    - Starts a new game.
- public void restart();
    - Restarts the current level.

### Private methods:
- private void checkLevelDone() throws IOException;
    - Checks if the level is completed and starts the next one.
- private void end();
    - Ends the game and displays the message "All levels finished!".

## IOController.java
Auxiliary class that offers support for loading and saving. Handles MenuViewImpl calls for file actions.

### Public methods:
- public void setComponents(View view, GameModel gameModel, MapModel mapModel);
    - Sets the components to work with.
- public void save(String file);
    - Saves the actual game.
- public void load(File file);
    - Loads game from a given file.

### Private methods:
- private void saveGame(String fileName);
    - Saves the current game into an xml file.
- private void loadGame(File file);
    - Uses an xml file to load a saved game.

## FileInfoModel.java:
This class handles the load and save options, it just has getters and setters for all the necesary data that needs to be saved/loaded.

### Methods:
- public String getMapLevelName();
    - Getter of mapLevelName.
- public MapElement[][] getLevelMap();
    - Getter of levelMap.
- public int getActualLevel();
    - Getter of actualLevel.
- public int getLevelScore();
    - Getter of levelScore.
- public int getGameScore();
    - Getter of gameScore.
- public void setMapLevelName(String mapLevelName);
    - Setter of mapLevelName.
- public void setLevelMap(MapElement[][] levelMap);
    - Setter of levelMap.
- public void setActualLevel(int actualLevel);
    - Setter of actualLevel.
- public void setLevelScore(int levelScore);
    - Setter of levelScore.
- public void setGameScore(int gameScore);
    - Setter of gameScore;
- public Deque<Integer> getMovementQueue();
    - Getter of movementQueue;
- public void setMovementQueue(Deque<Integer> movementQueue);
    - Setter of movementQueue.
- public Deque<Boolean> getBoxQueue();
    - Getter of boxQueue;
- public void setBoxQueue(Deque<Boolean> boxQueue);
    - Setter of boxQueue.

## GameModelImpl.java
This class has all the information about the current game, mainly the actual number level and the level/game score. This information will be updated from the controller.

### Methods:
- public int getActualLevel();
    - Getter of actualLevel.
- public void setActualLevel(int actualLevel);
    - Setter of actualLevel.
- public int getLevelScore();
    - Getter of levelScore.
- public void setLevelScore(int levelScore);
    - Setter of levelScore.
- public int getGameScore();
    - Getter of gameScore.
- public void setGameScore(int gameScore);
    - Setter of gameScore.

## MapModelImpl.java
This class loads a new map and has all the information about the new level. It has the actual map and the queue of the movements done.
  
### Public methods:
- public enum MapElement;
    - Lists all the elements (EMPTY, WALL, WAREHOUSE_MAN, GOAL, BOX, BOX_ON_GOAL, MAN_ON_GOAL).
- public MapElement[][] getLevelMap();
    - Getter of levelMap.
- public void setLevelMap(MapElement[][] map);
    - Setter of levelMap.
- public Pair getMapDimensions();
    - Getter of mapDimensions.
- public void setMapDimensions(Pair p);
    - Setter of mapDimensions.
- public String getMapLevelName();
    - Getter of mapLevelName.
- public void setMapLevelName(String s);
    - Setter of mapLevelName.
- public boolean createLevelMap(int numLevel) throws IOException;
    - Creates a new map element.
- public boolean numberValidator(int numLevel);
    - Checks if the number of the level we are trying to create already exists.
- public boolean levelFinished();
    - Returns true if all the boxes are on the goal positions.
- public void addMovementQueue(boolean box, int mov);
    - Adds the previous movement to the movements done.
- public Deque<Integer> getMovementQueue();
    - Getter of movementQueue (movements done).
- public Deque<Boolean> getBoxQueue();
    - Getter of boxQueue (boxes moved).
- public void setMovementQueue(Deque<Integer> cola);
    - Setter of a new queue of movements (used when loading a started game).
- public void setBoxQueue(Deque<Boolean> cola);
    - Setter of a new queue of moved boxes (used when loading a started game).
- public int getLastMovement();
    - returns the last movement of the queue.
- public int getBoxMovement();
    - returns the last moved box of the queue.

### Private methods:
- private boolean mapValidator(int numBox, int numGoal, int numWarehouseMan);
    - Checks if (1) there is at least one box and one goal, (2) there is only one warehouse man and (3) there are the same number of boxes and goal positions.

## MovementModelImpl.java
This class just checks if the movement required by the user is valid, if so, returns the map updated with that movement done.

### Public methods:
- public MapElement[][] goUp(MapElement[][] map);
    - Updates the map if possible, moves the warehouse man one step up and returns the updated map.
- public MapElement[][] goDown(MapElement[][] map);
    - Updates the map if possible, moves the warehouse man one step down and returns the updated map.
- public MapElement[][] goLeft(MapElement[][] map);
    - Updates the map if possible, moves the warehouse man one step left and returns the updated map.
- public MapElement[][] goRight(MapElement[][] map);
    - Updates the map if possible, moves the warehouse man one step right and returns the updated map.
- public MapElement[][] undo(boolean box,int mov,MapElement[][] map);
    - Updates the map applying the reverse move.
- public boolean mapChanged();
    - Returns true if the last movement done was valid and the map has changed.
- public boolean boxMoved();
    - Returns true if the last movement done has moved a box.

### Private methods:
- private void undoBox(Pair coord);
    - This method makes the changes for the map provided with the reverse movement for boxes.
- private MapElement[][] doMov(Pair coord1,Pair coord2);
    - This method makes the changes for the map provided with the movement required by the user.
- private void checkSquare(Pair coord, MapElement[][] resMap);
    - This method update the position of the warehouse man if the new square is empty or a goal position.
- private MapElement[][] checkBox(Pair coord1, Pair coord2);
    - This method update the position of the box that is going to be pushed by the warehouse man if its an empty square or a goal position.
- private Pair validCoord(int row, int column);
    - This method checks if the square that is going to be occupied by the warehouse man or a box is on the map dimensions or is not a wall.
- private void findCharacter();
    - This method finds the warehouse man on the map provided.

## Pair.java
This is an auxiliar class, it is a pair with 2 coordinates (x,y) that helps working with the matrix.
 
### Methods:
- public static Pair createPair(int element0, int element1);
    - Creates a pair of int values.
- public int getElement0();
    - Getter of first pair's value.
- public int getElement1();
    - Getter of second pair's value.
- public boolean equals(Object o);
- public int hashCode();

## ViewImpl.java
Shows on screen for user the situation of the game.

### Methods:
- public void initComponents();
    - Initializes components of the view and makes them visible on screen.
- public void show();
    - Show GUI on screen.
- public void blockMov();
    - Unloads listeners while loading new map.
- public void loadMap(MapElement[][] map, Pair dim, int gameScore, String levelName);
    - Loads data on screen.
- public void updateMap(MapElement[][] map);
    - Updates map data on screen.
- public void updateScore(int actualLvlScore, int totalLvlScore);
    - Updates score data on screen.
- public void updateNameLevel(String levelName);
    - Updates name of the current level.
- public void errorLoadingMap();
    - Notifies user map has an error and it hasn't been loaded.
- public void end(int score);
    - Notifies user map has passed the game.


## KeyboardDetecter.java
Auxiliary method of ViewImpl. Detects pressed keys and call controller for move the character.

### Methods:
- public void keyPressed(KeyEvent e);
    - Tells the controller which key has been pressed.

## MenuViewImpl.java
Auxiliary method of ViewImpl. Creates and manages menu buttons (save game, load, restart level, undo movement...).

### Methods:
- public void initMenu(JFrame window);
    - Creates a JMenuBar object (menuBar), a JMenu("File") object (menu) and 6 JMenuItem objects (newGame, restart, undo, save, load, close).