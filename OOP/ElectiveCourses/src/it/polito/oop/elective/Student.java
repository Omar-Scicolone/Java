package it.polito.oop.elective;

import java.util.List;

public class Student {
	private String id;
	private double gradeAverage;
	private String[] preferences = new String [3];
	private int choice;
	
	
	public Student(String id, double gradeAverage) {
		this.id = id;
		this.gradeAverage = gradeAverage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getGradeAverage() {
		return gradeAverage;
	}
	public void setGradeAverage(double gradeAverage) {
		this.gradeAverage = gradeAverage;
	}
	
	
	public String[] getPreferences() {
		return preferences;
	}
	
	public void setPreferences(List<String> courses) {
		int i = 0;
		
		for (String s : courses) {
			preferences[i] = s;
			i++;
		}
	}
	public int getChoice() {
		return choice;
	}
	
	public void setChoice(int so) {
		this.choice = so;
	}
	
	
	
}
