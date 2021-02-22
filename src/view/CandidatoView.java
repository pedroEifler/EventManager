package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import table.CandidatoTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import dao.CafeDAO;
import dao.CandidatoDAO;
import dao.EventoDAO;
import model.Cafe;
import model.Candidato;
import model.Evento;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CandidatoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfSobrenome;
	private JTextField tfPesquisar;
	private JTable table;
	private JTextField tfId;
	private JComboBox cbEvento;
	private JComboBox cbCafe;
	private CandidatoDAO dao = new CandidatoDAO();
	private Candidato candidato = new Candidato();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CandidatoView frame = new CandidatoView();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CandidatoView() {
		setTitle("Candidatos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*---ComboBox---*/
		cbEvento = new JComboBox();
		ArrayList<Evento> eventos = new EventoDAO().listarTodos();
		String[] cbMensagem = new String[eventos.size()];
		for (int i = 0; i < eventos.size(); i++) {
			cbMensagem[i] = eventos.get(i).getNome();
		}
		cbEvento.setModel(new DefaultComboBoxModel(cbMensagem));
		cbEvento.setBounds(114, 86, 560, 20);
		contentPane.add(cbEvento);

		/*---ComboBox---*/
		cbCafe = new JComboBox();
		ArrayList<Cafe> cafes = new CafeDAO().listarTodos();
		cbMensagem = new String[cafes.size()];
		for (int i = 0; i < cafes.size(); i++) {
			cbMensagem[i] = cafes.get(i).getNome();
		}
		cbCafe.setModel(new DefaultComboBoxModel(cbMensagem));
		cbCafe.setBounds(114, 111, 560, 20);
		contentPane.add(cbCafe);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 664, 213);
		contentPane.add(scrollPane);

		/*---Table---*/
		table = new JTable();
		scrollPane.setViewportView(table);
		/* onclick */
		table.addMouseListener(new MouseAdapter() {
			@Override
			/* pega dados de uma linha na tabela */
			public void mouseClicked(MouseEvent arg0) {
				tfId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				tfNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				tfSobrenome.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				cbEvento.setSelectedItem(table.getValueAt(table.getSelectedRow(), 3).toString());
				cbCafe.setSelectedItem(table.getValueAt(table.getSelectedRow(), 4).toString());
			}
		});
		AtualizarTable();

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(10, 39, 94, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sobrenome:");
		lblNewLabel_1.setBounds(10, 64, 94, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Evento:");
		lblNewLabel_2.setBounds(10, 89, 94, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Espa\u00E7o de Caf\u00E9:");
		lblNewLabel_3.setBounds(10, 114, 94, 14);
		contentPane.add(lblNewLabel_3);

		tfNome = new JTextField();
		tfNome.setBounds(114, 36, 560, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfSobrenome = new JTextField();
		tfSobrenome.setBounds(114, 61, 560, 20);
		contentPane.add(tfSobrenome);
		tfSobrenome.setColumns(10);

		/*---Salvar---*/
		JButton btSalvar = new JButton("Salvar");
		btSalvar.setBounds(585, 159, 89, 23);
		contentPane.add(btSalvar);
		/* onclick */
		btSalvar.addActionListener(new ActionListener() {
			/* insere ou altera */
			public void actionPerformed(ActionEvent arg0) {

				if (tfId.getText().equals("")) {
					inserirCandidato();

				} else {
					alterarCandidato();
				}
				AtualizarTable();
				limparTextField();
			}
		});

		/*---Excluir---*/
		JButton btExcluir = new JButton("Excluir");
		btExcluir.setBounds(486, 159, 89, 23);
		contentPane.add(btExcluir);
		/* onclick */
		btExcluir.addActionListener(new ActionListener() {
			/* exclui */
			public void actionPerformed(ActionEvent e) {
				if (tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Clique na tabela e escolha o dado que deseja excluir!");
				} else {
					dao.excluir(Integer.parseInt(tfId.getText()));
					limparTextField();
					AtualizarTable();
				}
			}
		});

		/*---Limpar---*/
		JButton btLimpar = new JButton("Limpar");
		btLimpar.setBounds(387, 159, 89, 23);
		contentPane.add(btLimpar);
		/* onclick */
		btLimpar.addActionListener(new ActionListener() {
			/* limpa */
			public void actionPerformed(ActionEvent arg0) {
				limparTextField();
			}
		});

		/*---Voltar---*/
		JButton btVoltar = new JButton("Voltar");
		btVoltar.setBounds(10, 159, 89, 23);
		contentPane.add(btVoltar);
		/* onclick */
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView main = new MainView();
				main.setLocationRelativeTo(null);
				main.setVisible(true);
				setVisible(false);
			}
		});

		JLabel lblNewLabel_4 = new JLabel("Pesquisar:");
		lblNewLabel_4.setBounds(10, 417, 94, 14);
		contentPane.add(lblNewLabel_4);

		tfPesquisar = new JTextField();
		tfPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String pesquisa = tfPesquisar.getText();
				AtualizarTable(pesquisa);
			}
		});
		tfPesquisar.setBounds(114, 414, 560, 20);
		contentPane.add(tfPesquisar);
		tfPesquisar.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Codigo:");
		lblNewLabel_5.setBounds(10, 11, 94, 14);
		contentPane.add(lblNewLabel_5);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(114, 8, 560, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);

		setVisible(false);
	}

	private void limparTextField() {

		tfId.setText("");
		tfNome.setText("");
		tfSobrenome.setText("");
		tfPesquisar.setText("");
	}

	private void inserirCandidato() {
		try {
			candidato.setNome(tfNome.getText());
			candidato.setSobrenome(tfSobrenome.getText());
			candidato.setEventos(new EventoDAO().listarNome(cbEvento.getSelectedItem().toString()));
			candidato.setCafe(new CafeDAO().listarNome(cbCafe.getSelectedItem().toString()));

			if (tfNome.getText().equals("") || tfSobrenome.getText().equals("")) {
				throw new RuntimeException();
			} else {
				dao.inserir(candidato);
				JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
			}

		} catch (Exception e) {
			if (candidato.getNome() == null || candidato.getSobrenome() == null || candidato.getId() == 0) {
				JOptionPane.showMessageDialog(null, "Você deve preencher todos os dados!");
			}
		}
	}

	private void alterarCandidato() {
		try {
			candidato.setId(Integer.parseInt(tfId.getText()));
			candidato.setNome(tfNome.getText());
			candidato.setSobrenome(tfSobrenome.getText());
			candidato.setEventos(new EventoDAO().listarNome(cbEvento.getSelectedItem().toString()));
			candidato.setCafe(new CafeDAO().listarNome(cbCafe.getSelectedItem().toString()));

			if (tfId.getText().equals("") || tfNome.getText().equals("") || tfSobrenome.getText().equals("")) {
				throw new RuntimeException();
			} else {
				dao.alterar(candidato);
				JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
			}

		} catch (

		Exception e) {
			if (candidato.getNome() == null || candidato.getSobrenome() == null || candidato.getId() == 0) {
				JOptionPane.showMessageDialog(null, "Você deve preencher todos os dados!");
			}
		}
	}

	private void AtualizarTable() {

		AtualizarTable(null);
	}

	private void AtualizarTable(String nome) {
		try {
			CandidatoTableModel ctm = new CandidatoTableModel(nome);
			table.setModel(new DefaultTableModel(ctm.data, ctm.columnNames));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco!");
		}
	}
}
