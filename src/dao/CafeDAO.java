package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cafe;

public class CafeDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;
	private ArrayList<Cafe> lista = new ArrayList<Cafe>();

	public CafeDAO() {
		conn = new ConnectionFactory().getConexao();
	}

	public ArrayList<Cafe> listarTodos() {
		String sql = "SELECT * FROM cafe";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Cafe cafe = new Cafe();
				cafe.setNome(rs.getString("nome"));
				cafe.setLotacao(rs.getInt("lotacao"));
				lista.add(cafe);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
		return lista;
	}

	public Cafe listarTodosId(int id) {
		String sql = "SELECT * FROM cafe WHERE id = " + id;
		Cafe cafe = new Cafe();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				cafe.setNome(rs.getString("nome"));
				cafe.setLotacao(rs.getInt("lotacao"));
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
		return cafe;
	}

	public Cafe listarTodosNome(String nome) {
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
			throw new RuntimeException("Erro: " + e);
		}
		return cafe;
	}

	public void inserir(Cafe cafe) {
		String sql = "INSERT INTO cafe (nome, lotacao) VALUES (?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cafe.getNome());
			stmt.setInt(2, cafe.getLotacao());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
	}

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
			throw new RuntimeException("Erro: " + e);
		}
	}

	public void excluir(int id) {
		String sql = "DELETE FROM cafe WHERE id = " + id;
		try {
			st = conn.createStatement();
			st.execute(sql);
			st.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
	}
}
