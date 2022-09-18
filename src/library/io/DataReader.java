package library.io;

import library.model.Book;
import library.model.Magazine;

import java.util.Scanner;

public class DataReader {
    ConsolePrinter printer;
    private Scanner sc = new Scanner(System.in);

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public Book readAndCreateBook() {
        printer.printLine("Title:");
        String title = sc.nextLine();
        printer.printLine("Author:");
        String author = sc.nextLine();
        printer.printLine("Publisher:");
        String publisher = sc.nextLine();
        printer.printLine("ISBN:");
        String isbn = sc.nextLine();
        printer.printLine("Release Date [year]:");
        int releaseDate = sc.nextInt();
        printer.printLine("Pages:");

        int pages = getInt();

        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine() {
        printer.printLine("Title:");
        String title = sc.nextLine();
        printer.printLine("Publisher:");
        String publisher = sc.nextLine();
        printer.printLine("Language:");
        String language = sc.nextLine();
        printer.printLine("Release Date [year]:");
        int year = getInt();
        printer.printLine("Month:");
        int month = getInt();
        printer.printLine("day");
        int day = getInt();


        return new Magazine(title, publisher, language, year, month, day);
    }

    public int getInt() {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }

    public void close() {
        sc.close();
    }
}
