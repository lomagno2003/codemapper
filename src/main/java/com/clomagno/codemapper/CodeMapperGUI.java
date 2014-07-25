package com.clomagno.codemapper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class CodeMapperGUI {

	private JFrame frame;
	
	private final JFileChooser configurationFileChooser = new JFileChooser();
	
	private final JFileChooser productsFileChooser = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CodeMapperGUI window = new CodeMapperGUI();
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
	public CodeMapperGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 449, 248);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblArchivoDeConfiguracion = new JLabel("Archivo de configuracion:");
		springLayout.putConstraint(SpringLayout.NORTH, lblArchivoDeConfiguracion, 38, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblArchivoDeConfiguracion, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblArchivoDeConfiguracion);
		
		JLabel lblConfigurationFile = new JLabel("FileName");
		springLayout.putConstraint(SpringLayout.WEST, lblConfigurationFile, 6, SpringLayout.EAST, lblArchivoDeConfiguracion);
		springLayout.putConstraint(SpringLayout.SOUTH, lblConfigurationFile, 0, SpringLayout.SOUTH, lblArchivoDeConfiguracion);
		frame.getContentPane().add(lblConfigurationFile);
		
		JButton btnSelectConfigurationFile = new JButton("Seleccionar");
		btnSelectConfigurationFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CodeMapperGUI.this.configurationFileChooser.showOpenDialog(CodeMapperGUI.this.frame);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSelectConfigurationFile, -5, SpringLayout.NORTH, lblArchivoDeConfiguracion);
		springLayout.putConstraint(SpringLayout.EAST, btnSelectConfigurationFile, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnSelectConfigurationFile);
		
		JButton btnSelectProductsFile = new JButton("Seleccionar");
		btnSelectProductsFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CodeMapperGUI.this.productsFileChooser.showOpenDialog(CodeMapperGUI.this.frame);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSelectProductsFile, 16, SpringLayout.SOUTH, btnSelectConfigurationFile);
		springLayout.putConstraint(SpringLayout.WEST, btnSelectProductsFile, 0, SpringLayout.WEST, btnSelectConfigurationFile);
		frame.getContentPane().add(btnSelectProductsFile);
		
		JButton btnGenerar = new JButton("Generar");
		springLayout.putConstraint(SpringLayout.SOUTH, btnGenerar, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnGenerar, 0, SpringLayout.EAST, btnSelectConfigurationFile);
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Add action listener for btnGenerateFile
			}
		});
		frame.getContentPane().add(btnGenerar);
		
		JLabel lblArchivoDeProductos = new JLabel("Archivo de productos:");
		springLayout.putConstraint(SpringLayout.WEST, lblArchivoDeProductos, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblArchivoDeProductos, 0, SpringLayout.SOUTH, btnSelectProductsFile);
		frame.getContentPane().add(lblArchivoDeProductos);
		
		JLabel lblProductsFile = new JLabel("FileName");
		springLayout.putConstraint(SpringLayout.WEST, lblProductsFile, 6, SpringLayout.EAST, lblArchivoDeProductos);
		springLayout.putConstraint(SpringLayout.SOUTH, lblProductsFile, 0, SpringLayout.SOUTH, btnSelectProductsFile);
		frame.getContentPane().add(lblProductsFile);
		
		JLabel lblPlanillaDeDistribuidora = new JLabel("Planilla de distribuidora:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPlanillaDeDistribuidora, 32, SpringLayout.SOUTH, lblArchivoDeProductos);
		springLayout.putConstraint(SpringLayout.WEST, lblPlanillaDeDistribuidora, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblPlanillaDeDistribuidora);
		
		JComboBox comboBoxDistributors = new JComboBox();
		springLayout.putConstraint(SpringLayout.SOUTH, comboBoxDistributors, 0, SpringLayout.SOUTH, lblPlanillaDeDistribuidora);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxDistributors, 0, SpringLayout.EAST, btnSelectConfigurationFile);
		frame.getContentPane().add(comboBoxDistributors);
	}
}
