package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataManager {
    
    public static final String SEPARATOR = "::"; //Setting the separator within file storage
    
    public void loadData(Library library) throws IOException, LibraryException;
    public void storeData(Library library) throws IOException; //creating the methods to load and store
}
 