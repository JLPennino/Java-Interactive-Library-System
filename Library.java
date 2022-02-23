package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.util.*;

public class Library {
    
    private int loanPeriod = 7;
    private final Map<Integer, Patron> patrons = new TreeMap<>();
    private final Map<Integer, Book> books = new TreeMap<>();
    public final ArrayList<Loan> loans = new ArrayList<>();
	public List<Patron> getPatronByID;

    public int getLoanPeriod() { //return loanperiod
        return loanPeriod;
    }

    public List<Book> getBooks() { //making books list unmodifiable
        List<Book> out = new ArrayList<>(books.values());
        return Collections.unmodifiableList(out);
    }

    public Book getBookByID(int id) throws LibraryException {
        if (!books.containsKey(id)) { //checking if book exists
            throw new LibraryException("There is no such book with that ID.");
        }
        return books.get(id);
    }

   public List<Patron> getPatrons() { //making patrons list unmodifiable
        List<Patron> out = new ArrayList<>(patrons.values());
        return Collections.unmodifiableList(out);

    }
   
   public List<Loan> getLoans() { //making loans list unmodifiable
       List<Loan> out = new ArrayList<Loan>(loans);
       return Collections.unmodifiableList(out);

   }
    public Patron getPatronByID(int id) throws LibraryException {
    	if (!patrons.containsKey(id)) { //checking if patron exists
            throw new LibraryException("There is no such patron with that ID.");
        }
        return patrons.get(id);
    }

    public void addBook(Book book) {
        if (books.containsKey(book.getId())) { //checking if book id is a duplicate
            throw new IllegalArgumentException("Duplicate book ID.");
        }
        books.put(book.getId(), book);
    }
    
    public void borrowBook(Book book) { //checking if book id is a duplicate
        if (books.containsKey(book.getId())) {
            throw new IllegalArgumentException("Duplicate book ID.");
        }
        
        books.put(book.getId(), book);
    }
    
    public void renewBook(Book book) { //checking if book id is a duplicate
        if (books.containsKey(book.getId())) {
            throw new IllegalArgumentException("Duplicate book ID.");
        }
        books.put(book.getId(), book);
    }
    
    public void returnBook(Book book) { //checking if book id is a duplicate
        if (books.containsKey(book.getId())) {
            throw new IllegalArgumentException("Duplicate book ID.");
        }
        books.put(book.getId(), book);
    }

    public void addPatron(Patron patron) { //checking if patron id is a duplicate
        // TODO: implementation here
    	if (patrons.containsKey(patron.getId())) {
            throw new IllegalArgumentException("Duplicate patron ID.");
        }
        patrons.put(patron.getId(), patron);
    }
    
    public void addLoan(Loan loan) { //Adding a new loan
        // TODO: implementation here
        loans.add(loan);
    }
    
    public void deleteLoan(Loan loan) { //Deleting a loan
        // TODO: implementation here
        loans.remove(loan);
    }

    
    public void clearData() { //clearing the memory of all data types
    	this.patrons.clear();
    	this.books.clear();
    	this.loans.clear();
    }
}
 