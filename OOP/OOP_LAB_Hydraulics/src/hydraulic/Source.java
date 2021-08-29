package hydraulic;

public class Source extends Element {

	double flow;
	
	public Source(String name) {
		super(name);
	}

	public void setFlow(double flow) {
		this.flow = flow;
	}
	
	

	@Override
	public void simulazione(SimulationObserver observer, double noFlow) {
		
		observer.notifyFlow("Source", getName(), noFlow, flow);
		
		getOutput().simulazione(observer, flow);
	};
}
