package es.upm.pproject.sokoban.model;

import es.upm.pproject.sokoban.model.MapModel.MapElement;

public class MovementModelImpl implements MovementModel {
	private Pair character;
	private MapElement[][] map;
	private Pair mapDimensions;
	private boolean cambio;
	private boolean boxMoved;

	public MovementModelImpl() {
		cambio = false;
		boxMoved=false;
	}

	public MapElement[][] goUp(MapElement[][] map) {
		this.map = map;
		findCharacter();
		//Position where the character should be at
		Pair coord1=new Pair(character.getElement0()-1,
				character.getElement1());
		//Position where the box could be at
		Pair coord2=new Pair(character.getElement0()-2,
				character.getElement1());
		doMov(coord1, coord2);
		return map;
	}

	public MapElement[][] goDown(MapElement[][] map) {
		this.map = map;
		findCharacter();
		Pair coord1=new Pair(character.getElement0()+1,
				character.getElement1());
		Pair coord2=new Pair(character.getElement0()+2,
				character.getElement1());
		doMov(coord1, coord2);
		return map;
	}

	public MapElement[][] goLeft(MapElement[][] map) {
		this.map = map;
		findCharacter();
		Pair coord1=new Pair(character.getElement0(),
				character.getElement1() - 1);
		Pair coord2=new Pair(character.getElement0(),
				character.getElement1() - 2);
		doMov(coord1, coord2);
		return map;
	}

	public MapElement[][] goRight(MapElement[][] map) {
		this.map = map;
		findCharacter();
		Pair coord1=new Pair(character.getElement0(),
				character.getElement1() + 1);
		Pair coord2=new Pair(character.getElement0(),
				character.getElement1() + 2);
		doMov(coord1, coord2);
		return map;
	}

	public MapElement[][] undo(boolean box, int mov, MapElement[][]map){
		this.map=map;
		findCharacter();
		Pair coord1=new Pair(0,0);
		Pair coord2=new Pair(0,0);
		switch(mov) {
		case 1:coord1=validCoord(character.getElement0()+1,
				character.getElement1());
			   coord2=validCoord(character.getElement0()-1,
						character.getElement1());
		break;
		case 2:coord1=validCoord(character.getElement0()-1,
				character.getElement1());
		       coord2=validCoord(character.getElement0()+1,
						character.getElement1());
		break;
		case 3: coord1=validCoord(character.getElement0(),
				character.getElement1()+1);
		        coord2=validCoord(character.getElement0(),
						character.getElement1()-1);
		break;
		case 4:coord1=validCoord(character.getElement0(),
				character.getElement1()-1);
		       coord2=validCoord(character.getElement0(),
						character.getElement1()+1);
		break;
		default:
		}
		if(coord1==null) {
			return map;
		}
		
		if(box) {
			if(coord2==null) {
				return map;
			}
			undoBox(coord2);
		}
			else {
			if(map[character.getElement0()][character.getElement1()]==MapElement.MAN_ON_GOAL) {
				map[character.getElement0()][character.getElement1()]=MapElement.GOAL;
			} else {
				map[character.getElement0()][character.getElement1()]=MapElement.EMPTY;
			}
		}

		if(map[coord1.getElement0()][coord1.getElement1()]==MapElement.GOAL) {
			map[coord1.getElement0()][coord1.getElement1()]=MapElement.MAN_ON_GOAL;
		} else {
			map[coord1.getElement0()][coord1.getElement1()]=MapElement.WAREHOUSE_MAN;
		}
		return map;
	}

	public boolean boxMoved() {
		return this.boxMoved;
	}

	public boolean mapChanged() {
		return this.cambio;
	}
	
	private void undoBox(Pair coord) {
		if(map[character.getElement0()][character.getElement1()]==MapElement.MAN_ON_GOAL) {
			map[character.getElement0()][character.getElement1()]=MapElement.BOX_ON_GOAL;
		} else {
			map[character.getElement0()][character.getElement1()]=MapElement.BOX;
		}
		if(map[coord.getElement0()][coord.getElement1()]==MapElement.BOX_ON_GOAL) {
			map[coord.getElement0()][coord.getElement1()]=MapElement.GOAL;
		} else {
			map[coord.getElement0()][coord.getElement1()]=MapElement.EMPTY;
		}
		
	}

	private void doMov(Pair coord1,Pair coord2){
		Pair coordAux=validCoord(coord1.getElement0(),
				coord1.getElement1());
		if (coordAux == null) {
			return ;
		}
		if (map[coordAux.getElement0()][coordAux.getElement1()] == MapElement.EMPTY ||
				map[coordAux.getElement0()][coordAux.getElement1()] == MapElement.GOAL) {
			checkSquare(coordAux);
			cambio = true;
		} else if (map[coordAux.getElement0()][coordAux.getElement1()] == MapElement.BOX ||
				map[coordAux.getElement0()][coordAux.getElement1()] == MapElement.BOX_ON_GOAL) {
			coordAux = validCoord(coord2.getElement0(),
					coord2.getElement1());
			if (coordAux == null) {
				return ;
			}
			if (map[coordAux.getElement0()][coordAux.getElement1()] != MapElement.EMPTY &&
					map[coordAux.getElement0()][coordAux.getElement1()] != MapElement.GOAL) {
				return ;
			}
			checkBox(coordAux,coord1);
			cambio = true;
			boxMoved=true;
		}
	}

	private void checkSquare(Pair coord) {
		if (map[character.getElement0()][character.getElement1()] == MapElement.MAN_ON_GOAL) {
			map[character.getElement0()][character.getElement1()] = MapElement.GOAL;
		} else {
			map[character.getElement0()][character.getElement1()] = MapElement.EMPTY;
		}
		if (map[coord.getElement0()][coord.getElement1()] == MapElement.GOAL) {
			map[coord.getElement0()][coord.getElement1()] = MapElement.MAN_ON_GOAL;
		} else {
			map[coord.getElement0()][coord.getElement1()] = MapElement.WAREHOUSE_MAN;
		}
	}

	private void checkBox(Pair coord1,Pair coord2) {
		if (map[character.getElement0()][character.getElement1()] == MapElement.MAN_ON_GOAL) {
			map[character.getElement0()][character.getElement1()] = MapElement.GOAL;
		} else {
			map[character.getElement0()][character.getElement1()] = MapElement.EMPTY;
		}
		if (map[coord1.getElement0()][coord1.getElement1()] == MapElement.GOAL) {
			map[coord1.getElement0()][coord1.getElement1()] = MapElement.BOX_ON_GOAL;
		} else {
			map[coord1.getElement0()][coord1.getElement1()] = MapElement.BOX;
		}
		if (map[coord2.getElement0()][coord2.getElement1()] == MapElement.BOX) {
			map[coord2.getElement0()][coord2.getElement1()] = MapElement.WAREHOUSE_MAN;
		} else {
			map[coord2.getElement0()][coord2.getElement1()] = MapElement.MAN_ON_GOAL;
		}
	}

	private Pair validCoord(int row, int column) {
		// If the new movement is outside the map, then error
		if (row >= mapDimensions.getElement0() || column >= mapDimensions.getElement1() ||
				row < 0 || column < 0) {
			return null;
		}
		// If it is a wall, dont allow the movement
		if (map[row][column] == MapElement.WALL) {
			return null;
		}
		return new Pair(row, column);
	}

	private void findCharacter() {
		cambio=false;
		boxMoved=false;
		mapDimensions = new Pair(map.length, map[0].length);
		Pair res = null;
		for (int i = 0; i < mapDimensions.getElement0() && res == null; i++) {
			for (int j = 0; j < mapDimensions.getElement1() && res == null; j++) {
				if (map[i][j] == MapElement.WAREHOUSE_MAN || map[i][j] == MapElement.MAN_ON_GOAL) {
					character = new Pair(i, j);
				}
			}
		}
	}
}