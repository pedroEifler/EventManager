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
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco! ");
			throw new RuntimeException(e.getMessage());
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
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco! ");
			throw new RuntimeException(e.getMessage());
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
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco! ");
			throw new RuntimeException(e.getMessage());
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

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel inserir os dados no banco! ");
			throw new RuntimeException(e.getMessage());
		}
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
			JOptionPane.showMessageDialog(null, "Não foi possivel alterar os dados no banco! " + e.getMessage());
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
			JOptionPane.showMessageDialog(null, "Não foi possivel excluir os dados no banco! ");
			throw new RuntimeException(e.getMessage());
		}
	}

	public ArrayList<Object[]> listarCandidatosPorIdCafe(int id) {
		String sql = "SELECT cand.*, cc.etapas FROM cafe c left join candidatos_has_cafe cc on cc.Cafe_id = c.id left join candidatos cand on cand.id = cc.Candidatos_id WHERE c.id = "
				+ id;
		ArrayList<Object[]> lista = new ArrayList<Object[]>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Object[] result = new Object[4];
				result[0] = rs.getInt("cand.id");
				result[1] = rs.getString("cand.nome");
				result[2] = rs.getString("cand.sobrenome");
				result[3] = rs.getString("cc.etapas");
				lista.add(result);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco!");
			throw new RuntimeException(e.getMessage());
		}
		return lista;
	}

	public ArrayList<Object[]> listarCandidatosPorIdEvento(int id) {
		String sql = "SELECT cand.*, ce.etapas FROM eventos e left join candidatos_has_eventos ce on ce.Eventos_id = e.id left join candidatos cand on cand.id = ce.Candidatos_id WHERE e.id = "
				+ id;
		ArrayList<Object[]> lista = new ArrayList<Object[]>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Object[] result = new Object[4];
				result[0] = rs.getInt("cand.id");
				result[1] = rs.getString("cand.nome");
				result[2] = rs.getString("cand.sobrenome");
				result[3] = rs.getString("ce.etapas");
				lista.add(result);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco!");
			throw new RuntimeException(e.getMessage());
		}
		return lista;
	}

}
