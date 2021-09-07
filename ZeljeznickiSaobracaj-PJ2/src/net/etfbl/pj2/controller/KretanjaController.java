package net.etfbl.pj2.controller;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import net.etfbl.pj2.model.Mapa;
import net.etfbl.pj2.model.ZeljeznickaKompozicija;

public class KretanjaController {

	@FXML
	private GridPane sadrzajGrid;

	@FXML
	private TextArea textKretanja;

	private static int brojKolona = 5;

	@FXML
	public void initialize() {
		prikaziSerijalizovanaKretanja();
	}

	@FXML
	void zatvoriKretanja(ActionEvent event) {
		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
	}

	private void prikaziSerijalizovanaKretanja() {
		sadrzajGrid.getColumnConstraints().clear();
		sadrzajGrid.getRowConstraints().clear();
		sadrzajGrid.getChildren().clear();

		for (int i = 0; i < brojKolona; i++) {
			ColumnConstraints temp = new ColumnConstraints();
			temp.setHalignment(HPos.CENTER);
			temp.setPercentWidth(100 / brojKolona);
			sadrzajGrid.getColumnConstraints().add(temp);
		}

		//File file = new File("src" + File.separator + "net" + File.separator + "etfbl" + File.separator + "pj2"
			//	+ File.separator + "kretanja");
		//System.out.println("putanja"+Mapa.getKretanjaPutanja());
		File file = new File(Mapa.getKretanjaPutanja());

		int brojSerijalizacija = file.listFiles().length;
		File[] serijalizacije = file.listFiles();

		int brojRedova = (brojSerijalizacija / brojKolona);
		if (brojSerijalizacija % brojKolona != 0)
			brojRedova++;
		sadrzajGrid.getRowConstraints().clear();
		for (int i = 0; i < brojRedova; i++) {
			RowConstraints temp = new RowConstraints();
			temp.setValignment(VPos.CENTER);
			temp.setPercentHeight(100 / brojRedova);
			sadrzajGrid.getRowConstraints().add(temp);
		}
		for (int i = 0; i < brojKolona; i++) {
			for (int j = 0; j < brojRedova; j++) {
				if (brojSerijalizacija > 0) {
					ZeljeznickaKompozicija zk = ZeljeznickaKompozicija
							.deserijalizujZeljeznickuKompoziciju(serijalizacije[brojSerijalizacija - 1].toString());
					Button button = new Button(zk.getNaziv());
					button.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							textKretanja.setText(zk.toString());

						}

					});
					button.setPrefHeight(50);
					button.setPrefWidth(100);
					sadrzajGrid.add(button, i, j);
					brojSerijalizacija--;
				}
			}
		}
	}

}