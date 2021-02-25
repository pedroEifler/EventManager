package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import model.Cafe;
import model.Candidato;
import model.CandidatoHasCafe;
import model.Evento;

public class CandidatoHasCafeDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;

	public CandidatoHasCafeDAO() {
		conn = new ConnectionFactory().getConexao();
	}

	public ArrayList<Object[]> listarEventoPorIdCandidato(int idCandidato) {
		String sql = "SELECT ca.*, cc.etapas FROM candidatos_has_cafe cc LEFT JOIN cafe ca ON ca.id = cc.Cafe_id LEFT JOIN candidatos c ON c.id = cc.Candidatos_id WHERE c.id = "
				+ idCandidato;
		ArrayList<Object[]> lista = new ArrayList<Object[]>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Object[] d = new Object[4];
				d[0] = (rs.getString("ca.id"));
				d[1] = (rs.getString("ca.nome"));
				d[2] = (rs.getString("ca.lotacao"));
				d[3] = (rs.getString("cc.etapas"));
				lista.add(d);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco!");
		}
		return lista;
	}

	public void inserir(CandidatoHasCafe C_C) {
		String sql = "INSERT INTO candidatos_has_cafe (Candidatos_id, Cafe_id, etapas) VALUES (?, ?, ?);";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, C_C.getIdCandidato());
			stmt.setInt(2, C_C.getIdCafe());
			stmt.setString(3, C_C.getEtapa());
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel inserir os dados no banco! " + e);
		}
	}

	public void excluirTodos() {
		String sql = "DELETE FROM candidatos_has_cafe";
		try {
			st = conn.createStatement();
			st.execute(sql);
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel excluir os dados no banco!");
		}
	}

	public void AlocarCandidatosNoCafe() {
		CandidatoHasCafeDAO C_CDao = new CandidatoHasCafeDAO();
		CandidatoHasCafe C_C = new CandidatoHasCafe();
		CafeDAO cafeDao = new CafeDAO();
		CandidatoDAO candidatoDao = new CandidatoDAO();

		C_CDao.excluirTodos();

		adicionarPrimeiraEtapa(C_CDao, C_C, cafeDao, candidatoDao);
		adicionarSegundaEtapa(C_CDao, C_C, cafeDao, candidatoDao);
	}

	private void adicionarPrimeiraEtapa(CandidatoHasCafeDAO c_CDao, CandidatoHasCafe c_C, CafeDAO cafeDao,
			CandidatoDAO candidatoDao) {

		ArrayList<Cafe> cafe = cafeDao.listarTodos();
		ArrayList<Candidato> candidato = candidatoDao.listarTodos();
		int numeroDeEspacoCafe = cafe.size();
		int numeroDeCandidatos = candidato.size();

		for (int j = 0; j < numeroDeCandidatos;) {
			for (int i = 0; i < numeroDeEspacoCafe; i++) {
				if (j == numeroDeCandidatos) {
					break;
				}

				c_C.setIdCafe(cafe.get(i).getId());
				c_C.setIdCandidato(candidato.get(j).getId());
				c_C.setEtapa(1);

				System.out.println(i);
				System.out.println(j);
				c_CDao.inserir(c_C);

				j++;
			}
		}
	}

	private void adicionarSegundaEtapa(CandidatoHasCafeDAO c_CDao, CandidatoHasCafe c_C, CafeDAO cafeDao,
			CandidatoDAO candidatoDao) {

		ArrayList<Cafe> cafe = cafeDao.listarTodos();
		ArrayList<Candidato> candidato = candidatoDao.listarTodos();
		int numeroDeEspacoCafe = cafe.size();
		int numeroDeCandidatos = candidato.size();

		for (int j = 0; j < numeroDeCandidatos;) {  
			for (int i = numeroDeEspacoCafe - 1; i >= 0; i--) {
				if (j == numeroDeCandidatos) {
					break;
				}

				c_C.setIdCafe(cafe.get(i).getId());
				c_C.setIdCandidato(candidato.get(j).getId());
				c_C.setEtapa(2);

				c_CDao.inserir(c_C);

				j++;
			}
		}

	}
}
