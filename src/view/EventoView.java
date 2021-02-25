package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dao.EventoDAO;
import model.Evento;
import table.EventoTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EventoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfNome;
	private JTextField tfLotacao;
	private JTable table;
	private JTextField tfPesquisar;
	private EventoDAO dao = new EventoDAO();
	private Evento evento = new Evento();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventoView frame = new EventoView();
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
	public EventoView() {
		setTitle("Salas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Codigo: ");
		lblNewLabel.setBounds(10, 11, 94, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome: ");
		lblNewLabel_1.setBounds(10, 36, 94, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Lota\u00E7\u00E3o: ");
		lblNewLabel_2.setBounds(10, 61, 94, 14);
		contentPane.add(lblNewLabel_2);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(114, 8, 560, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);

		tfNome = new JTextField();
		tfNome.setBounds(114, 33, 560, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfLotacao = new JTextField();
		tfLotacao.setBounds(114, 58, 560, 20);
		contentPane.add(tfLotacao);
		tfLotacao.setColumns(10);

		/*---Salvar---*/
		JButton btSalvar = new JButton("Salvar");
		btSalvar.setBounds(585, 110, 89, 23);
		contentPane.add(btSalvar);
		/* onclick */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfId.getText().equals("")) {
					InserirEvento();
				} else {
					AlterarEvento();
				}
				AtualizarTable();
				LimparTextField();
			}
		});

		/*---Excluir---*/
		JButton btExcluir = new JButton("Excluir");
		btExcluir.setBounds(486, 110, 89, 23);
		contentPane.add(btExcluir);
		/* onclick */
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
		btLimpar.setBounds(387, 110, 89, 23);
		contentPane.add(btLimpar);
		/* onclick */
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimparTextField();
			}
		});

		/*---Voltar---*/
		JButton btVoltar = new JButton("Voltar");
		btVoltar.setBounds(10, 110, 89, 23);
		contentPane.add(btVoltar);
		/* onclick */
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainView main = new MainView();
				main.setLocationRelativeTo(null);
				main.setVisible(true);
				setVisible(false);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 664, 213);
		contentPane.add(scrollPane);

		/*---Table---*/
		table = new JTable();
		scrollPane.setViewportView(table);
		/* onclick */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				tfNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				tfLotacao.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			}
		});
		AtualizarTable();

		JLabel lblNewLabel_3 = new JLabel("Pesquisar:");
		lblNewLabel_3.setBounds(10, 410, 89, 14);
		contentPane.add(lblNewLabel_3);

		tfPesquisar = new JTextField();
		tfPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				AtualizarTable(tfPesquisar.getText());
			}
		});
		tfPesquisar.setBounds(114, 407, 560, 20);
		contentPane.add(tfPesquisar);
		tfPesquisar.setColumns(10);
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
			EventoTableModel ctm = new EventoTableModel(nome);
			table.setModel(new DefaultTableModel(ctm.data, ctm.columnNames));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o foi possivel buscar os dados no banco!");
		}
	}

	private void InserirEvento() {
		try {
			evento.setNome(tfNome.getText());
			evento.setLotacao(Integer.parseInt(tfLotacao.getText()));
			dao.inserir(evento);

			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");

		} catch (Exception e) {
			if (evento.getNome() == null || evento.getLotacao() == 0 || evento.getId() == 0) {
				JOptionPane.showMessageDialog(null, "Voc� deve preencher todos os dados!");
			}
		}
	}

	private void AlterarEvento() {
		try {
			evento.setId(Integer.parseInt(tfId.getText()));
			evento.setNome(tfNome.getText());
			evento.setLotacao(Integer.parseInt(tfLotacao.getText()));
			dao.alterar(evento);

			JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");

		} catch (Exception e) {
			if (evento.getNome() == null || evento.getLotacao() == 0 || evento.getId() == 0) {
				JOptionPane.showMessageDialog(null, "Voc� deve preencher todos os dados!");
			}
		}
	}
}
