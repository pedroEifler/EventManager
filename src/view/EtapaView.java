package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dao.CafeDAO;
import dao.EventoDAO;
import model.Evento;

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

public class EtapaView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbCafe;
	private JTable tbEvento;
	private JTable tbCandidato;
	private EventoDAO eventoDAO = new EventoDAO();
	private CafeDAO cafeDAO = new CafeDAO();

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
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		PainelEvento();

		PainelCandidato();

		PainelCafe();
	}

	/*---Cafe---*/
	private void PainelCafe() {

		JPanel painelCafe = new JPanel();
		painelCafe.setBounds(10, 287, 379, 265);
		contentPane.add(painelCafe);
		painelCafe.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Espa\u00E7o de Caf\u00E9");
		lblNewLabel_1.setBounds(10, 11, 359, 14);
		painelCafe.add(lblNewLabel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 36, 359, 218);
		painelCafe.add(scrollPane_1);

		tbCafe = new JTable();
		scrollPane_1.setViewportView(tbCafe);
		AtualizarTableCafe();
	}

	private void AtualizarTableCafe() {
		CafeTableModel cafeTM = new CafeTableModel(null);
		tbCafe.setModel(new DefaultTableModel(cafeTM.data, cafeTM.columnNames));

	}

	/*---Evento---*/
	private void PainelEvento() {

		JPanel painelEvento = new JPanel();
		painelEvento.setBounds(10, 11, 379, 265);
		contentPane.add(painelEvento);
		painelEvento.setLayout(null);

		JLabel lblNewLabel = new JLabel("Salas do evento");
		lblNewLabel.setBounds(10, 11, 359, 14);
		painelEvento.add(lblNewLabel);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 36, 359, 218);
		painelEvento.add(scrollPane_2);

		tbEvento = new JTable();
		scrollPane_2.setViewportView(tbEvento);
		AtualizarTableEvento();
	}

	private void AtualizarTableEvento() {
		EventoTableModel eventTM = new EventoTableModel(null);
		tbEvento.setModel(new DefaultTableModel(eventTM.data, eventTM.columnNames));
	}

	/*---Candidato---*/
	private void PainelCandidato() {

		JPanel painelCandidato = new JPanel();
		painelCandidato.setBounds(399, 11, 375, 539);
		contentPane.add(painelCandidato);
		painelCandidato.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Candidatos");
		lblNewLabel_2.setBounds(10, 11, 355, 14);
		painelCandidato.add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 355, 492);
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
			}
		});

		scrollPane.setViewportView(tbCandidato);
		AtualizarTableCandidato();
	}

	private void AtualizarTableCandidato() {
		CandidatoTableModel candidatoTM = new CandidatoTableModel(null);
		tbCandidato.setModel(new DefaultTableModel(candidatoTM.data, candidatoTM.columnNames));
	}
}
