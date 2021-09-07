package net.etfbl.pj2.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.regex.Pattern;

import net.etfbl.pj2.utility.FileLogger;

public class Mapa {

	private static int dimenzija = 30;
	private static Polje[][] mapa = new Polje[dimenzija][dimenzija];

	private static List<Vozilo> vozilaLijeviPut = new ArrayList<Vozilo>();
	private static List<Vozilo> vozilaDesniPut = new ArrayList<Vozilo>();
	private static List<Vozilo> vozilaCentralniPut = new ArrayList<Vozilo>();

	private static List<ZeljeznickaStanica> zeljeznickeStanice = new ArrayList<ZeljeznickaStanica>();

	private static int brojVozilaLijeviPut;
	private static int brojVozilaDesniPut;
	private static int brojVozilaCentralniPut;

	private static int maxBrzinaCentralniPut;
	private static int maxBrzinaLijeviPut;
	private static int maxBrzinaDesniPut;

	private static List<ZeljeznickaKompozicija> kompozicijePrugaAB = new ArrayList<ZeljeznickaKompozicija>();
	private static List<ZeljeznickaKompozicija> kompozicijePrugaBC = new ArrayList<ZeljeznickaKompozicija>();
	private static List<ZeljeznickaKompozicija> kompozicijePrugaCD = new ArrayList<ZeljeznickaKompozicija>();
	private static List<ZeljeznickaKompozicija> kompozicijePrugaCE = new ArrayList<ZeljeznickaKompozicija>();
	private static List<ZeljeznickaKompozicija> kompozicijePrugaAV = new ArrayList<ZeljeznickaKompozicija>();
	private static List<ZeljeznickaKompozicija> kompozicijePrugaEV = new ArrayList<ZeljeznickaKompozicija>();

	private static int lijeviPrelaz = 0;
	private static int centralniPrelaz = 0;
	private static int desniPrelaz = 0;

	private static final String razdvojPutanjeKarakter = "-";
	private static boolean kraj = false;
	private static int minBrzina = 500;
	
	private static String vozoviPutanja;
	private static String kretanjaPutanja;
	private static String loggerPutanja;
	static {
		ucitajConfiguracijuPuteva();
	}

	public static void postaviZeljeznickeStanice() {
		ZeljeznickaStanica bStanica = new ZeljeznickaStanica("B");
		Mapa.mapa[6][5] = new Polje();
		Mapa.mapa[6][5].getElementi().add(new Pruga());
		Mapa.mapa[6][5].getElementi().add(bStanica);
		Mapa.mapa[6][6] = new Polje();
		Mapa.mapa[6][6].getElementi().add(new Pruga());
		Mapa.mapa[6][6].getElementi().add(bStanica);
		Mapa.mapa[7][6] = new Polje();
		Mapa.mapa[7][6].getElementi().add(new Pruga());
		Mapa.mapa[7][6].getElementi().add(bStanica);
		Mapa.mapa[7][5] = new Polje();
		Mapa.mapa[7][5].getElementi().add(new Pruga());
		Mapa.mapa[7][5].getElementi().add(bStanica);
		zeljeznickeStanice.add(bStanica);

		ZeljeznickaStanica vStanica = new ZeljeznickaStanica("V");
		zeljeznickeStanice.add(vStanica);
		
		ZeljeznickaStanica aStanica = new ZeljeznickaStanica("A");
		Mapa.mapa[2][28] = new Polje();
		Mapa.mapa[2][28].getElementi().add(new Pruga());
		Mapa.mapa[2][28].getElementi().add(aStanica);
		Mapa.mapa[2][27] = new Polje();
		Mapa.mapa[2][27].getElementi().add(new Pruga());
		Mapa.mapa[2][27].getElementi().add(aStanica);
		Mapa.mapa[1][28] = new Polje();
		Mapa.mapa[1][28].getElementi().add(new Pruga());
		Mapa.mapa[1][28].getElementi().add(aStanica);
		Mapa.mapa[1][27] = new Polje();
		Mapa.mapa[1][27].getElementi().add(new Pruga());
		Mapa.mapa[1][27].getElementi().add(aStanica);
		zeljeznickeStanice.add(aStanica);

		ZeljeznickaStanica dStanica = new ZeljeznickaStanica("D");
		Mapa.mapa[26][1] = new Polje();
		Mapa.mapa[26][1].getElementi().add(new Pruga());
		Mapa.mapa[26][1].getElementi().add(dStanica);
		Mapa.mapa[27][1] = new Polje();
		Mapa.mapa[27][1].getElementi().add(new Pruga());
		Mapa.mapa[27][1].getElementi().add(dStanica);
		Mapa.mapa[26][2] = new Polje();
		Mapa.mapa[26][2].getElementi().add(new Pruga());
		Mapa.mapa[26][2].getElementi().add(dStanica);
		Mapa.mapa[27][2] = new Polje();
		Mapa.mapa[27][2].getElementi().add(new Pruga());
		Mapa.mapa[27][2].getElementi().add(dStanica);
		zeljeznickeStanice.add(dStanica);

		ZeljeznickaStanica eStanica = new ZeljeznickaStanica("E");
		Mapa.mapa[25][25] = new Polje();
		Mapa.mapa[25][25].getElementi().add(new Pruga());
		Mapa.mapa[25][25].getElementi().add(eStanica);
		Mapa.mapa[26][25] = new Polje();
		Mapa.mapa[26][25].getElementi().add(new Pruga());
		Mapa.mapa[26][25].getElementi().add(eStanica);
		Mapa.mapa[25][26] = new Polje();
		Mapa.mapa[25][26].getElementi().add(new Pruga());
		Mapa.mapa[25][26].getElementi().add(eStanica);
		Mapa.mapa[26][26] = new Polje();
		Mapa.mapa[26][26].getElementi().add(new Pruga());
		Mapa.mapa[26][26].getElementi().add(eStanica);
		zeljeznickeStanice.add(eStanica);

		ZeljeznickaStanica cStanica = new ZeljeznickaStanica("C");
		// x19 y=12
		Mapa.mapa[19][12] = new Polje();
		Mapa.mapa[19][12].getElementi().add(new Pruga());
		Mapa.mapa[19][12].getElementi().add(cStanica);
		Mapa.mapa[20][12] = new Polje();
		Mapa.mapa[20][12].getElementi().add(new Pruga());
		Mapa.mapa[20][12].getElementi().add(cStanica);
		Mapa.mapa[19][13] = new Polje();
		Mapa.mapa[19][13].getElementi().add(new Pruga());
		Mapa.mapa[19][13].getElementi().add(cStanica);
		Mapa.mapa[20][13] = new Polje();
		Mapa.mapa[20][13].getElementi().add(new Pruga());
		Mapa.mapa[20][13].getElementi().add(cStanica);
		zeljeznickeStanice.add(cStanica);

	}

	public static void postaviZeljeznicu() {

		for (int i = 6; i <= 16; i++) {
			mapa[5][i] = new Polje();
			mapa[5][i].getElementi().add(new Pruga());
		}

		for (int i = 8; i <= 19; i++) {
			mapa[i][6] = new Polje();
			mapa[i][6].getElementi().add(new Pruga());
		}

		for (int i = 2; i <= 5; i++) {
			mapa[i][16] = new Polje();
			mapa[i][16].getElementi().add(new Pruga());
		}

		for (int i = 17; i < dimenzija; i++) {
			if (i < 27 || i > 28) {
				mapa[2][i] = new Polje();
				mapa[2][i].getElementi().add(new Pruga());
			}
		}

		for (int i = 6; i <= 11; i++) {
			mapa[19][i] = new Polje();
			mapa[19][i].getElementi().add(new Pruga());
		}

		for (int i = 14; i <= 18; i++) {
			mapa[20][i] = new Polje();
			mapa[20][i].getElementi().add(new Pruga());
		}
		for (int i = 21; i <= 26; i++) {
			mapa[i][18] = new Polje();
			mapa[i][18].getElementi().add(new Pruga());
		}
		for (int i = 19; i <= 24; i++) {
			mapa[26][i] = new Polje();
			mapa[26][i].getElementi().add(new Pruga());
		}
		for (int i = 21; i <= 26; i++) {
			mapa[i][12] = new Polje();
			mapa[i][12].getElementi().add(new Pruga());
		}

		for (int i = 9; i <= 11; i++) {
			mapa[26][i] = new Polje();
			mapa[26][i].getElementi().add(new Pruga());
		}
		mapa[27][9] = new Polje();
		mapa[27][9].getElementi().add(new Pruga());

		for (int i = 5; i <= 9; i++) {
			mapa[28][i] = new Polje();
			mapa[28][i].getElementi().add(new Pruga());
		}

		for (int i = 23; i <= 28; i++) {
			mapa[i][5] = new Polje();
			mapa[i][5].getElementi().add(new Pruga());
		}
		mapa[23][4] = new Polje();
		mapa[23][4].getElementi().add(new Pruga());
		mapa[23][3] = new Polje();
		mapa[23][3].getElementi().add(new Pruga());
		mapa[23][3] = new Polje();
		mapa[23][3].getElementi().add(new Pruga());
		mapa[22][3] = new Polje();
		mapa[22][3].getElementi().add(new Pruga());
		mapa[22][2] = new Polje();
		mapa[22][2].getElementi().add(new Pruga());

		for (int i = 22; i <= 25; i++) {
			mapa[i][1] = new Polje();
			mapa[i][1].getElementi().add(new Pruga());
		}
		for (int i = 27; i < dimenzija; i++) {
			mapa[i][25] = new Polje();
			mapa[i][25].getElementi().add(new Pruga());
		}

	}

	public static void postaviPuteve() {
		// put od vrha do dna najveci put
		for (int i = 13; i <= 14; i++) {
			for (int j = 0; j < dimenzija; j++) {
				if (mapa[i][j] == null) {
					mapa[i][j] = new Polje();
					mapa[i][j].getElementi().add(new Put());
				} else {
					mapa[i][j].getElementi().add(new Put());
				}
			}
		}
		// horizontalni dio puta
		for (int i = 20; i <= 21; i++) {
			for (int j = 0; j < dimenzija; j++) {
				if (j <= 8 || j >= 21) {
					if (mapa[j][i] == null) {
						mapa[j][i] = new Polje();
						mapa[j][i].getElementi().add(new Put());
					} else {
						mapa[j][i].getElementi().add(new Put());
					}
				}
			}
		}
		// vertikalni putevi manji
		for (int i = 7; i <= 8; i++) {
			for (int j = 20; j < dimenzija; j++) {
				if (mapa[i][j] == null) {
					mapa[i][j] = new Polje();
					mapa[i][j].getElementi().add(new Put());
				} else {
					mapa[i][j].getElementi().add(new Put());
				}
			}
		}

		for (int i = 21; i <= 22; i++) {
			for (int j = 20; j < dimenzija; j++) {
				if (mapa[i][j] == null) {
					mapa[i][j] = new Polje();
					mapa[i][j].getElementi().add(new Put());
				} else {
					mapa[i][j].getElementi().add(new Put());
				}
			}
		}
	}

	public synchronized static void ucitajConfiguracijuPuteva() {
		Properties p = new Properties();
		try {
			InputStream ulaz = new FileInputStream("src" + File.separator + "net" + File.separator + "etfbl"
					+ File.separator + "pj2" + File.separator + "configuration" + File.separator + "config.properties");
			p.load(ulaz);

			setMaxBrzinaCentralniPut(Integer.parseInt(p.getProperty("Maksimalna brzina centralni put")));
			setMaxBrzinaDesniPut(Integer.parseInt(p.getProperty("Maksimalna brzina desni put")));
			setMaxBrzinaLijeviPut(Integer.parseInt(p.getProperty("Maksimalna brzina lijevi put")));
			int broj;
			broj = Integer.parseInt(p.getProperty("Broj vozila centralni put"));
			if (broj > brojVozilaCentralniPut)
				brojVozilaCentralniPut = broj;

			broj = Integer.parseInt(p.getProperty("Broj vozila lijevi put"));
			if (broj > brojVozilaLijeviPut)
				brojVozilaLijeviPut = broj;

			broj = Integer.parseInt(p.getProperty("Broj vozila desni put"));
			if (broj > brojVozilaDesniPut)
				brojVozilaDesniPut = broj;
			
			vozoviPutanja = p.getProperty("Vozovi putanja");
			kretanjaPutanja=p.getProperty("Kretanja putanja");
			setLoggerPutanja(p.getProperty("Logger putanja"));
			
			
			ulaz.close();
			

		} catch (IOException e) {
			FileLogger.log(Level.SEVERE, null, e);
		}
	}

	public static List<ZeljeznickaStanica> getZeljeznickeStanice() {
		return zeljeznickeStanice;
	}

	public static void setZeljeznickeStanice(List<ZeljeznickaStanica> zeljeznickeStanice) {
		Mapa.zeljeznickeStanice = zeljeznickeStanice;
	}

	public static int getDimenzija() {
		return dimenzija;
	}

	public static void setDimenzija(int dimenzija) {
		Mapa.dimenzija = dimenzija;
	}

	public static List<ZeljeznickaKompozicija> getKompozicijePrugaAB() {
		return kompozicijePrugaAB;
	}

	public static void setKompozicijePrugaAB(List<ZeljeznickaKompozicija> kompozicijePrugaAB) {
		Mapa.kompozicijePrugaAB = kompozicijePrugaAB;
	}

	public static List<ZeljeznickaKompozicija> getKompozicijePrugaBC() {
		return kompozicijePrugaBC;
	}

	public static void setKompozicijePrugaBC(List<ZeljeznickaKompozicija> kompozicijePrugaBC) {
		Mapa.kompozicijePrugaBC = kompozicijePrugaBC;
	}

	public static List<ZeljeznickaKompozicija> getKompozicijePrugaCD() {
		return kompozicijePrugaCD;
	}

	public static void setKompozicijePrugaCD(List<ZeljeznickaKompozicija> kompozicijePrugaCD) {
		Mapa.kompozicijePrugaCD = kompozicijePrugaCD;
	}

	public static List<ZeljeznickaKompozicija> getKompozicijePrugaCE() {
		return kompozicijePrugaCE;
	}

	public static void setKompozicijePrugaCE(List<ZeljeznickaKompozicija> kompozicijePrugaCE) {
		Mapa.kompozicijePrugaCE = kompozicijePrugaCE;
	}

	public static String getRazdvojputanjekarakter() {
		return razdvojPutanjeKarakter;
	}

	public static void ucitajKompozicijeIzFajlova() {

		//File file = new File("src" + File.separator + "net" + File.separator + "etfbl" + File.separator + "pj2"
			//	+ File.separator + "vozovi");
		File file = new File(Mapa.getVozoviPutanja());
		for (File f : file.listFiles()) {
			ucitajJednuKompoziciju(f);
		}
	}

	public static String getVozoviPutanja() {
		return vozoviPutanja;
	}

	public static void setVozoviPutanja(String vozoviPutanja) {
		Mapa.vozoviPutanja = vozoviPutanja;
	}

	public static void ucitajJednuKompoziciju(File f) {
		try {
			List<String> lines = Files.readAllLines(f.toPath());
			if (lines.size() == 1) {
				List<Lokomotiva> lokomotive = new ArrayList<Lokomotiva>();
				List<Vagon> vagoni = new ArrayList<Vagon>();
				String[] parametri = lines.get(0).split(",");
				if(parametri.length!=3) {
					System.out.println("Nije validna kompozicija "+f.toString());
					return ;
				}
				try {
					if(Integer.parseInt(parametri[2])<=Mapa.getMinBrzina()) {
						System.out.println("Brzina mora biti veca od "+Mapa.getMinBrzina()+" "+f.toString());
						return;
					}
				}catch (Exception e) {
					System.out.println("Brzina mora biti integer veci od +"+Mapa.getMinBrzina()+" "+f.toString());
					FileLogger.log(Level.FINE, null, e);
					return;
				}
				String[] elementiKompozicije = parametri[0].split(" ");

				for (String e : elementiKompozicije) {
					if ("TeretnaLokomotiva".equals(e)) {
						lokomotive.add(new TeretnaLokomotiva());
					} else if ("PutnickaLokomotiva".equals(e)) {
						lokomotive.add(new PutnickaLokomotiva());
					} else if ("UniverzalnaLokomotiva".equals(e)) {
						lokomotive.add(new UniverzalnaLokomotiva());
					}else if("ManevarskaLokomotiva".equals(e)) {
						lokomotive.add(new ManevarskaLokomotiva());
					}else if ("TeretniVagon".equals(e)) {
						vagoni.add(new VagonTeretni());
					} else if ("VagonSaSjedistima".equals(e)) {
						vagoni.add(new VagonSaSjedistima());
					} else if ("VagonPosebneNamjene".equals(e)) {
						vagoni.add(new VagonPosebneNamjene());
					} else if ("VagonSaLezajevima".equals(e)) {
						vagoni.add(new VagonSaLezajevima());
					} else if ("VagonRestoran".equals(e)) {
						vagoni.add(new VagonRestoran());
					} else if ("VagonZaSpavanje".equals(e)) {
						vagoni.add(new VagonZaSpavanje());
					}
					else {
					System.out.println("Nije validna kompozicija "+f.toString());
						return;
					}
				}
				validirajKompoziciju(lokomotive,vagoni,f.toString());
				ZeljeznickaKompozicija zk = new ZeljeznickaKompozicija(f.getName().split(Pattern.quote("."))[0],
						lokomotive, vagoni, parametri[1], Integer.valueOf(parametri[2]));
				for (ZeljeznickaStanica zs : Mapa.getZeljeznickeStanice()) {
					synchronized(zs) {
					if (zs.getNaziv().equals(parametri[1].split(Mapa.getRazdvojputanjekarakter())[0])) {
						zs.getZeljeznickeKompozicije().add(zk);
					}
				}
				}

			} else {
				System.out.println("Nije dobar format kompozicije");
			}
		} catch (Exception e) {
			FileLogger.log(Level.SEVERE, null, e);
		}
	}
	public static void validirajKompoziciju(List<Lokomotiva>lokomotive, List<Vagon> vagoni,String putanja) {
		if(lokomotive.isEmpty()) {
			System.out.println("Nije validna kompozicija mora bar jedna lokomotiva da ima"+putanja);
			return;
		}
		for(Lokomotiva l:lokomotive) {
			if(l instanceof TeretnaLokomotiva) {
				for(Lokomotiva l1:lokomotive) {
					if(!(l1 instanceof TeretnaLokomotiva) && !(l1 instanceof UniverzalnaLokomotiva)) {
						System.out.println("Lokomotive nisu kompatibilne"+putanja);
						return;
					}
				}
			}else if(l instanceof PutnickaLokomotiva) {
				for(Lokomotiva l1:lokomotive) {
					if(!(l1 instanceof PutnickaLokomotiva) && !(l1 instanceof UniverzalnaLokomotiva)) {
						System.out.println("Lokomotive nisu kompatibilne"+putanja);
						return;
					}
				}
			}
		}
		for(Lokomotiva l:lokomotive) {
			if(l instanceof TeretnaLokomotiva) {
				for(Vagon v:vagoni) {
					if(!(v instanceof VagonTeretni)) {
						System.out.println("Lokomotive i vagoni nisu kompatibilni"+putanja);
						return;
					}
				}
			}else if (l instanceof PutnickaLokomotiva) {
				for(Vagon v:vagoni) {
					if(!(v instanceof PutnickiVagon)) {
						System.out.println("Lokomotive i vagoni nisu kompatibilni"+putanja);
						return;
					}
				}
			}else if(l instanceof ManevarskaLokomotiva) {
				for(Vagon v:vagoni) {
					if(!(v instanceof VagonPosebneNamjene)) {
						System.out.println("Lokomotive i vagoni nisu kompatibilni"+putanja);
						return;
					}
				}
			}else if (l instanceof UniverzalnaLokomotiva) {
				for(Vagon v:vagoni) {
					if(v instanceof PutnickiVagon) {
						for(Vagon v1:vagoni) {
							if(!(v1 instanceof PutnickiVagon)) {
								System.out.println("Vagoni nisu iste kategorije"+putanja);
								return;
							}
						}
					}else if(v instanceof VagonTeretni) {
						for(Vagon v1:vagoni) {
							if(!(v1 instanceof VagonTeretni)) {
								System.out.println("Vagoni nisu iste kategorije"+putanja);
								return;
							}
						}
					}else if(v instanceof VagonPosebneNamjene) {
						for(Vagon v1:vagoni) {
							if(!(v1 instanceof VagonPosebneNamjene)) {
								System.out.println("Vagoni nisu iste kategorije"+putanja);
								return;
							}
						}
					}
				}
			}
		}
	}
	public static void obrisiSerijalizacije() {
		File file = new File(Mapa.getKretanjaPutanja());
		for (File f : file.listFiles()) {
			//System.out.println(f);
			f.delete();
		}
	}

	public static boolean isKraj() {
		return kraj;
	}

	public static void setKraj(boolean kraj) {
		Mapa.kraj = kraj;
	}

	public static int getMaxBrzinaCentralniPut() {
		return maxBrzinaCentralniPut;
	}

	public static void setMaxBrzinaCentralniPut(int maxBrzinaCentralniPut) {
		Mapa.maxBrzinaCentralniPut = maxBrzinaCentralniPut;
	}

	public static int getMaxBrzinaLijeviPut() {
		return maxBrzinaLijeviPut;
	}

	public static void setMaxBrzinaLijeviPut(int maxBrzinaLijeviPut) {
		Mapa.maxBrzinaLijeviPut = maxBrzinaLijeviPut;
	}

	public static int getMaxBrzinaDesniPut() {
		return maxBrzinaDesniPut;
	}

	public static void setMaxBrzinaDesniPut(int maxBrzinaDesniPut) {
		Mapa.maxBrzinaDesniPut = maxBrzinaDesniPut;
	}

	public static int getMinBrzina() {
		return minBrzina;
	}

	public static void setMinBrzina(int minBrzina) {
		Mapa.minBrzina = minBrzina;
	}

	public static int getBrojVozilaLijeviPut() {
		return brojVozilaLijeviPut;
	}

	public static void setBrojVozilaLijeviPut(int brojVozilaLijeviPut) {
		Mapa.brojVozilaLijeviPut = brojVozilaLijeviPut;
	}

	public static int getBrojVozilaDesniPut() {
		return brojVozilaDesniPut;
	}

	public static void setBrojVozilaDesniPut(int brojVozilaDesniPut) {
		Mapa.brojVozilaDesniPut = brojVozilaDesniPut;
	}

	public static int getBrojVozilaCentralniPut() {
		return brojVozilaCentralniPut;
	}

	public static void setBrojVozilaCentralniPut(int brojVozilaCentralniPut) {
		Mapa.brojVozilaCentralniPut = brojVozilaCentralniPut;
	}

	public static List<Vozilo> getVozilaLijeviPut() {
		return vozilaLijeviPut;
	}

	public static void setVozilaLijeviPut(List<Vozilo> vozilaLijeviPut) {
		Mapa.vozilaLijeviPut = vozilaLijeviPut;
	}

	public static List<Vozilo> getVozilaDesniPut() {
		return vozilaDesniPut;
	}

	public static void setVozilaDesniPut(List<Vozilo> vozilaDesniPut) {
		Mapa.vozilaDesniPut = vozilaDesniPut;
	}

	public static List<Vozilo> getVozilaCentralniPut() {
		return vozilaCentralniPut;
	}

	public static void setVozilaCentralniPut(List<Vozilo> vozilaCentralniPut) {
		Mapa.vozilaCentralniPut = vozilaCentralniPut;
	}

	public static int getLijeviPrelaz() {
		return lijeviPrelaz;
	}

	public static void setLijeviPrelaz(int lijeviPrelaz) {
		Mapa.lijeviPrelaz = lijeviPrelaz;
	}

	public static int getCentralniPrelaz() {
		return centralniPrelaz;
	}

	public static void setCentralniPrelaz(int centralniPrelaz) {
		Mapa.centralniPrelaz = centralniPrelaz;
	}

	public static int getDesniPrelaz() {
		return desniPrelaz;
	}

	public static void setDesniPrelaz(int desniPrelaz) {
		Mapa.desniPrelaz = desniPrelaz;
	}

	public static Polje[][] getMapa() {
		return mapa;
	}

	public static void setMapa(Polje[][] mapa) {
		Mapa.mapa = mapa;
	}

	public static String getKretanjaPutanja() {
		return kretanjaPutanja;
	}

	public static void setKretanjaPutanja(String kretanjaPutanja) {
		Mapa.kretanjaPutanja = kretanjaPutanja;
	}

	public static String getLoggerPutanja() {
		return loggerPutanja;
	}

	public static void setLoggerPutanja(String loggerPutanja) {
		Mapa.loggerPutanja = loggerPutanja;
	}

	public static List<ZeljeznickaKompozicija> getKompozicijePrugaEV() {
		return kompozicijePrugaEV;
	}

	public static void setKompozicijePrugaEV(List<ZeljeznickaKompozicija> kompozicijePrugaEV) {
		Mapa.kompozicijePrugaEV = kompozicijePrugaEV;
	}

	public static List<ZeljeznickaKompozicija> getKompozicijePrugaAV() {
		return kompozicijePrugaAV;
	}

	public static void setKompozicijePrugaAV(List<ZeljeznickaKompozicija> kompozicijePrugaAV) {
		Mapa.kompozicijePrugaAV = kompozicijePrugaAV;
	}

}
