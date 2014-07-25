package com.clomagno.codemapper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class CodeMapperGUI {

	private JFrame frame;

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
		frame.setBounds(100, 100, 450, 300);
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
				//TODO Add action listener for btnSelectConfigurationFile
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSelectConfigurationFile, -5, SpringLayout.NORTH, lblArchivoDeConfiguracion);
		springLayout.putConstraint(SpringLayout.EAST, btnSelectConfigurationFile, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnSelectConfigurationFile);
		
		JButton btnSelectProductsFile = new JButton("Seleccionar");
		btnSelectProductsFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Add action listener for btnSelectProductsFile
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSelectProductsFile, 16, SpringLayout.SOUTH, btnSelectConfigurationFile);
		springLayout.putConstraint(SpringLayout.WEST, btnSelectProductsFile, 0, SpringLayout.WEST, btnSelectConfigurationFile);
		frame.getContentPane().add(btnSelectProductsFile);
		
		JButton btnGenerar = new JButton("Seleccionar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Add action listener for btnGenerateFile
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnGenerar, 20, SpringLayout.SOUTH, btnSelectProductsFile);
		springLayout.putConstraint(SpringLayout.WEST, btnGenerar, 0, SpringLayout.WEST, btnSelectConfigurationFile);
		frame.getContentPane().add(btnGenerar);
		
		JLabel lblArchivoDeProductos = new JLabel("Archivo de productos:");
		springLayout.putConstraint(SpringLayout.WEST, lblArchivoDeProductos, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblArchivoDeProductos, 0, SpringLayout.SOUTH, btnSelectProductsFile);
		frame.getContentPane().add(lblArchivoDeProductos);
		
		JLabel lblProductsFile = new JLabel("FileName");
		springLayout.putConstraint(SpringLayout.WEST, lblProductsFile, 6, SpringLayout.EAST, lblArchivoDeProductos);
		springLayout.putConstraint(SpringLayout.SOUTH, lblProductsFile, 0, SpringLayout.SOUTH, btnSelectProductsFile);
		frame.getContentPane().add(lblProductsFile);
	}
}