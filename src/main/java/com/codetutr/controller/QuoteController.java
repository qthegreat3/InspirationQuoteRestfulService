package com.codetutr.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class QuoteController {
	
	private final AtomicLong id = new AtomicLong();
	
	@RequestMapping(value="/quote", method=RequestMethod.GET)
	public Quote getQuote(@RequestParam(value="quote", defaultValue="Carpe Diem") String quote)
	{
		return new Quote(id.incrementAndGet(), quote);	
	}
}
