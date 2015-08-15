package com.codetutr.controller;

public class Quote {

	private String quote;
	private int id;
	
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
	
	public Quote()
	{
		this.quote = "";
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
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setQuote(String quote)
	{
		this.quote = quote;
	}
}
