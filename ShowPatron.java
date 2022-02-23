package bcu.cmp5332.librarysystem.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

public class ShowPatron implements Command { //Displays a particular patron using the id property
	private final int id;
	
	
	public ShowPatron(int id) {
        this.id = id;
        

    }
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		Patron patron= library.getPatronByID(id); //Getting the patron details via it's id
		if (patron.isHidden()) {
        	System.out.println("Patron is hidden so can no longer be viewed!");
        	return;
        }
		System.out.println(
	        	"ID: " + patron.getId()
	        	+ "\nName: " + patron.getName()
	        	+ "\nPhone: " + patron.getPhone() 
	        	+ "\nEmail: " + patron.getEmail() 
	        	+ "\nBooks:\n" + String.join("\n", patron.getBooks(library).stream()
	            		.map(book -> "Book #" + book.getId() + " - " + book.getTitle())
	            		.collect(Collectors.toList()))
	        ); //Output upon successful execution
    
	}

}
