package bcu.cmp5332.librarysystem.commands;
import java.time.LocalDate;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;

public class ShowBook implements Command { //Displays a particular book using the id property
	private final int id;
	
	
	public ShowBook(int id) {
        this.id = id;
        

    }
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		Book book= library.getBookByID(id); //Getting the book details via it's id
		if (book.isHidden()) {
        	System.out.println("Book is hidden so can no longer be viewed!");
        	return;
        }
		System.out.println(
	        	"ID: " + book.getId()
	        	+ "\nTitle: " + book.getTitle()
	        	+ "\nAuthor: " + book.getAuthor() 
	        	+ "\nPublication Year: " + book.getPublicationYear()
	        	+ "\nPublisher: " + book.getPublisher()
	        	+ "\nStatus: " + book.getStatus()
	        	+ "\nDate Due (If Applicable): " + book.getDueDate()
	        	+ "\nIs deleted: " + book.isDeleted()
	        ); //Output upon successful execution
    
	}

}
