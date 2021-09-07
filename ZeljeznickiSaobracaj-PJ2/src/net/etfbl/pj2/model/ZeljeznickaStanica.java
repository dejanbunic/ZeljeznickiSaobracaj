package net.etfbl.pj2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.etfbl.pj2.utility.FileLogger;

public class ZeljeznickaStanica extends Element {
	private List<ZeljeznickaKompozicija> zeljeznickeKompozicije = new ArrayList<ZeljeznickaKompozicija>();
	private String naziv;
	public ZeljeznickaStanica() {
		super();
	}
	public ZeljeznickaStanica(String naziv) {
		super();
		this.naziv = naziv;
	}
	@Override
	public void run() {
		while(!Mapa.isKraj()) {
			try {
				if(zeljeznickeKompozicije!=null) {
					if(zeljeznickeKompozicije.size()>0) {
						for(int i=0;i<zeljeznickeKompozicije.size();i++) {
							if("A".equals(naziv)){
								String[] stanice = zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter());
								if("B".equals(stanice[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1])) {
								zeljeznickeKompozicije.get(i).setX(2);
								zeljeznickeKompozicije.get(i).setY(26);
								zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(2,26));
								zeljeznickeKompozicije.get(i).setPrethodniX(2);
								zeljeznickeKompozicije.get(i).setPrethodniY(27);
								zeljeznickeKompozicije.get(i).setPolazak(0);
								synchronized (Mapa.getKompozicijePrugaAB()) {
									if(i<zeljeznickeKompozicije.size()) {	
								
								if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW) && ( Mapa.getKompozicijePrugaAB().isEmpty() || (Mapa.getKompozicijePrugaAB().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaAB().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaAB().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										//zeljeznickeKompozicije.get(i).setPolazak(0);
										Mapa.getKompozicijePrugaAB().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										zeljeznickeKompozicije.get(i).start();
										zeljeznickeKompozicije.remove(i);
										Mapa.setLijeviPrelaz(Mapa.getLijeviPrelaz()+2);
										Mapa.getMapa()[2][21].setZauzeto(true);
										Mapa.getMapa()[2][20].setZauzeto(true);
									}
									
								}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING) && ( Mapa.getKompozicijePrugaAB().isEmpty() || (Mapa.getKompozicijePrugaAB().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaAB().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaAB().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										//zeljeznickeKompozicije.get(i).setPolazak(0);
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										Mapa.getKompozicijePrugaAB().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).notify();
										zeljeznickeKompozicije.remove(i);
										Mapa.setLijeviPrelaz(Mapa.getLijeviPrelaz()+2);
										Mapa.getMapa()[2][21].setZauzeto(true);
										Mapa.getMapa()[2][20].setZauzeto(true);
									}
								}
								}
								}
							}else if("V".equals(stanice[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1])) {
								zeljeznickeKompozicije.get(i).setX(2);
								zeljeznickeKompozicije.get(i).setY(29);
								zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(2,29));
								zeljeznickeKompozicije.get(i).setPrethodniX(2);
								zeljeznickeKompozicije.get(i).setPrethodniY(28);
								zeljeznickeKompozicije.get(i).setPolazak(0);
								synchronized (Mapa.getKompozicijePrugaAV()) {
									if(i<zeljeznickeKompozicije.size()) {	
								
								if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW) && ( Mapa.getKompozicijePrugaAV().isEmpty() || (Mapa.getKompozicijePrugaAV().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaAV().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaAV().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										//zeljeznickeKompozicije.get(i).setPolazak(0);
										//Mapa.getKompozicijePrugaAV().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										zeljeznickeKompozicije.get(i).start();
										zeljeznickeKompozicije.remove(i);

									}
									
								}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING) && ( Mapa.getKompozicijePrugaAV().isEmpty() || (Mapa.getKompozicijePrugaAV().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaAV().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaAV().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										//zeljeznickeKompozicije.get(i).setPolazak(0);
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										//Mapa.getKompozicijePrugaAV().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).notify();
										zeljeznickeKompozicije.remove(i);
									
									}
								}
								}
								}
							}
						}
						else if("B".equals(naziv)) {
							
							String[] stanice = zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter());
							
							
							if("A".equals(stanice[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1])) {
								if(Mapa.getKompozicijePrugaAB().isEmpty()) {
									//Mapa.getKompozicijePrugaAB().add(zeljeznickeKompozicije.get(0));
									zeljeznickeKompozicije.get(i).setX(5);
									zeljeznickeKompozicije.get(i).setY(6);
									zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(5,6));
									zeljeznickeKompozicije.get(i).setPrethodniX(6);
									zeljeznickeKompozicije.get(i).setPrethodniY(6);
									zeljeznickeKompozicije.get(i).setPolazak(0);
									synchronized (Mapa.getKompozicijePrugaAB()) {
										if(i<zeljeznickeKompozicije.size()) {
											
									if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW) && ( Mapa.getKompozicijePrugaAB().isEmpty() || (Mapa.getKompozicijePrugaAB().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaAB().get(0).getPozicijaNaPutanji()+1]
											.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaAB().get(0).getPolazak()!=0))) {
										synchronized (zeljeznickeKompozicije.get(i)) {
											Mapa.getKompozicijePrugaAB().add(zeljeznickeKompozicije.get(i));
											zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
											zeljeznickeKompozicije.get(i).start();
											zeljeznickeKompozicije.remove(i);
											Mapa.setLijeviPrelaz(Mapa.getLijeviPrelaz()+2);
											Mapa.getMapa()[2][21].setZauzeto(true);
											Mapa.getMapa()[2][20].setZauzeto(true);
										}
										
									}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING) && ( Mapa.getKompozicijePrugaAB().isEmpty() || (Mapa.getKompozicijePrugaAB().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaAB().get(0).getPozicijaNaPutanji()+1]
											.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaAB().get(0).getPolazak()!=0))) {
										synchronized (zeljeznickeKompozicije.get(i)) {
											Mapa.getKompozicijePrugaAB().add(zeljeznickeKompozicije.get(i));
											zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
											zeljeznickeKompozicije.get(i).notify();
											zeljeznickeKompozicije.remove(i);
											Mapa.setLijeviPrelaz(Mapa.getLijeviPrelaz()+2);
											Mapa.getMapa()[2][21].setZauzeto(true);
											Mapa.getMapa()[2][20].setZauzeto(true);
										}
									}
									}
									}
									//zeljeznickeKompozicije.remove(0);
									//zeljeznickeKompozicije.remove(0);
								}
							}
							else if("C".equals(stanice[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1])) {
								zeljeznickeKompozicije.get(i).setX(8);
								zeljeznickeKompozicije.get(i).setY(6);
								zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(8,6));
								zeljeznickeKompozicije.get(i).setPrethodniX(7);
								zeljeznickeKompozicije.get(i).setPrethodniY(6);
								zeljeznickeKompozicije.get(i).setPolazak(0);
								synchronized (Mapa.getKompozicijePrugaBC()) {
									if(i<zeljeznickeKompozicije.size()) {
								if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW) && ( Mapa.getKompozicijePrugaBC().isEmpty() || (Mapa.getKompozicijePrugaBC().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaBC().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaBC().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										Mapa.getKompozicijePrugaBC().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										zeljeznickeKompozicije.get(i).start();
										zeljeznickeKompozicije.remove(i);
										Mapa.setCentralniPrelaz(Mapa.getCentralniPrelaz()+2);
										Mapa.getMapa()[13][6].setZauzeto(true);
										Mapa.getMapa()[14][6].setZauzeto(true);
									}
									
								}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING) && ( Mapa.getKompozicijePrugaBC().isEmpty() || (Mapa.getKompozicijePrugaBC().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaBC().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaBC().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										Mapa.getKompozicijePrugaBC().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										zeljeznickeKompozicije.get(i).notify();
										zeljeznickeKompozicije.remove(i);
										Mapa.setCentralniPrelaz(Mapa.getCentralniPrelaz()+2);
										Mapa.getMapa()[13][6].setZauzeto(true);
										Mapa.getMapa()[14][6].setZauzeto(true);
									}
								}}	}
								//zeljeznickeKompozicije.remove(0);
							}
						}
						else if ("C".equals(naziv)) {
							String[] stanice = zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter());
							if("B".equals(stanice[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1])) {
								zeljeznickeKompozicije.get(i).setX(19);
								zeljeznickeKompozicije.get(i).setY(11);
								zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(19,11));
								zeljeznickeKompozicije.get(i).setPrethodniX(19);
								zeljeznickeKompozicije.get(i).setPrethodniY(12);
								zeljeznickeKompozicije.get(i).setPolazak(0);
								synchronized (Mapa.getKompozicijePrugaBC()) {
									if(i<zeljeznickeKompozicije.size()) {
								if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW) && ( Mapa.getKompozicijePrugaBC().isEmpty() || (Mapa.getKompozicijePrugaBC().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaBC().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaBC().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										Mapa.getKompozicijePrugaBC().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										zeljeznickeKompozicije.get(i).start();
										zeljeznickeKompozicije.remove(i);
										Mapa.setCentralniPrelaz(Mapa.getCentralniPrelaz()+2);
										Mapa.getMapa()[13][6].setZauzeto(true);
										Mapa.getMapa()[14][6].setZauzeto(true);
									}
									
								}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING) && ( Mapa.getKompozicijePrugaBC().isEmpty() || (Mapa.getKompozicijePrugaBC().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaBC().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaBC().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										Mapa.getKompozicijePrugaBC().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										zeljeznickeKompozicije.get(i).notify();
										zeljeznickeKompozicije.remove(i);
										Mapa.setCentralniPrelaz(Mapa.getCentralniPrelaz()+2);
										Mapa.getMapa()[13][6].setZauzeto(true);
										Mapa.getMapa()[14][6].setZauzeto(true);
									}
								}
								}}
								//zeljeznickeKompozicije.remove(0);
							}else if("D".equals(stanice[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1])) {
								zeljeznickeKompozicije.get(i).setX(21);
								zeljeznickeKompozicije.get(i).setY(12);
								zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(21,12));
								zeljeznickeKompozicije.get(i).setPrethodniX(20);
								zeljeznickeKompozicije.get(i).setPrethodniY(12);
								zeljeznickeKompozicije.get(i).setPolazak(0);
								synchronized (Mapa.getKompozicijePrugaCD()) {
									if(i<zeljeznickeKompozicije.size() && (Mapa.getKompozicijePrugaCD().isEmpty() || (Mapa.getKompozicijePrugaCD().get(0).getPozicijaNaPutanji()+1)<Mapa.getKompozicijePrugaCD().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter()).length))  {
								if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW) && ( Mapa.getKompozicijePrugaCD().isEmpty() ||
										(Mapa.getKompozicijePrugaCD().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaCD().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaCD().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										Mapa.getKompozicijePrugaCD().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										zeljeznickeKompozicije.get(i).start();
										zeljeznickeKompozicije.remove(i);
									}
									
								}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING) && ( Mapa.getKompozicijePrugaCD().isEmpty() || (Mapa.getKompozicijePrugaCD().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaCD().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaCD().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										Mapa.getKompozicijePrugaCD().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										zeljeznickeKompozicije.get(i).notify();
										zeljeznickeKompozicije.remove(i);
									}
								}
								}
								}
								//zeljeznickeKompozicije.remove(0);
							}else if("E".equals(stanice[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1])) {
								zeljeznickeKompozicije.get(i).setX(20);
								zeljeznickeKompozicije.get(i).setY(14);
								zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(20,14));
								zeljeznickeKompozicije.get(i).setPrethodniX(20);
								zeljeznickeKompozicije.get(i).setPrethodniY(13);					
								zeljeznickeKompozicije.get(i).setPolazak(0);
								synchronized (Mapa.getKompozicijePrugaCE()) {
									
									if(i<zeljeznickeKompozicije.size() && (Mapa.getKompozicijePrugaCE().isEmpty() || (Mapa.getKompozicijePrugaCE().get(0).getPozicijaNaPutanji()+1)<Mapa.getKompozicijePrugaCE().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter()).length)) {
								if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW) && ( Mapa.getKompozicijePrugaCE().isEmpty() || (Mapa.getKompozicijePrugaCE().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaCE().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaCE().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										Mapa.getKompozicijePrugaCE().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										zeljeznickeKompozicije.get(i).start();
										zeljeznickeKompozicije.remove(i);
										Mapa.setDesniPrelaz(Mapa.getDesniPrelaz()+2);
										Mapa.getMapa()[26][21].setZauzeto(true);
										Mapa.getMapa()[26][20].setZauzeto(true);
										
									}
									
								}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING) && ( Mapa.getKompozicijePrugaCE().isEmpty() || (Mapa.getKompozicijePrugaCE().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaCE().get(0).getPozicijaNaPutanji()+1]
										.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaCE().get(0).getPolazak()!=0))) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										Mapa.getKompozicijePrugaCE().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										zeljeznickeKompozicije.get(i).notify();
										zeljeznickeKompozicije.remove(i);
										Mapa.setLijeviPrelaz(Mapa.getLijeviPrelaz()+2);
										Mapa.getMapa()[26][21].setZauzeto(true);
										Mapa.getMapa()[26][20].setZauzeto(true);
									}
								}
								}
								}
								//zeljeznickeKompozicije.remove(0);
							}
						}else if("D".equals(naziv)) {
							zeljeznickeKompozicije.get(i).setX(25);
							zeljeznickeKompozicije.get(i).setY(1);
							zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(25,1));
							zeljeznickeKompozicije.get(i).setPrethodniX(26);
							zeljeznickeKompozicije.get(i).setPrethodniY(1);
							zeljeznickeKompozicije.get(i).setPolazak(0);
							synchronized (Mapa.getKompozicijePrugaCD()) {
								if(i<zeljeznickeKompozicije.size()) {
							if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW)  && ( Mapa.getKompozicijePrugaCD().isEmpty() || (Mapa.getKompozicijePrugaCD().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaCD().get(0).getPozicijaNaPutanji()+1]
									.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaCD().get(0).getPolazak()!=0))) {
								synchronized (zeljeznickeKompozicije.get(i)) {
									Mapa.getKompozicijePrugaCD().add(zeljeznickeKompozicije.get(i));
									zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
									zeljeznickeKompozicije.get(i).start();
									zeljeznickeKompozicije.remove(i);
								}
							}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING)  && ( Mapa.getKompozicijePrugaCD().isEmpty() || (Mapa.getKompozicijePrugaCD().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaCD().get(0).getPozicijaNaPutanji()+1]
									.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaCD().get(0).getPolazak()!=0))) {
								synchronized (zeljeznickeKompozicije.get(i)) {
									Mapa.getKompozicijePrugaCD().add(zeljeznickeKompozicije.get(i));
									zeljeznickeKompozicije.get(i).notify();
									zeljeznickeKompozicije.remove(i);
								}
							}	
							}}
							//zeljeznickeKompozicije.remove(0);
						}
						else if("E".equals(naziv)) {
							String[] stanice = zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter());
							if("C".equals(stanice[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1])) {
							zeljeznickeKompozicije.get(i).setX(26);
							zeljeznickeKompozicije.get(i).setY(24);
							zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(26,24));
							zeljeznickeKompozicije.get(i).setPrethodniX(26);
							zeljeznickeKompozicije.get(i).setPrethodniY(25);
							zeljeznickeKompozicije.get(i).setPolazak(0);
							synchronized (Mapa.getKompozicijePrugaCE()) {
								
								if(i<zeljeznickeKompozicije.size()) {
							if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW) && ( Mapa.getKompozicijePrugaCE().isEmpty() || (Mapa.getKompozicijePrugaCE().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaCE().get(0).getPozicijaNaPutanji()+1]
									.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaCE().get(0).getPolazak()!=0))) {
								Mapa.getKompozicijePrugaCE().add(zeljeznickeKompozicije.get(i));
								zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
								zeljeznickeKompozicije.get(i).start();
								zeljeznickeKompozicije.remove(i);
								Mapa.setDesniPrelaz(Mapa.getDesniPrelaz()+2);
								Mapa.getMapa()[26][21].setZauzeto(true);
								Mapa.getMapa()[26][20].setZauzeto(true);
								
							}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING) && ( Mapa.getKompozicijePrugaCE().isEmpty() || (Mapa.getKompozicijePrugaCE().get(0).getPutanja().split(Mapa.getRazdvojputanjekarakter())[Mapa.getKompozicijePrugaCE().get(0).getPozicijaNaPutanji()+1]
									.equals(zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter())[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1]) &&  Mapa.getKompozicijePrugaCE().get(0).getPolazak()!=0))) {
								synchronized (zeljeznickeKompozicije.get(i)) {
									Mapa.getKompozicijePrugaCE().add(zeljeznickeKompozicije.get(i));
									zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
									zeljeznickeKompozicije.get(i).notify();
									zeljeznickeKompozicije.remove(i);
									Mapa.setDesniPrelaz(Mapa.getDesniPrelaz()+2);
									Mapa.getMapa()[26][21].setZauzeto(true);
									Mapa.getMapa()[26][20].setZauzeto(true);
								}
							}
							}}
							}else if("V".equals(stanice[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1])) {
								zeljeznickeKompozicije.get(i).setX(27);
								zeljeznickeKompozicije.get(i).setY(25);
								zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(27,25));
								zeljeznickeKompozicije.get(i).setPrethodniX(26);
								zeljeznickeKompozicije.get(i).setPrethodniY(25);
								zeljeznickeKompozicije.get(i).setPolazak(0);
								synchronized (Mapa.getKompozicijePrugaEV()) {
									if(i<zeljeznickeKompozicije.size()) {	
								
								if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW) &&  Mapa.getKompozicijePrugaEV().isEmpty()) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										//zeljeznickeKompozicije.get(i).setPolazak(0);
										Mapa.getKompozicijePrugaEV().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										zeljeznickeKompozicije.get(i).start();
										zeljeznickeKompozicije.remove(i);

									}
									
								}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING) && ( Mapa.getKompozicijePrugaEV().isEmpty())) {
									synchronized (zeljeznickeKompozicije.get(i)) {
										//zeljeznickeKompozicije.get(i).setPolazak(0);
										zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
										Mapa.getKompozicijePrugaEV().add(zeljeznickeKompozicije.get(i));
										zeljeznickeKompozicije.get(i).notify();
										zeljeznickeKompozicije.remove(i);
									
									}
								}
								}
								}
							}
							//zeljeznickeKompozicije.remove(0);
						}
						else if("V".equals(naziv)) {
							String[] stanice = zeljeznickeKompozicije.get(i).getPutanja().split(Mapa.getRazdvojputanjekarakter());
							if("E".equals(stanice[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1])) {
							zeljeznickeKompozicije.get(i).setX(29);
							zeljeznickeKompozicije.get(i).setY(25);
							zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(29,25));
							zeljeznickeKompozicije.get(i).setPrethodniX(29);
							zeljeznickeKompozicije.get(i).setPrethodniY(25);
							zeljeznickeKompozicije.get(i).setPolazak(0);
							synchronized (Mapa.getKompozicijePrugaEV()) {
								
								if(i<zeljeznickeKompozicije.size()) {
							if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW) &&  Mapa.getKompozicijePrugaEV().isEmpty()) {
								Mapa.getKompozicijePrugaEV().add(zeljeznickeKompozicije.get(i));
								zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
								zeljeznickeKompozicije.get(i).start();
								zeljeznickeKompozicije.remove(i);
								
								
							}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING) &&  Mapa.getKompozicijePrugaEV().isEmpty()) {
									
								synchronized (zeljeznickeKompozicije.get(i)) {
									Mapa.getKompozicijePrugaEV().add(zeljeznickeKompozicije.get(i));
									zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
									zeljeznickeKompozicije.get(i).notify();
									zeljeznickeKompozicije.remove(i);
									
								}
							}
							}
							}
						}else if("A".equals(stanice[zeljeznickeKompozicije.get(i).getPozicijaNaPutanji()+1])) {
							zeljeznickeKompozicije.get(i).setX(2);
							zeljeznickeKompozicije.get(i).setY(29);
							zeljeznickeKompozicije.get(i).setPocetnoPolje(new Pozicija(2,29));
							zeljeznickeKompozicije.get(i).setPrethodniX(2);
							zeljeznickeKompozicije.get(i).setPrethodniY(29);
							zeljeznickeKompozicije.get(i).setPolazak(0);
							synchronized (Mapa.getKompozicijePrugaEV()) {
								
								if(i<zeljeznickeKompozicije.size()) {
							if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.NEW) &&  Mapa.getKompozicijePrugaAV().isEmpty()) {
								Mapa.getKompozicijePrugaAV().add(zeljeznickeKompozicije.get(i));
								zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
								zeljeznickeKompozicije.get(i).start();
								zeljeznickeKompozicije.remove(i);
								
								
							}else if(zeljeznickeKompozicije.get(i).getState().equals(Thread.State.WAITING) &&  Mapa.getKompozicijePrugaAV().isEmpty()) {
									
								synchronized (zeljeznickeKompozicije.get(i)) {
									Mapa.getKompozicijePrugaAV().add(zeljeznickeKompozicije.get(i));
									zeljeznickeKompozicije.get(i).getPozicije().add(new Pozicija(zeljeznickeKompozicije.get(i).getPrethodniX(), zeljeznickeKompozicije.get(i).getPrethodniY()));
									zeljeznickeKompozicije.get(i).notify();
									zeljeznickeKompozicije.remove(i);
									
								}
							}
							}
							}
						}
						}
					}
					}
				}
				sleep(1000);
			} catch (InterruptedException e) {
				FileLogger.log(Level.SEVERE, null, e);
			}
		}
	}
	public static void prilagodiBrzinuZeljeznickeKompozicije(ZeljeznickaKompozicija zk) {
		ZeljeznickaKompozicija prethodna = null;
		for(ZeljeznickaStanica stanica:Mapa.getZeljeznickeStanice()) {
			if(stanica.getNaziv().equals(zk.getPutanja().split(Mapa.getRazdvojputanjekarakter())[zk.getPozicijaNaPutanji()+1])) {
				if(("B".equals(stanica.getNaziv()) && "A".equals(zk.getPutanja().split(Mapa.getRazdvojputanjekarakter())[zk.getPozicijaNaPutanji()])) ||("A".equals(stanica.getNaziv()) && "A".equals(zk.getPutanja().split(Mapa.getRazdvojputanjekarakter())[zk.getPozicijaNaPutanji()]) )){
					synchronized (Mapa.getKompozicijePrugaAB()) {
						for(ZeljeznickaKompozicija zeljKomp:Mapa.getKompozicijePrugaAB()) {
							if(zk == zeljKomp) {
								if(prethodna!=null) {
									zk.setBrzina(prethodna.getBrzina());
								}
							}
								prethodna = zeljKomp;
							
						}
					}
				}else if(("B".equals(stanica.getNaziv()) && "C".equals(zk.getPutanja().split(Mapa.getRazdvojputanjekarakter())[zk.getPozicijaNaPutanji()])) ||("C".equals(stanica.getNaziv()) && "B".equals(zk.getPutanja().split(Mapa.getRazdvojputanjekarakter())[zk.getPozicijaNaPutanji()]) )) {
					synchronized (Mapa.getKompozicijePrugaBC()) {
						for(ZeljeznickaKompozicija zeljKomp:Mapa.getKompozicijePrugaBC()) {
							if(zk == zeljKomp) {
								if(prethodna!=null) {
									zk.setBrzina(prethodna.getBrzina());
								}
							}
								prethodna = zeljKomp;			
						}
					}
				}else if(("D".equals(stanica.getNaziv()) && "C".equals(zk.getPutanja().split(Mapa.getRazdvojputanjekarakter())[zk.getPozicijaNaPutanji()])) ||("C".equals(stanica.getNaziv()) && "D".equals(zk.getPutanja().split(Mapa.getRazdvojputanjekarakter())[zk.getPozicijaNaPutanji()]) )) {
					synchronized (Mapa.getKompozicijePrugaCD()) {
						for(ZeljeznickaKompozicija zeljKomp:Mapa.getKompozicijePrugaCD()) {
							if(zk == zeljKomp) {
								if(prethodna!=null) {
									zk.setBrzina(prethodna.getBrzina());
								}
							}
								prethodna = zeljKomp;
						}
					}
				}else if(("E".equals(stanica.getNaziv()) && "C".equals(zk.getPutanja().split(Mapa.getRazdvojputanjekarakter())[zk.getPozicijaNaPutanji()])) ||("C".equals(stanica.getNaziv()) && "E".equals(zk.getPutanja().split(Mapa.getRazdvojputanjekarakter())[zk.getPozicijaNaPutanji()]) )) {
					synchronized (Mapa.getKompozicijePrugaCE()) {	
						for(ZeljeznickaKompozicija zeljKomp:Mapa.getKompozicijePrugaCE()) {
							if(zk == zeljKomp) {
								if(prethodna!=null) {
									zk.setBrzina(prethodna.getBrzina());
								}
							}
								prethodna = zeljKomp;
							
						}
					}
				}
			}
		}
	}

	public List<ZeljeznickaKompozicija> getZeljeznickeKompozicije() {
		return zeljeznickeKompozicije;
	}
	public void setZeljeznickeKompozicije(List<ZeljeznickaKompozicija> zeljeznickeKompozicije) {
		this.zeljeznickeKompozicije = zeljeznickeKompozicije;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
