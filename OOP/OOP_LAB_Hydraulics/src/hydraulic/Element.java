package hydraulic;

public abstract class Element {

	protected String name;
	Element precedente;
	Element successivo;

	

	public Element(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void connect(Element elem) {

		successivo = elem;
		elem.precedente = this;
	}

	public Element getOutput() {

		return successivo;

	}

	public abstract void simulazione(SimulationObserver observer, double flow);
	
}
