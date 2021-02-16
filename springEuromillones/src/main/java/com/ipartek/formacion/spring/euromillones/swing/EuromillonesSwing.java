package com.ipartek.formacion.spring.euromillones.swing;

import static com.ipartek.formacion.spring.euromillones.bibliotecas.Miscelania.createFormatter;
import static com.ipartek.formacion.spring.euromillones.bibliotecas.Miscelania.mostrarErrores;
import static com.ipartek.formacion.spring.euromillones.bibliotecas.Miscelania.sortear;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
import javax.swing.table.DefaultTableModel;

import com.ipartek.formacion.spring.euromillones.accesodatos.SorteoDao;
import com.ipartek.formacion.spring.euromillones.accesodatos.SorteoDaoJdbc;
import com.ipartek.formacion.spring.euromillones.entidades.Sorteo;

public class EuromillonesSwing {

	private static final SorteoDao DAO = SorteoDaoJdbc.getInstancia();

	private JFrame frame;

	private JTextField numero_1;
	private JTextField numero_2;
	private JTextField numero_3;
	private JTextField numero_4;
	private JTextField numero_5;

	private JTextField estrella_1;
	private JTextField estrella_2;

	private JFormattedTextField fechaSorteo;

	private JButton btnGuardar;
	private JButton btnSorteo;
	private JButton btnSalir;

	private DefaultTableModel modelo;
	private JTable table;
	private JLabel lblTabla;

	private JSeparator separatorNumeros, separatorEstrellas, separatorFecha;

	static final private int NUMEROS = 5;
	static final private int ESTRELLAS = 2;

	static final private int NUMERO_MAX_EUROMILLONES = 50;
	static final private int NUMERO_MAX_ESTRELLAS = 12;

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

	/**
	 * Create the application.
	 */
	public EuromillonesSwing() {
		initialize();

		modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Fecha", "Numeros", "Estrellas" });

		table.setModel(modelo);

		cargarTabla();
	}

	private void cargarTabla() {
		modelo.setRowCount(0);

		modelo.addRow(new Object[] { "Id", "Fecha", "Numeros", "Estrellas" });

		for (Sorteo sorteo : DAO.obtenerTodos()) {

			modelo.addRow(new Object[] { sorteo.getId(), sorteo.getFechaSorteo(),
					sorteo.getNumero_1() + " - " + sorteo.getNumero_2() + " - " + sorteo.getNumero_3() + " - "
							+ sorteo.getNumero_4() + " - " + sorteo.getNumero_5(),
					sorteo.getEstrella_1() + " - " + sorteo.getEstrella_2() });
		}

		lblTabla.setText("Número de registros en la BBDD Sorteos: " + DAO.numeroRegistros());
	}

	private void vaciarCampos() {

		numero_1.setText("");
		numero_2.setText("");
		numero_3.setText("");
		numero_4.setText("");
		numero_5.setText("");

		estrella_1.setText("");
		estrella_2.setText("");

		fechaSorteo.setText("");
	}

	/**
	 * Acción del botón Sortear
	 */
	private void btnSortearClick() {
		int[] sorteo;
		sorteo = sortear(NUMEROS, NUMERO_MAX_EUROMILLONES);

		numero_1.setText(String.valueOf(sorteo[0]));
		numero_2.setText(String.valueOf(sorteo[1]));
		numero_3.setText(String.valueOf(sorteo[2]));
		numero_4.setText(String.valueOf(sorteo[3]));
		numero_5.setText(String.valueOf(sorteo[4]));

		sorteo = sortear(ESTRELLAS, NUMERO_MAX_ESTRELLAS);

		estrella_1.setText(String.valueOf(sorteo[0]));
		estrella_2.setText(String.valueOf(sorteo[1]));

		btnSorteo.setEnabled(false);
		btnGuardar.setEnabled(true);
	}

	/**
	 * Acción del botón Guardar en BBDD
	 */
	private void btnGuardarClick() {

		String sumaNumeros = numero_1.getText() + numero_2.getText() + numero_3.getText() + numero_4.getText()
				+ numero_5.getText();
		String sumaEstrellas = estrella_1.getText() + estrella_2.getText();

		if (sumaNumeros.length() == 0 || sumaEstrellas.length() == 0) {
			JOptionPane.showMessageDialog(frame, "ATENCIÓN: No hay sorteo para guardar en la BBDD !!!");
			return;
		}

		LocalDate fechaLocalDate = null;

		String fecha = fechaSorteo.getText();

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date testDate = null;

		try {
			testDate = df.parse(fecha);

			// Baeldung
			fechaLocalDate = new java.sql.Date(testDate.getTime()).toLocalDate();
		} catch (Exception e1) {
			mostrarErrores(e1);
			JOptionPane.showMessageDialog(frame, "ATENCIÓN: Fecha de sorteo incorrecta !!!");
			return;
		}

		Sorteo sorteo = new Sorteo(null, numero_1.getText(), numero_2.getText(), numero_3.getText(), numero_4.getText(),
				numero_5.getText(), estrella_1.getText(), estrella_2.getText(), fechaLocalDate);

		try {
			DAO.agregar(sorteo);
			JOptionPane.showMessageDialog(frame, "Sorteo del día [" + fecha + "] guardado en la BB.DD.");
			cargarTabla();
			vaciarCampos();
			btnSorteo.setEnabled(true);
			btnGuardar.setEnabled(false);
		} catch (Exception e1) {
			mostrarErrores(e1);
			JOptionPane.showMessageDialog(frame,
					"ATENCIÓN: " + e1.getMessage() + "\n" + e1.getCause().getLocalizedMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Euromillones");
		frame.setBounds(100, 100, 501, 607);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Números
		JLabel labelNumeros = new JLabel("Números");
		labelNumeros.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelNumeros.setBounds(10, 11, 57, 20);
		frame.getContentPane().add(labelNumeros);

		numero_1 = new JTextField();
		numero_1.setHorizontalAlignment(SwingConstants.CENTER);
		numero_1.setBounds(10, 39, 34, 20);
		numero_1.setEditable(false);
		numero_1.setColumns(10);
		frame.getContentPane().add(numero_1);

		numero_2 = new JTextField();
		numero_2.setHorizontalAlignment(SwingConstants.CENTER);
		numero_2.setBounds(54, 39, 34, 20);
		numero_2.setEditable(false);
		numero_2.setColumns(10);
		frame.getContentPane().add(numero_2);

		numero_3 = new JTextField();
		numero_3.setHorizontalAlignment(SwingConstants.CENTER);
		numero_3.setBounds(98, 39, 34, 20);
		numero_3.setEditable(false);
		numero_3.setColumns(10);
		frame.getContentPane().add(numero_3);

		numero_4 = new JTextField();
		numero_4.setHorizontalAlignment(SwingConstants.CENTER);
		numero_4.setBounds(142, 39, 34, 20);
		numero_4.setEditable(false);
		numero_4.setColumns(10);
		frame.getContentPane().add(numero_4);

		numero_5 = new JTextField();
		numero_5.setHorizontalAlignment(SwingConstants.CENTER);
		numero_5.setBounds(186, 39, 34, 20);
		numero_5.setEditable(false);
		numero_5.setColumns(10);
		frame.getContentPane().add(numero_5);

		// Separador
		separatorNumeros = new JSeparator();
		separatorNumeros.setBounds(10, 30, 465, 2);
		frame.getContentPane().add(separatorNumeros);

		// Estrellas
		JLabel labelEstrellas = new JLabel("Estrellas");
		labelEstrellas.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelEstrellas.setBounds(10, 75, 95, 20);
		frame.getContentPane().add(labelEstrellas);

		estrella_1 = new JTextField();
		estrella_1.setHorizontalAlignment(SwingConstants.CENTER);
		estrella_1.setColumns(10);
		estrella_1.setBounds(10, 106, 34, 20);
		estrella_1.setEditable(false);
		frame.getContentPane().add(estrella_1);

		estrella_2 = new JTextField();
		estrella_2.setHorizontalAlignment(SwingConstants.CENTER);
		estrella_2.setColumns(10);
		estrella_2.setBounds(54, 106, 34, 21);
		estrella_2.setEditable(false);
		frame.getContentPane().add(estrella_2);

		// Separador
		separatorEstrellas = new JSeparator();
		separatorEstrellas.setBounds(10, 95, 465, 2);
		frame.getContentPane().add(separatorEstrellas);

		// Fecha Sorteo
		JLabel lblNewLabel = new JLabel("Fecha sorteo (dd/mm/yyyy)");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 138, 187, 20);
		frame.getContentPane().add(lblNewLabel);

		LocalDate fechaHoy = LocalDate.now();
		String formattedDate = fechaHoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		fechaSorteo = new JFormattedTextField(createFormatter("##/##/####"));
		fechaSorteo.setText(formattedDate);
		fechaSorteo.setHorizontalAlignment(SwingConstants.CENTER);
		fechaSorteo.setBounds(10, 169, 72, 20);
		frame.getContentPane().add(fechaSorteo);

		// Separador
		separatorFecha = new JSeparator();
		separatorFecha.setBounds(10, 156, 465, 7);
		frame.getContentPane().add(separatorFecha);

		// Botón Realizar Sorteo
		btnSorteo = new JButton("Realizar Sorteo");
		btnSorteo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSorteo.setBounds(336, 168, 139, 23);
		frame.getContentPane().add(btnSorteo);

		// Botón Guardar en BBDD
		btnGuardar = new JButton("Guardar en BBDD");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGuardar.setBounds(336, 202, 139, 23);
		btnGuardar.setEnabled(false);
		frame.getContentPane().add(btnGuardar);

		// Botón Salir
		btnSalir = new JButton("Salir");
		btnSalir.setToolTipText("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalir.setBounds(336, 236, 139, 23);
		frame.getContentPane().add(btnSalir);

		// Label Tabla
		lblTabla = new JLabel("");
		lblTabla.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTabla.setBounds(10, 275, 302, 20);
		frame.getContentPane().add(lblTabla);

		// Tabla
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(Color.WHITE);
		table.setBounds(10, 306, 465, 251);
		frame.getContentPane().add(table);

		// Acción Botón Guardar en BBDD
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGuardarClick();
			}
		});

		// Acción Realizar Sorteo
		btnSorteo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSortearClick();
			}
		});

		// Acción Salir
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});

	}
}
