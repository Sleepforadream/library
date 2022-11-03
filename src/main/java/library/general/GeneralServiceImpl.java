package library.general;

import library.entities.Press;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static library.entities.PressParameters.*;
import static library.messages.ErrorMessages.printNotFoundInLibraryMessage;
import static library.messages.InstructionsMessages.printEnterTitleOfSearchMessage;

public class GeneralServiceImpl implements GeneralService {

    GeneralResources resources = new GeneralResources();

    Scanner sc = new Scanner(System.in);
    ArrayList<Press> allPress = resources.getListAllPress();

    public ArrayList<Press> searchPressByParameter() {
        String answer = sc.nextLine();
        printEnterTitleOfSearchMessage();
        ArrayList<Press> findBooks = new ArrayList<>();
        for (Press press : allPress) {
            if (resources.findInList(press, answer)) {
                findBooks.add(press);
            }
        }
        if (!findBooks.isEmpty()) {
            resources.printListWithNumbers(findBooks);
            return findBooks;
        } else {
            printNotFoundInLibraryMessage();
            return searchPressByParameter();
        }
    }

    public void viewAllPress() {
        resources.printListWithNumbers(allPress);
    }

    public void viewAllBooks() {
        ArrayList<Press> books = resources.getListByType("книга", allPress);
        resources.printListWithNumbers(books);
    }

    public void viewAllMagazines() {
        ArrayList<Press> magazines = resources.getListByType("журнал", allPress);
        resources.printListWithNumbers(magazines);
    }

    public void sortByValue(ArrayList<Press> pressList, Comparator<Press> comparator) {
        pressList.sort(comparator);
        resources.printListWithNumbers(pressList);
    }

    public void sortByAuthor(ArrayList<Press> pressList) {
        sortByValue(pressList, resources.comparatorByAuthor);
    }

    public void sortByName(ArrayList<Press> pressList) {
        sortByValue(pressList, resources.comparatorByName);
    }

    public void sortByLength(ArrayList<Press> pressList) {
        sortByValue(pressList, resources.comparatorByLength);
    }

    public void sortByDate(ArrayList<Press> pressList) {
        pressList.sort(resources.comparatorByDate);
        resources.printListWithParameterIfNotRepeat(pressList, dateOfCreate);
    }

    public void sortByType(ArrayList<Press> pressList) {
        pressList.sort(resources.comparatorByType);
        resources.printListWithParameterIfNotRepeat(pressList, type);
    }

    public void sortByGenre(ArrayList<Press> pressList) {
        pressList.sort(resources.comparatorByGenre);
        resources.printListWithParameterIfNotRepeat(pressList, genre);
    }

    public void exit() {
        System.out.println("До свидания!");
        System.exit(0);
    }
}
