package library.app;

import library.exeption.NoSuchOptionException;

public enum Option {
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
