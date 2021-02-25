package table;

import java.util.ArrayList;
import dao.CandidatoHasEventoDAO;

public class EtapaEventoTableModel {

	public Object[][] data;
	public Object[] columnNames = { "Codigo", "Nome", "Lotação", "Etapa" };

	public EtapaEventoTableModel() {
	}

	public EtapaEventoTableModel(String id) {

		CandidatoHasEventoDAO C_EDao = new CandidatoHasEventoDAO();
		ArrayList<Object[]> lista = C_EDao.listarEventoPorIdCandidato(Integer.parseInt(id));

		data = new Object[lista.size()][columnNames.length];

		for (int i = 0; i < lista.size(); i++) {
			for (int j = 0; j < columnNames.length; j++) {
				data[i][j] = lista.get(i)[j];
			}
		}
	}
}
