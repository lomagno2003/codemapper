package com.clomagno.codemapper.wizard;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CodeMapperWizard extends Wizard {
	protected ConfigurationFileWizardPage page1;
	protected ProductsListFileWizardPage page2;
	protected DistributorSelectionWizardPage page3;
	protected OutputFileWizardPage page4;
	protected FinishWizardPage page5;

	public CodeMapperWizard() {
		super();
		setWindowTitle("Code Mapper");

		PendingMap sharedData = new PendingMap();

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
		return true;
	}

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = display.getActiveShell();
		WizardDialog wizardDialog = new WizardDialog(shell, new CodeMapperWizard());
		wizardDialog.create();
		wizardDialog.open();
	}

}
