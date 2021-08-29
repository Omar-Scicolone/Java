package hydraulic;


public class Sink extends Element {

	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);
		
	}
	
@Override
public void connect(Element elem){}

@Override
public void simulazione(SimulationObserver observer, double flow) {
	
	observer.notifyFlow("Sink", getName(), flow, SimulationObserver.NO_FLOW);
}

}
