package battleships;

import java.io.IOException;

<<<<<<< HEAD
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
=======
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
>>>>>>> 244e20815337b4e4e8e066bdadad3d193b5c2c6f
