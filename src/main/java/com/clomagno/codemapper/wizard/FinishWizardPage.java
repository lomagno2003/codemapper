package com.clomagno.codemapper.wizard;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.clomagno.codemapper.mapper.IRow;
import com.clomagno.codemapper.mapper.exceptions.MapperException;
import com.clomagno.codemapper.mapper.exceptions.UnmappedCodesException;

public class FinishWizardPage extends WizardPage {
	private static final String DESCRIPTION = "Generacion finalizada";
	private PendingMap sharedData;

	public FinishWizardPage(PendingMap sharedData){
		super("");
		this.setDescription(DESCRIPTION);
		this.sharedData = sharedData;
	}
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
	}
	
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(visible){
			try {
				this.sharedData.execute();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnmappedCodesException e) {
				try {
					String unmappedFileName = this.sharedData.getOutputFile().getAbsoluteFile().toString();
					unmappedFileName = unmappedFileName.substring(0, unmappedFileName.indexOf(".")) + "_unmapped.xls";
					
					Shell shell = this.getShell();
					MessageBox dialog = new MessageBox(shell, SWT.ICON_WARNING
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
			}
		}
	}

}
