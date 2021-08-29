package it.polito.oop.books;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Topic {
	private String keyword;

	private List<Topic> subTopic = new ArrayList<>();

	public Topic(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	@Override
	public String toString() {
		return keyword;
	}

	public boolean addSubTopic(Topic topic) {
		
	
		for (Topic sb : subTopic) {
			if (sb.getKeyword().equals(topic.getKeyword())) {
				return false;
			}
		}
		
		subTopic.add(topic);
		return true;
	}

	public List<Topic> getSubTopics() {
		
		return subTopic.stream().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
	}
}
