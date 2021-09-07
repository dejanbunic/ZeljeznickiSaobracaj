package net.etfbl.pj2.controller;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.etfbl.pj2.enumeration.Smijer;
import net.etfbl.pj2.model.Automobil;
import net.etfbl.pj2.model.Kamion;
import net.etfbl.pj2.model.ManevarskaLokomotiva;
import net.etfbl.pj2.model.Mapa;
import net.etfbl.pj2.model.Pruga;
import net.etfbl.pj2.model.Put;
import net.etfbl.pj2.model.PutnickaLokomotiva;
import net.etfbl.pj2.model.TeretnaLokomotiva;
import net.etfbl.pj2.model.UniverzalnaLokomotiva;
import net.etfbl.pj2.model.VagonPosebneNamjene;
import net.etfbl.pj2.model.VagonRestoran;
import net.etfbl.pj2.model.VagonSaLezajevima;
import net.etfbl.pj2.model.VagonSaSjedistima;
import net.etfbl.pj2.model.VagonTeretni;
import net.etfbl.pj2.model.VagonZaSpavanje;
import net.etfbl.pj2.model.Vozilo;
import net.etfbl.pj2.model.ZeljeznickaKompozicija;
import net.etfbl.pj2.model.ZeljeznickaStanica;
import net.etfbl.pj2.utility.FileLogger;
import net.etfbl.pj2.utility.FileWatcher;
import net.etfbl.pj2.utility.FileWatcherVozovi;

public class GlavnaController {

	private int sirinaIVisinaPolja;
	
	@FXML
	private FlowPane vagonSjedistaBoja;
	
	@FXML
    private FlowPane lokomotivaPutnickaBoja;
	
	@FXML
    private FlowPane lokomotivaUniverzalnaBoja;
	
	@FXML
    private FlowPane vagonPosebneNamjeneBoja;
	
	@FXML
	private FlowPane vagonRestoranBoja;
	
	@FXML
	private FlowPane vagonLezajBoja;
	
	@FXML
	private FlowPane vagonSpavanjeBoja;	
	
	@FXML
	private FlowPane vagonTeretniBoja;
	
	@FXML
	private FlowPane lokomotivaTeretnaBoja;
	
	@FXML
	private FlowPane automobilBoja;

	@FXML
	private FlowPane kamionBoja;

	@FXML
	private GridPane gridMapa;
	
    @FXML
    private FlowPane lokomotivaManevarskaBoja;
    
	@FXML
	public void initialize() {
		
		Mapa.ucitajConfiguracijuPuteva();
		FileWatcher fileWatcher = new FileWatcher("src" + File.separator + "net" + File.separator + "etfbl"
				+ File.separator + "pj2" + File.separator + "configuration");
		
		fileWatcher.start();
		
		//FileWatcherVozovi fileWatcherVozovi = new FileWatcherVozovi("src" + File.separator + "net" + File.separator + "etfbl"
			//	+ File.separator + "pj2"+File.separator+"vozovi");
		
		FileWatcherVozovi fileWatcherVozovi = new FileWatcherVozovi(Mapa.getVozoviPutanja());
		fileWatcherVozovi.start();

		Mapa.postaviZeljeznicu();
		Mapa.postaviPuteve();
		Mapa.postaviZeljeznickeStanice();
		postaviLegendu();
		Mapa.ucitajKompozicijeIzFajlova();
		Mapa.obrisiSerijalizacije(); 
		postaviMapu();

	}
	@FXML
	void zatvoriAplikaciju(ActionEvent event) {
		Mapa.setKraj(true);
		 System.exit(0);
	}
	
	private void postaviLegendu() {
		
		BackgroundFill fill1 = new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY);
		automobilBoja.setBackground(new Background(fill1));
		BackgroundFill fill2 = new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY);
		kamionBoja.setBackground(new Background(fill2));

		BackgroundFill fill3 = new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY);
		lokomotivaTeretnaBoja.setBackground(new Background(fill3));
		BackgroundFill fill4 = new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY);
		vagonTeretniBoja.setBackground(new Background(fill4));
		
		BackgroundFill fill5 = new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY);
		vagonSjedistaBoja.setBackground(new Background(fill5));
		BackgroundFill fill6 = new BackgroundFill(Color.SPRINGGREEN, CornerRadii.EMPTY, Insets.EMPTY);
		vagonLezajBoja.setBackground(new Background(fill6));
		
		BackgroundFill fill7 = new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY);
		vagonSpavanjeBoja.setBackground(new Background(fill7));
		BackgroundFill fill8 = new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY);
		vagonRestoranBoja.setBackground(new Background(fill8));
		
		BackgroundFill fill9 = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		lokomotivaPutnickaBoja.setBackground(new Background(fill9));
		BackgroundFill fill10 = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
		lokomotivaUniverzalnaBoja.setBackground(new Background(fill10));
		BackgroundFill fill11 = new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY);
		vagonPosebneNamjeneBoja.setBackground(new Background(fill11));
		
		BackgroundFill fill12 = new BackgroundFill(Color.CADETBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		lokomotivaManevarskaBoja.setBackground(new Background(fill12));
	}

	@FXML
	void pokreniSimulaciju(ActionEvent event) {
		for(ZeljeznickaStanica z:Mapa.getZeljeznickeStanice())
			z.start();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Runnable updater = new Runnable() {
					int i = 0;

					@Override
					public void run() {
						if (i % 2 == 0) {
							kreirajVozila();
						}
						i++;
						prikaziTrenutnoStanjeMape(sirinaIVisinaPolja);

					}
				};
				while (true) {
					Platform.runLater(updater);
					try {
						Thread.sleep(1000);

					} catch (InterruptedException ex) {
						 FileLogger.log(Level.SEVERE, null, ex);
					}
				}
			}

		});
		thread.start();
	}

	public void kreirajVozila() {
		Random random = new Random();
		Vozilo vozilo = null;
		
		if (Mapa.getVozilaCentralniPut().size() < Mapa.getBrojVozilaCentralniPut()) {
			int broj;
			if (random.nextInt(100) % 2 == 0) {
				broj = 0;
				if(Mapa.getMapa()[14][29].getElementi().get(Mapa.getMapa()[14][29].getElementi().size()-1) instanceof Put &&
						Mapa.getMapa()[13][0].getElementi().get(Mapa.getMapa()[13][0].getElementi().size()-1) instanceof Put) {
					broj = random.nextInt(100);
				}else {
					if(Mapa.getMapa()[14][29].getElementi().get(Mapa.getMapa()[14][29].getElementi().size()-1) instanceof Put)
						broj = 0;
					else 
						broj = 1;
				}
				if (broj % 2 == 0) {
					if(Mapa.getMapa()[14][29].getElementi().get(Mapa.getMapa()[14][29].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaCentralniPut()-Mapa.getMinBrzina());
						vozilo = new Automobil(14, 29,brzina);
						vozilo.setSmijer(Smijer.GORE);
						Mapa.getMapa()[14][29].getElementi().add(vozilo);
						Mapa.getVozilaCentralniPut().add(vozilo);
						vozilo.start();
					}
				} else {
					if(Mapa.getMapa()[13][0].getElementi().get(Mapa.getMapa()[13][0].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaCentralniPut()-Mapa.getMinBrzina());
						vozilo = new Automobil(13, 0,brzina);
						vozilo.setSmijer(Smijer.DOLE);
						Mapa.getMapa()[13][0].getElementi().add(vozilo);
						Mapa.getVozilaCentralniPut().add(vozilo);
						vozilo.start();
					}
				}
			} else {
				broj = 0;
				if(Mapa.getMapa()[14][29].getElementi().get(Mapa.getMapa()[14][29].getElementi().size()-1) instanceof Put &&
						Mapa.getMapa()[13][0].getElementi().get(Mapa.getMapa()[13][0].getElementi().size()-1) instanceof Put) {
					broj = random.nextInt(100);
				}else {
					if(Mapa.getMapa()[14][29].getElementi().get(Mapa.getMapa()[14][29].getElementi().size()-1) instanceof Put)
						broj = 0;
					else 
						broj = 1;
				}
				if (broj % 2 == 0) {
					if(Mapa.getMapa()[14][29].getElementi().get(Mapa.getMapa()[14][29].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaCentralniPut()-Mapa.getMinBrzina());
						vozilo = new Kamion(14, 29,brzina);
						vozilo.setSmijer(Smijer.GORE);
						Mapa.getMapa()[14][29].getElementi().add(vozilo);
						Mapa.getVozilaCentralniPut().add(vozilo);
					}
					vozilo.start();
				} else {
					if(Mapa.getMapa()[13][0].getElementi().get(Mapa.getMapa()[13][0].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaCentralniPut()-Mapa.getMinBrzina());
						vozilo = new Kamion(13, 0,brzina);
						vozilo.setSmijer(Smijer.DOLE);
						Mapa.getMapa()[13][0].getElementi().add(vozilo);
						Mapa.getVozilaCentralniPut().add(vozilo);
						vozilo.start();
					}
				}
			}
		}
		if (Mapa.getVozilaLijeviPut().size() < Mapa.getBrojVozilaLijeviPut()) {
			int broj;
			if (random.nextInt(100) % 2 == 0) {
				broj = 0;
				if(Mapa.getMapa()[8][29].getElementi().get(Mapa.getMapa()[8][29].getElementi().size()-1) instanceof Put &&
						Mapa.getMapa()[0][21].getElementi().get(Mapa.getMapa()[0][21].getElementi().size()-1) instanceof Put) {
					broj = random.nextInt(100);
				}else {
					if(Mapa.getMapa()[8][29].getElementi().get(Mapa.getMapa()[8][29].getElementi().size()-1) instanceof Put)
						broj = 0;
					else 
						broj = 1;
				}
				if (broj % 2 == 0) {
					if(Mapa.getMapa()[8][29].getElementi().get(Mapa.getMapa()[8][29].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaLijeviPut()-Mapa.getMinBrzina());
						vozilo = new Automobil(8, 29,brzina);
						vozilo.setSmijer(Smijer.GORE);
						Mapa.getMapa()[8][29].getElementi().add(vozilo);
						//
						Mapa.getVozilaLijeviPut().add(vozilo);
						vozilo.start();
					}
					
				} else {
					if(Mapa.getMapa()[0][21].getElementi().get(Mapa.getMapa()[0][21].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaLijeviPut()-Mapa.getMinBrzina());
						vozilo = new Automobil(0, 21,brzina);
						vozilo.setSmijer(Smijer.DESNO);
						Mapa.getMapa()[0][21].getElementi().add(vozilo);
						
						//
						Mapa.getVozilaLijeviPut().add(vozilo);
						vozilo.start();
					}
				}
			} else {
				broj = 0;
				if(Mapa.getMapa()[8][29].getElementi().get(Mapa.getMapa()[8][29].getElementi().size()-1) instanceof Put &&
						Mapa.getMapa()[0][21].getElementi().get(Mapa.getMapa()[0][21].getElementi().size()-1) instanceof Put) {
					broj = random.nextInt(100);
				}else {
					if(Mapa.getMapa()[8][29].getElementi().get(Mapa.getMapa()[8][29].getElementi().size()-1) instanceof Put)
						broj = 0;
					else 
						broj = 1;
				}
				if (broj % 2 == 0) {
					if(Mapa.getMapa()[8][29].getElementi().get(Mapa.getMapa()[8][29].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaLijeviPut()-Mapa.getMinBrzina());
						vozilo = new Kamion(8, 29,brzina);
						vozilo.setSmijer(Smijer.GORE);
						Mapa.getMapa()[8][29].getElementi().add(vozilo);
						//
						Mapa.getVozilaLijeviPut().add(vozilo);
						vozilo.start();
					}
				} else {
					if(Mapa.getMapa()[0][21].getElementi().get(Mapa.getMapa()[0][21].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaLijeviPut()-Mapa.getMinBrzina());
						vozilo = new Kamion(0, 21,brzina);
						vozilo.setSmijer(Smijer.DESNO);
						Mapa.getMapa()[0][21].getElementi().add(vozilo);
						//
						Mapa.getVozilaLijeviPut().add(vozilo);
						vozilo.start();
					}
				}
			}
		}
		if (Mapa.getVozilaDesniPut().size() < Mapa.getBrojVozilaDesniPut()) {
			int broj;
			if (random.nextInt(100) % 2 == 0) {
				broj = 0;
				if(Mapa.getMapa()[22][29].getElementi().get(Mapa.getMapa()[22][29].getElementi().size()-1) instanceof Put &&
						Mapa.getMapa()[29][20].getElementi().get(Mapa.getMapa()[29][20].getElementi().size()-1) instanceof Put) {
					broj = random.nextInt(100);
				}else {
					if(Mapa.getMapa()[22][29].getElementi().get(Mapa.getMapa()[22][29].getElementi().size()-1) instanceof Put)
						broj = 0;
					else 
						broj = 1;
				}
				if (broj % 2 == 0) {
					if(Mapa.getMapa()[22][29].getElementi().get(Mapa.getMapa()[22][29].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaDesniPut()-Mapa.getMinBrzina());
						vozilo = new Automobil(22, 29,brzina);
						vozilo.setSmijer(Smijer.GORE);
						Mapa.getMapa()[22][29].getElementi().add(vozilo);
						Mapa.getVozilaDesniPut().add(vozilo);
						vozilo.start();
					}
				} else {
					if(Mapa.getMapa()[29][20].getElementi().get(Mapa.getMapa()[29][20].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaDesniPut()-Mapa.getMinBrzina());
						vozilo = new Automobil(29, 20,brzina);
						vozilo.setSmijer(Smijer.LIJEVO);
						Mapa.getMapa()[29][20].getElementi().add(vozilo);
						Mapa.getVozilaDesniPut().add(vozilo);
						vozilo.start();
					}
				}
			} else {
				broj = 0;
				if(Mapa.getMapa()[22][29].getElementi().get(Mapa.getMapa()[22][29].getElementi().size()-1) instanceof Put &&
						Mapa.getMapa()[29][20].getElementi().get(Mapa.getMapa()[29][20].getElementi().size()-1) instanceof Put) {
					broj = random.nextInt(100);
				}else {
					if(Mapa.getMapa()[22][29].getElementi().get(Mapa.getMapa()[22][29].getElementi().size()-1) instanceof Put)
						broj = 0;
					else 
						broj = 1;
				}
				if (broj % 2 == 0) {
					if(Mapa.getMapa()[22][29].getElementi().get(Mapa.getMapa()[22][29].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaDesniPut()-Mapa.getMinBrzina());
						vozilo = new Kamion(22, 29,brzina);
						vozilo.setSmijer(Smijer.GORE);
						Mapa.getMapa()[22][29].getElementi().add(vozilo);
						Mapa.getVozilaDesniPut().add(vozilo);
						vozilo.start();
					}
				} else {
					if(Mapa.getMapa()[29][20].getElementi().get(Mapa.getMapa()[29][20].getElementi().size()-1) instanceof Put) {
						int brzina = Mapa.getMinBrzina()+random.nextInt(Mapa.getMaxBrzinaDesniPut()-Mapa.getMinBrzina());
						vozilo = new Kamion(29, 20,brzina);
						vozilo.setSmijer(Smijer.LIJEVO);
						Mapa.getMapa()[29][20].getElementi().add(vozilo);
						Mapa.getVozilaDesniPut().add(vozilo);
						vozilo.start();
					}
				}
			}
		}
	}

	private Color vratiBojuElementa(int i, int j) {
		Color color = null;
		int velicina = Mapa.getMapa()[i][j].getElementi().toArray().length;

		if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof Pruga) {
			color = Color.GRAY;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof Put) {
			color = Color.CORNFLOWERBLUE;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof ZeljeznickaStanica) {
			color = Color.GREEN;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof ZeljeznickaKompozicija) {
			color = Color.YELLOW;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof TeretnaLokomotiva) {
			color = Color.PURPLE;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof VagonTeretni) {
			color = Color.PINK;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof PutnickaLokomotiva) {
			color = Color.LIGHTBLUE;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof VagonPosebneNamjene) {
			color = Color.AQUA;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof UniverzalnaLokomotiva) {
			color = Color.BEIGE;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof VagonZaSpavanje) {
			color = Color.BROWN;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof VagonRestoran) {
			color = Color.GREENYELLOW;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof VagonSaLezajevima) {
			color = Color.SPRINGGREEN;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof VagonSaSjedistima) {
			color = Color.LIGHTYELLOW;
		}else if(Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof ManevarskaLokomotiva) {
			color = Color.CADETBLUE;
		}
		if (velicina == 2) {
			if (Mapa.getMapa()[i][j].getElementi().get(0) instanceof Pruga
					&& Mapa.getMapa()[i][j].getElementi().get(1) instanceof Put) {
				color = Color.BLACK;
			}
		}
		if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof Automobil) {
			color = Color.DARKRED;
		} else if (Mapa.getMapa()[i][j].getElementi().toArray()[velicina - 1] instanceof Kamion) {
			color = Color.RED;
		}
		return color;
	}

	public void postaviMapu() {
		
		sirinaIVisinaPolja = 20;
		int sirinaIVisinaMape = sirinaIVisinaPolja * Mapa.getDimenzija();
		gridMapa.setPrefWidth(sirinaIVisinaMape);
		gridMapa.setMaxWidth(sirinaIVisinaMape);
		gridMapa.setMinWidth(sirinaIVisinaMape);
		gridMapa.setPrefHeight(sirinaIVisinaMape);
		gridMapa.setMinHeight(sirinaIVisinaMape);
		gridMapa.setMaxHeight(sirinaIVisinaMape);

		for (int i = 0; i < Mapa.getDimenzija(); i++) {
			for (int j = 0; j < Mapa.getDimenzija(); j++) {
				FlowPane field = new FlowPane();
				field.setPrefHeight(sirinaIVisinaPolja);
				field.setMaxHeight(sirinaIVisinaPolja);
				field.setMinHeight(sirinaIVisinaPolja);
				field.setPrefWidth(sirinaIVisinaPolja);
				field.setMaxWidth(sirinaIVisinaPolja);
				field.setMinWidth(sirinaIVisinaPolja);
				Color color = Color.rgb(255, 255, 255);
				BackgroundFill fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
				field.setBackground(new Background(fill));
				gridMapa.add(field, i, j);
			}
		}
		prikaziTrenutnoStanjeMape(sirinaIVisinaMape);
	}

	public void prikaziTrenutnoStanjeMape(int sirinaIVisinaPolja) {
		for (int i = 0; i < Mapa.getDimenzija(); i++) {
			for (int j = 0; j < Mapa.getDimenzija(); j++) {
				if (Mapa.getMapa()[i][j] != null) {
					Color color = null;
					color = vratiBojuElementa(i, j);
					FlowPane field = new FlowPane();
					BackgroundFill fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
					field.setBackground(new Background(fill));
					gridMapa.add(field, i, j);

				}
			}
		}

	}
	@FXML
    void otvoriKretanja(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../View/Kretanja.fxml"));
			Scene scene = new Scene(root);			
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setTitle("Kretanja");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			FileLogger.log(Level.SEVERE, null, e);
		}
    }
}
