package table;

import java.util.ArrayList;

import dao.CandidatoDAO;
import model.Candidato;

public class CandidatoTableModel {

	public Object[][] data;
	public Object[] columnNames = { "Codigo", "Nome", "Sobrenome" };

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
}
