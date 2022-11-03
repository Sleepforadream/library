package library.general;

import library.entities.Press;
import library.entities.PressParameters;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import static library.dataBase.LibraryStorage.libraryDirectory;
import static library.dataBase.LoginsStorage.*;
import static library.entities.PressParameters.*;
import static library.messages.ActionMessages.*;
import static library.messages.ActionMessages.getToSortByLength;
import static library.messages.ErrorMessages.*;

public class GeneralResources {

    ArrayList<Press> allPress = getListAllPress();

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
            int length = getLengthCharsInFile(String.valueOf(allFiles[i]));
            Press press = new Press(name, author, dateOfCreate, type, genre, length, absolutePath);
            presses.add(press);
        }
        return presses;
    }

    public ArrayList<Press> getAllBooks() {
        return getListByType("книга", allPress);
    }

    public ArrayList<Press> getAllMagazines() {
        return getListByType("журнал", allPress);
    }

    public String getTitleByFileName(String fileName) {
        return getNextWordAfterTextInFile(fileName, "Название:");
    }

    public String getAuthorByFileName(String fileName) {
        return getNextWordAfterTextInFile(fileName, "Автор:");
    }

    public String getYearCreationOfBook(String fileName) {
        return getPartOfStringAfterTextByRegex(fileName, "Дата издания:", "\\.", 2);
    }

    public String getDateOfCreate(String fileName) {
        return getNextWordAfterTextInFile(fileName, "Дата издания:");
    }

    public String getTypeOfProduct(String fileName) {
        return getNextWordAfterTextInFile(fileName, "Тип:");
    }

    public String getGenreOfProduct(String fileName) {
        return getNextWordAfterTextInFile(fileName, "Жанр:");
    }

    public List<String> getListWithUpperCase(List<String> list) {
        List<String> uppers = new ArrayList<>();
        for (String string : list) {
            String upper = string.toUpperCase();
            uppers.add(upper);
        }
        return uppers;
    }

    public void printListWithNumbers(ArrayList<Press> list) {
        int count = 0;
        for (Press listBook : list) {
            count++;
            String bookWithNumber = (count + "." + " " + listBook);
            System.out.println(bookWithNumber);
        }
    }

    public ArrayList<Press> getListByType(String type, ArrayList<Press> allPress) {
        ArrayList<Press> allBooks = new ArrayList<>();
        for (Press press : allPress) {
            if (press.getType().equalsIgnoreCase(type)) {
                allBooks.add(press);
            }
        }
        return allBooks;
    }

    public Integer getLengthCharsInFile(String fileName) {
        Scanner sc = getScannerFromNameFile(fileName);
        int count = 0;
        sc.useDelimiter("");
        while (sc.hasNext()) {
            sc.next();
            count++;
        }
        return count;
    }

    public Scanner getScannerFromNameFile(String fileName) {
        File file = new File(fileName);
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            return new Scanner(fis);
        } catch (FileNotFoundException e) {
            printNotGetAccessToFileMessage();
            return null;
        }
    }

    public String getNextWordAfterTextInFile(String fileName, String text) {
        Scanner sc = getScannerFromNameFile(fileName);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains(text)) {
                String[] words = line.split(": ");
                return words[words.length - 1];
            }
        }
        return "";
    }

    public String getPartOfStringAfterTextByRegex(String fileName, String after, String regex, int index) {
        String dateOfCreation = getNextWordAfterTextInFile(fileName, after);
        String[] numbersInDate = dateOfCreation.split(regex);
        return numbersInDate[index];
    }

    public boolean findInList(Press press, String search) {
        List<String> answers = Arrays.asList(press.getTitle(), press.getAuthor(), press.getDateOfCreate(), press.getType(), press.getGenre());
        List<String> upperAnswers = getListWithUpperCase(answers);
        String stringWithAnswers = upperAnswers.toString();
        return stringWithAnswers.contains(search.toUpperCase());
    }

    public void createFolder() {
        try {
            if (!loginsDirectory.exists()) {
                Files.createDirectories(Paths.get(pathLoginsDirectory));
            }
            if (!loginsFile.exists()) {
                Files.createFile(Paths.get(pathLoginsFile));
            }
        } catch (IOException folderException) {
            printNotCreateFolderExceptionMessage();
        }
    }

    public void printListByNumbers(ArrayList<String> list) {
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    public boolean validateEnter(String answer, List<String> rightSymbols) {
        return rightSymbols.contains(answer.toUpperCase());
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

    public ArrayList<String> getListActions() {
        ArrayList<String> actions = new ArrayList<>();
        actions.add(getToSearchOnByAuthorOrNameMessage());
        actions.add(getToViewAllLibraryMessage());
        actions.add(getToViewAllBooksMessage());
        actions.add(getToViewAllMagazinesMessage());
        actions.add(getToExitMessage());
        return actions;
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

    public ArrayList <Press> getBackOption(){
        ArrayList<Press> q = new ArrayList<>();
        Press back = new Press("q", "q", "q", "q", "q", 0, "q");
        q.add(back);
        return q;
    }

    public ArrayList <Press> getNotValidOption(){
        ArrayList<Press> n = new ArrayList<>();
        Press back = new Press("n", "n", "n", "n", "n", 0, "n");
        n.add(back);
        return n;
    }

    public ArrayList <Press> getExitOption(){
        ArrayList<Press> e = new ArrayList<>();
        Press back = new Press("e", "e", "e", "e", "e", 0, "e");
        e.add(back);
        return e;
    }

}