package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Candidato;
import model.CandidatoHasEvento;
import model.Evento;

public class CandidatoHasEventoDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;

	public CandidatoHasEventoDAO() {
		conn = new ConnectionFactory().getConexao();
	}

	public ArrayList<Object[]> listarEventoPorIdCandidato(int idCandidato) {
		String sql = "SELECT e.*, ce.etapas FROM candidatos_has_eventos ce left join eventos e on e.id = ce.Eventos_id left join candidatos c on c.id = ce.Candidatos_id where c.id = "
				+ idCandidato;
		ArrayList<Object[]> lista = new ArrayList<Object[]>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Object[] d = new Object[4];
				d[0] = (rs.getString("e.id"));
				d[1] = (rs.getString("e.nome"));
				d[2] = (rs.getString("e.lotacao"));
				d[3] = (rs.getString("ce.etapas"));
				lista.add(d);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco!");
		}
		return lista;
	}

	public void inserir(CandidatoHasEvento C_E) {
		String sql = "INSERT INTO candidatos_has_eventos (Candidatos_id, Eventos_id, etapas) VALUES (?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, C_E.getIdCandidato());
			stmt.setInt(2, C_E.getIdEvento());
			stmt.setString(3, C_E.getEtapa());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel inserir os dados no banco!");
		}
	}

	public void excluirTodos() {
		String sql = "DELETE FROM candidatos_has_eventos";
		try {
			st = conn.createStatement();
			st.execute(sql);
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel excluir os dados no banco!");
		}
	}

	public void AlocarCandidatosNasSalas() {
		CandidatoHasEventoDAO C_EDao = new CandidatoHasEventoDAO();
		CandidatoHasEvento C_E = new CandidatoHasEvento();
		EventoDAO eventoDao = new EventoDAO();
		CandidatoDAO candidatoDao = new CandidatoDAO();

		C_EDao.excluirTodos();

		adicionarPrimeiraEtapa(C_EDao, C_E, eventoDao, candidatoDao);
		adicionarSegundaEtapa(C_EDao, C_E, eventoDao, candidatoDao);
	}

	private void adicionarPrimeiraEtapa(CandidatoHasEventoDAO c_EDao, CandidatoHasEvento c_E, EventoDAO eventoDao,
			CandidatoDAO candidatoDao) {

		ArrayList<Candidato> candidatos = candidatoDao.listarTodos();
		ArrayList<Evento> eventos = eventoDao.listarTodos();

		int numeroDeEspacoEvento = eventoDao.listarTodos().size();
		int numeroDeCandidatos = candidatoDao.listarTodos().size();

		for (int j = 0; j < numeroDeCandidatos;) {
			for (int i = 0; i < numeroDeEspacoEvento; i++) {
				if (j == numeroDeCandidatos) {
					break;
				}

				Evento evento = eventos.get(i);
				Candidato candidato = candidatos.get(j);

				c_E.setIdEvento(evento.getId());
				c_E.setIdCandidato(candidato.getId());
				c_E.setEtapa(1);

				c_EDao.inserir(c_E);

				j++;
			}
		}
	}

	private void adicionarSegundaEtapa(CandidatoHasEventoDAO c_EDao, CandidatoHasEvento c_E, EventoDAO eventoDao,
			CandidatoDAO candidatoDao) {

		ArrayList<Candidato> candidatos = candidatoDao.listarTodos();
		ArrayList<Evento> eventos = eventoDao.listarTodos();

		int numeroDeEspacoEvento = eventoDao.listarTodos().size();
		int numeroDeCandidatos = candidatoDao.listarTodos().size();

		for (int j = 0; j < numeroDeCandidatos;) {
			for (int i = numeroDeEspacoEvento - 1; i >= 0; i--) {
				if (j == numeroDeCandidatos) {
					break;
				}

				Evento evento = eventos.get(i);
				Candidato candidato = candidatos.get(j);

				c_E.setIdEvento(evento.getId());
				c_E.setIdCandidato(candidato.getId());
				c_E.setEtapa(2);

				c_EDao.inserir(c_E);

				j++;
			}
		}

	}

}
