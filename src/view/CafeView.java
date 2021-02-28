package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dao.CafeDAO;
import model.Cafe;
import table.CafeTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class CafeView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfNome;
	private JTextField tfLotacao;
	private JTable table;
	private JTextField tfPesquisar;
	private CafeDAO dao = new CafeDAO();
	private Cafe cafe = new Cafe();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CafeView frame = new CafeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CafeView() {
		setTitle("Caf\u00E9");
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

	private void PainelInputs() {
		JPanel painelInputs = new JPanel();
		painelInputs.setBounds(10, 11, 664, 154);
		contentPane.add(painelInputs);
		painelInputs.setLayout(null);

		/*---Titulo---*/
		JLabel lblNewLabel_4 = new JLabel("Cadastro dos Espa\u00E7o de Caf\u00E9");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 11, 644, 14);
		painelInputs.add(lblNewLabel_4);

		/*---Codigo---*/
		JLabel lblNewLabel = new JLabel("Codigo: ");
		lblNewLabel.setBounds(10, 45, 94, 14);
		painelInputs.add(lblNewLabel);

		tfId = new JTextField();
		tfId.setBounds(114, 39, 540, 20);
		painelInputs.add(tfId);
		tfId.setEditable(false);
		tfId.setColumns(10);

		/*---Nome---*/
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(10, 70, 94, 14);
		painelInputs.add(lblNewLabel_1);

		tfNome = new JTextField();
		tfNome.setBounds(114, 64, 540, 20);
		painelInputs.add(tfNome);
		tfNome.setColumns(10);

		/*---Lotação---*/
		JLabel lblNewLabel_2 = new JLabel("Lota\u00E7\u00E3o: ");
		lblNewLabel_2.setBounds(10, 95, 94, 14);
		painelInputs.add(lblNewLabel_2);

		tfLotacao = new JTextField();
		tfLotacao.setBounds(114, 89, 540, 20);
		painelInputs.add(tfLotacao);
		tfLotacao.setColumns(10);

		/*---Salvar---*/
		JButton btSalvar = new JButton("Salvar");
		btSalvar.setBounds(565, 120, 89, 23);
		painelInputs.add(btSalvar);

		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfId.getText().equals("")) {
					InserirCafe();
				} else {
					AlterarCafe();
				}
				AtualizarTable();
				LimparTextField();
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
					AtualizarTable();
					LimparTextField();
				}
			}
		});

		/*---Limpar---*/
		JButton btLimpar = new JButton("Limpar");
		btLimpar.setBounds(367, 120, 89, 23);
		painelInputs.add(btLimpar);
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimparTextField();
			}
		});

		/*---Voltar---*/
		JButton btVoltar = new JButton("Voltar");
		btVoltar.setBounds(10, 120, 89, 23);
		painelInputs.add(btVoltar);
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainView main = new MainView();
				main.setLocationRelativeTo(null);
				main.setVisible(true);
				setVisible(false);
			}
		});
	}

	private void PainelTable() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 176, 664, 274);
		contentPane.add(panel);
		panel.setLayout(null);

		/*---Table---*/
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 644, 224);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				tfNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				tfLotacao.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			}
		});

		/*---Pesquisar---*/
		JLabel lblNewLabel_3 = new JLabel("Pesquisar: ");
		lblNewLabel_3.setBounds(10, 246, 94, 14);
		panel.add(lblNewLabel_3);

		tfPesquisar = new JTextField();
		tfPesquisar.setBounds(114, 243, 540, 20);
		panel.add(tfPesquisar);
		tfPesquisar.setColumns(10);
		tfPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nome = tfPesquisar.getText();
				AtualizarTable(nome);
			}
		});
	}

	private void LimparTextField() {
		tfId.setText("");
		tfNome.setText("");
		tfLotacao.setText("");
		tfPesquisar.setText("");
	}

	private void AtualizarTable() {

		AtualizarTable(null);
	}

	private void AtualizarTable(String nome) {
		try {
			CafeTableModel ctm = new CafeTableModel(nome);
			table.setModel(new DefaultTableModel(ctm.data, ctm.columnNames));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel buscar os dados no banco!");
		}
	}

	private void InserirCafe() {
		try {
			cafe.setNome(tfNome.getText());
			cafe.setLotacao(Integer.parseInt(tfLotacao.getText()));
			dao.inserir(cafe);

			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");

		} catch (Exception e) {
			if (cafe.getNome() == null || cafe.getLotacao() == 0 || cafe.getId() == 0) {
				JOptionPane.showMessageDialog(null, "Você deve preencher todos os dados!");
			}
		}
	}

	private void AlterarCafe() {
		try {
			cafe.setId(Integer.parseInt(tfId.getText()));
			cafe.setNome(tfNome.getText());
			cafe.setLotacao(Integer.parseInt(tfLotacao.getText()));
			dao.alterar(cafe);

			JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");

		} catch (Exception e) {
			if (cafe.getNome() == null || cafe.getLotacao() == 0 || cafe.getId() == 0) {
				JOptionPane.showMessageDialog(null, "Você deve preencher todos os dados!");
			}
		}
	}
}
