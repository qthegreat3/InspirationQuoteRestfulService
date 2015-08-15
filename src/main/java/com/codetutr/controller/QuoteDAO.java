package com.codetutr.controller;

public interface QuoteDAO {

	public void saveOrUpdate(Quote quote);
	
	public Quote getQuote(int id);
	
	public int numberOfRows();
	
}
