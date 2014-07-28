package com.clomagno.codemapper.mapper.facades.hssf;

import org.apache.poi.ss.usermodel.Cell;

import com.clomagno.codemapper.mapper.ICell;

public class HSSFCellFacade implements ICell {
	private Cell cell;
	
	public HSSFCellFacade(Cell cell) {
		this.cell = cell;
	}
	
	@Override
	public String toString(){
		if(cell!=null){
			return cell.toString();
		} else {
			return null;
		}
	}

	public void setCellValue(String value) {
		cell.setCellValue(value);
	}
}
