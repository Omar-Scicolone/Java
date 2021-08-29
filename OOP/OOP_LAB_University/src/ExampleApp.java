import university.*;

public class ExampleApp {

	public static void main(String[] args) {
		
		String universityName = "Politecnico di Torino";
		
		University poli = new University(universityName);
		
		poli.setRector("Guido", "Saracco");
		
		System.out.println("Rector of " + poli.getName() + " : " + poli.getRector()); // Guido Saracco
		
		int s1 = poli.enroll("Mario","Rossi");
		int s2 = poli.enroll("Giuseppe","Verdi");
		int s3 = poli.enroll("Enrico","Gay");
		int s4 = poli.enroll("Claudio", "Pumbinellu");
		
		System.out.println("Enrolled students " + s1 + ", " + s2 + ", " +s3 + ", "+s4); // 10000, 10001
		System.out.println("s1 = " + poli.student(s1)); // 10000 Mario Rossi
		System.out.println("s2 = " + poli.student(s2));
		System.out.println("s4 = " + poli.student(s4));
		
		
		int macro = poli.activate("Macro Economics", "Paul Krugman");
		int oop = poli.activate("Object Oriented Programming", "James Gosling");
		int mcu = poli.activate("Marvel Cinematic Universe", "Tony Stark");
		
		System.out.println("Activated courses " + macro + " and " + oop + " and " + mcu); // 10 and 11
		
		System.out.println("oop = " + poli.course(oop));
		System.out.println("mcu = " + poli.course(mcu));
		System.out.println("macro = " + poli.course(macro));
		
		
		poli.register(s1, macro);
		poli.register(s2, macro);
		poli.register(s2, oop);
		
		System.out.println(poli.listAttendees(macro));
		// 10000 Mario Rossi
		// 10001 Giuseppe Verdi
		
		System.out.println(poli.studyPlan(s2));
		// 10,Macro Economics,Paul Krugman
		// 11,Object Oriented Programming,Marco Torchiano
	}
}
