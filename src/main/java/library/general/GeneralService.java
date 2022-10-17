package library.general;

import library.entities.Press;
import library.entities.PressParameters;
import library.messages.ActionMessages;
import library.messages.ErrorMessages;
import library.messages.InstructionsMessages;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.io.IOException;
import java.util.List;

import static library.dataBase.LibraryStorage.*;
import static library.entities.PressParameters.*;

public class GeneralService implements GeneralInterface {

    InstructionsMessages instructionsMessages = new InstructionsMessages();

    ActionMessages actionMessages = new ActionMessages();

    ErrorMessages errorMessages = new ErrorMessages();

    GeneralTools generalTools = new GeneralTools();

    ArrayList<Press> allPress = getListAllPress();

    Scanner sc = new Scanner(System.in);

    public ArrayList<Press> getListAllPress() {
        ArrayList<Press> presses = new ArrayList<>();
        File[] allFiles = libraryDirectory.listFiles();
        if (allFiles == null) {
            errorMessages.printNotFoundBooksOrMagazineMessage();
            System.exit(1);
        }
        for (int i = 0; i < Objects.requireNonNull(allFiles).length; i++) {
            String name = getTitleByFileName(allFiles[i].getAbsolutePath());
            String author = getAuthorByFileName(allFiles[i].toString());
            String dateOfCreate = getDateOfCreate(allFiles[i].toString());
            String type = getTypeOfProduct(allFiles[i].toString());
            String genre = getGenreOfProduct(allFiles[i].toString());
            int length = generalTools.getLengthCharsInFile(allFiles[i].toString());
            Press press = new Press(name, author, dateOfCreate, type, genre, length);
            presses.add(press);
        }
        return presses;
    }

    public ArrayList<String> getListActions() {
        ArrayList<String> actions = new ArrayList<>();
        actions.add(actionMessages.getToSearchOnByAuthorOrNameMessage());
        actions.add(actionMessages.getToViewAllLibraryMessage());
        actions.add(actionMessages.getToViewAllBooksMessage());
        actions.add(actionMessages.getToViewAllMagazinesMessage());
        actions.add(actionMessages.getToExitMessage());
        return actions;
    }

    public void getListActionsByNumbers() {
        ArrayList<String> actions = getListActions();
        for (int i = 0; i < actions.size(); i++) {
            System.out.println((i + 1) + ". " + actions.get(i));
        }
    }

    public boolean validateEnterActions(String answer) {
        List<String> rightSymbols = Arrays.asList("F", "E", "B", "M", "X");
        return rightSymbols.contains(answer.toUpperCase());
    }

    public String getAnswerForAction() {
        String answer = sc.nextLine();
        if (validateEnterActions(answer)) {
            return answer;
        } else {
            errorMessages.printNotCorrectAnswerMessage();
            return getAnswerForAction();
        }
    }

    public void choiceAction() {
        String answer = getAnswerForAction();
        if (answer.equalsIgnoreCase("f")) {
            searchPressByParameter();
        } else if (answer.equalsIgnoreCase("e")) {
            viewAllPress();
        } else if (answer.equalsIgnoreCase("b")) {
            viewAllBooks();
        } else if (answer.equalsIgnoreCase("m")) {
            viewAllMagazines();
        } else if (answer.equalsIgnoreCase("x")) {
            exit();
        }
    }

    public ArrayList<Press> searchPressByParameter() {
        instructionsMessages.printEnterTitleOfSearchMessage();
        String answer = sc.nextLine();
        ArrayList<Press> findBooks = new ArrayList<>();
        for (Press press : allPress) {
            if (generalTools.findInList(press, answer)) {
                findBooks.add(press);
            }
        }
        if (!findBooks.isEmpty()) {
            generalTools.printListWithNumbers(findBooks);
            return findBooks;
        } else {
            errorMessages.printNotFoundInLibraryMessage();
            return searchPressByParameter();
        }
    }

    public void viewAllPress() {
        generalTools.printListWithNumbers(allPress);
    }

    public void viewAllBooks() {
        ArrayList<Press> books = generalTools.getListByType("книга", allPress);
        generalTools.printListWithNumbers(books);
    }

    public void viewAllMagazines() {
        ArrayList<Press> magazines = generalTools.getListByType("журнал", allPress);
        generalTools.printListWithNumbers(magazines);
    }

    public void openFileByNumberInList(ArrayList<Press> allBooks) {
        instructionsMessages.printChoosePressWantOpenMessage();
        if (sc.hasNextInt()) {
            int number = sc.nextInt();
            Press name = allBooks.get(number - 1);
            File file = new File(String.valueOf(name));
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
            } catch (IOException errorReadException) {
                errorMessages.printNotOpenPressMessage();
            }
        }
    }

    public String getTitleByFileName(String fileName) {
        return generalTools.getNextWordAfterTextInFile(fileName, "Название:");
    }

    public String getAuthorByFileName(String fileName) {
        return generalTools.getNextWordAfterTextInFile(fileName, "Автор:");
    }

    public String getYearCreationOfBook(String fileName) {
        return generalTools.getPartOfStringAfterTextByRegex(fileName, "Дата издания:", "\\.", 2);
    }

    public String getMonthCreationOfBook(String fileName) {
        return generalTools.getPartOfStringAfterTextByRegex(fileName, "Дата издания:", "\\.", 1);
    }

    public String getDayCreationOfBook(String fileName) {
        return generalTools.getPartOfStringAfterTextByRegex(fileName, "Дата издания:", "\\.", 0);
    }

    public String getDateOfCreate(String fileName) {
        return generalTools.getNextWordAfterTextInFile(fileName, "Дата издания:");
    }

    public String getTypeOfProduct(String fileName) {
        return generalTools.getNextWordAfterTextInFile(fileName, "Тип:");
    }

    public String getGenreOfProduct(String fileName) {
        return generalTools.getNextWordAfterTextInFile(fileName, "Жанр:");
    }

    public void sortList(ArrayList<Press> allBooks) {
        actionMessages.getToSortByTitle();
        actionMessages.getToSortByAuthor();
        actionMessages.getToSortByDate();
        actionMessages.getToSortByType();
        actionMessages.getToSortByGenre();
        actionMessages.getToSortByLength();
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("L")) {
            sortByLength(allBooks);
        } else if (answer.equalsIgnoreCase("A")) {
            sortByAuthor(allBooks);
        } else if (answer.equalsIgnoreCase("N")) {
            sortByName(allBooks);
        } else if (answer.equalsIgnoreCase("D")) {
            sortByDate(allBooks);
        } else if (answer.equalsIgnoreCase("T")) {
            sortByType(allBooks);
        } else if (answer.equalsIgnoreCase("G")) {
            sortByGenre(allBooks);
        } else {
            errorMessages.printNotCorrectAnswerMessage();
            sortList(allBooks);
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

    public void printListByNumbers(ArrayList<Press> list) {
        int count = 0;
        for (Press listBook : list) {
            count++;
            String bookWithNumber = (count + "." + " " + listBook.toString());
            System.out.println(bookWithNumber);
        }
    }

    public void sortByValue(ArrayList<Press> pressList, Comparator<Press> comparator) {
        pressList.sort(comparator);
        printListByNumbers(pressList);
        openFileByNumberInList(pressList);
        sortList(pressList);
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
                parameterPrint = getYearCreationOfBook(String.valueOf(pressList.get(i))) + " год:";
            } else {
                errorMessages.printNotCorrectAnswerMessage();
            }
            if (!parameterPrint.equals(defaultString)) {
                System.out.println(parameterPrint);
            }
            System.out.println((i++) + "." + " " + pressList.get(i).toString());
            defaultString = parameterPrint;
        }
    }

    public void exit() {
        System.out.println("До свидания!");
        System.exit(0);
    }
}