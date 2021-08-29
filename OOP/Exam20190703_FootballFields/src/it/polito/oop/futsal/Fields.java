package it.polito.oop.futsal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Represents a infrastructure with a set of playgrounds, it allows teams to
 * book, use, and leave fields.
 *
 */
public class Fields {

	private List<Field> fields = new ArrayList<>();
	private List<Associate> associates = new ArrayList<>();

	private int id = 1;
	private int idA = 0;

	private String OpeningTime = new String();
	private String ClosingTime = new String();


	public static class Features {
		public final boolean indoor; // otherwise outdoor
		public final boolean heating;
		public final boolean ac;

		public Features(boolean i, boolean h, boolean a) {
			this.indoor = i;
			this.heating = h;
			this.ac = a;
		}
	}

	public void defineFields(Features... features) throws FutsalException {

		for (Features f : features) {

			Field c = new Field();

			c.setIndoor(f.indoor);

			if (f.indoor == false) {
				if (f.heating == true || f.ac == true) {
					throw new FutsalException();
				}
			} else {
				c.setAc(f.ac);
				c.setHeating(f.heating);
			}

			c.setId(id);
			id++;
			fields.add(c);

		}

	}

	public long countFields() {
		return fields.size();
	}

	public long countIndoor() {

		List<Field> indoors = new ArrayList<>();

		for (Field f : fields) {
			if (f.isIndoor()) {
				indoors.add(f);
			}
		}

		return indoors.size();
	}

	public String getOpeningTime() {
		return OpeningTime;
	}

	
	public void setOpeningTime(String time) {
		this.OpeningTime = time;
	}

	public String getClosingTime() {
		return ClosingTime;
	}

	public void setClosingTime(String time) {
			this.ClosingTime = time;
	}

	
	
	public int newAssociate(String first, String last, String mobile) {
		idA++;
		Associate a = new Associate(first, last, mobile, idA);
		associates.add(a);
		
		return idA;
	}

	
	public String getFirst(int partyId) throws FutsalException {
		
		List <Integer> Idassociati = new ArrayList<>(); 
		for (Associate a : associates) {
			Idassociati.add(a.getId());
		}
		if (!Idassociati.contains(partyId)) {
			throw new FutsalException();
		}
		
		for (Associate a : associates) {
			if (a.getId() == partyId) {
				return a.getFirst();
			}
		}
		
		return null;
	}

	
	public String getLast(int associate) throws FutsalException {
		
		List <Integer> Idassociati = new ArrayList<>(); 
		for (Associate a : associates) {
			Idassociati.add(a.getId());
		}
		if (!Idassociati.contains(associate)) {
			throw new FutsalException();
		}
		
		for (Associate a : associates) {
			if (a.getId() == associate) {
				return a.getLast();
			}
		}
	
		return null;
	}

	
	public String getPhone(int associate) throws FutsalException {
		
		List <Integer> Idassociati = new ArrayList<>(); 
		for (Associate a : associates) {
			Idassociati.add(a.getId());
		}
		if (!Idassociati.contains(associate)) {
			throw new FutsalException();
		}
		
		for (Associate a : associates) {
			if (a.getId() == associate) {
				return a.getPhone();
			}
		}
		
		return null;
	}

	
	public int countAssociates() {
		return associates.size();
	}

	public void bookField(int field, int associate, String time) throws FutsalException {
		
		List <Integer> Idassociati = new ArrayList<>(); 
		for (Associate a : associates) {
			Idassociati.add(a.getId());
		}
		if (!Idassociati.contains(associate)) {
			throw new FutsalException();
		}
		
		List <Integer> IdCampi = new ArrayList<>(); 
		for (Field f : fields) {
			IdCampi.add(f.getId());
		}
		if (!IdCampi.contains(field)) {
			throw new FutsalException();
		}
		
		String[] orarioPren = time.split(":");
		String[] orarioOpen = OpeningTime.split(":");
//		String[] orarioClose = ClosingTime.split(":");
		
				
//		if (Integer.parseInt(orarioPren[0]) < Integer.parseInt(orarioOpen[0])) {
//			throw new FutsalException();
//		}
//		if (Integer.parseInt(orarioPren[0]) >= Integer.parseInt(orarioClose[0])) {
//			throw new FutsalException();
//		}
//		
		if (Integer.parseInt(orarioPren[1]) != Integer.parseInt(orarioOpen[1])) {
			throw new FutsalException();
		}
		
		for (Field f : fields) {
			if (f.getId() == field) {
				f.setPrenotation(time);
			}
		}
		
		for (Associate a : associates) {
			if (a.getId() == associate) {
				a.setPrenotation(time);
			}
		}
		
	}

	public boolean isBooked(int field, String time) {
		for (Field f : fields) {
			if (f.getId() == field) {
				
				if (f.getPrenotation().contains(time)) {
					return true;
				}
				
			}
		}
		
		
		return false;
	}

	public int getOccupation(int field) {
		int occ = 0;
		
		for (Field f : fields) {
			if (f.getId() == field) {
				occ = f.getPrenotation().size();
			}
		}
		
		return occ;
	}

	public List<FieldOption> findOptions(String time, Features required) {
		List<FieldOption> alt = new ArrayList<>();
		
		
		
		for (Field f : fields) {
			
			if (!f.getPrenotation().contains(time)) { 
				boolean check = true;
				if (required.indoor == true && f.getIndoor() == false && check == true) check = false;
				if (required.ac == true && f.getAc() == false && check ==  true) check = false;
				if (required.heating == true && f.getHeating() == false && check == true ) check = false;
				if (check == true) alt.add(f);
			}
		}
		
		
		alt = alt.stream().distinct().sorted(Comparator.comparing(FieldOption :: getOccupation).thenComparing(FieldOption :: getField))
				.collect(Collectors.toList());
				
		return alt;
	}

	public long countServedAssociates() {
		long count = 0;
		
		for (Associate a : associates) {
			if (a.getPrenotation() != 0) {
				count++;
			}
		}
		return count;
	}

	public Map<Integer, Long> fieldTurnover() {
		return fields.stream().collect(Collectors.toMap(Field::getId, t -> (long)(t.getOccupation())));
	}

	public double occupation() {
		String[] orarioOpen = OpeningTime.split(":");
		String[] orarioClose = ClosingTime.split(":");
		
		double blocchi = Integer.parseInt(orarioClose[0]) - Integer.parseInt(orarioOpen[0]);
		
		
		double prenCampi = 0;
		
		for (Field f : fields) {
			prenCampi = prenCampi + f.getOccupation(); 
		}
		
		return prenCampi/(blocchi*fields.size());
	}

}
