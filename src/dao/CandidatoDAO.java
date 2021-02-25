package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Candidato;

public class CandidatoDAO implements IDAO<Candidato> {
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;

	public CandidatoDAO() {
		conn = new ConnectionFactory().getConexao();
	}

	@Override
	public ArrayList<Candidato> listarTodos() {
		String sql = "SELECT * FROM candidatos";
		ArrayList<Candidato> lista = new ArrayList<Candidato>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Candidato candidato = new Candidato();
				candidato.setId(rs.getInt("id"));
				candidato.setNome(rs.getString("nome"));
				candidato.setSobrenome(rs.getString("sobrenome"));
				lista.add(candidato);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco!");
		}
		return lista;
	}

	@Override
	public Candidato listarTodosId(int id) {
		String sql = "SELECT * FROM candidatos WHERE id = " + id;
		Candidato candidato = new Candidato();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				candidato.setId(rs.getInt("id"));
				candidato.setNome(rs.getString("nome"));
				candidato.setSobrenome(rs.getString("sobrenome"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco!");
		}
		return candidato;
	}

	@Override
	public ArrayList<Candidato> listarTodosNome(String nome) {
		String sql = "SELECT * FROM candidatos WHERE nome LIKE '%" + nome + "%'";
		ArrayList<Candidato> lista = new ArrayList<Candidato>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Candidato candidato = new Candidato();
				candidato.setId(rs.getInt("id"));
				candidato.setNome(rs.getString("nome"));
				candidato.setSobrenome(rs.getString("sobrenome"));
				lista.add(candidato);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco!");
		}
		return lista;
	}

	@Override
	public void inserir(Candidato candidato) {
		String sql = "INSERT INTO candidatos (nome, sobrenome) VALUES (?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, candidato.getNome());
			stmt.setString(2, candidato.getSobrenome());
			stmt.execute();
			stmt.close();

			adicionarEvento();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel inserir os dados no banco!");
		}
	}

	private void adicionarEvento() {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(Candidato candidato) {
		String sql = "UPDATE candidatos SET nome = ?, sobrenome = ? WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, candidato.getNome());
			stmt.setString(2, candidato.getSobrenome());
			stmt.setInt(3, candidato.getId());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel alterar os dados no banco!");
		}
	}

	@Override
	public void excluir(int id) {
		String sql = "DELETE FROM candidatos WHERE id = " + id;
		try {
			st = conn.createStatement();
			st.execute(sql);
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel excluir os dados no banco!");
		}
	}
}
