package library.io.file;

import library.exeption.NoSuchFileTypeException;
import library.io.ConsolePrinter;
import library.io.DataReader;

public class FileManagerBuilder {
    private ConsolePrinter printer;
    private DataReader reader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    public FileManager build() {
        printer.printLine("Choice data type:");
        FileType fileType = getFileType();
        switch (fileType) {

            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeException("Wrong data type");
        }
    }

    private FileType getFileType() {
        boolean typeOk = false;
        FileType result = null;
        do {
            printTypes();
            String type = reader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("Wrong input, try again");
            }
        } while (!typeOk);
        return result;
    }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            printer.printLine(value.name());
        }
    }
}
