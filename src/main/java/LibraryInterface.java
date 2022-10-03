import java.io.IOException;
import java.util.ArrayList;

public interface LibraryInterface {

    void toGreet ();

    void searchForString();

    void viewAll ();

    void viewAllBooks ();

    void viewAllMagazines ();

    void sortByAuthor(ArrayList<String> allBooks);

    void sortByName(ArrayList<String> allBooks);

    void sortByLength(ArrayList<String> allBooks);

    void sortByDate(ArrayList<String> allBooks);
    void sortByType(ArrayList<String> allBooks);
    void sortByGenre(ArrayList<String> allBooks);

}
