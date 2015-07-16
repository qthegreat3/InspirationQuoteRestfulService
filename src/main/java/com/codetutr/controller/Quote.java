package com.codetutr.controller;

public class Quote {

	private final String quote;
	private final long id;
	
	public Quote(long id, String quote)
	{
		this.quote = quote;
		this.id = id;
	}
	
	public long getId()
	{
		return this.id;
	}
	
	public String getQuote()
	{
		return this.quote;
	}
}
