package net.etfbl.pj2.application;

import java.util.logging.Level;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.etfbl.pj2.model.Mapa;
import net.etfbl.pj2.utility.FileLogger;

public class Simulacija extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../View/Glavna.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Glavna");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					Mapa.setKraj(true);
					System.exit(0);
				}
			});
		} catch (Exception e) {
			FileLogger.log(Level.SEVERE, "logging", e);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

}
