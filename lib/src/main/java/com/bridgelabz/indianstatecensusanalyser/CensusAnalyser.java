package com.bridgelabz.indianstatecensusanalyser;

import com.google.gson.Gson;

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

}