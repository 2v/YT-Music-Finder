package com.thomasbuckley.extractor;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Extractor {
	 // This is a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private static final int MAX_DEPTH = 2;
    private HashSet<String> links;
    private static ArrayList<String> listOfLinks;
    private String genre;
    
    public Extractor() {
        links = new HashSet<>();
		listOfLinks = new ArrayList<>();
    }

    public void getPageLinks(String URL, int depth, String genre) {
    	this.genre = genre;
        if ((!links.contains(URL) && (depth <= MAX_DEPTH))) {
            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            try {
                links.add(URL);
                
                Connection connection = Jsoup.connect(URL).userAgent(USER_AGENT);
                Document document = connection.get();
                Elements linksOnPage = document.select("a[href^=/watch?v=]");
                

                depth++;
                if(connection.response().statusCode() == 200) {// 200 is the HTTP OK status code
                    // indicating that everything is great.
                	System.out.println("\n**Visiting** Received web page at " + URL);
                }
                if (!connection.response().contentType().contains("text/html")) {
                	System.out.println("**Failure** Retrieved something other than HTML");
                }
                
                // Checks if the page has the specified words
                if (checkForMatchingWord(document, genre) || checkForMatchingWord(document, "Category\r\n" + 
                		"Music")) {
                	System.out.println("Success finding " + genre + " music.");
                    for (Element page : linksOnPage) {
                        if (links.add(URL)) {

                            System.out.println(URL);
                        }
                        getPageLinks(page.attr("abs:href"), depth, genre);
                        
						if (!listOfLinks.contains(URL)) {
							listOfLinks.add(URL); //The absolute URL of the article
						}
                    }
                } else {
                	System.out.println("Falure finding " + genre + " music.");
                    for (Element page : linksOnPage) {
                        
                        getPageLinks(page.attr("abs:href"), depth, genre);
                    }
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
    
    public boolean checkForMatchingWord(Document document, String word) {
    	Elements comments = document.select("div:contains(" + word + ")");

    	if (comments.hasText()) {
    		return true;
    	}
    	
    	return false;
    }
    
    public ArrayList<String> getList() {
		return listOfLinks;
    }
}

