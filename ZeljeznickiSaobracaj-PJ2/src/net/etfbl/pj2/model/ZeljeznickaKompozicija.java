package net.etfbl.pj2.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.etfbl.pj2.enumeration.Smijer;
import net.etfbl.pj2.utility.FileLogger;


public class ZeljeznickaKompozicija extends PokretniElement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5299998521205861599L;
	
	private List<Lokomotiva> lokomotive = new ArrayList<Lokomotiva>();
	private List<Vagon> vagoni = new ArrayList<Vagon>();
	private List<Pozicija>pozicije = new ArrayList<Pozicija>();
	private String naziv;
	private String nazivDestinacije;
	private int duzinaPrikaza;
	private Pozicija pocetnoPolje;
	private int polazak = 0;
	private List<PokretniElement> elementiNaMapi = new ArrayList<PokretniElement>();
	private String putanja;
	private int pozicijaNaPutanji = 0;
	private int brzina;
	private int originalnaBrzina;
	private long pocetnoVrijeme;
	private long zavrsnoVrijeme;
	private boolean kraj = false;
	public ZeljeznickaKompozicija() {
		super();
	}

	public ZeljeznickaKompozicija(String naziv,List<Lokomotiva> lokomotive, List<Vagon> vagoni, String putanja, int brzina) {
		super();
		this.lokomotive = lokomotive;
		this.vagoni = vagoni;
		this.putanja = putanja;
		this.brzina = brzina;
		this.originalnaBrzina = brzina;
		this.naziv = naziv;
		pocetnoVrijeme = System.currentTimeMillis();
	}
	@Override
	public void run() {
		while (!kraj) {
			if(!detektujPrugu()) {
				try {
					synchronized (this) {
						wait();
						sleep(brzina);
					}
					
				} catch (InterruptedException e) {
					
					zavrsnoVrijeme = System.currentTimeMillis();
					serijalizujZeljeznickuKompoziciju(this);
					FileLogger.log(Level.FINE, null, e);
					break;
					
				}
			}
			
			
		}
	}

	public synchronized void dodajElementKompozicijeNaMapu(PokretniElement e) {
		e.setSmijer(null);
		int noviX = e.getX();
		int noviY = e.getY();
		if (e.getX() - 1 >= 0 && Mapa.getMapa()[e.getX() - 1][e.getY()] != null)
			if (Mapa.getMapa()[e.getX() - 1][e.getY()].getElementi()
					.get(Mapa.getMapa()[e.getX() - 1][e.getY()].getElementi().size() - 1) instanceof Pruga
					|| provjeriPruzniPrelaz(e.getX() - 1, e.getY())) {
				if (e.getPrethodniX() != e.getX() - 1 ) {
					if(e.getSmijer()==null) {
						if(!provjeriDaLiImaPokretniElement(e.getX()-1, e.getY()))
							e.setSmijer(Smijer.LIJEVO);// smijer= Smijer.LIJEVO;
						else {
							//brzina = 1000;
							ZeljeznickaStanica.prilagodiBrzinuZeljeznickeKompozicije(this);
						}
					}
				}
			}
		if (e.getX() + 1 < Mapa.getDimenzija() && Mapa.getMapa()[e.getX() + 1][e.getY()] != null)
			if (Mapa.getMapa()[e.getX() + 1][e.getY()].getElementi()
					.get(Mapa.getMapa()[e.getX() + 1][e.getY()].getElementi().size() - 1) instanceof Pruga
					|| provjeriPruzniPrelaz(e.getX() + 1, e.getY())) {
				if (e.getPrethodniX() != e.getX() + 1 ) {
					if(e.getSmijer()==null) {
						if(!provjeriDaLiImaPokretniElement(e.getX()+1, e.getY()))
							e.setSmijer(Smijer.DESNO);// smijer= Smijer.DESNO;
						else {
							//brzina = 1000;
							ZeljeznickaStanica.prilagodiBrzinuZeljeznickeKompozicije(this);
						}
					}
				}
			}
		if (e.getY() + 1 < Mapa.getDimenzija() && Mapa.getMapa()[e.getX()][e.getY() + 1] != null)
			if (Mapa.getMapa()[e.getX()][e.getY() + 1].getElementi()
					.get(Mapa.getMapa()[e.getX()][e.getY() + 1].getElementi().size() - 1) instanceof Pruga
					|| provjeriPruzniPrelaz(e.getX(), e.getY() + 1)) {
				if ( e.getPrethodniY() != e.getY() + 1) {
					if(e.getSmijer()==null) {
						if(!provjeriDaLiImaPokretniElement(e.getX(), e.getY() + 1))
							e.setSmijer(Smijer.DOLE);// smijer= Smijer.DOLE;
						else {
							//brzina = 1000;
							ZeljeznickaStanica.prilagodiBrzinuZeljeznickeKompozicije(this);
						}
					}
				}
			}
		if (this.getY() - 1 >= 0 && Mapa.getMapa()[e.getX()][e.getY() - 1] != null)
			if (Mapa.getMapa()[e.getX()][e.getY() - 1].getElementi()
					.get(Mapa.getMapa()[e.getX()][e.getY() - 1].getElementi().size() - 1) instanceof Pruga
					|| provjeriPruzniPrelaz(e.getX(), e.getY() - 1)) {
				if ( e.getPrethodniY() != e.getY() - 1) {
					if(e.getSmijer()==null) {
						if(!provjeriDaLiImaPokretniElement(e.getX(), e.getY() - 1))
							e.setSmijer(Smijer.GORE);
						else {
							//brzina = 1000;
							ZeljeznickaStanica.prilagodiBrzinuZeljeznickeKompozicije(this);
						}
					}
				}
			}
		if (e.getSmijer() != null) {
			if (Smijer.GORE.equals(e.getSmijer()))
				pomjeri(noviX, noviY - 1, e); 
			else if (Smijer.DOLE.equals(e.getSmijer()))
				pomjeri(noviX, noviY + 1, e);
			else if (Smijer.LIJEVO.equals(e.getSmijer()))
				pomjeri(noviX - 1, noviY, e);
			else if (Smijer.DESNO.equals(e.getSmijer()))
				pomjeri(noviX + 1, noviY, e);
		}else {
			
			if(Mapa.getMapa()[noviX][noviY].getElementi().get(Mapa.getMapa()[noviX][noviY].getElementi().size()-1) instanceof PokretniElement)
				Mapa.getMapa()[noviX][noviY].getElementi().remove(Mapa.getMapa()[noviX][noviY].getElementi().size()-1);

		}
	}

	private synchronized boolean detektujPrugu() {
		
		if (lokomotive.size() > elementiNaMapi.size() && polazak==0) {
			Lokomotiva lokomotiva = lokomotive.get(elementiNaMapi.size());
			lokomotiva.setX(pocetnoPolje.getX());
			lokomotiva.setY(pocetnoPolje.getY());
			elementiNaMapi.add(lokomotiva);
			Mapa.getMapa()[pocetnoPolje.getX()][pocetnoPolje.getY()].getElementi().add(lokomotiva);
		} else if (elementiNaMapi.size() < lokomotive.size() + vagoni.size() && polazak==0) {
			Vagon vagon = vagoni.get(elementiNaMapi.size() - lokomotive.size());
			vagon.setX(pocetnoPolje.getX());
			vagon.setY(pocetnoPolje.getY());
			elementiNaMapi.add(vagon);
			Mapa.getMapa()[pocetnoPolje.getX()][pocetnoPolje.getY()].getElementi().add(vagon);
		}else {
			polazak = 1;
		}
		try {
			sleep(brzina);
		} catch (InterruptedException e) {
			FileLogger.log(Level.FINE, null, e);
		}

		for (int i = 0; i < elementiNaMapi.size(); i++) {
			if (i == 0) {
				if(!(Mapa.getMapa()[this.getX()][this.getY()].getElementi().get(Mapa.getMapa()[this.getX()][this.getY()].getElementi().size()-1) instanceof ZeljeznickaStanica)) {
					dodajElementKompozicijeNaMapu(this);
					elementiNaMapi.get(i).setX(this.getPrethodniX());
					elementiNaMapi.get(i).setY(this.getPrethodniY());
					pomjeri(this.getX(), this.getY(), elementiNaMapi.get(i));
				}else {
					pomjeri(this.getX(), this.getY(), elementiNaMapi.get(i));
				}

			} else {
				if(!(Mapa.getMapa()[elementiNaMapi.get(i - 1).getPrethodniX()][elementiNaMapi.get(i - 1).getPrethodniY()].getElementi().get(Mapa.getMapa()[elementiNaMapi.get(i - 1).getPrethodniX()][elementiNaMapi.get(i - 1).getPrethodniY()].getElementi().size()-1) instanceof ZeljeznickaStanica)) {
					pomjeri(elementiNaMapi.get(i - 1).getPrethodniX(), elementiNaMapi.get(i - 1).getPrethodniY(),
							elementiNaMapi.get(i));
				}
				else {
						pomjeri(elementiNaMapi.get(i - 1).getPrethodniX(), elementiNaMapi.get(i - 1).getPrethodniY(),
							elementiNaMapi.get(i));
				}

			}
		}
		if(sviElementiKompozicijeStigliNaStanicu()) {
			return false;
		}
		return true;

	}
	public int getPolazak() {
		return polazak;
	}

	public void setPolazak(int polazak) {
		this.polazak = polazak;
	}

	private boolean sviElementiKompozicijeStigliNaStanicu() {
		
		if(lokomotive.size()>0) {
			if(lokomotive.get(0).getX()==2 && lokomotive.get(0).getY()==29) {
				lokomotive.remove(0);
				elementiNaMapi.remove(0);
				Mapa.getMapa()[2][29].getElementi().remove(Mapa.getMapa()[2][29].getElementi().size()-1);
			}
			else if(lokomotive.get(0).getX()==29 && lokomotive.get(0).getY()==25) {
				lokomotive.remove(0);
				elementiNaMapi.remove(0);
				Mapa.getMapa()[29][25].getElementi().remove(Mapa.getMapa()[29][25].getElementi().size()-1);
			}
		}else {
			if(vagoni.size()>0) {
				if(vagoni.get(0).getX()==2 && vagoni.get(0).getY()==29) {
				vagoni.remove(0);
				elementiNaMapi.remove(0);
				Mapa.getMapa()[2][29].getElementi().remove(Mapa.getMapa()[2][29].getElementi().size()-1);
				}
				else if(vagoni.get(0).getX()==29 && vagoni.get(0).getY()==25) {
					vagoni.remove(0);
					elementiNaMapi.remove(0);
					Mapa.getMapa()[29][25].getElementi().remove(Mapa.getMapa()[29][25].getElementi().size()-1);
				}
			}
		}
		if(lokomotive.size()==0 && vagoni.size()==0) {
			kraj = true;
			//this.interrupt();
		}
	
		if(elementiNaMapi.size()>0) {
			int i = 0;
		int x = elementiNaMapi.get(0).getX();
		int y = elementiNaMapi.get(0).getY();
		for(PokretniElement e:elementiNaMapi) {
			if(e.getX()==x && e.getY()==y)
				i++;
		}
		if( i== elementiNaMapi.size() && polazak!=0) {
			elementiNaMapi.clear();
			
			brzina = originalnaBrzina;
			dodajZeljeznicuKompozicijuNaZeljeznickuStanicu();

			return true;
		}
		}
		return false;
	}
	public synchronized void dodajZeljeznicuKompozicijuNaZeljeznickuStanicu() {
		String[] staniceUPutanji = putanja.split(Mapa.getRazdvojputanjekarakter());
		pozicije.add(new Pozicija(this.getX(), this.getY()));

		synchronized (Mapa.getKompozicijePrugaAB()) {
			if(Mapa.getKompozicijePrugaAB().remove(this))
				pozicijaNaPutanji++;
		}
		synchronized(Mapa.getKompozicijePrugaBC()) {
			if(Mapa.getKompozicijePrugaBC().remove(this))
			pozicijaNaPutanji++;
		}
		synchronized(Mapa.getKompozicijePrugaCD()) {
			if(Mapa.getKompozicijePrugaCD().remove(this))
			pozicijaNaPutanji++;
		}
		synchronized (Mapa.getKompozicijePrugaCE()) {
			if(Mapa.getKompozicijePrugaCE().remove(this))
			pozicijaNaPutanji++;
		}
		synchronized (Mapa.getKompozicijePrugaEV()) {
			if(Mapa.getKompozicijePrugaEV().remove(this)) {
			pozicijaNaPutanji++;
			}
		}
		synchronized (Mapa.getKompozicijePrugaAV()) {
			if(Mapa.getKompozicijePrugaAV().remove(this)) {
			pozicijaNaPutanji++;
			}
		}
		for(ZeljeznickaStanica z:Mapa.getZeljeznickeStanice()) {
			if(pozicijaNaPutanji<staniceUPutanji.length-1) {
				if(z.getNaziv().equals(staniceUPutanji[pozicijaNaPutanji])) {
					z.getZeljeznickeKompozicije().add(this);
					
				}
			}else {
				this.interrupt();
			}
		}	
	}
	public synchronized boolean provjeriPruzniPrelaz(int x, int y) {
		if (Mapa.getMapa()[x][y] == null)
			return false;
		if (Mapa.getMapa()[x][y].getElementi().size() < 2)
			return false;
		if (!(Mapa.getMapa()[x][y].getElementi().get(0) instanceof Pruga))
			return false;
		return true;
	}
	public synchronized boolean provjeriDaLiImaPokretniElement(int x, int y) {
		for(int i=0;i<Mapa.getMapa()[x][y].getElementi().size();i++) {
			if(Mapa.getMapa()[x][y].getElementi().get(i) instanceof PokretniElement) {
				try {
					sleep(brzina);
					return true;
				} catch (InterruptedException e) {
					FileLogger.log(Level.SEVERE, null, e);
				}
				
			}
		}
		return false;
	}
	private synchronized boolean pomjeri(int noviX, int noviY, PokretniElement e) {
		if (noviX >= 0 && noviY >= 0 && noviX < Mapa.getDimenzija() && noviY < Mapa.getDimenzija()) {
			//int i = 0;
			if (!(e instanceof ZeljeznickaKompozicija)) {
				if(!(Mapa.getMapa()[noviX][noviY].getElementi().get(Mapa.getMapa()[noviX][noviY].getElementi().size()-1) instanceof ZeljeznickaStanica))
					Mapa.getMapa()[noviX][noviY].getElementi().add(e);
				
				if (Mapa.getMapa()[e.getX()][e.getY()].getElementi()
						.get(Mapa.getMapa()[e.getX()][e.getY()].getElementi().size() - 1) instanceof PokretniElement)
					Mapa.getMapa()[e.getX()][e.getY()].getElementi()
							.remove(Mapa.getMapa()[e.getX()][e.getY()].getElementi().size() - 1);
			}
			else {
				pozicije.add(new Pozicija(e.getX(), e.getY()));
				synchronized (Mapa.getKompozicijePrugaAB()) {
				for(ZeljeznickaKompozicija z: Mapa.getKompozicijePrugaAB()) {
					if(z==this) {					
						if(Mapa.getMapa()[noviX][noviY].isZauzeto()) {
							Mapa.setLijeviPrelaz(Mapa.getLijeviPrelaz()-1);
							
							if(Mapa.getLijeviPrelaz()<=1){
								
								Mapa.getMapa()[noviX][noviY].setZauzeto(false);
							}
						}
					}
				}
				}
				synchronized ( Mapa.getKompozicijePrugaBC()) {			
				for(ZeljeznickaKompozicija z: Mapa.getKompozicijePrugaBC()) {
					if(z==this) {
						if(Mapa.getMapa()[noviX][noviY].isZauzeto()) {
							Mapa.setCentralniPrelaz(Mapa.getCentralniPrelaz()-1);
							
							if(Mapa.getCentralniPrelaz()<=1){
								Mapa.getMapa()[noviX][noviY].setZauzeto(false);
							}
						}
					}
				}
				}
				synchronized (Mapa.getKompozicijePrugaCE()) {
				for(ZeljeznickaKompozicija z: Mapa.getKompozicijePrugaCE()) {
					if(z==this) {
						if(Mapa.getMapa()[noviX][noviY].isZauzeto()) {
							Mapa.setDesniPrelaz(Mapa.getDesniPrelaz()-1);
							
							if(Mapa.getDesniPrelaz()<=1){
								Mapa.getMapa()[noviX][noviY].setZauzeto(false);
							}
						}
					}
				}
				}
			}
			
			e.setPrethodniX(e.getX());
			e.setPrethodniY(e.getY());
			//if(i==0) {
			e.setX(noviX);
			e.setY(noviY);//}
		} else {
			return false;
		}
		return true;
	}

	public List<Lokomotiva> getLokomotive() {
		return lokomotive;
	}

	public void setLokomotive(List<Lokomotiva> lokomotive) {
		this.lokomotive = lokomotive;
	}

	public List<Vagon> getVagoni() {
		return vagoni;
	}

	public void setVagoni(List<Vagon> vagoni) {
		this.vagoni = vagoni;
	}

	public String getNazivDestinacije() {
		return nazivDestinacije;
	}

	public void setNazivDestinacije(String nazivDestinacije) {
		this.nazivDestinacije = nazivDestinacije;
	}

	public int getDuzinaPrikaza() {
		return duzinaPrikaza;
	}

	public void setDuzinaPrikaza(int duzinaPrikaza) {
		this.duzinaPrikaza = duzinaPrikaza;
	}

	public Pozicija getPocetnoPolje() {
		return pocetnoPolje;
	}

	public void setPocetnoPolje(Pozicija pocetnoPolje) {
		this.pocetnoPolje = pocetnoPolje;
	}

	@Override
	public String toString() {
		String putanje="Pozicije koje je presla zeljeznicka kompozicija: \n";
		for(Pozicija p:pozicije) {
			putanje+=p+"\n";
		}
		long vrijeme = zavrsnoVrijeme- pocetnoVrijeme;
		long sekunde =  vrijeme / 1000;
		long minuti =  sekunde / 60;
		sekunde = sekunde%60;
		String vrijemeFormat;
		if(minuti == 0)
			vrijemeFormat= sekunde+"s";
		else {
			vrijemeFormat= minuti+":"+sekunde;
		}
		return "VrijemeKretanja:  "+vrijemeFormat +"\nZeljeznickaKompozicija[ naziv="+naziv+", brzina="+brzina+", putanja"+putanja+", lokomotive="+lokomotive+", vagoni="+vagoni+"]"+"\n"+putanje;
	}

	public String getPutanja() {
		return putanja;
	}

	public void setPutanja(String putanja) {
		this.putanja = putanja;
	}

	public int getPozicijaNaPutanji() {
		return pozicijaNaPutanji;
	}

	public void setPozicijaNaPutanji(int pozicijaNaPutanji) {
		this.pozicijaNaPutanji = pozicijaNaPutanji;
	}

	public int getBrzina() {
		return brzina;
	}

	public void setBrzina(int brzina) {
		this.brzina = brzina;
	}

	public List<Pozicija> getPozicije() {
		return pozicije;
	}

	public void setPozicije(List<Pozicija> pozicije) {
		this.pozicije = pozicije;
	}

	public static void serijalizujZeljeznickuKompoziciju(ZeljeznickaKompozicija z) {
		try{
			FileOutputStream fis = new FileOutputStream("src" + File.separator + "net" + File.separator + "etfbl"
					+ File.separator + "pj2"+File.separator+"kretanja"+File.separator+z.getNaziv()+".ser");
			ObjectOutputStream o = new ObjectOutputStream(fis);
			o.writeObject(z);
			o.close();
			}catch(IOException e){
				FileLogger.log(Level.SEVERE, null, e);
			}
	}
	
	@SuppressWarnings("resource")
	public static ZeljeznickaKompozicija deserijalizujZeljeznickuKompoziciju(String imeFajla) {
		ZeljeznickaKompozicija z = new ZeljeznickaKompozicija();
		try{
		//FileInputStream fis = new FileInputStream("src" + File.separator + "net" + File.separator + "etfbl"
				//+ File.separator + "pj2"+File.separator+"kretanja"+File.separator+imeFajla+".ser");
		FileInputStream fis = new FileInputStream(imeFajla);
		ObjectInputStream o = new ObjectInputStream(fis);
		
		z = (ZeljeznickaKompozicija)o.readObject();
		
		}catch(IOException e){
			FileLogger.log(Level.SEVERE, null, e);
		}catch(ClassNotFoundException e){
			FileLogger.log(Level.SEVERE, null, e);
		}
		return z;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public long getPocetnoVrijeme() {
		return pocetnoVrijeme;
	}

	public void setPocetnoVrijeme(long pocetnoVrijeme) {
		this.pocetnoVrijeme = pocetnoVrijeme;
	}

	public long getZavrsnoVrijeme() {
		return zavrsnoVrijeme;
	}

	public void setZavrsnoVrijeme(long zavrsnoVrijeme) {
		this.zavrsnoVrijeme = zavrsnoVrijeme;
	}
	
}
