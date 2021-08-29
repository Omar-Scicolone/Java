package it.polito.oop.books;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Book {
	List<Topic> topics = new ArrayList<>();
	List<Question> questions = new ArrayList<>();
	List<TheoryChapter> theoryChapters = new ArrayList<>();
	List<ExerciseChapter> exerciseChapters = new ArrayList<>();

	public Topic getTopic(String keyword) throws BookException {

		if (keyword.isEmpty() || keyword == null) {
			throw new BookException();
		}

		for (Topic t : topics) {
			if (t.getKeyword().equals(keyword)) {
				return t;
			}
		}
		Topic t = new Topic(keyword);
		topics.add(t);
		return t;
	}

	public Question createQuestion(String question, Topic mainTopic) {
		Question q = new Question (question, mainTopic);
		questions.add(q);
		return q;
	}

	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
		TheoryChapter tc = new TheoryChapter(title, numPages, text);
		theoryChapters.add(tc);
		return tc;
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
		ExerciseChapter ec = new ExerciseChapter(title, numPages);
		exerciseChapters.add(ec);
		return ec;
		
	}

	public List<Topic> getAllTopics() {
		List<Topic> l = new ArrayList<Topic>();
		
		for (TheoryChapter t : theoryChapters) {
			l.addAll(t.getTopics());
		}
		
		for (ExerciseChapter e : exerciseChapters) {
			l.addAll(e.getTopics());
		}
		
		return l.stream().distinct().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
	}

	public boolean checkTopics() {
		List <Topic> list = new ArrayList<>();
		
		for (ExerciseChapter e : exerciseChapters) {
			list.addAll(e.getTopics());
		}
		
		
		for (Topic t : list) {
			for (TheoryChapter tc : theoryChapters) {
				List <Topic> listCh = tc.getTopics();
				if (!listCh.contains(t)) {
					return false;
				}
			}
			
		}
		
		return true;
	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
		Assignment a = new Assignment(ID, chapter);
		return a;
	}

	public Map<Long, List<Question>> questionOptions() {		
		
		return questions.stream().collect(Collectors.groupingBy(Question::numAnswers, Collectors.toList()));
										  
										  
//										         return questions.stream()
//								                .collect(Collectors.groupingBy(q -> q.numAnswers(), Collectors.toList()));								  
	}
}
