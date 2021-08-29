package university;

public class University {
	
	//Attributi
	private String name;
	private String first;
	private String last;
	public static final int NUM_MAX_STUDENT = 1000;
	public static final int NUM_MAX_COURSE = 50;
	public static final int INITIAL_MATR = 10000;
	public static final int INITIAL_COURSE = 10;
	public static final int MAX_COURSES_PER_STUDENT = 25;
	public static final int MAX_STUDENT_PER_COURSE = 100; 
	int nextMatr = INITIAL_MATR;
	int nextCourse = INITIAL_COURSE;
	private Student[] studenti = new Student [NUM_MAX_STUDENT];
	private Course[] insegnamenti = new Course [NUM_MAX_COURSE];
	
	//metodi
	public University(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	
	public void setRector(String first, String last){
		this.first = first;
		this.last = last;
	}
	public String getRector(){
		return first + " " + last;
	}
	

	public int enroll(String first, String last){
		
		Student s = new Student (first, last, nextMatr);
		studenti [nextMatr - INITIAL_MATR ] = s;
		return nextMatr++;
		
	}
	public String student(int id){
		return studenti[id - INITIAL_MATR].getInfo();
	}
	
	

	public int activate(String title, String teacher){
		Course c = new Course (title, teacher, nextCourse);
		insegnamenti [nextCourse - INITIAL_COURSE] = c;
		return nextCourse++;
	}
	
	public String course(int code){
		
	return insegnamenti[code - INITIAL_COURSE].getInfoCourse();
	}


	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		
		int index = studentID - 10000;
		Student s = studenti[index];
		int indice = courseCode - 10;
		Course c = insegnamenti[indice];
		
		// 1. aggiunge s agli iscritti del corso c
		c.aggiungiIscritto(s);
		
		//2. aggiunge c alle iscrizioni dello studente s
		s.aggiungiIscrizione(c);

	}
	
	
	public String listAttendees(int courseCode){
		
		int indice = courseCode - 10;
		Course c = insegnamenti[indice];
		
		StringBuffer s1 = c.stampaIscritti();
		
		String s = s1.toString();
		
		return s;
	}

	/**
	 * Retrieves the study plan for a student
	 * 
	 * @param studentID id of the student
	 * @return list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		
		int indice = studentID - 10000;
		Student stu = studenti[indice];
		
		StringBuffer s1 = stu.stampaIscrizioni();
		
		String s = s1.toString();
		
		return s;
	
		
	}
}
