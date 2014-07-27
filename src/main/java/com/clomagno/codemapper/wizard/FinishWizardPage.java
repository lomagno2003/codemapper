package com.clomagno.codemapper.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class FinishWizardPage extends WizardPage {
	private PendingMap sharedData;

	public FinishWizardPage(PendingMap sharedData){
		super("");
		this.setDescription("Pulse Finish para generar el archivo");
		this.sharedData = sharedData;
	}
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
	}

}
