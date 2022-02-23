package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
/**
 * @author Jamye Pennino
 */
public class HidePatron implements Command { //Deletes a book from view using id and ishidden property
    private int id;
    private boolean isHidden;
    private boolean isDeleted;
    /**@param id as type int
     * @param isHidden as type boolean
     * @param isDeleted as type boolean
     */
    public HidePatron(int id, boolean isHidden, boolean isDeleted) {
        this.id = id;
        this.isHidden = isHidden;
        this.isDeleted = isDeleted;
    }


    /**
     * @throws LibraryException
     * Executes the hidepatron command so that it receives the patron id through a patron object, then it goes on to call sethidden and setdelete to change the status of the patron to hidden and deleted
     */
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Patron patron = library.getPatronByID(id); //patron object calls library class to return the patron info through it's id
        
        patron.setHidden(isHidden);
        patron.setDelete(isDeleted);//setting patron to hidden upon completed execution
        System.out.println("Patron #" + patron.getId() + " is now " + (isHidden ? "hidden" : "not hidden")); 
        return;
    }
}

