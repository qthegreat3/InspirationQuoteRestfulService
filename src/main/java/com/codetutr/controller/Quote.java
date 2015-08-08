package com.codetutr.controller;

public class Quote {

	private final String quote;
	private final int id;
	
	public Quote(int id, String quote)
	{
		this.quote = quote;
		this.id = id;
	}
	
	public Quote(String quote)
	{
		this.quote = quote;
		this.id = -1;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getQuote()
	{
		return this.quote;
	}
}
