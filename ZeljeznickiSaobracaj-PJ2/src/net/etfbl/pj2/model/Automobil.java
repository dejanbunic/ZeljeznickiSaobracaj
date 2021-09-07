package net.etfbl.pj2.model;

import java.util.Random;

public class Automobil extends Vozilo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9180905420798130569L;

	private static String[] markeAuta = { "Ferari", "Mercedes", "BMW", "Toyota", "Audi", "Ford", "Renault", "Citroen",
			"Fiat", "Honda" };
	private int brojVrata;

	public Automobil(int x, int y, int brzina) {
		super(x, y, brzina);
		Random random = new Random();
		this.setMarka(markeAuta[random.nextInt(markeAuta.length)]);
		String model = String.valueOf(this.getMarka().charAt(0)) + String.valueOf(random.nextInt(100));
		this.setModel(model);
		brojVrata = (random.nextInt(2) + 1) * 2;
	}

	@Override
	public String toString() {
		return "Automobil [Marka=" + this.getMarka() + " Model=" + this.getModel() + " Godiste=" + this.getGodiste()
				+ " Brzina=" + this.getBrzina() + "Vrata=" + this.getBrojVrata() + " x=" + this.getX() + " y="
				+ this.getY() + "]";
	}

	public int getBrojVrata() {
		return brojVrata;
	}

	public void setBrojVrata(int brojVrata) {
		this.brojVrata = brojVrata;
	}

}
