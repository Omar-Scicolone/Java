package university;

public class Course {
	private String title;
	private String teacher;
	private int idCourse;
	private Student[] iscritti = new Student [University.MAX_STUDENT_PER_COURSE];
	
	public Course (String title, String teacher, int idCourse) {
		this.title = title;
		this.teacher = teacher;
		this.idCourse = idCourse;
	}

	public String getInfoCourse(){
		return idCourse + ", " + title + ", " + teacher;
	}
	
	public void aggiungiIscritto(Student studente) {
		for(int i=0;i<iscritti.length;i++) {
			if(iscritti[i]==null) {
				iscritti[i] = studente;
				break;
			}
		}
	}
	
	public StringBuffer stampaIscritti() {
			
		StringBuffer s = new StringBuffer();
		
		for(int i=0; i<iscritti.length; i++) {
			if (iscritti[i] != null) {
				s.append(iscritti[i].getInfo() + "\n");
			}
			
		}
		return s;
	}
	
	
}
