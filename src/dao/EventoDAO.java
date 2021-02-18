package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Evento;

public class EventoDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;
	private ArrayList<Evento> lista = new ArrayList<Evento>();

	public EventoDAO() {
		conn = new ConnectionFactory().getConexao();
	}

	public ArrayList<Evento> listarTodos() {
		String sql = "SELECT * FROM eventos";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setNome(rs.getString("nome"));
				evento.setLotacao(rs.getInt("lotacao"));
				lista.add(evento);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
		return lista;
	}

	public ArrayList<Evento> listarTodosId(int id) {
		String sql = "SELECT * FROM eventos WHERE id = " + id;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setNome(rs.getString("nome"));
				evento.setLotacao(rs.getInt("lotacao"));
				lista.add(evento);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
		return lista;
	}

	public void inserir(Evento evento) {
		String sql = "INSERT INTO eventos (nome, lotacao) VALUES (?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, evento.getNome());
			stmt.setInt(2, evento.getLotacao());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
	}

	public void alterar(Evento evento) {
		String sql = "UPDATE eventos SET nome = ?, lotacao = ? WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, evento.getNome());
			stmt.setInt(2, evento.getLotacao());
			stmt.setInt(3, evento.getId());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
	}

	public void excluir(int id) {
		String sql = "DELETE FROM eventos WHERE id = " + id;
		try {
			st = conn.createStatement();
			st.execute(sql);
			st.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
	}
}
