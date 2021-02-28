package table;

import java.util.ArrayList;
import dao.EventoDAO;
import model.Evento;

public class EventoTableModel {

	public Object[][] data;
	public Object[] columnNames = { "Codigo", "Nome", "Lotação" };

	public EventoTableModel(String nome) {
		ArrayList<Evento> evento;

		if (nome == null) {
			evento = new EventoDAO().listarTodos();
		} else {
			evento = new EventoDAO().listarTodosNome(nome);
		}
		data = new Object[evento.size()][columnNames.length];

		for (int i = 0; i < evento.size(); i++) {
			for (int j = 0; j < columnNames.length; j++) {

				switch (j) {
				case 0:
					data[i][j] = evento.get(i).getId();
					break;
				case 1:
					data[i][j] = evento.get(i).getNome();
					break;
				case 2:
					data[i][j] = evento.get(i).getLotacao();
					break;

				default:
					break;
				}
			}
		}
	}
}
