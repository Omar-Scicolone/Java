package mountainhuts;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		Region r = new Region("Sicilia");

		r.setAltitudeRanges("1001-2000", "0-1000", "2001-3000");
		System.out.println(r.range);
		System.out.println('\n');

		System.out.println(r.getAltitudeRange(3001));

		Municipality m2 = r.createOrGetMunicipality("Gela", "Caltanissetta", 20);
		Municipality m1 = r.createOrGetMunicipality("Leonforte", "Enna", 700);
		Municipality m3 = r.createOrGetMunicipality("Librino", "Catania", 1500);
		Municipality m4 = r.createOrGetMunicipality("Librino", "Catania", 1500);

		r.getMunicipalities().stream().map(s -> s.getName()).sorted(Comparator.comparing(s -> s.length()))
				.forEach(System.out::println);

		MountainHut r1 = r.createOrGetMountainHut("Rifugio1", 500, "Per famiglia", 5, m1);
		MountainHut r2 = r.createOrGetMountainHut("Rifugio2", "Coppie", 2, m1);
		MountainHut r3 = r.createOrGetMountainHut("Rifugio3", "finocchi", 50, m2);
		MountainHut r4 = r.createOrGetMountainHut("Rifugio4", "single", 1, m3);

		System.out.println('\n');
		
		
		System.out.println("Rifugi della Sicilia");
		//Stream.of(r.getMountainHuts()).map(MountainHut::getName).sorted().forEach(System.out::println);
		r.getMountainHuts().stream().map(MountainHut::getName).sorted().forEach(System.out::println);
		
		System.out.println('\n');
				
		System.out.println("Rifugi di Leonforte");
		r.getMountainHuts().stream().filter(s1 -> s1.getMunicipality().getName()
				.equals("Leonforte")).forEach(s -> System.out.println(s.getMunicipality().getName() + " " + s.getName() + " " + s.getAltitude().get()));
		
		
	}

}
