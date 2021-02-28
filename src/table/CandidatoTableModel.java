package table;

import java.util.ArrayList;
import dao.CandidatoDAO;
import model.Candidato;

public class CandidatoTableModel {

	public Object[][] data;
	public Object[] columnNames = { "Codigo", "Nome", "Sobrenome" };
	public Object[] columnNamesEtapas = { "Codigo", "Nome", "Sobrenome", "Etapas" };

	public CandidatoTableModel(String nome) {
		ArrayList<Candidato> candidato;

		if (nome == null) {
			candidato = new CandidatoDAO().listarTodos();

		} else {
			candidato = new CandidatoDAO().listarTodosNome(nome);

		}
		data = new Object[candidato.size()][columnNames.length];

		for (int i = 0; i < candidato.size(); i++) {
			for (int j = 0; j < columnNames.length; j++) {

				switch (j) {
				case 0:
					data[i][j] = candidato.get(i).getId();
					break;
				case 1:
					data[i][j] = candidato.get(i).getNome();
					break;
				case 2:
					data[i][j] = candidato.get(i).getSobrenome();
					break;

				default:
					break;
				}
			}
		}
	}

	public CandidatoTableModel(int idCafe, int idEvento) {
		ArrayList<Object[]> lista;

		if (idCafe != 0) {
			lista = new CandidatoDAO().listarCandidatosPorIdCafe(idCafe);

		} else {
			lista = new CandidatoDAO().listarCandidatosPorIdEvento(idEvento);
		}
		data = new Object[lista.size()][columnNamesEtapas.length];

		for (int i = 0; i < lista.size(); i++) {
			for (int j = 0; j < columnNamesEtapas.length; j++) {
				data[i][j] = lista.get(i)[j];
			}
		}

	}
}
