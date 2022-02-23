package bcu.cmp5332.librarysystem.test;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import bcu.cmp5332.librarysystem.commands.AddPatron;
import bcu.cmp5332.librarysystem.commands.ListPatrons;
import bcu.cmp5332.librarysystem.commands.ShowPatron;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

public class PatronTest { //Testing to make sure the new email and publisher property work throughout the library system
	private int id;
    private String name;
    private String phone;
    private String email;
    private List<Book> books = new ArrayList<>();
    private final Patron patron = new Patron(id, name, phone, email, books);
    private final Library library = new Library();
	
	@Test
    public void testPatron(){
		assertEquals(0, patron.getId());
        assertEquals(null, patron.getName());
        assertEquals(null, patron.getPhone());
        assertEquals(null, patron.getEmail());
        assertEquals(Collections.emptyList(), patron.getBooks(library)); //Testing to make sure all patron details start with no significant value
    }
	@Test
	public void testAddPatron() { 
		Patron patron = new Patron(1, "Jay", "333", "Jay@jay.com", books);
		AddPatron addPatron = new AddPatron(patron.getId(), patron.getName(), patron.getPhone(), patron.getEmail(), patron.getBooks(library));
		try {
			addPatron.execute(library, null);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		assertEquals(1, patron.getId());
        assertEquals("Jay", patron.getName());
        assertEquals("333", patron.getPhone());
        assertEquals("Jay@jay.com", patron.getEmail());
        assertEquals(Collections.emptyList(), patron.getBooks(library)); //Testing addpatron execution
	}
	@Test
	public void testListPatron() { 
		Patron patron = new Patron(1, "Jay", "333", "Jay@jay.com", books);
		AddPatron addPatron = new AddPatron(patron.getId(), patron.getName(), patron.getPhone(), patron.getEmail(), patron.getBooks(library));
		try {
			addPatron.execute(library, null);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		ListPatrons listPatron = new ListPatrons();
		try {
			listPatron.execute(library, null);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		assertEquals(1, patron.getId());
        assertEquals("Jay", patron.getName());
        assertEquals("333", patron.getPhone());
        assertEquals("Jay@jay.com", patron.getEmail());
        assertEquals(Collections.emptyList(), patron.getBooks(library)); //Testing listpatron execution
	}
	@Test
	public void testShowPatron() { 
		Patron patron = new Patron(1, "Jay", "333", "Jay@jay.com", books);
		AddPatron addPatron = new AddPatron(patron.getId(), patron.getName(), patron.getPhone(), patron.getEmail(), patron.getBooks(library));
		try {
			addPatron.execute(library, null);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		ShowPatron showPatron = new ShowPatron(patron.getId());
		try {
			showPatron.execute(library, null);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		assertEquals(1, patron.getId());
        assertEquals("Jay", patron.getName());
        assertEquals("333", patron.getPhone());
        assertEquals("Jay@jay.com", patron.getEmail());
        assertEquals(Collections.emptyList(), patron.getBooks(library)); //Testing showpatron execution
	}
}
