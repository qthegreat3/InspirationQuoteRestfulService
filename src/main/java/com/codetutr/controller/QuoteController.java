package com.codetutr.controller;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class QuoteController {
	
	@Autowired
	private QuoteDAO quoteDAO;
	
	private final AtomicInteger id = new AtomicInteger();
	
	@RequestMapping(value="/quote", method=RequestMethod.GET)
	public Quote getQuote()
	{
		int numberOfRows = quoteDAO.numberOfRows();
		
		Random rand = new Random();
		
		int id = rand.nextInt(numberOfRows + 1);
		
		Quote quote = quoteDAO.getQuote(id);
		
		return quote;	
	}
	
	@RequestMapping(value="/quote", method=RequestMethod.POST)
	public Boolean enterQuote(@RequestBody Quote quote)
	{
		quoteDAO.saveOrUpdate(quote);
		return true;
	}
}
