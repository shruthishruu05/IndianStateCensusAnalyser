package com.bridgelabz.indianstatecensusanalyser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
public class CensusAnalyserTest 
{
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndianStatesCensus.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndianStateCodesWrongDelimiter.csv";
	private static final String INCORRECT_FILE_FORMAT = "./src/test/resources/IncorrectFileFormat.txt";
	private static final String CSV_WITH_WRONG_DELIMITER = "./src/test/resources/CensusDataWithWrongDelimiter.csv";
	private static final String CSV_WITH_INCORRECT_HEADER = "./src/test/resources/CensusDataIncorrectHeader.csv";
	private static final String INDIAN_CENSUS_CSV_MISSING = "./src/test/resources/IndiaStateCensusDataMissingHeader.csv";
	private static final String INDIAN_CENSUS_EMPTY_FILE = "./src/test/resources/EmpltyCSV.csv";

	
	
	 @Test
	    public void givenIndianCensus_CSVFileReturnsCorrectRecords() {
	        try {
	            CensusAnalyser censusAnalyser = new CensusAnalyser();
	            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
	            Assert.assertEquals(29, numOfRecords);
	        } catch (CensusAnalyserException e) {
	        }
	    }
	 
	 @Test
		public void givenIndiaCensusData_WithWrongFilePath_ShouldThrowException() {
			try {
				CensusAnalyser censusAnalyser = new CensusAnalyser();
				ExpectedException exceptionRule = ExpectedException.none();
				exceptionRule.expect(CensusAnalyserException.class);
				censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
			} 
			catch (CensusAnalyserException e) {
				Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
				e.printStackTrace();
			}
		}
	 
	 @Test
	    public void givenIndianCensusCSVFile_WhenCorrectPathButWrongFileFormat_ShouldThrowException() {
			
			try {
				CensusAnalyser censusAnalyser = new CensusAnalyser();
				ExpectedException exceptionRule = ExpectedException.none();
				exceptionRule.expect(CensusAnalyserException.class);
				censusAnalyser.loadIndiaCensusData(INCORRECT_FILE_FORMAT);
			} 
			catch (CensusAnalyserException e) {
				Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
				e.printStackTrace();
			}
	    }

	    @Test
	    public void givenWrongDelimiter_InIndiaCensusData_ShouldReturnCustomExceptionType() {
	        try {
	            CensusAnalyser censusAnalyser = new CensusAnalyser();
	            int numOfRecords = censusAnalyser.loadIndiaCensusData(CSV_WITH_WRONG_DELIMITER);
	        } catch (CensusAnalyserException e) {
	            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
	        }
	    }

	    @Test
	    public void givenMissingHeader_InIndiaCensusData_ShouldReturnCustomExceptionType() {
	        try {
	            CensusAnalyser censusAnalyser = new CensusAnalyser();
	            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_MISSING);
	        } catch (CensusAnalyserException e) {
	            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
	        }
	    }

	    @Test
	    public void givenIndianCensusCSVFile_WhenIncorrectHeader_ShouldThrowException() {
	    	try {
	            CensusAnalyser censusAnalyser = new CensusAnalyser();
	            int numOfRecords = censusAnalyser.loadIndiaCensusData(CSV_WITH_INCORRECT_HEADER);
	        } catch (CensusAnalyserException e) {
	            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
	        }
	    }
			
    
}

	   

	   