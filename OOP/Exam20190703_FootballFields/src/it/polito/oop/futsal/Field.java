package it.polito.oop.futsal;

import java.util.ArrayList;
import java.util.List;

public class Field implements FieldOption{
	private boolean indoor;
	private boolean heating;
	private boolean ac;
	private int id;
	private List<String> prenotation = new ArrayList<>();
	
	private int occ;
	
	
	public boolean isIndoor() {
		return indoor;
	}
	public void setIndoor(boolean indoor) {
		this.indoor = indoor;
	}
	public boolean isHeating() {
		return heating;
	}
	public void setHeating(boolean heating) {
		this.heating = heating;
	}
	public boolean isAc() {
		return ac;
	}
	public void setAc(boolean ac) {
		this.ac = ac;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public void setPrenotation(String time) {
		prenotation.add(time);
		occ++;
	}
	public List<String> getPrenotation() {
		return prenotation;
	}
	
	@Override
	public int getField() {
		
		return id;
	}
	
	@Override
	public int getOccupation() {
		return occ;
	}

	public boolean getIndoor() {
		return indoor;
	}
	
	public boolean getHeating() {
		return heating;
	}
	
	public boolean getAc() {
		return ac;
	}
	
	
	
	
	
}
