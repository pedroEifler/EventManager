package table;

import java.util.ArrayList;

import dao.CafeDAO;
import model.Cafe;

public class CafeTableModel {

	public Object[][] data;
	public Object[] columnNames = { "Codigo", "Nome", "Lotação" };

	public CafeTableModel(String nome) {
		ArrayList<Cafe> cafe;

		if (nome == null) {
			cafe = new CafeDAO().listarTodos();
		} else {
			cafe = new CafeDAO().listarTodosNome(nome);
		}
		data = new Object[cafe.size()][columnNames.length];

		for (int i = 0; i < cafe.size(); i++) {
			for (int j = 0; j < columnNames.length; j++) {

				switch (j) {
				case 0:
					data[i][j] = cafe.get(i).getId();
					break;
				case 1:
					data[i][j] = cafe.get(i).getNome();
					break;
				case 2:
					data[i][j] = cafe.get(i).getLotacao();
					break;

				default:
					break;
				}
			}
		}
	}
}
