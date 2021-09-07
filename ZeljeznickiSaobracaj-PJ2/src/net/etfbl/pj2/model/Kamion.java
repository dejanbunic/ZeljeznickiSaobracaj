package net.etfbl.pj2.model;

import java.util.Random;

public class Kamion extends Vozilo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7511502112264106686L;

	private int nosivost;
	private static String[] markeKamiona = { "Mercedes", "Man", "Volvo", "Scania", "FAP" };

	public Kamion(int x, int y, int brzina) {
		super(x, y, brzina);
		Random random = new Random();
		this.setMarka(markeKamiona[random.nextInt(markeKamiona.length)]);
		String model = String.valueOf(this.getMarka().charAt(0)) + String.valueOf(random.nextInt(100));
		this.setModel(model);
		nosivost = random.nextInt(10000) + 5000;
	}

	public int getNosivost() {
		return nosivost;
	}

	public void setNosivost(int nosivost) {
		this.nosivost = nosivost;
	}

	@Override
	public String toString() {
		return "Kamion [Marka=" + this.getMarka() + " Model=" + this.getModel() + " Godiste=" + this.getGodiste()
				+ " Brzina=" + this.getBrzina() + " Nosivost=" + this.getNosivost() + " x=" + this.getX() + " y="
				+ this.getY() + "]";
	}
}
