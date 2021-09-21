package com.bridgelabz.indianstatecensusanalyser;

public class CSVBuilderFactory 
{

		public static ICSVBuilder createCSVBuilder() {
	        return new OpenCSVBuilder();
	    }
	}


