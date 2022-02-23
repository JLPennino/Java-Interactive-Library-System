package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Patron {
	private boolean isDeleted;
	private boolean isHidden;
    private int id;
    private String name;
    private String phone;
    private String email;
    private int borrowLimit = 2;
    private List<Book> books = new ArrayList<>();
    
    
    public Patron(int id, String name, String phone, String email, List<Book> books){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.books = books;
    }
    
    public boolean isHidden(){ // Returns ishidden
        return isHidden;
   }
   
    public boolean isDeleted(){ // Returns isdeleted
        return isDeleted;
    }
   
    public void setHidden(boolean isHidden) { //sets ishidden
        this.isHidden = isHidden;
    }
   
    public void setDelete(boolean isDeleted) { //sets is deleted
        this.isDeleted = isDeleted;
    }
    
    public int getId() { //return id 
        return id;
    } 
    
    public String getName() { //return name
        return name;
    } 
    
    public String getPhone() { //return phone
        return phone;
    }
    
    public String getEmail() { //return email
        return email;
    }
    
    public int getBorrowLimit() { //return borrowlimit
    	return borrowLimit;
    }
    
    public void setBorrowLimit(int limit) { //set borrowlimit
    	this.borrowLimit = limit;
    }
    
    public List<Book> getBooks(Library library) { //Return the books list that is currently on loan
        return library.getLoans().stream()
        		.filter(loan -> loan.getPatron().getId() == id)
        		.map(loan -> loan.getBook())
        		.collect(Collectors.toList());
    }
    

    public void setId(int id) { //set id
        this.id = id;
    }

    public void borrowBook(Library library, Book book, LocalDate dueDate) throws LibraryException {
        library.borrowBook(book); //calling borrowbook from library class
    }

    public void renewBook(Library library, Book book, LocalDate dueDate) throws LibraryException {
    	library.renewBook(book); //calling renewbook from library class
    }

    public void returnBook(Library library, Book book) throws LibraryException {
    	library.returnBook(book); //calling returnbook from library class
    }
    
    public void addBook(Library library, Book book) {
    	library.addBook(book); //calling addbook from library class
    }
}
 