package com.clomagno.codemapper.mapper.impls;

import java.io.File;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.clomagno.codemapper.mapper.Configuration;
import com.clomagno.codemapper.mapper.exceptions.BadHeadersException;

public class MapperFactory {
	public static String GENERAL_CONFIGURATION_SHEET_NAME = "GCSN";
	public static String DISTRIBUTOR_COLUMN_NAME = "D";
	public static String SUFFIX_COLUMN_NAME = "S";
	public static String EXTERNAL_CODE_COLUMN_NAME = "E";
	public static String MAPPINGS_SHEET_NAME = "Mapeos";

	public static Integer findHeaderIndex(HSSFSheet sheet, String header) {
		Integer result = 0;

		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next();
		// For each row, iterate through all the columns
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();

			if (cell.toString().equals(header)) {
				return result;
			}

			result++;
		}

		return null;
	}
	
	public static HSSFMapper getMapperFromDBF(File dbfFile) throws BadHeadersException{
		return getMapperFromWorkbook(HSSFWorkbookFactory.getMapperFromDBF(dbfFile, MapperFactory.GENERAL_CONFIGURATION_SHEET_NAME));
	}

	public static HSSFMapper getMapperFromWorkbook(HSSFWorkbook configuration)
			throws BadHeadersException {
		HSSFMapper result = new HSSFMapper();

		//Get the general configurations, including distributors list, suffixes and external column name
		HSSFSheet generalConfiguration = configuration
				.getSheet(GENERAL_CONFIGURATION_SHEET_NAME);

		if (generalConfiguration == null) {
			throw new BadHeadersException();
		}

		Integer distributorsIndex = findHeaderIndex(generalConfiguration,
				DISTRIBUTOR_COLUMN_NAME);
		Integer suffixIndex = findHeaderIndex(generalConfiguration,
				SUFFIX_COLUMN_NAME);
		Integer externalColumnNameIndex = findHeaderIndex(generalConfiguration,
				EXTERNAL_CODE_COLUMN_NAME);

		if (distributorsIndex == null || suffixIndex == null
				|| externalColumnNameIndex == null) {
			throw new BadHeadersException();
		}

		Iterator<Row> rowIterator = generalConfiguration.iterator();
		rowIterator.next();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Cell distributorCell = row.getCell(distributorsIndex);
			if (distributorCell == null) {
				// If the sheet have some row without any cells, the search
				// for new distributors is finished
				break;
			}
			
			//Add a new empty configuration to the map
			result.getConfigurations().put(
					distributorCell.getStringCellValue(), new Configuration());
			
			//Configure the name of the configuration
			result.getConfigurations().get(distributorCell.getStringCellValue())
				.setDistributorName(distributorCell.getStringCellValue());

			//Find the suffix of the distributor
			Cell suffixCell = row.getCell(suffixIndex);
			if (suffixCell == null) {
				throw new BadHeadersException();
			}
			//Configure the suffix of the distributor
			result.getConfigurations()
					.get(distributorCell.getStringCellValue())
					.setSuffix(suffixCell.getStringCellValue());

			//Find the external column name of the distributor
			Cell externalColumnNameCell = row.getCell(externalColumnNameIndex);
			if (externalColumnNameCell == null) {
				throw new BadHeadersException();
			}
			//Configure the external column name of the distributor
			result.getConfigurations()
					.get(distributorCell.getStringCellValue())
					.setExternalColumnName(
							externalColumnNameCell.getStringCellValue());

			configureDistributorMappings(
					result.getConfigurations().get(
							distributorCell.getStringCellValue()),
					configuration.getSheet(MAPPINGS_SHEET_NAME));
		}

		return result;
	}

	public static void configureDistributorMappings(
			Configuration configuration, HSSFSheet mappingSheet) {
		Integer internalCodeIndex = findHeaderIndex(mappingSheet,
				HSSFMapper.INTERNAL_CODE_COLUMN_NAME);
		Integer externalCodeIndex = findHeaderIndex(mappingSheet,
				configuration.getDistributorName());

		Iterator<Row> rowIterator = mappingSheet.iterator();
		rowIterator.next();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Cell internalCodeCell = row.getCell(internalCodeIndex);
			Cell externalCodeCell = row.getCell(externalCodeIndex);
			
			configuration.getMappings().put(externalCodeCell.toString(),
					internalCodeCell.toString());
		}
	}
}
