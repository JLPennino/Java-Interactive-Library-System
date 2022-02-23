package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.IOException;
import java.time.LocalDate;



public class AddBook implements  Command { // Adds a book using title, author, publication year and publisher property

    private final String title;
    private final String author;
    private final String publicationYear;
    private final String publisher;

    public AddBook(String title, String author, String publicationYear, String publisher) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        int maxId = 0; 
    	if (library.getBooks().size() > 0) {
    		int lastIndex = library.getBooks().size() - 1;
            maxId = library.getBooks().get(lastIndex).getId(); //increments a new bookid by 1 upon new creation 
    	}
        Book book = new Book(++maxId, title, author, publicationYear, publisher); //New book object
        
        library.addBook(book); //calling addbook method within library class
        System.out.println("Book #" + book.getId() + " added.");
        try {
    	 	LibraryData.store(library); //Stores new book to books.txt upon completed execution
     	} catch (IOException e) {
    	 	System.out.println("Failed to store the updated library! Rolling back changes...");
    	 	try {
				LibraryData.loadIntoLibrary(library); //Reload to previous state in library upon catching IOExecption ie failing to store to books.txt
			} catch (IOException e1) {
				e1.printStackTrace();
			}
     	}
    }
}
 