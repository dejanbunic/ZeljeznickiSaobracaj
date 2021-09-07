package net.etfbl.pj2.model;

import java.text.DecimalFormat;
import java.util.Random;

public abstract class Vagon extends PokretniElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8095876151256578985L;
	private static DecimalFormat df = new DecimalFormat("0.00");
	private String oznaka;
	private double duzina = 10.00;

	public Vagon() {
		oznaka = generisiString();
		Random random = new Random();
		duzina = Double.valueOf(df.format(10 + random.nextDouble() * 20));
	}

	private String generisiString() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public double getDuzina() {
		return duzina;
	}

	public void setDuzina(double duzina) {
		this.duzina = duzina;
	}

	@Override
	public String toString() {
		return " oznaka=" + oznaka + " duzina=" + duzina + " ";
	}
}
