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
import model.Candidato;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

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

		PainelInputs();

		PainelTable();

		AtualizarTable();
	}

	public void PainelInputs() {
		JPanel painelInputs = new JPanel();
		painelInputs.setBorder(null);
		painelInputs.setBounds(10, 11, 664, 154);
		contentPane.add(painelInputs);
		painelInputs.setLayout(null);

		/*---Titulo---*/
		JLabel lblNewLabel_2 = new JLabel("Cadastro de Candidatos");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 11, 644, 14);
		painelInputs.add(lblNewLabel_2);

		/*---Codigo---*/
		JLabel lblNewLabel_5 = new JLabel("Codigo:");
		lblNewLabel_5.setBounds(10, 45, 94, 14);
		painelInputs.add(lblNewLabel_5);

		tfId = new JTextField();
		tfId.setBounds(114, 39, 540, 20);
		painelInputs.add(tfId);
		tfId.setEditable(false);
		tfId.setColumns(10);

		/*---Nome---*/
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(10, 70, 94, 14);
		painelInputs.add(lblNewLabel);

		tfNome = new JTextField();
		tfNome.setBounds(114, 64, 540, 20);
		painelInputs.add(tfNome);
		tfNome.setColumns(10);

		/*---Sobrenome---*/
		JLabel lblNewLabel_1 = new JLabel("Sobrenome:");
		lblNewLabel_1.setBounds(10, 95, 94, 14);
		painelInputs.add(lblNewLabel_1);

		tfSobrenome = new JTextField();
		tfSobrenome.setBounds(114, 89, 540, 20);
		painelInputs.add(tfSobrenome);
		tfSobrenome.setColumns(10);

		/*---Salvar---*/
		JButton btSalvar = new JButton("Salvar");
		btSalvar.setBounds(565, 120, 89, 23);
		painelInputs.add(btSalvar);
		btSalvar.addActionListener(new ActionListener() {
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
		btExcluir.setBounds(466, 120, 89, 23);
		painelInputs.add(btExcluir);
		btExcluir.addActionListener(new ActionListener() {
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
		btLimpar.setBounds(367, 120, 89, 23);
		painelInputs.add(btLimpar);
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparTextField();
			}
		});

		/*---Voltar---*/
		JButton btVoltar = new JButton("Voltar");
		btVoltar.setBounds(10, 120, 89, 23);
		painelInputs.add(btVoltar);
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView main = new MainView();
				main.setLocationRelativeTo(null);
				main.setVisible(true);
				setVisible(false);
			}
		});

	}

	private void PainelTable() {
		JPanel painelTable = new JPanel();
		painelTable.setBorder(null);
		painelTable.setBounds(10, 176, 664, 274);
		contentPane.add(painelTable);
		painelTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 644, 221);
		painelTable.add(scrollPane);

		/*---Table---*/
		table = new JTable();
		scrollPane.setViewportView(table);

		tfPesquisar = new JTextField();
		tfPesquisar.setBounds(114, 243, 540, 20);
		painelTable.add(tfPesquisar);
		tfPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String pesquisa = tfPesquisar.getText();
				AtualizarTable(pesquisa);
			}
		});
		tfPesquisar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Pesquisar:");
		lblNewLabel_4.setBounds(10, 246, 94, 14);
		painelTable.add(lblNewLabel_4);
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
