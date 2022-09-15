package library.app;

import library.io.DataReader;
import library.model.Book;
import library.model.Library;
import library.model.Magazine;

public class LibraryControl {



    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop(){
        Option option;
        do {
            printOptions();
            option= Option.createFromInt(dataReader.getInt());
            switch (option){
                case EXIT:
                    exit();
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;

                default:
                    System.out.println("Choose correct option.");
            }
        }while (option != Option.EXIT);
    }

    private void printMagazines() {
        library.printMagazines();
    }

    private void addMagazine() {
        Magazine magazine = dataReader.readAndCreateMagazine();
        library.addMagazine(magazine);
    }

    private void exit() {
        System.out.println("Program finished");
        dataReader.close();
    }

    private void printBooks() {
        library.printBooks();
    }

    private void addBook() {
        Book book = dataReader.readAndCreateBook();
        library.addBook(book);
    }

    private void printOptions() {
        System.out.println("Choose option:");
        for (Option option : Option.values()) {
            System.out.println(option);
        }
    }


}
