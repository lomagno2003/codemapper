package com.clomagno.codemapper.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.clomagno.codemapper.mapper.IWorkbook;
import com.clomagno.codemapper.mapper.Mapper;
import com.clomagno.codemapper.mapper.exceptions.MapperException;
import com.clomagno.codemapper.mapper.impls.HSSFMapper;
import com.clomagno.codemapper.mapper.impls.MapperFactory;

public class PendingMap {
	private Mapper mapper;
	private IWorkbook configurationWorkbook;
	private IWorkbook productsWorkbook;
	private File outputFile;

	private String distributor;

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	public IWorkbook getConfigurationWorkbook() {
		return configurationWorkbook;
	}

	public void setConfigurationWorkbook(IWorkbook configurationWorkbook) {
		this.configurationWorkbook = configurationWorkbook;
	}

	public IWorkbook getProductsWorkbook() {
		return productsWorkbook;
	}

	public void setProductsWorkbook(IWorkbook productsWorkbook) {
		this.productsWorkbook = productsWorkbook;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public boolean canExecute() {
		return this.mapper != null & this.productsWorkbook != null
				& this.outputFile != null;
	}

	public void execute() throws FileNotFoundException, IOException,
			MapperException {
		IWorkbook newWorkbook = mapper.doMap(distributor, productsWorkbook);

		FileOutputStream out = new FileOutputStream(new File(
				outputFile.getAbsolutePath()));
		newWorkbook.write(out);
		out.close();
	}
}
