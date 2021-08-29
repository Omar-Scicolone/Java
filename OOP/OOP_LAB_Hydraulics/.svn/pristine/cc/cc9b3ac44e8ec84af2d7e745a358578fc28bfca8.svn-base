package hydraulic;

public class Tap extends Element {

	boolean stato;
	double flow;
	
	public Tap(String name) {
		super(name);
	}

	public void setOpen(boolean open) {
		stato = open;
	
	}

	@Override
	public void simulazione(SimulationObserver observer, double flow) {
		
		if (stato) {
			this.flow = flow;
		}
		else {
			this.flow = 0;
		}
		
		observer.notifyFlow("Tap", getName(), flow, this.flow);
		
		getOutput().simulazione(observer, this.flow);
		
	}

}
