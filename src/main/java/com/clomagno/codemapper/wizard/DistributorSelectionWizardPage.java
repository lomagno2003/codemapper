package com.clomagno.codemapper.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class DistributorSelectionWizardPage extends WizardPage {
	private static final String DESCRIPTION = "Selecciona el distribuidor";

	private PendingMap sharedData;

	private List list;

	public DistributorSelectionWizardPage(PendingMap sharedData) {
		super("Configuration file selection");
		this.setDescription(DESCRIPTION);
		this.sharedData = sharedData;
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		container.setLayout(new FormLayout());

		list = new List(container, SWT.BORDER);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				DistributorSelectionWizardPage.this.getWizard().getContainer()
						.updateButtons();
			}
		});
		FormData fd_list = new FormData();
		fd_list.bottom = new FormAttachment(0, 319);
		fd_list.right = new FormAttachment(0, 574);
		fd_list.top = new FormAttachment(0, 10);
		fd_list.left = new FormAttachment(0, 10);
		list.setLayoutData(fd_list);
	}

	@Override
	public void setVisible(boolean value) {
		super.setVisible(value);

		if (value) {
			list.removeAll();

			for (String key : this.sharedData.getMapper().getConfigurations().keySet()) {
				list.add(key);
			}
		}
	}

	@Override
	public boolean isPageComplete() {
		if (list.getSelection().length > 0) {
			this.sharedData.setDistributor(list.getSelection()[0]);

			return true;
		}

		return false;
	}
}
