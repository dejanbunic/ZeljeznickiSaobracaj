package net.etfbl.pj2.utility;


import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;

import net.etfbl.pj2.model.Mapa;

import java.nio.file.StandardWatchEventKinds;

public class FileWatcher extends Thread{
	public String s;
	public FileWatcher(String putanja){
		this.s=putanja;
		
	}
	
	public void run(){
		try{
			
			WatchService service=FileSystems.getDefault().newWatchService();
			
			Path p=Paths.get(s);
			
			p.register(service,StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_CREATE);
			while(!Mapa.isKraj()){
				 sleep(1000);
				WatchKey key=null;
				try{
					key=service.take();
				}catch(Exception e){
					FileLogger.log(Level.SEVERE, null, e);
				}
				for(WatchEvent<?> ev: key.pollEvents()){
					WatchEvent.Kind<?> kind=ev.kind();
					@SuppressWarnings("unchecked")
					WatchEvent<Path> pp=(WatchEvent<Path>)ev;
					Path f=pp.context();

					/*if(f.toString().trim().endsWith(".txt") && kind.equals(StandardWatchEventKinds.ENTRY_CREATE)){
						System.out.println(f.toString()+" je kreiran");
					}*/
					if( kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)){
						if("config.properties".equals(f.toString())) {
							Mapa.ucitajConfiguracijuPuteva();
						}
					}
	
				}				
				boolean valid=key.reset();
				if(!valid){break;}
			}
		
		}catch(Exception e){
			FileLogger.log(Level.SEVERE, null, e);
		}
		
	}
} 
