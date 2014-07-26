package com.clomagno.codemapper.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class FinishWizardPage extends WizardPage {
	private PendingMap sharedData;

	public FinishWizardPage(PendingMap sharedData){
		super("");
		
		this.sharedData = sharedData;
	}
	public void createControl(Composite arg0) {
		// TODO Auto-generated method stub

	}

}
