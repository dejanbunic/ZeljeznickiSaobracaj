package net.etfbl.pj2.model;

import java.io.Serializable;

public class Pozicija implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1658577572592784788L;
	private int x = -1;
	private int y = -1;

	public Pozicija(int x, int y) {
		super();
		this.setX(x);
		this.setY(y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public String toString() {
		return "Pozicija[x=" + x + " y=" + y + "]";
	}
}
