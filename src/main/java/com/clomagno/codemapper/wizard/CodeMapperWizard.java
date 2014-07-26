package com.clomagno.codemapper.wizard;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;

public class CodeMapperWizard extends Wizard {
	private WizardPage page1 = new ConfigurationFileWizardPage();
	private WizardPage page2 = new ProductsListFileWizardPage();
	private WizardPage page3 = new DistributorSelectionWizardPage();
	private WizardPage page4 = new OutputFileWizardPage();
	
	public CodeMapperWizard() {
		setWindowTitle("Code Mapper");
	}

	@Override
	public void addPages() {
		this.addPage(page1);
		this.addPage(page2);
		this.addPage(page3);
		this.addPage(page4);
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
