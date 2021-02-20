package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
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
	public MainView() {
		setResizable(false);
		setTitle("Event Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*---Candidatos---*/
		JButton btCandidatos = new JButton("Candidatos");
		btCandidatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CandidatoView candidatoView = new CandidatoView();
				candidatoView.setLocationRelativeTo(null);
				candidatoView.setVisible(true);
				setVisible(false);
			}
		});
		btCandidatos.setIcon(new ImageIcon("C:\\Users\\pedro\\eclipse-workspace\\EventManager\\util\\Man-icon.png"));
		btCandidatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btCandidatos.setBounds(10, 11, 154, 37);
		contentPane.add(btCandidatos);

		/*---Salas---*/
		JButton btSalas = new JButton("Salas");
		btSalas.setForeground(Color.BLACK);
		btSalas.setIcon(new ImageIcon("C:\\Users\\pedro\\eclipse-workspace\\EventManager\\util\\calendar-icon.png"));
		btSalas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btSalas.setBounds(10, 59, 154, 37);
		contentPane.add(btSalas);

		/*---Cafe---*/
		JButton btCafe = new JButton("Caf\u00E9");
		btCafe.setIcon(new ImageIcon("C:\\Users\\pedro\\eclipse-workspace\\EventManager\\util\\tea-icon.png"));
		btCafe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btCafe.setBounds(10, 107, 154, 37);
		contentPane.add(btCafe);

		JLabel lblNewLabel = new JLabel("Event");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(261, 64, 89, 37);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Manager");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(271, 114, 116, 37);
		contentPane.add(lblNewLabel_1);
	}
}
