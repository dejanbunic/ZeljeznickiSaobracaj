package net.etfbl.pj2.model;

import java.util.Random;

public class VagonSaSjedistima extends PutnickiVagon {
	private static final long serialVersionUID = 5295328201549445996L;
	private int brojSjedista;

	public VagonSaSjedistima() {
		super();
		Random random = new Random();
		brojSjedista = 50 + random.nextInt(50);
	}

	public int getBrojSjedista() {
		return brojSjedista;
	}

	public void setBrojSjetista(int brojSjedista) {
		this.brojSjedista = brojSjedista;
	}

	@Override
	public String toString() {
		return "VagonSaSjedistima[" + super.toString() + " , broj sjedista=" + brojSjedista + "]";
	}
}
