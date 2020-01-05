package battleships;

public abstract class AbstractGameItem implements GameItem {    
	
	private String name;    
	
	public AbstractGameItem(String name) {       
		this.name = name;    
		}    
	
	public String getName() {        
		return name;    
		}
	}