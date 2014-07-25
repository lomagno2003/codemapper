package com.clomagno.codemapper.mapper;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
	private String suffix;
	
	private String externalColumnName;
	
	private Map<String,String> mappings;
	
	public Configuration(){
		mappings = new HashMap<String, String>();
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getExternalColumnName() {
		return externalColumnName;
	}

	public void setExternalColumnName(String externalColumnName) {
		this.externalColumnName = externalColumnName;
	}

	public Map<String, String> getMappings() {
		return mappings;
	}

	public void setMappings(Map<String, String> mappings) {
		this.mappings = mappings;
	}
}
