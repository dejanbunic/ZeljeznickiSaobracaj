package net.etfbl.pj2.model;

import java.util.Random;

import net.etfbl.pj2.enumeration.VrstaPogona;

public abstract class Lokomotiva extends PokretniElement {
	// private VrstaPogona vrstaPogona;//

	/**
	 * 
	 */
	private static final long serialVersionUID = 4308963178977561505L;
	VrstaPogona vrstaPogona;
	String oznaka;
	String snaga;

	public Lokomotiva() {
		super();
		Random random = new Random();
		int broj = random.nextInt(3);
		if (broj == 0)
			vrstaPogona = VrstaPogona.DIZELSKI;
		else if (broj == 1)
			vrstaPogona = VrstaPogona.ELEKTRICNI;
		else
			vrstaPogona = VrstaPogona.PARNI;

		snaga = (random.nextInt(1000) + 2000) + " kW";
		oznaka = generisiString();

	}

	private String generisiString() {
		int leftLimit = 48;
		int rightLimit = 122;
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	@Override
	public String toString() {
		return "oznaka=" + oznaka + " ,vrsta pogona=" + vrstaPogona + ", snaga=" + snaga;
	}
}
