package transactions;

import java.util.ArrayList;
import java.util.List;

public class Carrier {
	private String name;
	private List<String> regions;
	private List<Integer> score = new ArrayList<Integer>();
	
	
	public Carrier(String name, List<String> regions) {
		this.name = name;
		this.regions = regions;
	}

	public String getName() {
		return name;
	}
	
	public List<String> getRegion(){
		return regions;
	}

	public void addScore(int score) {
		this.score.add(score);
	}

	public List<Integer> getScore() {
		// TODO Auto-generated method stub
		return score;
	}
	
	
}
