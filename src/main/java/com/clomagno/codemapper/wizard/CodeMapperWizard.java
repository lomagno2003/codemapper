package com.clomagno.codemapper.wizard;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CodeMapperWizard extends Wizard {
	private PendingMap pendingMap;
	protected ConfigurationFileWizardPage page1;
	protected ProductsListFileWizardPage page2;
	protected DistributorSelectionWizardPage page3;
	protected OutputFileWizardPage page4;
	protected PreFinishWizardPage page5;
	protected FinishWizardPage page6;

	public CodeMapperWizard() {
		super();

		setWindowTitle("Code Mapper");

		pendingMap = new PendingMap();

		page1 = new ConfigurationFileWizardPage(pendingMap);
		page2 = new ProductsListFileWizardPage(pendingMap);
		page3 = new DistributorSelectionWizardPage(pendingMap);
		page4 = new OutputFileWizardPage(pendingMap);
		page5 = new PreFinishWizardPage(pendingMap);
		page6 = new FinishWizardPage(pendingMap);
	}

	public PendingMap getPendingMap() {
		return pendingMap;
	}

	@Override
	public void addPages() {
		this.addPage(page1);
		this.addPage(page2);
		this.addPage(page3);
		this.addPage(page4);
		this.addPage(page5);
		this.addPage(page6);
		
	}

	@Override
	public boolean canFinish() {
		return this.pendingMap.isExecuted();
	};

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = display.getActiveShell();
		Wizard wizard = new CodeMapperWizard();
		WizardDialog wizardDialog = new WizardDialog(shell, wizard);
		wizardDialog.create();
		if (wizardDialog.open() == Window.OK) {
			
		}
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
