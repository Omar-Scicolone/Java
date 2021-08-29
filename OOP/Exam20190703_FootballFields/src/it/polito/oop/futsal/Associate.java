package it.polito.oop.futsal;

import java.util.ArrayList;
import java.util.List;

public class Associate {
	private String first;
	private String last;
	private String phone;
	private List<String> prenotation = new ArrayList<>();

	private int id;

	public Associate(String first, String last, String mobile, int idA) {
			this.first = first;
			this.last = last;
			this.phone = mobile;
			this.id = idA;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrenotation(String time) {
		prenotation.add(time);
	}

	public long getPrenotation() {
		return prenotation.size();
	}
	
	
}
