package library.general;

import library.entities.Press;
import library.messages.ErrorMessages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GeneralTools {

    ErrorMessages errorMessages = new ErrorMessages();

    public ArrayList<String> fileSearch(File directory, String search) {
        File[] listBooks = Objects.requireNonNull(directory.listFiles());
        ArrayList<String> findBooks = new ArrayList<>();
        for (File listBook : listBooks) {
            String[] name = listBook.toString().split("\\\\");
            if (name[name.length - 1].toLowerCase().contains(search.toLowerCase())) {
                String bookName = name[name.length - 1];
                findBooks.add(bookName);
            }
        }
        if (findBooks.isEmpty()) {
            errorMessages.getNotFoundInLibraryMessage();
            return null;
        }
        return findBooks;
    }

    public List<String> getListWithUpperCase(List<String> list) {
        List<String> uppers = new ArrayList<>();
        for (String string : list){
            String upper = string.toUpperCase();
            uppers.add(upper);
        }
        return uppers;
    }

    public void printListWithNumbers(ArrayList<Press> list) {
        int count = 0;
        for (Press listBook : list) {
            count++;
            String bookWithNumber = (count + "." + " " + listBook.toString());
            System.out.println(bookWithNumber);
        }
    }

    public ArrayList<Press> getListByType(String type,ArrayList<Press> allPress) {
        ArrayList<Press> allBooks = new ArrayList<>();
        for (Press press : allPress) {
            if(press.getType().equalsIgnoreCase(type)){
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
            errorMessages.getNotGetAccessToFileMessage();
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

    public String getPartOfStringAfterTextByRegex(String fileName,String after, String regex, int index) {
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
}
