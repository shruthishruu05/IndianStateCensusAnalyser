package com.bridgelabz.indianstatecensusanalyser;

import com.bridgelabz.indianstatecensusanalyser.CensusAnalyserException.ExceptionType;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser 
{
	
		List<IndianCensusDAO> csvFileList;

	    public CensusAnalyser() {
	        this.csvFileList = new ArrayList<IndianCensusDAO>();
	    }

	    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
	        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
	            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
	            Iterator<IndianCensusCSV> csvIterator = csvBuilder.getCSVFileIterator(reader, IndianCensusCSV.class);
	            while (csvIterator.hasNext()) {
	                this.csvFileList.add(new IndianCensusDAO(csvIterator.next()));
	            }
	            return csvFileList.size();
	        } catch (IOException e) {
	            throw new CensusAnalyserException(e.getMessage(),
	                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
	        } catch (CSVBuilderException e) {
	            throw new CensusAnalyserException(e.getMessage(), e.type.name());
	        }  catch (NullPointerException e) {
	            throw new CensusAnalyserException(e.getMessage(),
	                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
	        }
	        catch (RuntimeException e) {
	            throw new CensusAnalyserException(e.getMessage(),
	                    CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
	        }

	    }
	    
	    public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
	    	
	        try {
	        	
	        	if(csvFilePath.contains(".txt")) {
	    			throw new CensusAnalyserException("File must be in CSV Format", ExceptionType.CENSUS_FILE_PROBLEM);
	    		}
	        	
	        	Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
	            CsvToBeanBuilder<IndianStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<IndianStateCodeCSV>(reader);
	            csvToBeanBuilder.withType(IndianStateCodeCSV.class);
	            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
	            CsvToBean<IndianStateCodeCSV> csvToBean = csvToBeanBuilder.build();
	            Iterator<IndianStateCodeCSV> stateCodesCSVIterator = csvToBean.iterator();
	            
	            int numberOfEntries = 0;
	    		while(stateCodesCSVIterator.hasNext()) {
	    			numberOfEntries++;
	    			IndianStateCodeCSV censusData = stateCodesCSVIterator.next();
	    		}
	    		return numberOfEntries;
	        } 
	        catch (IOException e) {
	            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
	        } 
	        catch(RuntimeException e) {
	    		throw new CensusAnalyserException("CSV File Must Have Comma As Delimiter Or Has Incorrect Header", ExceptionType.CENSUS_WRONG_DELIMITER_OR_WRONG_HEADER);
	    	}
	    }    
	  	    
}