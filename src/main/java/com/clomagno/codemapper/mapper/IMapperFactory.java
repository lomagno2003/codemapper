package com.clomagno.codemapper.mapper;

import java.io.File;

public interface IMapperFactory {
	public Mapper getMapperFromFile(File file);
}
