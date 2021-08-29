package it.polito.oop.elective;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ElectiveManager {

	private List<Course> courses = new ArrayList<>();
	private List<Student> students = new ArrayList<>();

	public void addCourse(String name, int availablePositions) {
		Course c = new Course(name, availablePositions);
		courses.add(c);
	}

	public SortedSet<String> getCourses() {
		Set<String> courses1 = new HashSet<>();
		SortedSet<String> courses3 = new TreeSet<>();

		for (Course c : courses) {
			courses1.add(c.getName());
		}

		courses1 = courses1.stream().sorted().collect(Collectors.toSet());

		for (String s : courses1) {
			courses3.add(s);
		}

		return courses3;
	}

	public void loadStudent(String id, double gradeAverage) {

		for (Student s : students) {
			if (s.getId().equals(id)) {
				s.setGradeAverage(gradeAverage);
				return;
			}
		}
		Student ss = new Student(id, gradeAverage);
		students.add(ss);
		return;
	}

	public Collection<String> getStudents() {
		return students.stream().map(Student::getId).collect(Collectors.toList());
	}

	public Collection<String> getStudents(double inf, double sup) {

		return students.stream().filter(s -> (s.getGradeAverage() >= inf && s.getGradeAverage() <= sup))
				.map(Student::getId).collect(Collectors.toList());
	}

	public int requestEnroll(String id, List<String> courses) throws ElectiveException {

		List<String> ids = students.stream().map(Student::getId).collect(Collectors.toList());
		if (!ids.contains(id)) {
			throw new ElectiveException();
		}

		if (courses.size() >= 3 || courses.size() <= 1) {
			throw new ElectiveException();
		}

		List<String> cou = this.courses.stream().map(Course::getName).collect(Collectors.toList());
		for (String s : courses) {
			if (!cou.contains(s)) {
				throw new ElectiveException();
			}
		}

		for (int i = 0; i < courses.size(); i++) {

			for (Course c : this.courses) {
				if (c.getName().equals(courses.get(i))) {
					c.setRequest(i);
				}

			}

		}

		for (Student s : students) {
			if (s.getId().equals(id)) {
				s.setPreferences(courses);
			}
		}

		return courses.size();
	}

	public Map<String, List<Long>> numberRequests() {

		Map<String, List<Long>> numberRequests = new TreeMap<>();

		for (Course c : courses) {
			List<Long> preferences = new ArrayList<Long>();
			preferences = Arrays.asList(c.getRequest());

			numberRequests.put(c.getName(), preferences);
		}

		return numberRequests;
	}

	public long makeClasses() {
		int studentisoddisfatti = 0;
		int flag = 0;
		
		List<Student> bestStudents = new ArrayList<>();
		bestStudents = students.stream().sorted(Comparator.comparing(Student::getGradeAverage).reversed())
				.collect(Collectors.toList());

		for (Student s : bestStudents) {
			int so = 1;
			
			List<String> prefereces = Arrays.asList(s.getPreferences());
			for (String ss : prefereces) {
				for (Course c : courses) {
					if (c.getName().equals(ss)) {
						if (c.getAvailablePositions() != 0) {
							c.setStudentz(s);

							c.setAvailablePositions();
							studentisoddisfatti++;
							s.setChoice(so);

							flag = 1;
						}
						so++;
					}
					if (flag == 1) {
						break;
					}
				}
				if (flag == 1) {
					break;
				}
			}
			flag = 0;
			so = 1;
		}

		int sudentiINsoddisfatti = students.size() - studentisoddisfatti;

		return sudentiINsoddisfatti;
	}

	public Map<String, List<String>> getAssignments() {
		Map<String, List<String>> maps = new TreeMap<>();

		for (Course c : courses) {
			List<Student> studenti = c.getStudentz();

			studenti = studenti.stream().sorted(Comparator.comparing(Student::getGradeAverage).reversed())
					.collect(Collectors.toList());

			List<String> studenti2 = new ArrayList<>();

			for (Student s : studenti) {
				studenti2.add(s.getId());
			}

			maps.put(c.getName(), studenti2);

		}

		return maps;
	}

	public void addNotifier(Notifier listener) {
	}

	/**
	 * Computes the success rate w.r.t. to first (second, third) choice.
	 * 
	 * @param choice
	 *            : the number of choice to consider.
	 * @return the success rate (number between 0.0 and 1.0)
	 */
	public double successRate(int choice) {
		int count = 0;

		for (Student s : students) {
			if (s.getChoice() == choice) {
				count++;
			}
		}
		return (double) count / (students.size());
	}

	/**
	 * Returns the students not assigned to any course.
	 * 
	 * @return the student id list.
	 */
	public List<String> getNotAssigned() {

		return students.stream().filter(x -> x.getChoice() != 1 && x.getChoice() != 2 && x.getChoice() != 3)
				.map(Student::getId).collect(Collectors.toList());
	}

}
