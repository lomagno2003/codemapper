package com.clomagno.codemapper.mapper;

public interface ISheet extends Iterable<IRow> {
	public IRow createRow(Integer rowIndex);

	public String getName();
}
