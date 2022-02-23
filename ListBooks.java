package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class ListBooks implements Command {

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException { // Gets the list of books from Book class
        List<Book> books = library.getBooks(); //getting the book details from the list of books
        for (Book book : books) { //For loop to return all the contents within list of books
            if (book.isHidden()) {
            	continue;
            }
        	System.out.println(book.getDetailsLong()); // Prints the long details of books taken from books list
        }
        System.out.println(books.size() + " book(s)");  // Prints the amount of books in book list 
    }
}
 