package model;

public class CandidatoHasCafe {
	private int idCandidato;
	private int idCafe;
	private String etapa;

	private enum etapas {
		Etapa1, Etapa2
	}

	/*---Get---*/
	public int getIdCandidato() {
		return idCandidato;
	}

	public int getIdCafe() {
		return idCafe;
	}

	public String getEtapa() {
		return etapa;
	}

	/*---Set---*/
	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}

	public void setIdCafe(int idCafe) {
		this.idCafe = idCafe;
	}

	public void setEtapa(int etapa) {
		if (etapa == 1) {
			this.etapa = etapas.Etapa1.toString();

		}
		if (etapa == 2) {
			this.etapa = etapas.Etapa2.toString();

		}
	}

	public void setEtapa(String etapa) {
		if (etapa.equals("Etapa1")) {
			this.etapa = etapas.Etapa1.toString();

		}
		if (etapa.equals("Etapa2")) {
			this.etapa = etapas.Etapa2.toString();

		}
	}
}
