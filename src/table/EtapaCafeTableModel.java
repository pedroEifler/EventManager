package table;

import java.util.ArrayList;
import dao.CandidatoHasCafeDAO;

public class EtapaCafeTableModel {

	public Object[][] data;
	public Object[] columnNames = { "Codigo", "Nome", "Lotação", "Etapa" };

	public EtapaCafeTableModel() {
	}

	public EtapaCafeTableModel(String id) {

		CandidatoHasCafeDAO C_CDao = new CandidatoHasCafeDAO();
		ArrayList<Object[]> lista = C_CDao.listarEventoPorIdCandidato(Integer.parseInt(id));

		data = new Object[lista.size()][columnNames.length];

		for (int i = 0; i < lista.size(); i++) {
			for (int j = 0; j < columnNames.length; j++) {
				data[i][j] = lista.get(i)[j];
			}
		}
	}
}
