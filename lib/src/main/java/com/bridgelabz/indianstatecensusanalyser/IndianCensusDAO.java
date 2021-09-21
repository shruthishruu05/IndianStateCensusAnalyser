package com.bridgelabz.indianstatecensusanalyser;

public class IndianCensusDAO {
	public String state;
    public int densityPerSqKm;
    public int areaInSqKm;
    public int population;

    public IndianCensusDAO(IndianCensusCSV indianCensusCSV) {
        densityPerSqKm = indianCensusCSV.densityPerSqKm;
        areaInSqKm = indianCensusCSV.areaInSqKm;
        population = indianCensusCSV.population;
        state = indianCensusCSV.state;
    }
}
