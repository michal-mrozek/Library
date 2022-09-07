package library.app;

import library.io.DataReader;
import library.model.Book;
import library.model.Library;

public class LibraryControl {

    private final int exit = 0;
    private final int addBook = 1;
    private final int printBook = 2;

    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop(){
        int option;
        do {
            printOptions();
            option= dataReader.getInt();
            switch (option){
                case addBook:
                    addBook();
                    break;
                case printBook:
                    printBook();
                    break;
                case exit:
                    exit();
                    break;
                default:
                    System.out.println("Choose correct option.");
            }
        }while (option !=exit);
    }

    private void exit() {
        System.out.println("Program finished");
        dataReader.close();
    }

    private void printBook() {
        library.printBooks();
    }

    private void addBook() {
        Book book = dataReader.readAndCreateBook();
        library.addBook(book);
    }

    private void printOptions() {
        System.out.println("Choose option:");
        System.out.println("0 - exit");
        System.out.println("1 - add new book");
        System.out.println("2 - print all books");
    }


}
