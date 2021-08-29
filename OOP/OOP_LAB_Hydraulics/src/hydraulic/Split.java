package hydraulic;

public class Split extends Element {

	Element successivo2;

	public Split(String name) {
		super(name);

	}

	public Element[] getOutputs() {
		Element[] vettore = new Element[2];
		vettore[0] = this.successivo;
		vettore[1] = this.successivo2;
		return vettore;
	}

	public void connect(Element elem, int noutput) {
		if (noutput == 0) {
			this.successivo = elem;
		} else {
			this.successivo2 = elem;
		}
	}

	@Override
	public void simulazione(SimulationObserver observer, double flow) {
		
		
		observer.notifyFlow("Split", getName(), flow, flow/2);
		
		getOutputs()[0].simulazione(observer, flow/2);
		getOutputs()[1].simulazione(observer, flow/2);

	}

}