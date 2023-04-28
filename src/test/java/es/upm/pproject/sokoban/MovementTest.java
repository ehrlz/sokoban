package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import es.upm.pproject.sokoban.model.MapModel;
import es.upm.pproject.sokoban.model.MovementModel;
import es.upm.pproject.sokoban.model.MovementModelImpl;
import es.upm.pproject.sokoban.model.MapModel.MapElement;
import es.upm.pproject.sokoban.model.MapModelImpl;

public class MovementTest {
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	@TestMethodOrder(OrderAnnotation.class) 
	@DisplayName("Movements tests.")
	class movementsMethods{
		
		private MovementModel movement;
		private MapModel mapModel;
		private MapElement [][] map;


		@BeforeAll
		void initMapCreation() throws IOException {

			mapModel = new MapModelImpl();			
			mapModel.createLevelMap(1);
			map=mapModel.getLevelMap();
			movement= new MovementModelImpl();
		}

		@Test
		@Order(1)
		@DisplayName("GoUp method")
		void goUpTest() throws IOException {

			map = movement.goUp(map);
			map=movement.goUp(map);

			assertArrayEquals(map, new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.WAREHOUSE_MAN,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.GOAL,MapElement.WALL,MapElement.BOX,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
			
			assertTrue(movement.mapChanged());
		}

		@Test
		@Order(2)
		@DisplayName("GoDown method")
		void goDownTest() {
			map= movement.goDown(map);

			assertArrayEquals(map, new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.WAREHOUSE_MAN,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.GOAL,MapElement.WALL,MapElement.BOX,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
		}

		@Test
		@Order(3)
		@DisplayName("GoLeft method")
		void goLeftTest() {
			map = movement.goLeft(map);


			assertArrayEquals(map, new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WAREHOUSE_MAN,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.GOAL,MapElement.WALL,MapElement.BOX,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
		}

		@Test
		@Order(4)
		@DisplayName("GoRight method")
		void goRightTest() {
			map = movement.goRight(map);
			map = movement.goRight(map);
			map = movement.goRight(map);
			map = movement.goRight(map);
			map = movement.goRight(map);
			
			map = movement.goRight(map);
			map = movement.goRight(map);
			map = movement.goRight(map);


			assertArrayEquals(map, new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WAREHOUSE_MAN,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.GOAL,MapElement.WALL,MapElement.BOX,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
		}

		@Test
		@Order(5)
		@DisplayName("Collision of the warehouse man against a wall")
		void collisionWall() {
			map = movement.goRight(map);
			map = movement.goRight(map);
			map = movement.goDown(map);
			map = movement.goRight(map);
			map = movement.goRight(map);


			assertArrayEquals(map, new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.GOAL,MapElement.WALL,MapElement.BOX,MapElement.WAREHOUSE_MAN,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
			assertFalse(movement.mapChanged());
		}

		@Test
		@Order(6)
		@DisplayName("Collision of the warehouse man against a box, it should not change the map because the next position is not free or a"
				+ " goal position")
		void collisionBox1() {
			map = movement.goLeft(map);
			map = movement.goLeft(map);
			map = movement.goLeft(map);


			assertArrayEquals(map, new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.GOAL,MapElement.WALL,MapElement.BOX,MapElement.WAREHOUSE_MAN,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
		}

		@Test
		@Order(7)
		@DisplayName("Collision of the warehouse man against a box, it should change the map")
		void collisionBox2() {
			map= movement.goDown(map);
			map = movement.goLeft(map);
			map = movement.goUp(map);
			map = movement.goRight(map);
			map = movement.goUp(map);
			map = movement.goLeft(map);
			map = movement.goLeft(map);
			map = movement.goLeft(map);

			assertArrayEquals(map, new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.BOX,MapElement.WAREHOUSE_MAN,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.GOAL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
		}

		@Test
		@Order(8)
		@DisplayName("If the man is on the goal position, the goal position should be changed with the warehouse man")
		void manOnGoal() {
			map = movement.goDown(map);


			assertArrayEquals(map, new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.BOX,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.MAN_ON_GOAL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
		}

		@Test
		@Order(9)
		@DisplayName("If the man is on the goal position, the next legal movement should leave the square with GOAL")
		void manOnNotGoal() {
			map = movement.goLeft(map);


			assertArrayEquals(map, new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.BOX,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WAREHOUSE_MAN,MapElement.GOAL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
		}

		@Test
		@Order(10)
		@DisplayName("Box in the goal position")
		void boxOnGoal() {
			map= movement.goUp(map);
			map = movement.goLeft(map);
			map = movement.goUp(map);
			map = movement.goUp(map);
			map = movement.goRight(map);
			map = movement.goDown(map);
			map = movement.goDown(map);
			map = movement.goDown(map);
			map = movement.goRight(map);
			map = movement.goDown(map);
			map = movement.goDown(map);
			map = movement.goLeft(map);
			map = movement.goLeft(map);
			map = movement.goUp(map);
			map = movement.goRight(map);
			map = movement.goDown(map);
			map = movement.goRight(map);
			map = movement.goUp(map);



			assertArrayEquals(map, new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.BOX_ON_GOAL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WAREHOUSE_MAN,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
			//The & MapElementacter means that a box is on a goal position
		}

		@Test
		@Order(11)
		@DisplayName("get a box out of a goal position")
		void boxNotOnGoal() {
			map = movement.goUp(map);
			map = movement.goDown(map);



			assertArrayEquals(map, new MapElement [][] {{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.BOX,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.GOAL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WAREHOUSE_MAN,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
		}

		@Test
		@Order(12)
		@DisplayName("Collision between two boxes")
		void colisionBoxBox() throws IOException {
			mapModel = new MapModelImpl();			
			mapModel.createLevelMap(4);
			map=mapModel.getLevelMap();
			map=movement.goUp(map);
			map=movement.goUp(map);
			map=movement.goLeft(map);
			map=movement.goDown(map);
			map=movement.goDown(map);
			map=movement.goDown(map);
			map=movement.goDown(map);
			map=movement.goDown(map);

			assertArrayEquals(map, new MapElement [][] {{MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WAREHOUSE_MAN,MapElement.EMPTY,MapElement.GOAL,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.BOX,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.BOX,MapElement.WALL,MapElement.GOAL,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
		}
		
		@Test
		@Order(13)
		@DisplayName("Undo box")
		void undoBox() throws IOException {
			mapModel = new MapModelImpl();
			map=movement.undo(true,2,map);

			assertArrayEquals(map, new MapElement [][] {{MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.EMPTY,MapElement.WALL,MapElement.WAREHOUSE_MAN,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.BOX,MapElement.EMPTY,MapElement.GOAL,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.BOX,MapElement.WALL,MapElement.GOAL,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
			assertFalse(movement.boxMoved());
			assertFalse(movement.mapChanged());
		}
		
		@Test
		@Order(14)
		@DisplayName("Undo no box")
		void undoNoBox() throws IOException {
			mapModel = new MapModelImpl();
			map=movement.undo(false,3,map);

			assertArrayEquals(map, new MapElement [][] {{MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.EMPTY,MapElement.WALL,MapElement.EMPTY,MapElement.WAREHOUSE_MAN,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.BOX,MapElement.EMPTY,MapElement.GOAL,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.BOX,MapElement.WALL,MapElement.GOAL,MapElement.EMPTY,MapElement.WALL},
				{MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL},
				{MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.WALL,MapElement.EMPTY,MapElement.EMPTY,MapElement.EMPTY}});
		}
		
	}
}
