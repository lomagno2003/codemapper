package com.clomagno.codemapper.mapper.facades.hssf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.clomagno.codemapper.mapper.ISheet;
import com.clomagno.codemapper.mapper.IWorkbook;

public class HSSFWorkbookFacade implements IWorkbook {
	private HSSFWorkbook workbook;

	public HSSFWorkbookFacade(HSSFWorkbook workbook) {
		super();
		this.workbook = workbook;
	}

	public ISheet getSheet(String distributor) {
		return new HSSFSheetFacade(workbook.getSheet(distributor));
	}

	public void write(OutputStream outputStream) throws IOException {
		workbook.write(outputStream);
	}

	public Iterator<ISheet> iterator() {
		return new HSSFSheetIterator(workbook);
	}
}
