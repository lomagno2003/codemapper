package com.clomagno.codemapper.mapper;

import java.io.IOException;
import java.io.OutputStream;

public interface IWorkbook extends Iterable<ISheet>{
	public ISheet getSheet(String distributor);
	
	public void write(OutputStream outputStream) throws IOException;
}
