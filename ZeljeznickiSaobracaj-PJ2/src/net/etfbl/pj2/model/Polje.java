package net.etfbl.pj2.model;

import java.util.ArrayList;
import java.util.List;

public class Polje {
	private int x;
	private int y;
	private List<Element> elementi = new ArrayList<Element>();
	private boolean zauzeto;

	public Polje() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Polje(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public List<Element> getElementi() {
		return elementi;
	}

	public void setElementi(List<Element> elementi) {
		this.elementi = elementi;
	}

	public boolean isZauzeto() {
		return zauzeto;
	}

	public void setZauzeto(boolean zauzeto) {
		this.zauzeto = zauzeto;
	}

}
