package com.clomagno.codemapper.wizard;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;

public class CodeMapperWizard extends Wizard {
	private ConfigurationFileWizardPage page1;
	private ProductsListFileWizardPage page2;
	private DistributorSelectionWizardPage page3;
	private OutputFileWizardPage page4;
	private FinishWizardPage page5;
	
	public CodeMapperWizard() {
		setWindowTitle("Code Mapper");
		
		SharedData sharedData = new SharedData();
		
		page1 = new ConfigurationFileWizardPage(sharedData);
		page2 = new ProductsListFileWizardPage(sharedData);
		page3 = new DistributorSelectionWizardPage(sharedData);
		page4 = new OutputFileWizardPage(sharedData);
		page5 = new FinishWizardPage(sharedData);
	}

	@Override
	public void addPages() {
		this.addPage(page1);
		this.addPage(page2);
		this.addPage(page3);
		this.addPage(page4);
		this.addPage(page5);
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
