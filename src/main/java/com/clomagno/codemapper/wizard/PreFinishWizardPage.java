package com.clomagno.codemapper.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class PreFinishWizardPage extends WizardPage {
	private static final String DESCRIPTION = "Pulse siguiente para generar el archivo";
	private PendingMap sharedData;

	public PreFinishWizardPage(PendingMap sharedData){
		super("");
		this.setDescription(DESCRIPTION);
		this.sharedData = sharedData;
	}
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
	}

}
