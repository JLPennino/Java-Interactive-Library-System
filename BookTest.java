package bcu.cmp5332.librarysystem.test;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import bcu.cmp5332.librarysystem.commands.AddBook;
import bcu.cmp5332.librarysystem.commands.ListBooks;
import bcu.cmp5332.librarysystem.commands.ShowBook;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

public class BookTest { //Testing to make sure the new email and publisher property work throughout the library system
	private int id;
	private String title;
	private String author;
	private String publicationYear;
	private String publisher;
    private final Book book = new Book(id, title, author, publicationYear, publisher);
    private final Library library = new Library();
	
	@Test
    public void testBook(){
		assertEquals(0, book.getId());
        assertEquals(null, book.getTitle());
        assertEquals(null, book.getAuthor());
        assertEquals(null, book.getPublicationYear());
        assertEquals(null, book.getPublisher()); //Testing to make sure all book details start with no significant value
    }
	@Test
	public void testAddBook() { 
		Book book = new Book(1, "The Chronicles of Freshlooks", "Rohl Dahl", "2013", "Rohl Dahl Productions");
		AddBook addBook = new AddBook(book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getPublisher());
		try {
			addBook.execute(library, null);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		assertEquals(1, book.getId());
        assertEquals("The Chronicles of Freshlooks", book.getTitle());
        assertEquals("Rohl Dahl", book.getAuthor());
        assertEquals("2013", book.getPublicationYear());
        assertEquals("Rohl Dahl Productions", book.getPublisher()); //Testing addbook execution
	}
	@Test
	public void testListBook() { 
		Book book = new Book(1, "The Chronicles of Freshlooks", "Rohl Dahl", "2013", "Rohl Dahl Productions");
		AddBook addBook = new AddBook(book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getPublisher());
		try {
			addBook.execute(library, null);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		ListBooks listbooks = new ListBooks();
		try {
			listbooks.execute(library, null);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		assertEquals(1, book.getId());
        assertEquals("The Chronicles of Freshlooks", book.getTitle());
        assertEquals("Rohl Dahl", book.getAuthor());
        assertEquals("2013", book.getPublicationYear());
        assertEquals("Rohl Dahl Productions", book.getPublisher()); //Testing listbook execution
	}

	@Test
	public void testShowBook() { 
		Book book = new Book(1, "The Chronicles of Freshlooks", "Rohl Dahl", "2013", "Rohl Dahl Productions");
		AddBook addBook = new AddBook(book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getPublisher());
		try {
			addBook.execute(library, null);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		ShowBook showbook = new ShowBook(book.getId());
		try {
			showbook.execute(library, null);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		assertEquals(1, book.getId());
        assertEquals("The Chronicles of Freshlooks", book.getTitle());
        assertEquals("Rohl Dahl", book.getAuthor());
        assertEquals("2013", book.getPublicationYear());
        assertEquals("Rohl Dahl Productions", book.getPublisher()); //Testing showbook execution
	}
}