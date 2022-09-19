package library.io.file;

import library.exeption.ExportDataException;
import library.exeption.ImportDataException;
import library.exeption.InvalidDataException;
import library.model.Book;
import library.model.Library;
import library.model.Magazine;
import library.model.Publication;

import java.io.*;
import java.util.Scanner;

public class CsvFileManager implements FileManager {
    private static final String FILE_NAME = "Library.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        try (
                Scanner fileReader = new Scanner(new File(FILE_NAME));
        ) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Publication publication = createObjectFromString(line);
                library.addPublication(publication);
            }
        } catch (FileNotFoundException e) {
            throw new ImportDataException("Import error");
        }
        return library;

    }

    private Publication createObjectFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if (Book.TYPE.equals(type)) {
            return createBook(split);
        } else if (Magazine.TYPE.equals(type)) {
            return createMagazine(split);
        }
        throw new InvalidDataException("Unknow publication's type " + type);
    }

    private Magazine createMagazine(String[] split) {
        String title = split[1];
        String publisher = split[2];
        int year = Integer.valueOf(split[3]);
        int day = Integer.valueOf(split[4]);
        int month = Integer.valueOf(split[5]);
        String language = split[6];
        return new Magazine(title, publisher, language, year, month, day);
    }

    private Book createBook(String[] split) {
        String title = split[1];
        String publisher = split[2];
        int year = Integer.valueOf(split[3]);
        String author = split[4];
        int pages = Integer.valueOf(split[5]);
        String isbn = split[6];

        return new Book(title, author, year, pages, publisher, isbn);
    }

    @Override
    public void exportData(Library library) {
        Publication[] publications = library.getPublications();
        try (
                FileWriter fileWriter = new FileWriter(FILE_NAME);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            for (Publication publication : publications) {
                bufferedWriter.write(publication.toCsv());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new ExportDataException("Export error " + FILE_NAME);
        }
    }
}
