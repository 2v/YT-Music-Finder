package com.thomasbuckley.extractor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
	private String filename;
	
	public Writer(String filename) {
		this.filename = filename;
	}
	
	public Writer() {
		this.filename = "MusicLinks";
	}
	
    public void writeToFile(ArrayList<String> listOfLinks) {
        FileWriter writer;
        try {
            writer = new FileWriter(filename + ".html");
            writer.write("<!DOCTYPE html>\r\n" + 
            		"<html lang=\"en\">\r\n" + 
            		"<head>\r\n" + 
            		"  <title>Music Finder</title>\r\n" + 
            		"  <meta charset=\"utf-8\">\r\n" + 
            		"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
            		"  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n" + 
            		"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
            		"  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n" + 
            		"</head>\r\n" + 
            		"<body>");
            writer.write("<div class=\"jumbotron text-center\">\r\n" + 
            		"  <h1>" + filename + "</h1>\r\n" + 
            		"  <p>Your list of " + Main.genre + " music.!</p> \r\n" + 
            		"</div>");
            listOfLinks.forEach(a -> {
                try {
                	String temp = "<div class=\"container\">\r\n" + 
                			"  <div class=\"row\">\r\n" + 
                			"    <div class=\"col-sm-4\">\r\n" + 
                			"      <h3>Link</h3>\r\n" + 
                			"      <a href=\"" + a + "\">" + a + "</a><br />\r\n" + 
                			"    </div>";
                    //display to console
                    System.out.println(temp);
                    //save to file
                    writer.write(temp);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            });
            writer.write("</html>");
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
