package com.clomagno.codemapper.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class OutputFileWizardPage extends WizardPage {
	private PendingMap sharedData;


	public OutputFileWizardPage(PendingMap sharedData){
		super("Configuration file selection");
		
		this.sharedData = sharedData;
	}
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
	}
}
