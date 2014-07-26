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
	private File configurationFile;
	private File productsFile;
	private File outputFolder;

	private String distributor;

	public File getConfigurationFile() {
		return configurationFile;
	}

	public void setConfigurationFile(File configurationFile) {
		this.configurationFile = configurationFile;
	}

	public File getProductsFile() {
		return productsFile;
	}

	public void setProductsFile(File productsFile) {
		this.productsFile = productsFile;
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
		HSSFWorkbook configurationWorkbook = new HSSFWorkbook(
				new FileInputStream(configurationFile));
		Mapper mapper = MapperFactory
				.getMapperFromWorkbook(configurationWorkbook);

		HSSFWorkbook productsWorkbook = new HSSFWorkbook(new FileInputStream(
				productsFile));
		HSSFWorkbook newWorkbook = mapper.doMap(distributor, productsWorkbook);

		FileOutputStream out = new FileOutputStream(new File(
				outputFolder.getAbsolutePath() + "/mapped.xlsx"));
		newWorkbook.write(out);
		out.close();
	}
}
