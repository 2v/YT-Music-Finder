package com.thomasbuckley.extractor;

public class Main {
	public static String genre;
	
	// The starting point for our application
	public static void main(String[] args) {
    	Extractor extractor = new Extractor();
    	// no spaces please!
    	Writer writer = new Writer("jazzlist");
    	extractor.getPageLinks("https://www.youtube.com/watch?v=jqWBgiO-8Pc", 0, "jazz");
    	genre = extractor.getGenre();
    	writer.writeToFile(extractor.getList());
    	System.out.println("Success! Thanks for using!");

	}

}
