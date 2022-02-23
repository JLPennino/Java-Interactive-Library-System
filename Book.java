package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Book { // Book class contain isHidden, isdeleted, id, title, author, publication year and publisher, loan, dueDate property
    

	private boolean isDeleted;
    private boolean isHidden; 
    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private String publisher;

    private Loan loan;
    private LocalDate dueDate;

    public Book(int id, String title, String author, String publicationYear, String publisher) {
        
      
        
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
    }
    /**
     * @return isHidden
     */
    public boolean isHidden(){ // Returns isHidden
    	return isHidden;

    }
    /**
     * @return isDeleted
     */
    public boolean isDeleted(){ // Returns isDeleted
        return isDeleted;

    }
    /**
     * @param isHidden
     * Sets ishidden
     */
    public void setHidden(boolean isHidden) { //Sets ishidden
        this.isHidden = isHidden;
    }
    /**
     * @param isDeleted
     * Sets isdeleted
     */
    public void setDelete(boolean isDeleted) { //Sets ishidden
        this.isDeleted = isDeleted;
    }

    public int getId() { //return id
        return id;
    } 

    public void setId(int id) { //sets id
        this.id = id;
    }

    public String getTitle() { //return title 
        return title;
    }

    public void setTitle(String title) { //set title
        this.title = title;
    }
    
    public String getAuthor() {//return author
        return author;
    }
    
    public void setAuthor(String author) { //set author
        this.author = author;
    }

    public String getPublicationYear() { //return publication year
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) { //set publication year
        this.publicationYear = publicationYear;
    }
    
    public String getPublisher() { //return publisher
        return publisher;
    }

    public void setPublisher(String publisher) { //set publisher
        this.publisher = publisher;
    }
	
    public String getDetailsShort() { //Return a shortened format of book details
        return "Book #" + id + " - " + title;
    }

    public String getDetailsLong() { //Return a long format of book details
        return (
        		" Book #" + id + "\n" +
        		" Title:" + author + "\n" +
        		" Publication Year:"	+ publicationYear + "\n" +
        		" Publisher:" + publisher + "\n"
        		 + " Availability:" + getStatus() +"\n"
        		 + " Due date (If applicable):" + getDueDate() + "\n"
        		 + " Is deleted: " + isDeleted()
        		);
    }
    
    public boolean isOnLoan() { //Return the value of loan not being equal to null
        return (loan != null);
    }
    
    public String getStatus() { //Return on loan message upon checking that the book is on loan
    	Loan loan = getLoan();
    	return isOnLoan() ? "On Loan to patron #" + loan.getPatron().getId() : "Available";
    }

    public LocalDate getDueDate() { //Return the due date of chosen loan upon checking the loan exists
    	return getLoan() != null ? this.getLoan().getdueDate() : null;
    }
    
    public boolean isPastDueDate() { //Return true or false upon checking if the loan's duedate is over due via current date
    	LocalDate due = getDueDate();
    	if (due != null && due.compareTo(LocalDate.now()) < 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public long daysPastDueDate() { //Return the number of days over through checking if loan exists and checking days between duedate and currentdate
    	return this.getLoan() != null ? ChronoUnit.DAYS.between(getDueDate(), LocalDate.now()) : null;
    }

    public Loan getLoan() { //return loan
        return loan;
    }

    public void setLoan(Loan loan) { //set loan
        this.loan = loan;
    }

    public void returnToLibrary() { //changes loans value to null
        loan = null;
    }
    
    
}
