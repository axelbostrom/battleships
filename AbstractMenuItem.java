package battleships;

import java.io.IOException;

public abstract class AbstractMenuItem implements MenuItem {    
	
	private String title;    
	
	public AbstractMenuItem(String title) {       
		this.title = title;    
		}    
	
	public abstract void execute() throws IOException;  
	
	public String getTitle() {        
		return title;    
		}
	}