package com.thomasbuckley.extractor;

public class Main {

	// The starting point for our application
	public static void main(String[] args) {
    	Extractor extractor = new Extractor();
    	Writer writer = new Writer("List of Jazz Links");
    	extractor.getPageLinks("https://www.youtube.com/watch?v=0CdBLq_skb0", 0);
    	writer.writeToFile(extractor.getList());
    	System.out.println("Success! Thanks for using!");

	}

}
