package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.omg.CORBA.portable.ValueOutputStream;

import model.Candidato;

public class CandidatoDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;
	private ArrayList<Candidato> lista = new ArrayList<Candidato>();

	public CandidatoDAO() {
		conn = new ConnectionFactory().getConexao();
	}

	public ArrayList<Candidato> listarTodos() {
		String sql = "SELECT * FROM candidatos";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Candidato candidato = new Candidato();
				candidato.setNome(rs.getString("nome"));
				candidato.setSobrenome(rs.getString("sobrenome"));
				candidato.setEventos(rs.getInt("eventos"));
				candidato.setCafe(rs.getInt("cafe"));
				lista.add(candidato);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
		return lista;
	}
	
	public ArrayList<Candidato> listarTodosNome(String nome) {
		String sql = "SELECT * FROM candidatos WHERE nome LIKE '%"+nome+"%'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Candidato candidato = new Candidato();
				candidato.setNome(rs.getString("nome"));
				candidato.setSobrenome(rs.getString("sobrenome"));
				candidato.setEventos(rs.getInt("eventos"));
				candidato.setCafe(rs.getInt("cafe"));
				lista.add(candidato);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
		return lista;
	}

	public void inserir(Candidato candidato) {
		String sql = "INSERT INTO cadidatos (nome, sobrenome, eventos, cafe) VALUES (?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, candidato.getNome());
			stmt.setString(2, candidato.getSobrenome());
			stmt.setInt(3, candidato.getEventos().getId());
			stmt.setInt(4, candidato.getCafe().getId());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
	}

	public void alterar(Candidato candidato) {
		String sql = "UPDATE candidatos SET nome = ?, sobrenome = ?, eventos = ?, cafe = ? WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, candidato.getNome());
			stmt.setString(2, candidato.getSobrenome());
			stmt.setInt(3, candidato.getEventos().getId());
			stmt.setInt(4, candidato.getCafe().getId());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
	}

	public void excluir(int id) {
		String sql = "DELETE FROM candidatos WHERE id = " + id;
		try {
			st = conn.createStatement();
			st.execute(sql);
			st.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
	}
}
