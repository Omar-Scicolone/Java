package transactions;

import java.util.ArrayList;
import java.util.List;

public class Region {
	private String name;
	private List<String> placeNames;
	private List<String> nameCarrier = new ArrayList<String>();
	
	
	public Region(String name, List<String> placeNames) {
		this.name = name;
		this.placeNames = placeNames;
	}

	public String getName() {
		return name;
	}
	
	public void addToNameCarrier(String c) {
		nameCarrier.add(c);
	}

	public List<String> getNameCarrier() {
		return nameCarrier;
	}
	
	public List<String> placeNames() {
		return placeNames;
	}
}
