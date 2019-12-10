package battleships;

public abstract class AbstractMenuItem implements MenuItem {    
	
	private String title;    
	
	public AbstractMenuItem(String title) {       
		this.title = title;    
		}    
	
	public abstract void execute();  
	
	public String getTitle() {        
		return title;    
		}
	}