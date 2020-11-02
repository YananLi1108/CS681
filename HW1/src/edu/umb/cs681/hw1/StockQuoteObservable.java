package edu.umb.cs681.hw1;

import java.util.HashMap;
import java.util.Map;
//import java.util.Observable;

public class StockQuoteObservable extends Observable {

	private Map<String, Float> ticker_quote = new HashMap<>();

	public void setQuote(String t, Float q) {
		ticker_quote.put(t, q);
		setChanged();
	}

	public void changeQuote(String t, Float q) {
		ticker_quote.put(t, q);
		setChanged();
	}

}