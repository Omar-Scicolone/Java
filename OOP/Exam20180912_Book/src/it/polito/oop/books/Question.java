package it.polito.oop.books;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Question {
	private String question;
	private Topic mainTopic;
	private Map<String, Boolean> answer = new HashMap<String, Boolean>();
	
	public Question(String question, Topic mainTopic) {
		this.question = question;
		this.mainTopic = mainTopic;
	}

	public String getQuestion() {
		return question;
	}
	
	public Topic getMainTopic() {
		return mainTopic;
	}

	public void addAnswer(String answer, boolean correct) {
		this.answer.put(answer, correct);
	}
	
    @Override
    public String toString() {
    	
        return this.getQuestion() + "(" + this.getMainTopic().getKeyword() + ")";
    }

	public long numAnswers() {
	    return answer.size();
	}

	public Set<String> getCorrectAnswers() {
		Set<String> quest = answer.keySet();
		Set<String> truee = new HashSet<>();
		
		for (String s : quest) {
			if (answer.get(s)==true) {
				truee.add(s);
			}
		}
		
		return truee;
	}

	public Set<String> getIncorrectAnswers() {
		Set<String> quest = answer.keySet();
		Set<String> falsee = new HashSet<>();
		
		for (String s : quest) {
			if (answer.get(s)==false) {
				falsee.add(s);
			}
		}
		
		return falsee;
	}
}
