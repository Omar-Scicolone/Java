package ticketing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.sun.glass.ui.EventLoop.State;

public class IssueManager {
	int id = 0;
	List<User> users = new ArrayList<>();
	List<Component> components = new ArrayList<>();
	List<Ticket> tickets = new ArrayList<>();

	public static enum UserClass {
		Reporter, Maintainer
	}

	public void createUser(String username, UserClass... classes) throws TicketException {
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				throw new TicketException();
			}
		}

		if (classes.length == 0) {
			throw new TicketException();
		}

		User us = new User(username, classes);

		users.add(us);

	}

	public void createUser(String username, Set<UserClass> classes) throws TicketException {
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				throw new TicketException();
			}
		}

		if (classes.size() == 0) {
			throw new TicketException();
		}

		User us = new User(username, classes);

		users.add(us);

	}

	public Set<UserClass> getUserClasses(String username) {

		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return u.getUserClass();
			}
		}

		return null;
	}

	public void defineComponent(String name) throws TicketException {

		for (Component c : components) {
			if (c.getName().equals(name)) {
				throw new TicketException();
			}
		}

		Component c = new Component(name);
		components.add(c);
	}

	public void defineSubComponent(String name, String parentPath) throws TicketException {

		String[] s = parentPath.split("/");
		int size = s.length - 1;
		String sp = s[size];
		int flag = 0;
		
		for (Component cc : components) {
			if (cc.getName().equals(sp)) {
				flag = 1;
			}
		}
		if (flag == 0) {
			throw new TicketException();
		}


		
		
		for (Component c : components) {
			if (c.getName().equals(sp)) {
				List<Component> sc = c.getSubComponent();
	
				for (Component sub : sc) {
					if (sub.getName().equals(name)) {
						throw new TicketException();
					}
				}

			}
		}

		Component c = new Component(name);
		components.add(c);
		
		for (Component cc : components) {
			if (cc.getName().equals(sp)) {
				cc.addSubComponent(c);
			}
		}
		
		
		
		
	}

	public Set<String> getSubComponents(String path) {
		String[] s = path.split("/");
		int size = s.length - 1;
		String elemento = s[size];
		List<Component> subC = new ArrayList<Component>();
		
		
		for (Component c : components) {
			if (c.getName().equals(elemento)) {
				subC = c.getSubComponent();
			}
		}
		
		Set<String> nameSub = new HashSet<String>();

		for (Component cc : subC) {
			nameSub.add(cc.getName());
		}
		
		return nameSub;
	}

	public String getParentComponent(String path) {
		StringBuilder sb = new StringBuilder();
		
		String[] s = path.split("/");
		int sz = s.length -2 ;
		
		if (sz != 0) {
			for (int i = 1; i <= sz; i++) {				 
			sb.append("/" + s[i]);
			}
			return sb.toString();
		}
		else
			return null;
	}


	public int openTicket(String username, String componentPath, String description, Ticket.Severity severity)
			throws TicketException {
		int flag = 0;
		
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				flag = 1;
				if (!u.getUserClass().contains(UserClass.Reporter)) {
					throw new TicketException();
				}
				
			}
		}
		if(flag==0) {
			throw new TicketException();
		}
		
		
		//---------
		String[] s = componentPath.split("/");
		int size = s.length - 1;
		String elemento = s[size];
		int flag1 = 0;
		for (Component c : components) {
			if (c.getName().equals(elemento)) {
				flag1 = 1;
			}
		}
		if(flag1==0) {
			throw new TicketException();
		}
		
		
		//------------
		
		
		id++;
		Ticket t = new Ticket(id, description, elemento, username, severity);
		tickets.add(t);
		
		return id;
	}


	public Ticket getTicket(int ticketId) {
		
		for(Ticket t : tickets) {
			if (t.getId() == ticketId) {
				return t;
			}
		}
		
		
		return null;
	}

	
	public List<Ticket> getAllTickets() {
		return tickets.stream().collect(Collectors.toList());
	}


	public void assingTicket(int ticketId, String username) throws TicketException {
		
		int flag = 0;
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				flag = 1;
				if (!u.getUserClass().contains(UserClass.Maintainer)) {
					throw new TicketException();
				}
			}
		}
		if(flag==0) {
			throw new TicketException();
		}
		//-------------------
		flag = 0;
		for (Ticket t : tickets) {			
			if (t.getId()==ticketId) {
				flag = 1;
				if (t.getState() == Ticket.State.Closed) {
					throw new TicketException();
				}
			}
		}
		if(flag==0) {
			throw new TicketException();
		}
		//-------------------
		
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				
				for (Ticket t : tickets) {
					if (t.getId() == ticketId) {
						t.setAssegnatario(u);
						t.setState(Ticket.State.Assigned);
					}
				}
				
			}
		}
		
		
		
		
		
	}

	public void closeTicket(int ticketId, String description) throws TicketException {
		
		for (Ticket t : tickets) {
			if (t.getId() == ticketId) {
				if (t.getState() != Ticket.State.Assigned) {
					throw new TicketException();
				}
			}
		}
		
		for (Ticket t : tickets) {
			if (t.getId() == ticketId) {
				t.setState(Ticket.State.Closed);
				t.setDescription(description);
			}
		}
		
		
		
	}

	
	public SortedMap<Ticket.Severity, Long> countBySeverityOfState(Ticket.State state) {
		if (state != null) {
			
			return tickets.stream().filter(t -> t.getState() == state).collect(Collectors.groupingBy(Ticket::getSeverity,
																									 TreeMap::new,
																									 Collectors.counting()));
			
		}
		else {
			
			return tickets.stream().collect(Collectors.groupingBy(Ticket::getSeverity,
					 TreeMap::new,
					 Collectors.counting()));	
		}
	}


	public List<String> topMaintainers() {
//		Map<User, Long> mappa =  tickets.
//								 stream().
//								 filter(t-> t.getState() == Ticket.State.Closed).
//								 collect(Collectors.groupingBy(Ticket::getAssegnatario,
//										 						       TreeMap::new,
//										 						       Collectors.counting()));
//	 		
		return null;
	}

}
