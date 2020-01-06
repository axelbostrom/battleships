package battleships;

import java.io.IOException;

public interface MenuItem {
<<<<<<< HEAD
	/** * Returnerar menyvalets rubrik. */

	public String getTitle();

	/**
	 * * Exekverar/väljer menyvalet.
	 * 
	 * @throws IOException
	 */

=======
	/**    * Returnerar menyvalets rubrik.    */    
	
	public String getTitle();
	
	/**    * Exekverar/väljer menyvalet.    
	 * @throws IOException */    
	
>>>>>>> 244e20815337b4e4e8e066bdadad3d193b5c2c6f
	public void execute() throws IOException;
}
