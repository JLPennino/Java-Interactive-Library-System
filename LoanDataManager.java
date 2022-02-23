package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.main.LibraryException;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Loan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class LoanDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/loans.txt";

    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        // TODO: implementation here
    	try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                
                Patron patron;
                Book book;
                
                try {
                    patron = library.getPatronByID(Integer.parseInt(properties[0]));
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse patron id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex); //Checking to make sure the patron id exists
                }
                
                try {
                    book = library.getBookByID(Integer.parseInt(properties[1]));
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse book id " + properties[1] + " on line " + line_idx
                            + "\nError: " + ex); //Checking to make sure the book id exists
                }
                
                LocalDate startDate = LocalDate.parse(properties[2]);
                LocalDate dueDate = LocalDate.parse(properties[3]); //Formatting the data propertes of loan
                Loan loan = new Loan(patron, book, startDate, dueDate);
                
                library.addLoan(loan);
                book.setLoan(loan); //calling various loan methods to add and setloan 
                line_idx++;
            }
        }
    }


    @Override
    public void storeData(Library library) throws IOException {
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Loan loan : library.getLoans()) {
                out.print(loan.getPatron().getId() + SEPARATOR);
                out.print(loan.getBook().getId() + SEPARATOR);
                out.print(loan.getdueDate() + SEPARATOR);
                out.print(loan.getStartdate() + SEPARATOR);
                out.println(); //Format of storing the loan details within loans.txt   
            }
    
    	}
    }
}