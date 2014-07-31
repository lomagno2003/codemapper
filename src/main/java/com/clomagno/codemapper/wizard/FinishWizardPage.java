package com.clomagno.codemapper.wizard;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.clomagno.codemapper.mapper.exceptions.MappedAlreadyExecutedException;
import com.clomagno.codemapper.mapper.exceptions.MapperException;
import com.clomagno.codemapper.mapper.exceptions.UnmappedCodesException;

public class FinishWizardPage extends WizardPage {
	private static final String DESCRIPTION = "Generacion finalizada";
	private PendingMap sharedData;

	public FinishWizardPage(PendingMap sharedData) {
		super("");
		this.setDescription(DESCRIPTION);
		this.sharedData = sharedData;
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
	}

	@Override
	public WizardPage getPreviousPage() {
		return null;
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			try {
				this.sharedData.execute();
			} catch (FileNotFoundException e) {
				this.setErrorMessage("Se produjo un error al leer/escribir alguno de los archivos");
				e.printStackTrace();
			} catch (IOException e) {
				this.setErrorMessage("Se produjo un error al leer/escribir alguno de los archivos");
				e.printStackTrace();
			} catch (UnmappedCodesException e) {
				String unmappedFileName = this.sharedData.getOutputFile()
						.getAbsoluteFile().toString();
				unmappedFileName = unmappedFileName.substring(0,
						unmappedFileName.indexOf(".")) + "_unmapped.xls";

				Shell shell = this.getShell();
				MessageBox dialog = new MessageBox(shell, SWT.ICON_WARNING
						| SWT.OK);
				dialog.setText("Algunos codigos no pudieron ser mapeados");
				dialog.setMessage("Algunos de los codigos no pudieron ser mapeados, los mismos se listan en "
						+ unmappedFileName);
				dialog.open();
				this.setErrorMessage("Hubieron algunos codigos que no pudieron ser mapeados");
			} catch (MapperException e) {
				this.setErrorMessage("Se produjo un error al realizar el mappeo");
				e.printStackTrace();
			} catch (MappedAlreadyExecutedException e) {
				e.printStackTrace();
			}

			this.getWizard().getContainer().updateButtons();
		}
	}

}
