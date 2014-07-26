package com.clomagno.codemapper.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class FinishWizardPage extends WizardPage {
	private SharedData sharedData;

	public FinishWizardPage(SharedData sharedData){
		super("");
		
		this.sharedData = sharedData;
	}
	public void createControl(Composite arg0) {
		// TODO Auto-generated method stub

	}

}
