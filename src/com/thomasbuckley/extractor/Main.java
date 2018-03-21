package com.thomasbuckley.extractor;

public class Main {

	// The starting point for our application
	public static void main(String[] args) {
    	Extractor extractor = new Extractor();
    	Writer writer = new Writer("List of City Pop Links");
    	extractor.getPageLinks("https://www.youtube.com/watch?v=-BmBqQ3LIuc", 0, "city pop");
    	writer.writeToFile(extractor.getList());
    	System.out.println("Success! Thanks for using!");

	}

}
