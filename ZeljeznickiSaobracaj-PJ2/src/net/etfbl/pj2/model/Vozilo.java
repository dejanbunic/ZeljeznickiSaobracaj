package net.etfbl.pj2.model;

import java.util.Random;
import java.util.logging.Level;

import net.etfbl.pj2.enumeration.Smijer;
import net.etfbl.pj2.utility.FileLogger;

/**
 * @author DejanBunic
 *
 */
public abstract class Vozilo extends PokretniElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7024121443078070531L;
	private String marka;
	private String model;
	private int godiste;
	private int brzina;
	private boolean kraj = false;

	public Vozilo(int x, int y, int brzina) {
		super(x, y);
		Random random = new Random();
		// brzina = random.nextInt(1500)+500;
		this.brzina = brzina;
		godiste = random.nextInt(1950) + 72;

	}

	public Vozilo(int x, int y, String marka, String model) {
		super(x, y);
		Random random = new Random();
		brzina = random.nextInt(1500) + 500;
		godiste = random.nextInt(72) + 1950;
	}


	@Override
	public void run() {
		while (!kraj) {
			synchronized (this) {
				try {
					sleep(brzina);

					if (Smijer.DESNO.equals(getSmijer()) && Mapa.getMapa()[this.getX()][this.getY() + 1] != null
							&& Mapa.getMapa()[this.getX() + 1][this.getY() + 1] != null) {
						if (Mapa.getMapa()[this.getX()][this.getY() + 1].getElementi().get(0) instanceof Put)
							setSmijer(Smijer.DOLE);
					}
					if (Smijer.GORE.equals(getSmijer()) && Mapa.getMapa()[this.getX() + 1][this.getY()] != null) {
						if (Mapa.getMapa()[this.getX() + 1][this.getY()].getElementi().get(0) instanceof Put)
							setSmijer(Smijer.DESNO);
					}
					if (Smijer.GORE.equals(getSmijer())) {
						if (this.getY() - 1 < Mapa.getDimenzija() && this.getY() - 1 >= 0) {
							if (Mapa.getMapa()[this.getX()][this.getY() - 1] == null) {
								if (Mapa.getMapa()[this.getX() - 1][this.getY()] != null)
									if (Mapa.getMapa()[this.getX() - 1][this.getY()].getElementi()
											.get(0) instanceof Put) {
										setSmijer(Smijer.LIJEVO);
									}
							}
						}
					}
					if (Smijer.LIJEVO.equals(getSmijer())) {
						if (this.getX() - 1 < Mapa.getDimenzija() && this.getX() - 1 >= 0) {
							if (Mapa.getMapa()[this.getX() - 1][this.getY()] == null) {

								if (Mapa.getMapa()[this.getX()][this.getY() + 1] != null) {
									if (Mapa.getMapa()[this.getX()][this.getY() + 1].getElementi()
											.get(0) instanceof Put) {
										setSmijer(Smijer.DOLE);
									}
								}
							}
						}
					}
					if (Smijer.GORE.equals(getSmijer())) {
						if (!pomjeri(this.getX(), this.getY() - 1))
							break;
					} else if (Smijer.DOLE.equals(getSmijer())) {
						if (!pomjeri(this.getX(), this.getY() + 1))
							break;
					} else if (Smijer.LIJEVO.equals(getSmijer())) {
						if (!pomjeri(this.getX() - 1, this.getY()))
							break;
					} else if (Smijer.DESNO.equals(getSmijer())) {
						if (!pomjeri(this.getX() + 1, this.getY()))
							break;
					} else {
						try {
							sleep(brzina);
						} catch (InterruptedException e) {
							FileLogger.log(Level.SEVERE, null, e);
						}
					}

				} catch (InterruptedException e) {
					FileLogger.log(Level.SEVERE, null, e);
				}
			}
		}
	}

	private synchronized boolean pomjeri(int noviX, int noviY) {
		if (noviX >= 0 && noviY >= 0 && noviX < Mapa.getDimenzija() && noviY < Mapa.getDimenzija()) {
			if (!(Mapa.getMapa()[noviX][noviY].getElementi()
					.get(Mapa.getMapa()[noviX][noviY].getElementi().size() - 1) instanceof PokretniElement)
					&& !Mapa.getMapa()[noviX][noviY].isZauzeto()) {
				Mapa.getMapa()[noviX][noviY].getElementi().add(this);
				Mapa.getMapa()[this.getX()][this.getY()].getElementi()
						.remove(Mapa.getMapa()[this.getX()][this.getY()].getElementi().size() - 1);
				this.setX(noviX);
				this.setY(noviY);
			}
		} else {
			Mapa.getMapa()[this.getX()][this.getY()].getElementi()
					.remove(Mapa.getMapa()[this.getX()][this.getY()].getElementi().size() - 1);
			// this.interrupt();
			kraj = true;
			return false;
		}
		return true;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getGodiste() {
		return godiste;
	}

	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}

	public int getBrzina() {
		return brzina;
	}

	public void setBrzina(int brzina) {
		this.brzina = brzina;
	}

}
