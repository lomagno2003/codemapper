package com.clomagno.codemapper.wizard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JDialog;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.clomagno.codemapper.mapper.IRow;
import com.clomagno.codemapper.mapper.IWorkbook;
import com.clomagno.codemapper.mapper.exceptions.MapperException;
import com.clomagno.codemapper.mapper.exceptions.UnmappedCodesException;
import com.clomagno.codemapper.mapper.facades.hssf.HSSFWorkbookFacade;

public class CodeMapperWizard extends Wizard {
	private PendingMap pendingMap;
	protected ConfigurationFileWizardPage page1;
	protected ProductsListFileWizardPage page2;
	protected DistributorSelectionWizardPage page3;
	protected OutputFileWizardPage page4;
	protected PreFinishWizardPage page5;
	protected FinishWizardPage page6;

	public CodeMapperWizard() {
		super();

		setWindowTitle("Code Mapper");

		pendingMap = new PendingMap();

		page1 = new ConfigurationFileWizardPage(pendingMap);
		page2 = new ProductsListFileWizardPage(pendingMap);
		page3 = new DistributorSelectionWizardPage(pendingMap);
		page4 = new OutputFileWizardPage(pendingMap);
		page5 = new PreFinishWizardPage(pendingMap);
		page6 = new FinishWizardPage(pendingMap);
	}

	public PendingMap getPendingMap() {
		return pendingMap;
	}

	@Override
	public void addPages() {
		this.addPage(page1);
		this.addPage(page2);
		this.addPage(page3);
		this.addPage(page4);
		this.addPage(page5);
		this.addPage(page6);
		
	}

	@Override
	public boolean performFinish() {
		return this.pendingMap.canExecute();
	}

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = display.getActiveShell();
		Wizard wizard = new CodeMapperWizard();
		WizardDialog wizardDialog = new WizardDialog(shell, wizard);
		wizardDialog.create();
		if (wizardDialog.open() == Window.OK) {
			/*try {
				((CodeMapperWizard) wizard).getPendingMap().execute();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnmappedCodesException e) {
				try {
					String unmappedFileName = ((CodeMapperWizard)wizard).getPendingMap().getOutputFile().getAbsoluteFile().toString();
					unmappedFileName = unmappedFileName.substring(0, unmappedFileName.indexOf(".")) + "_unmapped.xls";

					Display display2 = new Display();
					Shell shell2 = display.getActiveShell();
					MessageBox dialog = new MessageBox(shell2, SWT.ICON_WARNING
							| SWT.OK);
					dialog.setText("Algunos codigos no pudieron ser mapeados");
					dialog.setMessage("Algunos de los codigos no pudieron ser mapeados, los mismos se listan en " + 
							unmappedFileName);
												dialog.open();

					
					
					FileWriter f0 = new FileWriter(unmappedFileName);
	
					String newLine = System.getProperty("line.separator");
	
					for(IRow row:e.getUnmappedCodes())
					{
					    f0.write(row.getCell(0) + newLine);
					}
				
					f0.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (MapperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}

}
