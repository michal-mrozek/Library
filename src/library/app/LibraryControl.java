package library.app;

import library.exeption.ExportDataException;
import library.exeption.ImportDataException;
import library.exeption.InvalidDataException;
import library.exeption.NoSuchOptionException;
import library.io.ConsolePrinter;
import library.io.DataReader;
import library.io.file.FileManager;
import library.io.file.FileManagerBuilder;
import library.model.Book;
import library.model.Library;
import library.model.Magazine;
import library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {

private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private Library library;
    private FileManager fileManager;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Import success");
        } catch (ImportDataException | InvalidDataException e){
            printer.printLine(e.getMessage());
            printer.printLine("New database initialised");
            library = new Library();
        }
    }

    void controlLoop() {
        Option option;
        do {
            printOptions();
            option = getOption();
            switch (option) {
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
                    printer.printLine("Choose correct option.");
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
printer.printLine(e.getMessage());
            } catch (InputMismatchException e) {
                printer.printLine("Wrong input!");
            }
        }
        return option;
    }

    private void printMagazines() {
        Publication[] publications = library.getPublications();
        printer.printMagazines(publications);
    }



    private void exit() {
        try {
            fileManager.exportData(library);
            printer.printLine("Export success");
        } catch (ExportDataException e){
            printer.printLine(e.getMessage());
        }
        printer.printLine("Program finished");
        dataReader.close();
    }

    private void printBooks() {
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch(InputMismatchException e){
            printer.printLine("Cannot create book, wrong input.");
        } catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Cannot add more books");
        }
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        }catch(InputMismatchException e){
            printer.printLine("Cannot create magazine, wrong input.");
        } catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Cannot add more magazines");
        }
    }
    private void printOptions() {
        printer.printLine("Choose option:");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private enum Option {
        EXIT(0, "exit"),
        ADD_BOOK(1, "add book"),
        ADD_MAGAZINE(2, "add magazine"),
        PRINT_BOOKS(3, "print all books"),
        PRINT_MAGAZINES(4, "print all magazines");

        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("No option " + option);
            }
        }
    }
}
