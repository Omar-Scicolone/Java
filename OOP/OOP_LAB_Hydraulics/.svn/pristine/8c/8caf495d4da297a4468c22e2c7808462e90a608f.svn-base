package hydraulic;
import java.util.ArrayList;

public class HSystem {

	ArrayList<Element> elementi = new ArrayList<Element>();
	
	
	public void addElement(Element elem){
		elementi.add(elem);
	}
	

	public Element[] getElements(){
		Element[] vett = elementi.toArray(new Element[elementi.size()]);
		return vett;
	}
	
	
	public String layout(){
		// TODO: to be implemented
		return null;
	}
	
	public void simulate(SimulationObserver observer){
		
		Element[] vett = elementi.toArray(new Element[elementi.size()]);
		
		vett[0].simulazione(observer, SimulationObserver.NO_FLOW);
		
	}

}
