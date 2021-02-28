package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dao.CandidatoHasCafeDAO;
import dao.CandidatoHasEventoDAO;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import table.CafeTableModel;
import table.CandidatoTableModel;
import table.EtapaCafeTableModel;
import table.EtapaEventoTableModel;
import table.EventoTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;

public class EtapaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbCafe;
	private JTable tbEvento;
	private JTable tbCandidato;
	private JLabel lbLinhaSelecionada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EtapaView frame = new EtapaView();
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
	public EtapaView() {
		setTitle("Etapas");
		/*---Etapa---*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btVoltar = new JButton("Voltar");
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView mainView = new MainView();
				mainView.setLocationRelativeTo(null);
				mainView.setVisible(true);
				setVisible(false);
			}
		});
		btVoltar.setBounds(10, 11, 89, 23);
		contentPane.add(btVoltar);

		JButton btRealocar = new JButton("Realocar");
		btRealocar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CandidatoHasEventoDAO dao = new CandidatoHasEventoDAO();
				dao.AlocarCandidatosNasSalas();
				CandidatoHasCafeDAO cdao = new CandidatoHasCafeDAO();
				cdao.AlocarCandidatosNoCafe();
			}
		});
		btRealocar.setBounds(685, 11, 89, 23);
		contentPane.add(btRealocar);

		lbLinhaSelecionada = new JLabel("");
		lbLinhaSelecionada.setBounds(20, 45, 764, 14);
		contentPane.add(lbLinhaSelecionada);

		PainelEvento();

		PainelCandidato();

		PainelCafe();
	}

	private void PainelCafe() {

		JPanel painelCafe = new JPanel();
		painelCafe.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		painelCafe.setBounds(10, 346, 379, 265);
		contentPane.add(painelCafe);
		painelCafe.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Espa\u00E7o de Caf\u00E9");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 11, 359, 14);
		painelCafe.add(lblNewLabel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				AtualizarTableCafe();
			}
		});
		scrollPane_1.setBounds(10, 54, 359, 200);
		painelCafe.add(scrollPane_1);

		tbCafe = new JTable();
		tbCafe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AtualizarTableCandidato(Integer.parseInt(tbCafe.getValueAt(tbCafe.getSelectedRow(), 0).toString()), 0);
				lbLinhaSelecionada.setText(
						"Espaço café selecionado: " + tbCafe.getValueAt(tbCafe.getSelectedRow(), 1).toString());
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				AtualizarTableCafe();
			}
		});
		scrollPane_1.setViewportView(tbCafe);

		JLabel lblNewLabel_3 = new JLabel("Clique em um espa\u00E7o de caf\u00E9 para visualizar seus candidatos.");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setBounds(10, 29, 355, 14);
		painelCafe.add(lblNewLabel_3);

		AtualizarTableCafe();
	}

	private void AtualizarTableCafe() {
		CafeTableModel cafeTM = new CafeTableModel(null);
		tbCafe.setModel(new DefaultTableModel(cafeTM.data, cafeTM.columnNames));

	}

	private void PainelEvento() {

		JPanel painelEvento = new JPanel();
		painelEvento.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		painelEvento.setBounds(10, 70, 379, 265);
		contentPane.add(painelEvento);
		painelEvento.setLayout(null);

		JLabel lblNewLabel = new JLabel("Salas do evento");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 359, 14);
		painelEvento.add(lblNewLabel);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				AtualizarTableEvento();
			}
		});
		scrollPane_2.setBounds(10, 53, 359, 200);
		painelEvento.add(scrollPane_2);

		tbEvento = new JTable();
		tbEvento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AtualizarTableCandidato(0,
						Integer.parseInt(tbEvento.getValueAt(tbEvento.getSelectedRow(), 0).toString()));
				lbLinhaSelecionada
						.setText("Evento selecionado: " + tbEvento.getValueAt(tbEvento.getSelectedRow(), 1).toString());
			}
		});
		scrollPane_2.setViewportView(tbEvento);

		JLabel lblNewLabel_3 = new JLabel("Clique em uma sala para visualizar seus candidatos.");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setBounds(10, 28, 355, 14);
		painelEvento.add(lblNewLabel_3);
		AtualizarTableEvento();
	}

	private void AtualizarTableEvento() {
		EventoTableModel eventTM = new EventoTableModel(null);
		tbEvento.setModel(new DefaultTableModel(eventTM.data, eventTM.columnNames));

	}

	private void PainelCandidato() {

		JPanel painelCandidato = new JPanel();
		painelCandidato.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		painelCandidato.setBounds(399, 70, 375, 539);
		contentPane.add(painelCandidato);
		painelCandidato.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Candidatos");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 10, 355, 14);
		painelCandidato.add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				AtualizarTableCandidato();
			}
		});
		scrollPane.setBounds(10, 60, 355, 469);
		painelCandidato.add(scrollPane);

		tbCandidato = new JTable();
		tbCandidato.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/* Table Evento */
				EtapaEventoTableModel eventoTM = new EtapaEventoTableModel(
						tbCandidato.getValueAt(tbCandidato.getSelectedRow(), 0).toString());
				tbEvento.setModel(new DefaultTableModel(eventoTM.data, eventoTM.columnNames));

				/* Table Cafe */
				EtapaCafeTableModel cafeTM = new EtapaCafeTableModel(
						tbCandidato.getValueAt(tbCandidato.getSelectedRow(), 0).toString());
				tbCafe.setModel(new DefaultTableModel(cafeTM.data, cafeTM.columnNames));

				lbLinhaSelecionada.setText(
						"Candidato selecionado: " + tbCandidato.getValueAt(tbCandidato.getSelectedRow(), 1).toString());
			}
		});

		scrollPane.setViewportView(tbCandidato);

		JLabel lblNewLabel_3 = new JLabel("Clique em um candidato para visualizar as sala que ficar\u00E1.");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setBounds(10, 35, 355, 14);
		painelCandidato.add(lblNewLabel_3);

		AtualizarTableCandidato();
	}

	private void AtualizarTableCandidato(int idCafe, int idEvento) {
		CandidatoTableModel candidatoTM = new CandidatoTableModel(idCafe, idEvento);
		tbCandidato.setModel(new DefaultTableModel(candidatoTM.data, candidatoTM.columnNamesEtapas));

	}

	private void AtualizarTableCandidato() {
		CandidatoTableModel candidatoTM = new CandidatoTableModel(null);
		tbCandidato.setModel(new DefaultTableModel(candidatoTM.data, candidatoTM.columnNames));

	}
}
