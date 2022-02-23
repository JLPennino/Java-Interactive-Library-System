package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class AddPatron implements Command { // Adds a patron using id, name, phone, email and books property


    private final int id;
    private final String name;
    private final String phone;
    private final String email;
    private List<Book> books = new ArrayList<>();
   

    public AddPatron(int id, String name, String phone, String email, List<Book> books) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.books = books;

    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
    	Patron patron= new Patron(id, name, phone, email, books); // Makes new patron object
     	library.addPatron(patron); //calling addpatron method within library class
     	
     	List<Patron> list = new ArrayList<Patron>();
     	System.out.println("Patron #" + patron.getId() + " added.");
     	try {
    	 	LibraryData.store(library); //Stores new patron to patrons.txt upon completed execution
     	} catch (IOException e) {
    	 	System.out.println("Failed to store the updated library! Rolling back changes..."); 
    	 	try {
				LibraryData.loadIntoLibrary(library); //Reload to previous state in library upon catching IOExecption ie failing to store to patrons.txt
			} catch (IOException e1) {
				e1.printStackTrace();
			}
     	}
    }
}
 