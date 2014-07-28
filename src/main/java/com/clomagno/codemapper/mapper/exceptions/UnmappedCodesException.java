package com.clomagno.codemapper.mapper.exceptions;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.clomagno.codemapper.mapper.IRow;

public class UnmappedCodesException extends MapperException{
	private static final long serialVersionUID = -7733653661221706141L;
	
	private List<IRow> unmappedCodes;

	public UnmappedCodesException(List<IRow> unmappedCodes2) {
		super();
		this.unmappedCodes = unmappedCodes2;
	}

	public List<IRow> getUnmappedCodes() {
		return unmappedCodes;
	}
}
