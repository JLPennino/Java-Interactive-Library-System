package bcu.cmp5332.librarysystem.commands;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;

public class BorrowBook implements Command { // Borrows a book using library, patron, book, startDate, dueDate property

	private Library library;
	private Patron patron;
	private Book book;
	private LocalDate startDate;
	private LocalDate dueDate;
	
	public BorrowBook(Library library, Patron patron, Book book, LocalDate startDate, LocalDate dueDate) { 
		this.library = library;
		this.patron = patron;
		this.book = book;
		this.startDate = startDate;
		this.dueDate = dueDate;
	}
	
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		// check if loan exists for the book
		if (book.isOnLoan()) {
			System.out.println("This book is currently on loan for patron #" + book.getLoan().getPatron().getId());
			return;
		}
		if (patron.getBooks(library).size() >= patron.getBorrowLimit()) {
			System.out.println("Max borrow limit reached");
			return;
		}
		Loan loan = new Loan(patron, book, startDate, dueDate); //creates new loan object
		library.addLoan(loan); //calls addloan method from library using he new loan object created
		
		book.setLoan(loan); //calls setloan method from look using the new loan object created
		
		System.out.println(
				"Book #" + book.getId()
				+ " has been loaned to patron #" + patron.getId()
				+ " from " + loan.getStartdate()
				+ " for " + library.getLoanPeriod() + " days"
				+ " is due " + loan.getdueDate() + "."); //Printed output upon successful loan creation
		try {
    	 	LibraryData.store(library); //Stores new loan to loans.txt upon completed execution
     	} catch (IOException e) {
    	 	System.out.println("Failed to store the updated library! Rolling back changes..."); //Reload to previous state in library upon catching IOExecption ie failing to store to loans.txt
    	 	try {
				LibraryData.loadIntoLibrary(library);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
     	}

	}
}
