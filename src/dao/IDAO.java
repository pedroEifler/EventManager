package dao;

import java.util.ArrayList;

public interface IDAO<T> {

	public ArrayList<T> listarTodos();

	public T listarTodosId(int id);

	public ArrayList<T> listarTodosNome(String nome);

	public void inserir(T t);

	public void alterar(T t);

	public void excluir(int id);
}
