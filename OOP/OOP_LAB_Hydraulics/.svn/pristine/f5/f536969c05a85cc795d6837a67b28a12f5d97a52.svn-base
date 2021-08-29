package hydraulic;


public interface SimulationObserver {
	
	public final static double NO_FLOW = Double.NaN;
	
	void notifyFlow(String type, String name, double inFlow, double outFlow);
	
	/**
	 * method to check whether a flow is defined, (i.e. it is not a {@link #NO_FLOW}).
	 * 
	 * @param flow the flow to be tested
	 * 
	 * @return {@code true} if the flow is defined
	 */
	static boolean exists(double flow) {
		return ! Double.isNaN(flow);
	}
}
