package library.general;

import library.entities.Press;

import java.util.ArrayList;

public interface GeneralInterface {

    ArrayList<Press> searchPressByParameter();

    void viewAllPress();

    void viewAllBooks();

    void viewAllMagazines();

    void sortByAuthor(ArrayList<Press> pressList);

    void sortByName(ArrayList<Press> pressList);

    void sortByLength(ArrayList<Press> pressList);

    void sortByDate(ArrayList<Press> pressList);

    void sortByType(ArrayList<Press> pressList);

    void sortByGenre(ArrayList<Press> pressList);

    void exit();
}
