package MODEL;

public class PokemonMODEL {
	
	private int id;
	private String pokemon;
	private String tipo;
	
	public PokemonMODEL() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getpokemon() {
		return pokemon;
	}

	public void setpokemon(String pokemon) {
		this.pokemon = pokemon;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isDuplicado() {
		// TODO Auto-generated method stub
		return false;
	}

}
