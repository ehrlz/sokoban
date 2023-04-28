package es.upm.pproject.sokoban.model;

public class Pair {
	private int element0;
	private int element1;

	public static Pair createPair(int element0, int element1) {
		return new Pair(element0, element1);
	}

	public Pair(int element0, int element1) {
		this.element0 = element0;
		this.element1 = element1;
	}

	public int getElement0() {
		return element0;
	}

	public int getElement1() {
		return element1;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Pair) {
			Pair pair = (Pair) o;
			return this.element0 == pair.element0 && this.element1 == pair.element1;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + this.element0;
		hash = 89 * hash + this.element1;
		return hash;
	}
}
