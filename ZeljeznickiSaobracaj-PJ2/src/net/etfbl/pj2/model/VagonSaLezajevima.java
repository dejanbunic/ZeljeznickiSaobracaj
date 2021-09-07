package net.etfbl.pj2.model;

import java.util.Random;

public class VagonSaLezajevima extends PutnickiVagon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5295328201549445996L;
	private int brojLezaja;

	public VagonSaLezajevima() {
		super();
		Random random = new Random();
		brojLezaja = random.nextInt(50);
	}

	public int getBrojLezaja() {
		return brojLezaja;
	}

	public void setBrojLezaja(int brojLezaja) {
		this.brojLezaja = brojLezaja;
	}

	@Override
	public String toString() {
		return "VagonSaLezajevima[" + super.toString() + " , broj lezaja=" + brojLezaja + "]";
	}
}
