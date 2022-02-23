package bcu.cmp5332.librarysystem.commands;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

public class ListPatrons implements Command {

		@Override
	    public void execute(Library library, LocalDate currentDate) throws LibraryException { // Gets patron list from patron class then prints out the details of each patron
	        List<Patron> patrons = library.getPatrons(); //getting the patron details from the list of patrons
	        for (Patron patron : patrons) { //For loop to return all the contents within list of patrons
	        	if (patron.isHidden()) {
	            	continue;
	            }
	        	System.out.println(
	            	"ID: " + patron.getId()
	            	+ "\nName: " + patron.getName()
	            	+ "\nPhone: " + patron.getPhone() 
	            	+ "\nEmail: " + patron.getEmail() 
	            	+ "\nBooks:\n" + String.join("\n", patron.getBooks(library).stream()
	            		.map(book -> "Book #" + book.getId() + " - " + book.getTitle())
	            		.collect(Collectors.toList()))
	            	+ "\nIs Deleted: " + patron.isDeleted()//Output of patron details once complete
	            );
	        }
	        System.out.println(patrons.size() + " Patron(s)"); // Prints the amount of patrons in patron list
	    }
	}


