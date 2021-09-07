package net.etfbl.pj2.utility;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.etfbl.pj2.model.Mapa;


public class FileLogger {

	private static final Logger logger = Logger.getLogger(Logger.class.getName());

	public static void log(Level level, String msg, Throwable thrown) {
		FileHandler fh = null;
		try {
			//fh = new FileHandler("src" + File.separator + "net" + File.separator + "etfbl" + File.separator + "pj2"
				//	+ File.separator + "log" + File.separator + "log.log", true);
			fh = new FileHandler(Mapa.getLoggerPutanja(), true);
			logger.addHandler(fh);
			logger.log(level, msg, thrown);
		} catch (IOException | SecurityException e) {
			logger.log(Level.SEVERE, "logging", e);
		}
	}
}
