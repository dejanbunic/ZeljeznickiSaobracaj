package net.etfbl.pj2.utility;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;

import net.etfbl.pj2.model.Mapa;

public class FileWatcherVozovi extends Thread{
	int i=1;
	public String putanja;
	public FileWatcherVozovi(String putanja){
		this.putanja=putanja;
		
	}
	
	public void run(){
		try{			
			WatchService service=FileSystems.getDefault().newWatchService();			
			Path p=Paths.get(putanja);	
			p.register(service,StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_CREATE);
			while(!Mapa.isKraj()){
				 sleep(1000);
				WatchKey key=null;
				try{
					key=service.take();
				}catch(Exception e){
					FileLogger.log(Level.FINE, null, e);
				}
				for(WatchEvent<?> ev: key.pollEvents()){
					WatchEvent.Kind<?> kind=ev.kind();
					//if(kind==OVERF)
					@SuppressWarnings("unchecked")
					WatchEvent<Path> pp=(WatchEvent<Path>)ev;
					Path f=pp.context();
					if(kind.equals(StandardWatchEventKinds.OVERFLOW))
						continue;
					if( kind.equals(StandardWatchEventKinds.ENTRY_CREATE)){
						//File file = new File(putanja+File.separator+f.toString());
						//if(file.)
						//if(file.canRead())
							//Mapa.ucitajJednuKompoziciju(file);
					}
					if( f.toString().trim().endsWith(".txt") && kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)){	
						File file = new File(putanja+File.separator+f.toString());
						if(file.canRead()) {
							Mapa.ucitajJednuKompoziciju(new File(putanja+File.separator+f.toString()));
							
						}
					}
				}	
				boolean valid=key.reset();
				if(!valid){break;}
			}
			
			
		}catch(Exception e){
			FileLogger.log(Level.WARNING, null, e);
		}
		
	}
	
}
