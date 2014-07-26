package com.clomagno.codemapper.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class OutputFileWizardPage extends WizardPage {
	private SharedData sharedData;


	public OutputFileWizardPage(SharedData sharedData){
		super("Configuration file selection");
		
		this.sharedData = sharedData;
	}
	public void createControl(Composite arg0) {
		// TODO Auto-generated method stub

	}
}
