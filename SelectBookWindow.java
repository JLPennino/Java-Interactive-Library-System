package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.ShowPatron;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.ShowBook;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class SelectBookWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField idText = new JTextField();

    private JButton selectBtn = new JButton("Select");
    private JButton cancelBtn = new JButton("Cancel");

    public SelectBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize(); //Linking to main window
    }

    /**
     * Initialise the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        } 

        setTitle("Select a book");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("ID : "));
        topPanel.add(idText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(selectBtn);
        bottomPanel.add(cancelBtn);

        selectBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw); //Creating select book window

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == selectBtn) {
            showBooks();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void showBooks() {
        try {
        	String id = idText.getText();
            int identity = Integer.parseInt(id);
            
            // create and execute the ShowBook Command
            Command showBook = new ShowBook(identity);
            showBook.execute(mw.getLibrary(), LocalDate.now());
            // refresh the view with the specific book within the table
            mw.displayViewPatron(identity);
            // hide (close) the SelectBookWindow
            this.setVisible(false);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Library getLibrary() {
    	return this.mw.getLibrary();
    }

}
