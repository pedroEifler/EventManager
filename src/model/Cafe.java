package model;

public class Cafe {
	private int id;
	private String nome;
	private int lotacao;

	/*---Get---*/
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getLotacao() {
		return lotacao;
	}

	/*---Set---*/
	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}
}
