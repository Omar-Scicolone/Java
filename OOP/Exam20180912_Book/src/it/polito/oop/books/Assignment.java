package it.polito.oop.books;

import java.util.List;


public class Assignment {
	private String iD;
	private ExerciseChapter chapter;
	
    public Assignment(String iD, ExerciseChapter chapter) {
    this.iD = iD;
    this.chapter = chapter;
    }

	public String getID() {
        return iD;
    }

    public ExerciseChapter getChapter() {
        return chapter;
    }

    public double addResponse(Question q,List<String> answers) {
    	
    	
        return -1.0;
    }
    
    public double totalScore() {
        return -1.0;
    }

}
