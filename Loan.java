package bcu.cmp5332.librarysystem.model;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.List;

public class Loan {
    
    private Patron patron;
    private Book book;
    private LocalDate startDate;
    private LocalDate dueDate;

    public Loan(Patron patron, Book book, LocalDate startDate, LocalDate dueDate) {
        this.patron = patron;
        this.book = book;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }
    
    public Patron getPatron() { //Return patron
        return patron; 
    } 
    
    public Book getBook() { //Return book
        return book;
    } 
    
    public LocalDate getStartdate() { //Return startdate
        return startDate;
    }
    
    public LocalDate getdueDate() { //return duedate
        return dueDate;
    }
    

    public void setDuedate(LocalDate dueDate) { //set duedate
        this.dueDate = dueDate;
    }
}
 