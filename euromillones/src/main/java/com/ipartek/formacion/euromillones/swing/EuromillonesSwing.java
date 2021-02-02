package com.ipartek.formacion.euromillones.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

public class EuromillonesSwing {

	private JFrame frame;

	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextField textField_4;

	private JTable table;

	private JTextField estrellaField_1;
	private JTextField estrellaField_2;

	static final private int NUMEROS = 5;
	static final private int ESTRELLAS = 2;

	static final private int NUMERO_MAX_EUROMILLONES = 50;
	static final private int NUMERO_MAX_ESTRELLAS = 12;
	private JButton btnNewButton_1;
	private JSeparator separator_1;

	// public static final int combinacion[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EuromillonesSwing window = new EuromillonesSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static boolean contiene(int[] array, int numero, int rellenos) {
		boolean resultado = false;

		for (int i = 0; i < rellenos; i++) {
			if (numero == array[i]) {
				resultado = true;
			}
		}

		return resultado;
	}

	public static int[] sortear(int numeros, int numeroMaximo) {
		int combinacion[] = new int[numeros];
		Random random = new Random();

		for (int i = 0; i < numeros; i++) {
			do {
				combinacion[i] = random.nextInt(numeroMaximo) + 1;
			} while (contiene(combinacion, combinacion[i], i));
		}

		Arrays.sort(combinacion);

		return combinacion;
	}

	private MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatter;
	}

	/**
	 * Create the application.
	 */
	public EuromillonesSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Euromillones");
		frame.setBounds(100, 100, 450, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel labelNumeros = new JLabel("Números");
		labelNumeros.setBounds(10, 11, 57, 21);
		frame.getContentPane().add(labelNumeros);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(10, 39, 34, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBounds(98, 39, 34, 20);
		frame.getContentPane().add(textField_2);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setColumns(10);
		textField_3.setBounds(54, 39, 34, 20);
		frame.getContentPane().add(textField_3);

		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(10);
		textField_4.setBounds(186, 39, 34, 20);
		frame.getContentPane().add(textField_4);

		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setColumns(10);
		textField_5.setBounds(142, 39, 34, 20);
		frame.getContentPane().add(textField_5);

		JLabel labelEstrellas = new JLabel("Estrellas");
		labelEstrellas.setBounds(10, 81, 95, 14);
		frame.getContentPane().add(labelEstrellas);

		estrellaField_1 = new JTextField();
		estrellaField_1.setHorizontalAlignment(SwingConstants.CENTER);
		estrellaField_1.setBounds(10, 106, 34, 21);
		frame.getContentPane().add(estrellaField_1);
		estrellaField_1.setColumns(10);

		estrellaField_2 = new JTextField();
		estrellaField_2.setHorizontalAlignment(SwingConstants.CENTER);
		estrellaField_2.setColumns(10);
		estrellaField_2.setBounds(54, 106, 34, 21);
		frame.getContentPane().add(estrellaField_2);

		JLabel lblNewLabel = new JLabel("Fecha sorteo");
		lblNewLabel.setBounds(10, 138, 78, 28);
		frame.getContentPane().add(lblNewLabel);

		LocalDate fechaHoy = LocalDate.now();
		System.out.println(fechaHoy);
		String formattedDate = fechaHoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		System.out.println(formattedDate);

		JFormattedTextField frmtdtxtfld = new JFormattedTextField(createFormatter("##/##/####"));
		frmtdtxtfld.setText(formattedDate);
		frmtdtxtfld.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfld.setBounds(98, 142, 72, 20);
		frame.getContentPane().add(frmtdtxtfld);

		JButton btnNewButton = new JButton("Realizar Sorteo");
		btnNewButton.setBounds(280, 119, 139, 23);
		frame.getContentPane().add(btnNewButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 30, 409, 2);
		frame.getContentPane().add(separator);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(Color.WHITE);
		table.setBounds(429, 212, -423, 142);
		frame.getContentPane().add(table);

		btnNewButton_1 = new JButton("Guardar en BBDD");
		btnNewButton_1.setBounds(280, 153, 139, 23);
		frame.getContentPane().add(btnNewButton_1);

		separator_1 = new JSeparator();
		separator_1.setBounds(10, 95, 409, 2);
		frame.getContentPane().add(separator_1);

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String fecha = frmtdtxtfld.getText();
				System.out.println("fecha " + fecha);

				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date testDate = null;

				try {
					testDate = df.parse(fecha);
					System.out.println("Ahora hemos creado un objeto date con la fecha indicada, " + testDate);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "Fecha: invalid format !!!");
					System.out.println("invalid format !!!");
				}

				// Baeldung
				LocalDate fechalocalDate = new java.sql.Date(testDate.getTime()).toLocalDate();
				System.out.println("fechalocalDate: " + fechalocalDate.toString());

			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] sorteo;
				sorteo = sortear(NUMEROS, NUMERO_MAX_EUROMILLONES);

				textField_1.setText(String.valueOf(sorteo[0]));
				textField_3.setText(String.valueOf(sorteo[1]));
				textField_2.setText(String.valueOf(sorteo[2]));
				textField_5.setText(String.valueOf(sorteo[3]));
				textField_4.setText(String.valueOf(sorteo[4]));

				sorteo = sortear(ESTRELLAS, NUMERO_MAX_ESTRELLAS);

				estrellaField_1.setText(String.valueOf(sorteo[0]));
				estrellaField_2.setText(String.valueOf(sorteo[1]));

			}
		});
	}
}
