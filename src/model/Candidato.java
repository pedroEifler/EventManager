package model;

public class Candidato {
	private int id;
	private String nome;
	private String sobrenome;
	private Evento eventos;
	private Cafe cafe;

	/*---Get---*/
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public Evento getEventos() {
		return eventos;
	}

	public Cafe getCafe() {
		return cafe;
	}

	/*---Set---*/
	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setEventos(Evento eventos) {
		this.eventos = eventos;
	}

	public void setCafe(Cafe cafe) {
		this.cafe = cafe;
	}

}