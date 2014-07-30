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
			switch(cell.getCellType()){
			case Cell.CELL_TYPE_NUMERIC:
				Double variable = cell.getNumericCellValue();
				if ((variable == Math.floor(variable)) && !Double.isInfinite(variable)) {
					//Is Integer, so convert it
					return Integer.valueOf(variable.intValue()).toString();
				} else {
					//Is Double, so return it directly
					return variable.toString();
				}
			}
			//Else, is string
			return cell.toString();
		} else {
			return null;
		}
	}

	public void setCellValue(String value) {
		cell.setCellValue(value);
	}
}
