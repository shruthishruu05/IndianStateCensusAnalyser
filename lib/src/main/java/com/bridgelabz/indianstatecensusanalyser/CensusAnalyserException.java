package com.bridgelabz.indianstatecensusanalyser;

public class CensusAnalyserException extends Exception {
	public CensusAnalyserException(String message, String name) 
	{
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

    enum ExceptionType {
    	CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE,  NO_CENSUS_DATA, CSV_FILE_INTERNAL_ISSUES,CENSUS_WRONG_DELIMITER_OR_WRONG_HEADER;
    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
