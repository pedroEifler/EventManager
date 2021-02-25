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
import dao.CandidatoDAO;
import dao.CandidatoHasCafeDAO;
import model.Candidato;
import model.CandidatoHasCafe;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 664, 213);
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
			}
		});
		AtualizarTable();

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(10, 36, 94, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sobrenome:");
		lblNewLabel_1.setBounds(10, 61, 94, 14);
		contentPane.add(lblNewLabel_1);

		tfNome = new JTextField();
		tfNome.setBounds(114, 33, 560, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfSobrenome = new JTextField();
		tfSobrenome.setBounds(114, 58, 560, 20);
		contentPane.add(tfSobrenome);
		tfSobrenome.setColumns(10);

		/*---Salvar---*/
		JButton btSalvar = new JButton("Salvar");
		btSalvar.setBounds(585, 110, 89, 23);
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
		btExcluir.setBounds(486, 110, 89, 23);
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
		btLimpar.setBounds(387, 110, 89, 23);
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
		btVoltar.setBounds(10, 110, 89, 23);
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
		lblNewLabel_4.setBounds(10, 410, 94, 14);
		contentPane.add(lblNewLabel_4);

		tfPesquisar = new JTextField();
		tfPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String pesquisa = tfPesquisar.getText();
				AtualizarTable(pesquisa);
			}
		});
		tfPesquisar.setBounds(114, 407, 560, 20);
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
		
		JButton btnNewButton = new JButton("Adicionar cafe");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CandidatoHasCafeDAO C_CDao = new CandidatoHasCafeDAO();
				CandidatoHasCafe C_C = new CandidatoHasCafe();
				C_C.setIdCafe(2);
				C_C.setIdCandidato(2);
				C_CDao.inserir(C_C);
			}
		});
		btnNewButton.setBounds(114, 110, 113, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Adicionar evento");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(237, 110, 113, 23);
		contentPane.add(btnNewButton_1);

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
