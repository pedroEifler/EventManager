package main;

import dao.CandidatoHasCafeDAO;
import dao.CandidatoHasEventoDAO;
import view.MainView;

public class Run {

	public static void main(String[] args) {
		/*MainView main = new MainView();
		main.setLocationRelativeTo(null);
		main.setVisible(true);*/
		CandidatoHasEventoDAO dao = new CandidatoHasEventoDAO();
		dao.AlocarCandidatosNasSalas();
		CandidatoHasCafeDAO cdao = new CandidatoHasCafeDAO();
		cdao.AlocarCandidatosNoCafe();
	}

}
