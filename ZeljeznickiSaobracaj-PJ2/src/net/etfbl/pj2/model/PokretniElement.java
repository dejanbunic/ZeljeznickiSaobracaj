package net.etfbl.pj2.model;

import java.io.Serializable;

import net.etfbl.pj2.enumeration.Smijer;

public abstract class PokretniElement extends Element implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -774023858000849834L;
	private int prethodniX;
	private int prethodniY;
	private Smijer smijer;

	public PokretniElement() {
		super();
	}

	public PokretniElement(int x, int y) {
		super(x, y);
	}

	public int getPrethodniX() {
		return prethodniX;
	}

	public void setPrethodniX(int prethodniX) {
		this.prethodniX = prethodniX;
	}

	public int getPrethodniY() {
		return prethodniY;
	}

	public void setPrethodniY(int prethodniY) {
		this.prethodniY = prethodniY;
	}

	public Smijer getSmijer() {
		return smijer;
	}

	public void setSmijer(Smijer smijer) {
		this.smijer = smijer;
	}

	@Override
	public String toString() {
		return "PokretniElement [" + super.toString() + "prethodniX=" + prethodniX + ", prethodniY=" + prethodniY
				+ ", smijer=" + smijer + "]";
	}

}
