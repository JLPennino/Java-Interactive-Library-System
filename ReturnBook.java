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

public class ReturnBook implements Command {//Return a book using patron and book property

	private Patron patron;
	private Book book;
	
	public ReturnBook(Patron patron, Book book) {
		this.patron = patron;
		this.book = book;
	}
	
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		List<Loan> foundLoanList = library.loans.stream()
				.filter(
						loan ->
							loan.getBook().getId() == book.getId()
							&& loan.getPatron().getId() == patron.getId()
						) //Searching for loans associated with book and patron id
				.collect(Collectors.toList());
		
		if (foundLoanList.size() == 0) {
			System.out.println("No loan found with book # " + book.getId() + " and patron #" + patron.getId());
			return; //Output if no loans found under those specific ids
		}
		Loan loan = foundLoanList.get(0);
		
		if (book.isPastDueDate() == true) {
			System.out.println("This book was " + loan.getBook().daysPastDueDate() + " days overdue.");
		}
		
		library.deleteLoan(loan); //Calls the deleteloan method in library upon success
		book.returnToLibrary(); //Calls the method within book class to set it's loan to null
		
		System.out.println("Book #" + book.getId() + " has been returned"); //Output upon completion
		try {
    	 	LibraryData.store(library); //Saves the deletion of the loan to loans.txt upon completed execution
     	} catch (IOException e) {
    	 	System.out.println("Failed to store the updated library! Rolling back changes...");
    	 	try {
				LibraryData.loadIntoLibrary(library); //Reload to previous state in library upon catching IOExecption ie failing to store to loans.txt
			} catch (IOException e1) {
				e1.printStackTrace();
			}
     	}

	}
}
