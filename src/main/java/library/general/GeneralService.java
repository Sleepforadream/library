package library.general;

import library.entities.Press;
import library.entities.PressParameters;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.io.IOException;
import java.util.List;

import static library.dataBase.LibraryStorage.*;
import static library.entities.PressParameters.*;
import static library.messages.ActionMessages.*;
import static library.messages.ErrorMessages.*;
import static library.messages.InstructionsMessages.*;

public class GeneralService implements GeneralInterface {

    GeneralResources generalResources = new GeneralResources();
    ArrayList<Press> allPress = getListAllPress();
    Scanner sc = new Scanner(System.in);

    public ArrayList<Press> getListAllPress() {
        ArrayList<Press> presses = new ArrayList<>();
        File[] allFiles = libraryDirectory.listFiles();
        if (allFiles == null) {
            printNotFoundBooksOrMagazineMessage();
            System.exit(1);
        }
        for (int i = 0; i < Objects.requireNonNull(allFiles).length; i++) {
            String name = getTitleByFileName(String.valueOf(allFiles[i]));
            String author = getAuthorByFileName(String.valueOf(allFiles[i]));
            String dateOfCreate = getDateOfCreate(String.valueOf(allFiles[i]));
            String type = getTypeOfProduct(String.valueOf(allFiles[i]));
            String genre = getGenreOfProduct(String.valueOf(allFiles[i]));
            String absolutePath = String.valueOf(allFiles[i]);
            int length = generalResources.getLengthCharsInFile(String.valueOf(allFiles[i]));
            Press press = new Press(name, author, dateOfCreate, type, genre, length, absolutePath);
            presses.add(press);
        }
        return presses;
    }

    public void viewAllPress() {
        generalResources.printListWithNumbers(allPress);
    }

    public void viewAllBooks() {
        ArrayList<Press> books = generalResources.getListByType("книга", allPress);
        generalResources.printListWithNumbers(books);
    }

    public ArrayList<Press> getAllBooks() {
        return generalResources.getListByType("книга", allPress);
    }

    public void viewAllMagazines() {
        ArrayList<Press> magazines = generalResources.getListByType("журнал", allPress);
        generalResources.printListWithNumbers(magazines);
    }

    public ArrayList<Press> getAllMagazines() {
        return generalResources.getListByType("журнал", allPress);
    }

    public String getTitleByFileName(String fileName) {
        return generalResources.getNextWordAfterTextInFile(fileName, "Название:");
    }

    public String getAuthorByFileName(String fileName) {
        return generalResources.getNextWordAfterTextInFile(fileName, "Автор:");
    }

    public String getYearCreationOfBook(String fileName) {
        return generalResources.getPartOfStringAfterTextByRegex(fileName, "Дата издания:", "\\.", 2);
    }

    public String getMonthCreationOfBook(String fileName) {
        return generalResources.getPartOfStringAfterTextByRegex(fileName, "Дата издания:", "\\.", 1);
    }

    public String getDayCreationOfBook(String fileName) {
        return generalResources.getPartOfStringAfterTextByRegex(fileName, "Дата издания:", "\\.", 0);
    }

    public String getDateOfCreate(String fileName) {
        return generalResources.getNextWordAfterTextInFile(fileName, "Дата издания:");
    }

    public String getTypeOfProduct(String fileName) {
        return generalResources.getNextWordAfterTextInFile(fileName, "Тип:");
    }

    public String getGenreOfProduct(String fileName) {
        return generalResources.getNextWordAfterTextInFile(fileName, "Жанр:");
    }

    public ArrayList<String> getListActions() {
        ArrayList<String> actions = new ArrayList<>();
        actions.add(getToSearchOnByAuthorOrNameMessage());
        actions.add(getToViewAllLibraryMessage());
        actions.add(getToViewAllBooksMessage());
        actions.add(getToViewAllMagazinesMessage());
        actions.add(getToExitMessage());
        return actions;
    }

    public void printListActionsByNumbers() {
        generalResources.printListByNumbers(getListActions());
    }

    public List<String> enterActionsKeys() {
        return Arrays.asList("F", "E", "B", "M", "X");
    }

    public ArrayList<Press> choiceAction() {
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase(enterActionsKeys().get(0))) {
            return searchPressByParameter();
        } else if (answer.equalsIgnoreCase(enterActionsKeys().get(1))) {
            viewAllPress();
            return getListAllPress();
        } else if (answer.equalsIgnoreCase(enterActionsKeys().get(2))) {
            viewAllBooks();
            return getAllBooks();
        } else if (answer.equalsIgnoreCase(enterActionsKeys().get(3))) {
            viewAllMagazines();
            return getAllMagazines();
        } else if (answer.equalsIgnoreCase(enterActionsKeys().get(4))) {
            return null;
        }
        return null;
    }

    public ArrayList<Press> getPressListChooseAction() {
        printListActionsByNumbers();
        return choiceAction();
    }

    public ArrayList<Press> searchPressByParameter() {
        String answer = sc.nextLine();
        printEnterTitleOfSearchMessage();
        ArrayList<Press> findBooks = new ArrayList<>();
        for (Press press : allPress) {
            if (generalResources.findInList(press, answer)) {
                findBooks.add(press);
            }
        }
        if (!findBooks.isEmpty()) {
            generalResources.printListWithNumbers(findBooks);
            return findBooks;
        } else {
            printNotFoundInLibraryMessage();
            return searchPressByParameter();
        }
    }

    public void openFileByNumberInList(ArrayList<Press> allBooks, Integer answer) {
        if (answer > allBooks.size()) {
            printNoThisNumberInListMessage();
        } else {
            Press press = allBooks.get(answer - 1);
            String name = press.getAbsolutePath();
            File file = new File(name);
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
            } catch (IOException errorReadException) {
                printNotOpenPressMessage();
            }
        }
    }

    public ArrayList<String> getListSort() {
        ArrayList<String> sorts = new ArrayList<>();
        sorts.add(getToSortByTitle());
        sorts.add(getToSortByAuthor());
        sorts.add(getToSortByDate());
        sorts.add(getToSortByType());
        sorts.add(getToSortByGenre());
        sorts.add(getToSortByLength());
        return sorts;
    }

    public void printListSorts() {
        List<String> list = getListSort();
        for (String s : list) {
            System.out.println(s);
        }
    }

    public List<String> enterSortsKeys() {
        return Arrays.asList("L", "A", "N", "D", "T", "G");
    }

    public ArrayList<Press> choiceSortAction(ArrayList<Press> listPress, String answer) {
        if (answer.equalsIgnoreCase(enterSortsKeys().get(0))) {
            sortByLength(listPress);
            return listPress;
        } else if (answer.equalsIgnoreCase(enterSortsKeys().get(1))) {
            sortByAuthor(listPress);
            return listPress;
        } else if (answer.equalsIgnoreCase(enterSortsKeys().get(2))) {
            sortByName(listPress);
            return listPress;
        } else if (answer.equalsIgnoreCase(enterSortsKeys().get(3))) {
            sortByDate(listPress);
            return listPress;
        } else if (answer.equalsIgnoreCase(enterSortsKeys().get(4))) {
            sortByType(listPress);
            return listPress;
        } else if (answer.equalsIgnoreCase(enterSortsKeys().get(5))) {
            sortByGenre(listPress);
            return listPress;
        } else {
            return null;
        }
    }

    Comparator<Press> comparatorByName = Comparator.comparing(Press::getTitle);
    Comparator<Press> comparatorByAuthor = Comparator.comparing(Press::getAuthor);
    Comparator<Press> comparatorByType = Comparator.comparing(Press::getType);
    Comparator<Press> comparatorByGenre = Comparator.comparing(Press::getGenre);
    Comparator<Press> comparatorByLength = Comparator.comparing(Press::getLength);
    Comparator<Press> comparatorByDate = (o1, o2) -> {
        String[] o1DayMonthYear = o1.getDateOfCreate().split("\\.");
        String o1Year = o1DayMonthYear[2];
        String o1Month = o1DayMonthYear[1];
        String o1Day = o1DayMonthYear[0];
        String[] o2DayMonthYear = o2.getDateOfCreate().split("\\.");
        String o2Year = o2DayMonthYear[2];
        String o2Month = o2DayMonthYear[1];
        String o2Day = o2DayMonthYear[0];
        if (!o1Year.equals(o2Year)) {
            return o1Year.compareTo(o2Year);
        } else {
            if (o1Month.equals(o2Month)) {
                return o1Day.compareTo(o2Day);
            } else {
                return o1Month.compareTo(o2Month);
            }
        }
    };

    public void sortByValue(ArrayList<Press> pressList, Comparator<Press> comparator) {
        pressList.sort(comparator);
        generalResources.printListWithNumbers(pressList);
    }

    public void sortByAuthor(ArrayList<Press> pressList) {
        sortByValue(pressList, comparatorByAuthor);
    }

    public void sortByName(ArrayList<Press> pressList) {
        sortByValue(pressList, comparatorByName);
    }

    public void sortByLength(ArrayList<Press> pressList) {
        sortByValue(pressList, comparatorByLength);
    }

    public void sortByDate(ArrayList<Press> pressList) {
        pressList.sort(comparatorByDate);
        printListWithParameterIfNotRepeat(pressList, dateOfCreate);
    }

    public void sortByType(ArrayList<Press> pressList) {
        pressList.sort(comparatorByType);
        printListWithParameterIfNotRepeat(pressList, type);
    }

    public void sortByGenre(ArrayList<Press> pressList) {
        pressList.sort(comparatorByGenre);
        printListWithParameterIfNotRepeat(pressList, genre);
    }

    public void printListWithParameterIfNotRepeat(ArrayList<Press> pressList, PressParameters parameter) {
        String defaultString = "";
        String parameterPrint = "";
        for (int i = 0; i < pressList.size(); i++) {
            if (parameter == type) {
                parameterPrint = parameter.toString(parameter) + " - " + pressList.get(i).getType() + ":";
            } else if (parameter == genre) {
                parameterPrint = parameter.toString(parameter) + " - " + pressList.get(i).getGenre() + ":";
            } else if (parameter == dateOfCreate) {
                parameterPrint = getYearCreationOfBook(pressList.get(i).getAbsolutePath()) + " год:";
            } else {
                printNotCorrectAnswerMessage();
            }
            if (!parameterPrint.equals(defaultString)) {
                System.out.println(parameterPrint);
            }
            System.out.println((i + 1) + "." + " " + pressList.get(i).toString());
            defaultString = parameterPrint;
        }
    }

    public ArrayList<Press> actionsWithList(ArrayList<Press> press) {
        printChoosePressWantOpenMessage();
        System.out.println();
        printListSorts();
        printToReturnInMainMenu();
        String answer = sc.nextLine();
        if (generalResources.validateEnter(answer, enterSortsKeys())) {
            return choiceSortAction(press, answer);
        } else if (answer.matches("\\d+")) {
            openFileByNumberInList(press, (Integer.valueOf(answer)));
            generalResources.printListWithNumbers(press);
            return press;
        } else if (answer.equalsIgnoreCase("q")) {
            ArrayList<Press> q = new ArrayList<>();
            Press back = new Press("q","q","q","q","q",0,"q");
            q.add(back);
            return q;
        }
        return null;
    }

    public void exit() {
        System.out.println("До свидания!");
        System.exit(0);
    }

    public boolean runLibrary() {
        ArrayList<Press> pressFromAction = getPressListChooseAction();
        while (pressFromAction != null) {
            pressFromAction = actionsWithList(pressFromAction);
            if (Objects.equals(pressFromAction.get(0).getTitle(), "q")) {
                return runLibrary();
            }
        }
        exit();
        return false;
    }
}