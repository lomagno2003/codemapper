package com.clomagno.codemapper.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.clomagno.codemapper.mapper.Mapper;
import com.clomagno.codemapper.mapper.MapperFactory;

public class PendingMap {
	private Mapper mapper;
	private HSSFWorkbook configurationWorkbook;
	private HSSFWorkbook productsWorkbook;
	private File outputFolder;

	private String distributor;

	public HSSFWorkbook getConfigurationWorkbook() {
		return configurationWorkbook;
	}

	public void setConfigurationWorkbook(HSSFWorkbook configurationWorkbook) {
		this.configurationWorkbook = configurationWorkbook;
	}

	public HSSFWorkbook getProductsWorkbook() {
		return productsWorkbook;
	}

	public void setProductsWorkbook(HSSFWorkbook productsWorkbook) {
		this.productsWorkbook = productsWorkbook;
	}

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	public File getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(File outputFolder) {
		this.outputFolder = outputFolder;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public void execute() throws FileNotFoundException, IOException {
		HSSFWorkbook newWorkbook = mapper.doMap(distributor, productsWorkbook);

		FileOutputStream out = new FileOutputStream(new File(
				outputFolder.getAbsolutePath() + "/mapped.xlsx"));
		newWorkbook.write(out);
		out.close();
	}
}
