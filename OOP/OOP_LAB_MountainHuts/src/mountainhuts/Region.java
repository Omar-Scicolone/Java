package mountainhuts;

import static java.util.stream.Collectors.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Region {

	private String nome;
	Map<String, ArrayList<Integer>> range = new TreeMap<String, ArrayList<Integer>>();
	
	private List<Municipality> comuni = new ArrayList<Municipality>();
	
	private List<MountainHut> rifugi = new ArrayList<MountainHut>();

	public Region(String name) {
		nome = name;
	}

	public String getName() {
		return nome;
	}

	public void setAltitudeRanges(String... ranges) {

		for (String e : ranges) {
			String[] tmp = e.split("-");
			int int1 = Integer.valueOf(tmp[0]);
			int int2 = Integer.valueOf(tmp[1]);

			ArrayList<Integer> tmp4 = new ArrayList<>();
			tmp4.add(int1);
			tmp4.add(int2);

			range.put(e, tmp4);

		}
	}

	public String getAltitudeRange(Integer altitude) {

		Set<String> keys = range.keySet();

		for (String e : keys) {
			ArrayList<Integer> values = range.get(e);

			if (altitude >= values.get(0) && altitude <= values.get(1)) {
				return e;
			}
		}
		return "0-INF";
	}


	public Collection<Municipality> getMunicipalities() {
		return comuni;
	}

	public Collection<MountainHut> getMountainHuts() {
		return rifugi;
	}

	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {

		for (Municipality e : comuni) {
			if (name.equals(e.getName())) {
				return e;
			}
		}

		Municipality m = new Municipality(name, province, altitude);
		comuni.add(m);
		return m;
	}

	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber, Municipality municipality) {
		for (MountainHut e : rifugi) {
			if (name.equals(e.getName())) {
				return e;
			}
		}

		MountainHut m = new MountainHut(name, category, bedsNumber, municipality);
		rifugi.add(m);
		return m;
	}
	
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber, Municipality municipality) {
		for (MountainHut e : rifugi) {
			if (name.equals(e.getName())) {
				return e;
			}
		}

		MountainHut m = new MountainHut(name, category, bedsNumber, municipality, altitude);
		rifugi.add(m);
		return m;
	}

	/**
	 * Creates a new region and loads its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
	
		Region r = new Region(name);
		r.readData(file);
		
		return r;
	}

	@SuppressWarnings("unused")
	private void readData(String file) {
		
		List<String> lines = null;

		try (BufferedReader in = new BufferedReader(new FileReader(file))) {	
			lines = in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		if (lines == null)
			return;

		// Continue the implementation here
		
		lines.remove(0);
		
		for(String e : lines) {
			
			String[] tmp = e.split(";");
			Municipality m = this.createOrGetMunicipality(tmp[1], tmp[0], Integer.parseInt(tmp[2]));
			
			
			//Optional<String> o = Optional.ofNullable(tmp[4]);
			
			if(tmp[4] == null) {
				this.createOrGetMountainHut(tmp[3], Integer.parseInt(tmp[4]), tmp[5], Integer.parseInt(tmp[6]), m);
			}
			else {
				this.createOrGetMountainHut(tmp[3], tmp[5], Integer.parseInt(tmp[6]), m);
			}
		}
		
	}
	
	public Map<String, Long> countMunicipalitiesPerProvince() {
		return comuni.stream().map(Municipality::getProvince).collect(groupingBy(x -> x, counting()));
	}

	
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {	
		return rifugi.stream().map(MountainHut::getMunicipality).collect(groupingBy(Municipality::getProvince , 
																	     groupingBy(Municipality::getName, counting())));
		
		
		
		
	}

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() {
		
	return null;
	}

	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
		return null;
	}

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		
		
		return rifugi.stream().collect(groupingBy( x -> {
			Integer alt = null;
			if (!x.getAltitude().isPresent()) {
				alt = x.getMunicipality().getAltitude();
			}
			else {
				alt = x.getAltitude().get();
			}
			return getAltitudeRange(alt);
		},
				mapping (MountainHut::getBedsNumber, maxBy((a, b) -> a - b))
				));	
	}

	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
		return null;
	}

}
