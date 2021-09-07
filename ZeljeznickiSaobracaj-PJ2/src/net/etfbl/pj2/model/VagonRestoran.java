package net.etfbl.pj2.model;

import java.util.Random;

public class VagonRestoran extends PutnickiVagon {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8765626044041700385L;
	private String opis;

	public VagonRestoran() {
		super();
		opis = generisiString();
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Override
	public String toString() {
		return "VagonRestoran[" + super.toString() + " , opis=" + opis + "]";
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
}
