package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Cafe;

public class CafeDAO implements IDAO<Cafe> {
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;

	public CafeDAO() {
		conn = new ConnectionFactory().getConexao();
	}

	@Override
	public ArrayList<Cafe> listarTodos() {
		String sql = "SELECT * FROM cafe";
		ArrayList<Cafe> lista = new ArrayList<Cafe>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Cafe cafe = new Cafe();
				cafe.setId(rs.getInt("id"));
				cafe.setNome(rs.getString("nome"));
				cafe.setLotacao(rs.getInt("lotacao"));
				lista.add(cafe);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel buscar os dados no banco! " + e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return lista;
	}

	@Override
	public Cafe listarTodosId(int id) {
		String sql = "SELECT * FROM cafe WHERE id = " + id;
		Cafe cafe = new Cafe();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				cafe.setId(rs.getInt("id"));
				cafe.setNome(rs.getString("nome"));
				cafe.setLotacao(rs.getInt("lotacao"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel buscar os dados no banco! ");
			throw new RuntimeException(e.getMessage());
		}
		return cafe;
	}

	public Cafe listarNome(String nome) {
		String sql = "SELECT * FROM cafe WHERE nome LIKE '%" + nome + "%'";
		Cafe cafe = new Cafe();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				cafe.setId(rs.getInt("id"));
				cafe.setNome(rs.getString("nome"));
				cafe.setLotacao(rs.getInt("lotacao"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel buscar os dados no banco! ");
			throw new RuntimeException(e.getMessage());
		}
		return cafe;
	}

	@Override
	public ArrayList<Cafe> listarTodosNome(String nome) {
		String sql = "SELECT * FROM cafe WHERE nome LIKE '%" + nome + "%'";
		ArrayList<Cafe> lista = new ArrayList<Cafe>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Cafe cafe = new Cafe();
				cafe.setId(rs.getInt("id"));
				cafe.setNome(rs.getString("nome"));
				cafe.setLotacao(rs.getInt("lotacao"));
				lista.add(cafe);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel buscar os dados no banco! ");
			throw new RuntimeException(e.getMessage());
		}
		return lista;
	}

	@Override
	public void inserir(Cafe cafe) {
		String sql = "INSERT INTO cafe (nome, lotacao) VALUES (?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cafe.getNome());
			stmt.setInt(2, cafe.getLotacao());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel inserir os dados no banco! ");
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void alterar(Cafe cafe) {
		String sql = "UPDATE cafe SET nome = ?, lotacao = ? WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cafe.getNome());
			stmt.setInt(2, cafe.getLotacao());
			stmt.setInt(3, cafe.getId());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel alterar os dados no banco!");
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void excluir(int id) {
		String sql = "DELETE FROM cafe WHERE id = " + id;
		try {
			new CandidatoHasCafeDAO().excluirTodos();
			st = conn.createStatement();
			st.execute(sql);
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel excluir os dados no banco!");
			throw new RuntimeException(e.getMessage());
		}
	}
}
