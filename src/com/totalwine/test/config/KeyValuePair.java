package com.totalwine.test.config;

//KeyValue pair of strings - useful data structure for storing input along with expected result

public class KeyValuePair {
	private final String term;
	private final String result;
	
	public KeyValuePair (String aTerm, String aResult) {
		term = aTerm;
		result=aResult;
	}
	
	public String term() {
		return term;
	}
	
	public String result() {
		return result;
	}
}

