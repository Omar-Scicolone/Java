package mountainhuts;

public class Municipality {

	private String nome;
	private String provincia;
	private int altitudine;
	
	
	public Municipality(String nome, String provincia, int altitudine) {
		this.nome = nome;
		this.provincia = provincia;
		this.altitudine = altitudine;
	}

	public String getName() {
		return this.nome;
	}

	public String getProvince() {
		return this.provincia;
	}

	public Integer getAltitude() {
		return this.altitudine;
	}

}
