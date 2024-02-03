# sokoban
Sokoban is a classic puzzle game where the player controls a character who must push boxes to designated storage locations. The goal is to complete each level by strategically moving the boxes without getting stuck or blocking critical paths.

This project is a Java implementation of the Sokoban game, featuring a graphical user interface and various game mechanics.

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

## Table of Contents
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Gameplay](#gameplay)
- [Additional Features](#additional-features)
- [Contributing](#contributing)

## Project Structure
The project follows a MVC architecture, that is organized into several packages:

- es.upm.pproject.sokoban: Main package containing the Sokoban class, which serves as the entry point for the game.
- es.upm.pproject.sokoban.controller: Contains the game controller implementation (ControllerImpl) responsible for managing the game's logic.
- es.upm.pproject.sokoban.model: Includes the implementations for the game models (GameModelImpl, MapModelImpl, MovementModelImpl), representing the game state and movements.
- es.upm.pproject.sokoban.view: Contains the implementation of the graphical user interface (ViewImpl) and menu (MenuViewImpl) using Java Swing.

This architecture is introduced in a Maven project for testing and dependencies management.

## Getting started

### Requeriments
- Java: recommended 11, works 17.
- Maven: recommended 3.9.6.

### How to run
1. Open the project in your preferred Java IDE.
2. Locate the Sokoban class in the es.upm.pproject.sokoban package.
3. Run the main method within the Sokoban class.

Using Maven:
1. Go to the sokoban folder
2. Compiles the project: `mvn compile`
3. Run the program: `mvn exec:java`

## Features

### Menu options
The menu provides various options for managing the game, including starting a new game, saving, loading, restarting, undoing moves, and closing the game.

### Map creation
The game loads all *.txt* file that exist in `levelFiles/`. More levels can be added, and must follow this format:

- The first line contains the name of the level.
- The second line contains the elements of the board, *nRows* and *nColumns* of the board.
- The following *nRows* lines contains the elements of the board. Each row must contain *nColumns* with:
    - +: Wall
    - .: Empty square
    - *: Goal Position
    - #: Box
    - W: Warehouse man

#### Rules
1. At least 1 box and 1 goal position.
2. Only 1 warehouse man
3. The board must contain the same number of boxes and goal positions.

> The application will show if the level doesn't follow this rules

## Contributing
Contributions to the Sokoban project are welcome! If you find any issues or have suggestions for improvements, please create an issue or submit a pull request.
