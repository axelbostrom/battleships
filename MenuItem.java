package battleships;

import java.io.IOException;

public interface MenuItem {
	/**    * Returnerar menyvalets rubrik.    */    
	
	public String getTitle();
	
	/**    * Exekverar/väljer menyvalet.    
	 * @throws IOException */    
	
	public void execute() throws IOException;
}
