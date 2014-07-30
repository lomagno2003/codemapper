package com.clomagno.codemapper.mapper.facades.hssf;

import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
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
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					return formatter.format(cell.getDateCellValue());
				} else {
					Double variable = cell.getNumericCellValue();
					//Check if the difference between the Integer value, and the double value is less than a alpha
					if ((variable - Integer.valueOf(variable.intValue()).doubleValue() < 0.005) && !Double.isInfinite(variable)) {
						//Is Integer, so convert it
						return Integer.valueOf(variable.intValue()).toString();
					} else {
						//Is Double, so return it directly
						return variable.toString();
					}
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
