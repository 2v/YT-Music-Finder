package com.thomasbuckley.extractor;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Main extends JFrame {
	public static String genre;
	
	public Main() {
		setTitle("YouTube Music Finder");
		setSize(300,200); // default size is 0,0
		setLocation(200,200); // default is 0,0 (top left corner)
		setResizable(false);

		// Window Listeners
		addWindowListener(new WindowAdapter() {
		  	public void windowClosing(WindowEvent e) {
			   System.exit(0);
		  	} //windowClosing
		} );
	}
	
	// The starting point for our application
	public static void main(String[] args) {
	    JFrame f = new Main();
	    f.setVisible(true);
    	Extractor extractor = new Extractor();
    	// no spaces please!
    	Writer writer = new Writer("largefunkplaylist");
    	extractor.getPageLinks("https://www.youtube.com/watch?v=T0ZGNGBNIL8", 0, "funk");
    	genre = extractor.getGenre();
    	writer.writeToFile(extractor.getList());
    	System.out.println("Success! Thanks for using!");

	}

}
