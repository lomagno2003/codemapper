package com.clomagno.codemapper.mapper;

public interface IRow extends Iterable<ICell>{
	public ICell createCell(Integer cellIndex);
	
	public ICell getCell(Integer cellIndex);
}
