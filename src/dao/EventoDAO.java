package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Evento;

public class EventoDAO implements IDAO<Evento> {
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;

	public EventoDAO() {
		conn = new ConnectionFactory().getConexao();
	}

	@Override
	public ArrayList<Evento> listarTodos() {
		String sql = "SELECT * FROM eventos";
		ArrayList<Evento> lista = new ArrayList<Evento>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setId(rs.getInt("id"));
				evento.setNome(rs.getString("nome"));
				evento.setLotacao(rs.getInt("lotacao"));
				lista.add(evento);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel buscar os dados no banco! ");
			throw new RuntimeException(e.getMessage());
		}
		return lista;
	}

	@Override
	public Evento listarTodosId(int id) {
		String sql = "SELECT * FROM eventos WHERE id = " + id;
		Evento evento = new Evento();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				evento.setId(rs.getInt("id"));
				evento.setNome(rs.getString("nome"));
				evento.setLotacao(rs.getInt("lotacao"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel buscar os dados no banco! ");
			throw new RuntimeException(e.getMessage());
		}
		return evento;
	}

	public Evento listarNome(String nome) {
		String sql = "SELECT * FROM eventos WHERE nome LIKE '%" + nome + "%'";
		Evento evento = new Evento();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				evento.setId(rs.getInt("id"));
				evento.setNome(rs.getString("nome"));
				evento.setLotacao(rs.getInt("lotacao"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel buscar os dados no banco! ");
			throw new RuntimeException(e.getMessage());
		}
		return evento;
	}

	@Override
	public ArrayList<Evento> listarTodosNome(String nome) {
		String sql = "SELECT * FROM eventos WHERE nome LIKE '%" + nome + "%'";
		ArrayList<Evento> lista = new ArrayList<Evento>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setId(rs.getInt("id"));
				evento.setNome(rs.getString("nome"));
				evento.setLotacao(rs.getInt("lotacao"));
				lista.add(evento);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel buscar os dados no banco! ");
			throw new RuntimeException(e.getMessage());
		}
		return lista;
	}

	@Override
	public void inserir(Evento evento) {
		String sql = "INSERT INTO eventos (nome, lotacao) VALUES (?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, evento.getNome());
			stmt.setInt(2, evento.getLotacao());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel inserir os dados no banco!");
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
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
			JOptionPane.showMessageDialog(null, "N�o foi possivel alterar os dados no banco!");
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void excluir(int id) {
		String sql = "DELETE FROM eventos WHERE id = " + id;
		try {
			new CandidatoHasEventoDAO().excluirTodos();
			st = conn.createStatement();
			st.execute(sql);
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel excluir os dados no banco!");
			throw new RuntimeException(e.getMessage());
		}
	}
}
