package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Deque;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import es.upm.pproject.sokoban.model.MapModel;
import es.upm.pproject.sokoban.model.MapModel.MapElement;
import es.upm.pproject.sokoban.model.MapModelImpl;
import es.upm.pproject.sokoban.model.Pair;

public class MapTest {

	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	@TestMethodOrder(OrderAnnotation.class) 
	@DisplayName("Map creation tests.")
	class mapCreation{
		
		private MapModel mapModel;
		
		@BeforeAll
		void initMapCreation() {
			
			mapModel = new MapModelImpl();
		}
		
		@Test
		@Order(1)
	    @DisplayName("Map creation back-end (inner map matrix), level 1.")
	    void matrixCreation_lvl1() throws IOException {
	        
			boolean res = mapModel.createLevelMap(1);
	    	
	    	MapModel.MapElement [][] levelMap_res = mapModel.getLevelMap();
	    	
	    	assertTrue(res);
	    	assertArrayEquals(new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
	    											{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
	    											{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
	    											{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
	    											{MapElement.WALL,MapElement.WALL,MapElement.WAREHOUSE_MAN,MapElement.GOAL,MapElement.WALL,MapElement.BOX,MapElement.EMPTY,MapElement.WALL},
	    											{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
	    											{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
	    											{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}},
	    						levelMap_res);
		}
		
		@Test
		@Order(2)
		@DisplayName("Map creation dimensions, level 1.")
		void levelDimensions_lvl1() {
			Pair mapDimensions_res = mapModel.getMapDimensions();
			assertEquals(mapDimensions_res, new Pair(8, 8));
		}
		
		@Test
		@Order(3)
		@DisplayName("Map creation name, level 1.")
		void levelName_lvl1() {
			String mapName_res = mapModel.getMapLevelName();
			assertEquals("Initial level",mapName_res);
		}
		
		@Test
		@Order(4)
	    @DisplayName("Wrong Map creation back-end (inner map matrix), level 2.")
	    void matrixCreation_lvl2() throws IOException {
			
			MapModel mapModel = new MapModelImpl();
	        
			boolean res = mapModel.createLevelMap(3);
	    	assertFalse(res);
	    }
		
		@Test
		@Order(5)
	    @DisplayName("Try to load an existing number of a map")
	    void noExistingNumberLevel() throws IOException {
			boolean res = mapModel.numberValidator(4);
			assertTrue(res);
			res = mapModel.numberValidator(7);
	    	assertFalse(res);
	    	assertFalse(mapModel.levelFinished());
	    }
		
		@Test
		@Order(6)
	    @DisplayName("getters and setters")
	    void gettersSetters() throws IOException {
			Pair dimension=mapModel.getMapDimensions();
			String name=mapModel.getMapLevelName();
			MapElement[][]mapa=mapModel.getLevelMap();
			Deque<Integer>colaMov=mapModel.getMovementQueue();
			Deque<Boolean>colaBox=mapModel.getBoxQueue();
			mapModel.addMovementQueue(true,1);
			mapModel.setMapDimensions(mapModel.getMapDimensions());
			mapModel.setMapLevelName(mapModel.getMapLevelName());
			mapModel.setLevelMap(mapModel.getLevelMap());
			mapModel.setBoxQueue(mapModel.getBoxQueue());
			mapModel.setMovementQueue(mapModel.getMovementQueue());
			assertEquals(dimension, mapModel.getMapDimensions());
			assertEquals(name, mapModel.getMapLevelName());
			assertEquals(mapa, mapModel.getLevelMap());
			assertEquals(colaBox, mapModel.getBoxQueue());
			assertEquals(colaMov, mapModel.getMovementQueue());
	    	assertFalse(mapModel.levelFinished());
			mapModel.getLastMovement();
			mapModel.getLastBox();
	    }
	}
}
