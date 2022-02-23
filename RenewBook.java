package bcu.cmp5332.librarysystem.commands;

import java.io.IOException;
import java.time.LocalDate;

import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;

public class RenewBook implements Command { // Renews a book using library, patron, book, startDate and dueDate property

	private Library library;
	private Patron patron;
	private Book book;
	private LocalDate startDate;
	private LocalDate dueDate;
	
	public RenewBook(Library library, Patron patron, Book book, LocalDate startDate, LocalDate dueDate) {
		this.library = library;
		this.patron = patron;
		this.book = book;
		this.startDate = startDate;
		this.dueDate = dueDate;
	}
	
	public void execute(Library library, LocalDate currentDate) throws LibraryException { //Checks if loan is already created through bookid
		Loan loan = book.getLoan();
		if (loan == null) {
			System.out.println("Book # " + book.getId() +" does not have a loan!");
			return;
		}
		
		loan.setDuedate(loan.getdueDate().plusDays(library.getLoanPeriod()));
		
		System.out.println(
				"Book #" + book.getId()
				+ " has been renewed by patron #" + patron.getId()
				+ " from " + loan.getStartdate()
				+ " for " + library.getLoanPeriod() + " additional " + " days"
				+ " is due " + loan.getdueDate() + "."); //Output upon completion
		try {
    	 	LibraryData.store(library); //Stores new loan to loans.txt upon completed execution
     	} catch (IOException e) {
    	 	System.out.println("Failed to store the updated library! Rolling back changes...");
    	 	try {
				LibraryData.loadIntoLibrary(library);//Reload to previous state in library upon catching IOExecption ie failing to store to loans.txt
			} catch (IOException e1) {
				e1.printStackTrace();
			}
     	}

	}
}
