package library.io.file;

import library.exeption.ExportDataException;
import library.exeption.ImportDataException;
import library.exeption.InvalidDataException;
import library.model.*;

import java.io.*;
import java.util.Collection;

public class CsvFileManager implements FileManager {
    private static final String FILE_NAME = "Library.csv";
    private static final String USERS_FILE_NAME = "Library_users.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        importPublications(library);
        importUsers(library);

        return library;

    }

    private void importUsers(Library library) {
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(USERS_FILE_NAME))
        ) {
            bufferedReader.lines()
                    .map(this::createUserFromString)
                    .forEach(library::addUser);
        } catch (IOException e) {
            throw new ImportDataException("Import error");
        }
    }

    private LibraryUser createUserFromString(String line) {
        String[] split = line.split(";");
        String firstName = split[0];
        String lastName = split[1];
        String nin = split[2];
        return new LibraryUser(firstName, lastName, nin);

    }

    private void importPublications(Library library) {
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))
        ) {
            bufferedReader.lines()
                    .map(this::createObjectFromString)
                    .forEach(library::addPublication);
        } catch (IOException e) {
            throw new ImportDataException("Import error");
        }
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

        exportPublications(library);
        exportUsers(library);


    }

    private void exportUsers(Library library) {
        Collection<LibraryUser> users = library.getUsers().values();
        exportToCsv(users, USERS_FILE_NAME);
    }

    private void exportPublications(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        exportToCsv(publications, FILE_NAME);
    }


    private <T extends CsvConvertible> void exportToCsv(Collection<T> collection, String fileName) {
        try (
                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            for (T element : collection) {
                bufferedWriter.write(element.toCsv());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new ExportDataException("Export error ");
        }
    }
}
