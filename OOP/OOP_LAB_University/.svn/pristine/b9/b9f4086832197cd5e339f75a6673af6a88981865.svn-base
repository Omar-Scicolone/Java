package university;

public class Student {
	private String first;
	private String last;
	private int matr;
	private Course [] iscrizioni= new Course[University.MAX_COURSES_PER_STUDENT]; ;
	
	
	public Student(String first, String last, int matr) {
		this.first = first;
		this.last = last;
		this.matr = matr;
		
	}
	
	public String getInfo(){
		return matr + " " + first + " " + last;
	}

	public void aggiungiIscrizione(Course corso) {
		for(int i=0;i<iscrizioni.length;i++) {
			if(iscrizioni[i]==null) {
				iscrizioni[i] = corso;
				break;
			}
		}
	}
	
	
	public StringBuffer stampaIscrizioni() {
			
		StringBuffer s = new StringBuffer();
		
		for(int i=0; i<iscrizioni.length; i++) {
			if (iscrizioni[i] != null) {
				s.append(iscrizioni[i].getInfoCourse() + "\n");
			}
			
		}
		return s;
	}
	
	
}
