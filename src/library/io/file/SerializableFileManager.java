package library.io.file;

import library.exeption.ExportDataException;
import library.exeption.ImportDataException;
import library.model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "Library.o";

    @Override
    public Library importData() {

        try (
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {

            return (Library) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new ImportDataException("File not found: " + FILE_NAME);
        } catch (IOException e) {
            throw new ImportDataException("Import error");
        } catch (ClassNotFoundException e) {
            throw new ImportDataException("Wrong data type in file: " + FILE_NAME);
        }

    }

    @Override
    public void exportData(Library library) {

        try (
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {

            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new ExportDataException("File no found: " + FILE_NAME);
        } catch (IOException e) {
            throw new ExportDataException("Export error");
        }

    }
}
