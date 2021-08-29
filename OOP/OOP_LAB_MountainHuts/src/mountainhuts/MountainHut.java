package mountainhuts;

import java.util.Optional;

public class MountainHut {
	
	private String nome;
	private String categoria;
	private int postiLetto;
	private Municipality comune;
	private int altitudine;
	

	public MountainHut(String nome, String categoria, int postiLetto, Municipality comune) {
		this.nome = nome;
		this.categoria = categoria;
		this.postiLetto = postiLetto;
		this.comune = comune;
	}
	
	public MountainHut(String nome, String categoria, int postiLetto, Municipality comune, int altitudine) {
		this.nome = nome;
		this.categoria = categoria;
		this.postiLetto = postiLetto;
		this.comune = comune;
		this.altitudine = altitudine;
	}

	public String getName() {
		String e = nome;
		
		return e;
	}

	public Optional<Integer> getAltitude() {
		Optional<Integer> i = Optional.ofNullable(altitudine);
		return i;
	}

	public String getCategory() {
		return this.categoria;
	}

	public Integer getBedsNumber() {
		return this.postiLetto;
	}

	public Municipality getMunicipality() {
		return this.comune;
	}

}
