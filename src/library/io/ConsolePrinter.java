package library.io;

import library.model.Book;
import library.model.Magazine;
import library.model.Publication;

public class ConsolePrinter {


    public void printBooks(Publication[] publications){
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
    public void printMagazines(Publication[] publications){
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
    public void printLine(String string){
        System.out.println(string);
    }
}
