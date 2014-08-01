package com.clomagno.codemapper.mapper;

import java.util.HashMap;
import java.util.Map;

import com.clomagno.codemapper.mapper.exceptions.MapperException;

public abstract class Mapper {
	public static String INTERNAL_CODE_COLUMN_NAME = "Codigo Interno";
	public static String MAPPED_SHEET_NAME = "Mapeo";

	private Map<String,Configuration> configurations;
	
	public Mapper(){
		configurations = new HashMap<String, Configuration>();
	}
	
	public Map<String, Configuration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(Map<String, Configuration> configurations) {
		this.configurations = configurations;
	}
	
	public IWorkbook doMap(String distributor, IWorkbook workbook) throws MapperException{
		return doMap(distributor, workbook.getSheet(distributor));
	}
	
	public abstract IWorkbook doMap(String distributor, ISheet workbook) throws MapperException;
}
