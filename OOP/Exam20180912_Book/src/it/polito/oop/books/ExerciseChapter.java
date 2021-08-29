package it.polito.oop.books;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ExerciseChapter {
	
	private String title;
	private int numPages;
	private List<Question> questions = new ArrayList<>(); 

	
	
    public ExerciseChapter(String title, int numPages) {
		this.title = title;
		this.numPages = numPages;
	}


	public List<Topic> getTopics() {
		List <Topic> topics = new ArrayList<>();
		for (Question q : questions) {
			topics.add(q.getMainTopic());
		}
		
        return topics.stream().distinct().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
	};
	

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
    	title = newTitle;
    }

    public int getNumPages() {
        return numPages;
    }
    
    public void setNumPages(int newPages) {
    	numPages = newPages;
    }
    

	public void addQuestion(Question question) {
		questions.add(question);
		
		
	}	
}
