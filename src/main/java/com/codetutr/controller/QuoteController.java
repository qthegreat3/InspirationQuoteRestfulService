package com.codetutr.controller;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class QuoteController {
	
	private static final Logger log = LoggerFactory.getLogger(QuoteController.class);
	
	@Autowired
	private QuoteDAO quoteDAO;
	
	@RequestMapping(value="/quote", method=RequestMethod.GET)
	public Quote getQuote()
	{
		int numberOfRows = quoteDAO.numberOfRows();
		
		log.debug("Number of Rows found: " + numberOfRows);
		
		Random rand = new Random();
		
		int id = rand.nextInt(numberOfRows + 1);
		
		if (id < 1)
		{
			id = 1;
		}
		
		Quote quote = quoteDAO.getQuote(id);
		
		log.debug("Quote found: id: " + quote.getId() + " quote: " + quote.getQuote());
		
		return quote;	
	}
	
	@RequestMapping(value="/quote", method=RequestMethod.POST)
	public Boolean enterQuote(@RequestBody String quote)
	{
		log.debug(" Quote Received: " + quote);
		
		Quote q = new Quote(quote);
		quoteDAO.saveOrUpdate(q);
		return true;
	}
}
