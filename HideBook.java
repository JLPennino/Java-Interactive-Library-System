package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
/**
 * @author Jamye Pennino
 */
public class HideBook implements Command { //Deletes a book from view using id and ishidden property
    private int id;
    private boolean isHidden;
    private boolean isDeleted;
   /**@param id as type int
    * @param isHidden as type boolean
    * @param isDeleted as type boolean
    */
    public HideBook(int id, boolean isHidden, boolean isDeleted) {
        this.id = id;
        this.isHidden = isHidden;
        this.isDeleted = isDeleted;
    }


    /**
     * @throws LibraryException
     * Executes the hidebook command so that it recieves the book id through a book object, then it goes on to call sethidden and setdelete to change the status of the book to hidden and deleted
     */
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Book book = library.getBookByID(id); //book object calls library class to return the book info through it's id
        
        book.setHidden(isHidden);
        book.setDelete(isDeleted);//setting book to hidden upon completed execution
        System.out.println("Book #" + book.getId() + " is now " + (isHidden ? "hidden" : "not hidden")); //Output upon completed execution
        return;
    }
}

