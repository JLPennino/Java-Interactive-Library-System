package bcu.cmp5332.librarysystem.main;

import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.model.Library;

import java.io.*;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) throws IOException, LibraryException { // Main class 
        Library library = LibraryData.load(); // Loads library data
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
 
        System.out.println("Library system");        
        System.out.println("Enter 'help' to see a list of available commands.");
        while (true) {
            System.out.print("> ");
            String line = br.readLine();
            if (line.equals("exit")) {
            	LibraryData.store(library); // Saves onto library data just before ending program
            	
                break;
            }

            try {
                Command command = CommandParser.parse(library, line); // Creates new command object to parse commands
                command.execute(library, LocalDate.now());
            } catch (LibraryException ex) { 
                System.out.println(ex.getMessage());
            }
        }
        LibraryData.store(library);
        System.exit(0);
    }
}
 