package com.clomagno.codemapper.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.clomagno.codemapper.mapper.Mapper;
import com.clomagno.codemapper.mapper.MapperFactory;

public class OutputFileWizardPage extends WizardPage {
	private PendingMap sharedData;
	
	private Text text;
	
	public OutputFileWizardPage(PendingMap sharedData){
		super("Selección del archivo de Configuracion");
		
		this.sharedData = sharedData;
	}
	
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		container.setLayout(new FormLayout());
		
		Label lblArchivoDeConfiguracin = new Label(container, SWT.NONE);
		FormData fd_lblArchivoDeConfiguracin = new FormData();
		fd_lblArchivoDeConfiguracin.top = new FormAttachment(0, 52);
		fd_lblArchivoDeConfiguracin.left = new FormAttachment(0, 10);
		lblArchivoDeConfiguracin.setLayoutData(fd_lblArchivoDeConfiguracin);
		lblArchivoDeConfiguracin.setText("Archivo de configuración:");
		
		text = new Text(container, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				OutputFileWizardPage.this.getWizard().getContainer().updateButtons();
			}
		});
		FormData fd_text = new FormData();
		fd_text.left = new FormAttachment(lblArchivoDeConfiguracin, 6);
		fd_text.bottom = new FormAttachment(lblArchivoDeConfiguracin, 0, SWT.BOTTOM);
		text.setLayoutData(fd_text);
		
		Button btnSeleccionar = new Button(container, SWT.NONE);
		btnSeleccionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				Display display = Display.getCurrent();
			    Shell shell = new Shell(display);
				FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
				OutputFileWizardPage.this.text.setText(fileDialog.open());
				OutputFileWizardPage.this.getWizard().getContainer().updateButtons();
			}
		});
		fd_text.right = new FormAttachment(100, -102);
		FormData fd_btnSeleccionar = new FormData();
		fd_btnSeleccionar.top = new FormAttachment(lblArchivoDeConfiguracin, -29);
		fd_btnSeleccionar.bottom = new FormAttachment(lblArchivoDeConfiguracin, 0, SWT.BOTTOM);
		fd_btnSeleccionar.left = new FormAttachment(text, 6);
		btnSeleccionar.setLayoutData(fd_btnSeleccionar);
		btnSeleccionar.setText("Seleccionar");
	}
	
	@Override
	public boolean isPageComplete(){
		File outputFile = new File(text.getText());

		try {
			System.out.println("Test1");
			if(outputFile.createNewFile()||outputFile.canWrite()){
				System.out.println("Test2");
				this.sharedData.setOutputFile(outputFile);
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
