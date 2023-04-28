package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.GameModel;
import es.upm.pproject.sokoban.model.GameModelImpl;

class GameTest {
	
	private static GameModel gameModel;
	
	@BeforeAll
	static void initGameCreation(){
		System.out.println("hola");
		gameModel = new GameModelImpl();
	}
	
	@Test
	@DisplayName("Get the actual number of the level")
	void actualLevelNumber() {
        assertEquals(1, gameModel.getActualLevel());
        gameModel.setActualLevel(2);
        int res = gameModel.getActualLevel();
        assertEquals(2, res);
	}
	
	@Test
	@DisplayName("Get the actual score number of the level")
	void actualScoreLevel() {
        assertEquals(0, gameModel.getLevelScore());
        gameModel.setLevelScore(1);
        assertEquals(1, gameModel.getLevelScore());
	}
	
	@Test
	@DisplayName("Get the actual score number of the game")
	void actualScoreGame() {
        assertEquals(0, gameModel.getGameScore());
        gameModel.setGameScore(1);
        assertEquals(1, gameModel.getGameScore());
	}
	
}