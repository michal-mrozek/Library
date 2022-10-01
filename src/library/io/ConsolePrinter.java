package library.io;

import library.model.Book;
import library.model.LibraryUser;
import library.model.Magazine;
import library.model.Publication;

import java.util.Collection;

public class ConsolePrinter {


    public void printBooks(Collection<Publication> publications){
        int countBooks = 0;

        for (Publication publication : publications)
        {
            if(publication instanceof Book) {
                printLine(publication.toString());
                countBooks++;
            }
        }
        if(countBooks == 0) {
            printLine("There are no books in library");
        }
    }
    public void printMagazines(Collection<Publication> publications){
        int countMagazines = 0;

        for (Publication publication : publications)
            if(publication instanceof Magazine) {
                printLine(publication.toString());
                countMagazines++;
            }

        if(countMagazines== 0) {
            printLine("There are no magazines in library");
        }
    }

    public void printUsers(Collection<LibraryUser> users){
        for (LibraryUser user : users) {
            printLine(user.toString());
        }
    }

    public void printLine(String string){
        System.out.println(string);
    }
}
