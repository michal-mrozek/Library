package library.model;

public class Library {

    private final int maxBooks = 1000;
    private Book[] books = new Book[maxBooks];
    private int booksNumber = 0;

    public void addBook(Book book){
        if (booksNumber < maxBooks){
            books[booksNumber] = book;
            booksNumber++;
        } else {
            System.out.println("Cannot add new book, no more space for new books.");
        }
    }

    public void printBooks(){
        if(booksNumber == 0) {
            System.out.println("There are no books in library");
        }
        for (int i = 0; i < booksNumber; i++) {
            books[i].printInfo();
        }
    }
}
