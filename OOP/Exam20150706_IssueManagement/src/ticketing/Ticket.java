package ticketing;

public class Ticket {
	
	private int id;
	private String description;
	private String component;
	private String author;
	private Severity severity;
	private State state;
	
	private User assegnatario;
	
	public Ticket(int id, String description,  String component, String author, Severity severity) {
		this.id = id;
		this.description = description;
		this.component = component;
		this.author = author;
		this.severity = severity;
		
		this.state = State.Open;
	}

    
    /**
     * Enumeration of possible severity levels for the tickets.
     * 
     * Note: the natural order corresponds to the order of declaration
     */
    public enum Severity { Blocking, Critical, Major, Minor, Cosmetic };
    
    /**
     * Enumeration of the possible valid states for a ticket
     */
    public static enum State { Open, Assigned, Closed }
    
    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }
    
    public Severity getSeverity() {
        return severity;
    }

    public String getAuthor(){
        return author;
    }
    
    public String getComponent(){
        return component;
    }
    
    public State getState(){
        return state;
    }
    
    public User getAssegnatario(){
        return assegnatario;
    }
    
    
    public String getSolutionDescription() throws TicketException {
    	if (this.state != State.Closed) {
    		throw new TicketException();
    	}
    	
        return description;
    }

	public void setAssegnatario(User u) {
		assegnatario = u;
	}

	public void setState(State assigned) {
		this.state = assigned;
	}

	public void setDescription(String description) {
		this.description = description;
		
	}
}
