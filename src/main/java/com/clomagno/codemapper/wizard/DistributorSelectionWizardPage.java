package com.clomagno.codemapper.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class DistributorSelectionWizardPage extends WizardPage {
	private SharedData sharedData;

	public DistributorSelectionWizardPage(SharedData sharedData){
		super("Configuration file selection");
		
		this.sharedData = sharedData;
	}
	public void createControl(Composite arg0) {
		// TODO Auto-generated method stub

	}
}
