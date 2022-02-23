package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryData {
    
    private static final List<DataManager> dataManagers = new ArrayList<>();
    
    // runs only once when the object gets loaded to memory
    static {
         dataManagers.add(new BookDataManager());
         dataManagers.add(new PatronDataManager());
         dataManagers.add(new LoanDataManager());
    }
    
    public static Library load() throws LibraryException, IOException {

        Library library = new Library();
        for (DataManager dm : dataManagers) {
            dm.loadData(library); //Loading all the datamanagers to library's memory
        }
        return library; //Returns the library system
    }
    
    public static Library loadIntoLibrary(Library lib) throws LibraryException, IOException {
    	lib.clearData(); //Clears data of the last failed save
    	
    	for (DataManager dm : dataManagers) {
            dm.loadData(lib); //Reloads the last saved data to librarys memory
        }
    	return lib;
    }

    public static void store(Library library) throws IOException {

        for (DataManager dm : dataManagers) {
            dm.storeData(library); //Storing all the data to each datamanagers associated txt file
        }
    }
    
}
 