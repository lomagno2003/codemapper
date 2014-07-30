package com.clomagno.codemapper.wizard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.clomagno.codemapper.mapper.IRow;
import com.clomagno.codemapper.mapper.IWorkbook;
import com.clomagno.codemapper.mapper.Mapper;
import com.clomagno.codemapper.mapper.exceptions.MappedAlreadyExecutedException;
import com.clomagno.codemapper.mapper.exceptions.MapperException;
import com.clomagno.codemapper.mapper.exceptions.UnmappedCodesException;

public class PendingMap {
	private Mapper mapper;
	private IWorkbook configurationWorkbook;
	private IWorkbook productsWorkbook;
	private File outputFile;

	private String distributor;
	
	private Boolean executed = false;
	
	public Boolean isExecuted(){
		return executed;
	}

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
			MapperException, MappedAlreadyExecutedException {
		if(!executed){
			
			
			IWorkbook newWorkbook = null;
			try{
				newWorkbook = mapper.doMap(distributor, productsWorkbook);
			} catch (UnmappedCodesException e){
				//Save the mapped codes
				FileOutputStream out = new FileOutputStream(new File(
						outputFile.getAbsolutePath()));
				e.getWorkbook().write(out);
				out.close();
				
				//Save the unmapped codes
				String unmappedFileName = getOutputFile().getAbsoluteFile().toString();
				unmappedFileName = unmappedFileName.substring(0, unmappedFileName.indexOf(".")) + "_unmapped.xls";
				
				FileWriter f0 = new FileWriter(unmappedFileName);
				String newLine = System.getProperty("line.separator");
				for(IRow row:e.getUnmappedCodes())
				{
				    f0.write(row.getCell(0) + newLine);
				}
			
				f0.close();
				
				executed = true;
				
				throw e;
			}
			
			FileOutputStream out = new FileOutputStream(new File(
					outputFile.getAbsolutePath()));
			newWorkbook.write(out);
			out.close();
			
			executed = true;
		} else {
			throw new MappedAlreadyExecutedException();
		}
	}
}
