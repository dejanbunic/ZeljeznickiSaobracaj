package net.etfbl.pj2.model;


public abstract class Element extends Thread {
	/**
	 * 
	 */
	

	Pozicija pozicija = new Pozicija(-1, -1);

	public Element() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Element(int x, int y) {
		super();
		pozicija = new Pozicija(x, y);
	}

	public int getX() {
		return pozicija.getX();
	}

	public void setX(int x) {
		pozicija.setX(x);
		;
	}

	public int getY() {
		return pozicija.getY();
	}

	public void setY(int y) {
		pozicija.setY(y);
		;
	}

	@Override
	public void run() {

	}

	@Override
	public String toString() {
		return "Element [x=" + pozicija.getX() + ", y=" + pozicija.getY() + "]";
	}

}
