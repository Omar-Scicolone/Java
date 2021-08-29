package it.polito.oop.elective;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private String name;
	private int availablePositions;
	private Long[] request = {(long) 0, (long) 0, (long) 0};
	private List<Student> studentz = new ArrayList<Student>();
	
	
	public Course(String name, int availablePositions) {
		this.name = name;
		this.availablePositions = availablePositions;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAvailablePositions() {
		return availablePositions;
	}


	public void setAvailablePositions(int availablePositions) {
		this.availablePositions = availablePositions;
	}
	
	public void setAvailablePositions() {
		this.availablePositions--;
	}

	public void setRequest (int position) {
		Long l = this.request[position]+1;
		this.request[position] =  l;
	}
	
	public Long[] getRequest() {
		return request;
	}


	public List<Student> getStudentz() {
		return studentz;
	}


	public void setStudentz(Student studentz) {
		this.studentz.add(studentz);
	}

	
}
