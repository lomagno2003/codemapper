package com.clomagno.codemapper.mapper.exceptions;

import java.util.List;

import com.clomagno.codemapper.mapper.IRow;
import com.clomagno.codemapper.mapper.IWorkbook;

public class UnmappedCodesException extends MapperException{
	private static final long serialVersionUID = -7733653661221706141L;
	
	private IWorkbook workbook;
	
	private List<IRow> unmappedCodes;

	public UnmappedCodesException(IWorkbook workbook, List<IRow> unmappedCodes2) {
		super();
		this.workbook = workbook;
		this.unmappedCodes = unmappedCodes2;
	}
	
	public IWorkbook getWorkbook(){
		return this.workbook;
	}

	public List<IRow> getUnmappedCodes() {
		return unmappedCodes;
	}
}
