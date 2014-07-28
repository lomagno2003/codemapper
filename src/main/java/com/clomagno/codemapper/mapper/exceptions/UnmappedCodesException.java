package com.clomagno.codemapper.mapper.exceptions;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

public class UnmappedCodesException extends MapperException{
	private static final long serialVersionUID = -7733653661221706141L;
	
	private List<Row> unmappedCodes;

	public UnmappedCodesException(List<Row> unmappedCodes) {
		super();
		this.unmappedCodes = unmappedCodes;
	}

	public List<Row> getUnmappedCodes() {
		return unmappedCodes;
	}

	public void setUnmappedCodes(List<Row> unmappedCodes) {
		this.unmappedCodes = unmappedCodes;
	}
}
