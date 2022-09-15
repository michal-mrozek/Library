package library.model;

public class Library {

    private static final int MAX_PUBLICATIONS = 2000;
    private Publication[] publications = new Publication[MAX_PUBLICATIONS];
    private int publicationsNumber;

    public void addBook(Book book){
        if (publicationsNumber < MAX_PUBLICATIONS){
            publications[publicationsNumber] = book;
            publicationsNumber++;
        } else {
            System.out.println("Cannot add new book, no more space for new books.");
        }
    }

    public void addMagazine(Magazine magazine){
        if (publicationsNumber < MAX_PUBLICATIONS){
            publications[publicationsNumber] = magazine;
            publicationsNumber++;
        } else {
            System.out.println("Cannot add new magazine, no more space for new books.");
        }
    }

    public void printBooks(){
        int countBooks = 0;

        for (int i = 0; i < publicationsNumber; i++) {
            if(publications[i] instanceof Book) {
                System.out.println(publications[i]);
                countBooks++;
            }
        }
        if(countBooks == 0) {
            System.out.println("There are no books in library");
        }
    }
    public void printMagazines(){
        int countMagazines = 0;

        for (int i = 0; i < publicationsNumber; i++) {
            if(publications[i] instanceof Magazine) {
                System.out.println(publications[i]);
                countMagazines++;
            }
        }
        if(countMagazines== 0) {
            System.out.println("There are no magazines in library");
        }
    }
}
