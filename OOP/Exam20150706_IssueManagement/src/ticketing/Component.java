package ticketing;
import java.util.ArrayList;
import java.util.List;

public class Component {
	private String name;
	private List<Component> subComponent = new ArrayList<Component>();
	
	
	public Component(String name) {
		this.name = name;
	
	}


	public String getName() {
		return name;
	}
	
	public List<Component> getSubComponent() {
		return subComponent;
	}
	
	public void addSubComponent(Component c) {
		subComponent.add(c);		
	}
	
}
