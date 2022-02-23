package bcu.cmp5332.librarysystem.main;

import bcu.cmp5332.librarysystem.commands.LoadGUI;
import bcu.cmp5332.librarysystem.commands.RenewBook;
import bcu.cmp5332.librarysystem.commands.ReturnBook;
import bcu.cmp5332.librarysystem.commands.ShowBook;
import bcu.cmp5332.librarysystem.commands.ShowPatron;
import bcu.cmp5332.librarysystem.commands.HideBook;
import bcu.cmp5332.librarysystem.commands.HidePatron;
import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.commands.AddPatron;
import bcu.cmp5332.librarysystem.commands.BorrowBook;
import bcu.cmp5332.librarysystem.commands.ListBooks;
import bcu.cmp5332.librarysystem.commands.ListPatrons;
import bcu.cmp5332.librarysystem.commands.AddBook;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.Help;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;

public class CommandParser {
    
    public static <Books> Command parse(Library library, String line) throws IOException, LibraryException {
        try {
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];

            
            if (cmd.equals("addbook")) { // If the input was addbook then user inputs book details
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Title: ");
                String title = br.readLine();
                System.out.print("Author: ");
                String author = br.readLine();
                System.out.print("Publication Year: ");
                String publicationYear = br.readLine();
                System.out.print("Publisher: ");
                String publisher = br.readLine();
                return new AddBook(title, author, publicationYear, publisher); //Returns new Book object
            } else if (cmd.equals("addpatron")) { // If the input was addpatron then user inputs patron details
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("ID: ");
                int id = Integer.parseInt(br.readLine());
                System.out.print("Name: ");
                String name = br.readLine();
                System.out.print("Phone: ");
                String phone = br.readLine();
                System.out.print("Email: ");
                String email = br.readLine();
                ArrayList<Book> books = new ArrayList<>();
                return new AddPatron(id, name, phone, email, books); //Returns new Patron object
            } else if (cmd.equals("removebook")) { // User enters id and a response
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                
                System.out.print("Enter ID: ");
                int id = Integer.parseInt(br.readLine());
                System.out.print("Hide? y/n: ");
                String hideStr = br.readLine();
                
                boolean hide = hideStr.equalsIgnoreCase("y") ? true : false;
                return new HideBook(id, hide, hide); // Returns new 

              } else if (cmd.equals("removepatron")) { // User enters id and a response
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                
                System.out.print("Enter ID: ");
                int id = Integer.parseInt(br.readLine());
                System.out.print("Hide? y/n: ");
                String hideStr = br.readLine();
                boolean hide = hideStr.equalsIgnoreCase("y") ? true : false; // If input is "y" then book is removed
                
                return new HidePatron(id, hide, hide);
                
            } else if (cmd.equals("loadgui")) { // Loads GUI
                return new LoadGUI();
            } else if (parts.length == 1) { 
                if (line.equals("listbooks")) { // If user inputs list books then it will output the list.
                    return new ListBooks();
                } else if (line.equals("listpatrons")) { // If user inputs list patrons then it will output the list.
                     return new ListPatrons();
                } else if (line.equals("help")) { // If user inputs help they will be taken to help screen.
                    return new Help();
                }
            } else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);

                if (cmd.equals("showbook")) { //If user inputs showbook then it will display a specific book
                	Book book = library.getBookByID(id);
                	return new ShowBook(book.getId());
                } else if (cmd.equals("showpatron")) { //If user inputs showpatron then it will display a specific patron
                	Patron patron = library.getPatronByID(id);
                	return new ShowPatron(patron.getId());
                }
            } else if (parts.length == 3) {
                int patronID = Integer.parseInt(parts[1]);
                int bookID = Integer.parseInt(parts[2]);

                if (cmd.equals("borrow")) { // If user inputs borrow, it will get the necessary parameter and execute the borrow command
                    Book book = library.getBookByID(bookID);
                    Patron patron = library.getPatronByID(patronID);
                    
                    LocalDate startDate = LocalDate.now();
                    LocalDate dueDate = startDate.plusDays(library.getLoanPeriod());
                    
                    return new BorrowBook(library, patron, book, startDate, dueDate); // Returns new borrow book object
                } else if (cmd.equals("renew")) { // If user inputs renew, it will get the necessary parameters
                	Book book = library.getBookByID(bookID);
                    Patron patron = library.getPatronByID(patronID);
                    
                    LocalDate startDate = LocalDate.now();
                    LocalDate dueDate = startDate.plusDays(library.getLoanPeriod());
                    
                    return new RenewBook(library, patron, book, startDate, dueDate); // Returns new renew book object
                } else if (cmd.equals("return")) { // If user inputs return, it will get the necessary parameters
                	Book book = library.getBookByID(bookID);
                    Patron patron = library.getPatronByID(patronID);  
                    
                    return new ReturnBook(patron, book); // Returns new return book object

                   
                }
            }
        } catch (NumberFormatException ex) {  // Catch this if string is converted into a improper format

        }

        throw new LibraryException("Invalid command."); // If there is a wrong input this message is thrown
    }
}
