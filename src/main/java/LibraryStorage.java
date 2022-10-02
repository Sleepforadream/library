import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;

public class LibraryStorage implements LibraryInterface {

    String property = System.getProperty("user.home");
    String[] paths = property.split("\\\\");
    String userDir = paths[paths.length - 1];
    String pathDirectory = "C:\\Users\\" + userDir + "\\Desktop\\Library\\LibraryStorage";
    File directory = new File(pathDirectory);

    @Override
    public void toGreet() {
        System.out.println();
        System.out.println("Какое действие вы хотите совершить?");
        System.out.println("1. Поиск книги или журнала по автору или названию");
        System.out.println("2. Показать всю библиотеку");
        System.out.println("3. Показать все книги");
        System.out.println("4. Показать все журналы");
        System.out.println("5. Выйти");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        if (Objects.equals(answer, "1") || Objects.equals(answer, "1.")) {
            try {
                searchForString();
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
                System.err.println("Не получилось открыть книгу");
            }
        }
        if (Objects.equals(answer, "2") || Objects.equals(answer, "2.")) {
            System.out.println();
            viewAll();
        }
        if (Objects.equals(answer, "3") || Objects.equals(answer, "3.")) {
            System.out.println();
            viewAllBooks();
        }
        if (Objects.equals(answer, "4") || Objects.equals(answer, "4.")) {
            System.out.println();
            viewAllMagazines();
        }
        if (Objects.equals(answer, "5") || Objects.equals(answer, "5.")) {
            System.out.println("До свидания!");
        }
    }

    @Override
    public void searchForString() {
        System.out.println();
        System.out.println("Введите название книги, журнала или имя автора");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        ArrayList<String> findBooks = new ArrayList<>();
        int count = 0;
        File[] listBooks = Objects.requireNonNull(directory.listFiles());
        for (File listBook : listBooks) {
            String[] name = listBook.toString().split("\\\\");
            if (name[name.length - 1].toLowerCase().contains(answer.toLowerCase())) {
                String bookName = name[name.length - 1];
                findBooks.add(bookName);
            }
        }
        if (findBooks.isEmpty()) {
            System.out.println();
            System.err.println("Таких данных не было найдено в библиотеке");
            searchForString();
        } else {
            System.out.println();
            System.out.println("Вот всё что удалось найти по Вашему запросу:");
            for (String iteration : findBooks) {
                count++;
                System.out.println(count + "." + " " + findBooks.get(count - 1));
            }
            openFile(findBooks);
        }
    }

    @Override
    public void viewAll() {
        ArrayList<String> allBooksAndMagazines = new ArrayList<>();
        int count = 0;
        File[] listBooks = Objects.requireNonNull(directory.listFiles());
        for (File listBook : listBooks) {
            String[] name = listBook.toString().split("\\\\");
            count++;
            String bookName = name[name.length - 1];
            allBooksAndMagazines.add(bookName);
            System.out.println((count + "." + " " + bookName).replace(".txt", ""));
        }
        openFile(allBooksAndMagazines);
    }

    @Override
    public void viewAllBooks() {
        ArrayList<String> allBooks = new ArrayList<>();
        int count = 0;
        File[] listBooks = Objects.requireNonNull(directory.listFiles());
        for (File listBook : listBooks) {
            try (FileInputStream fis = new FileInputStream(listBook)) {
                Scanner sc = new Scanner(fis);
                while (sc.hasNextLine()) {
                    if (sc.nextLine().contains("Тип: книга")) {
                        String[] name = listBook.toString().split("\\\\");
                        count++;
                        String bookName = name[name.length - 1];
                        allBooks.add(bookName);
                        System.out.println((count + "." + " " + bookName).replace(".txt", ""));
                        break;
                    }
                }
            } catch (IOException foundException) {
                System.err.println(foundException.getMessage());
                System.err.println("Не получилось проверить тип продукта. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
            }
        }
        openFile(allBooks);
    }

        @Override
        public void viewAllMagazines() {
            ArrayList<String> allBooks = new ArrayList<>();
            int count = 0;
            File[] listBooks = Objects.requireNonNull(directory.listFiles());
            for (File listBook : listBooks) {
                try (FileInputStream fis = new FileInputStream(listBook)) {
                    Scanner sc = new Scanner(fis);
                    while (sc.hasNextLine()) {
                        if (sc.nextLine().contains("Тип: журнал")) {
                            String[] name = listBook.toString().split("\\\\");
                            count++;
                            String bookName = name[name.length - 1];
                            allBooks.add(bookName);
                            System.out.println((count + "." + " " + bookName).replace(".txt", ""));
                        }
                    }
                } catch (IOException foundException) {
                    System.err.println(foundException.getMessage());
                    System.err.println("Не получилось проверить тип продукта. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
                }
            }
            openFile(allBooks);
        }

        public void openFile (ArrayList < String > allBooks) {
            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.println("Выберите произведение которое желаете открыть. Для этого введите его порядковый номер");
            System.out.println();
            System.out.println("Для сортировки введите - 'S'");
            if (sc.hasNextInt()) {
                int number = sc.nextInt();
                String name = allBooks.get(number - 1);
                File file = new File(pathDirectory + "\\" + name);
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(file);
                    System.out.println();
                    System.out.println("Книга была открыта");
                    toGreet();
                } catch (IOException errorReadException) {
                    System.err.println(errorReadException.getMessage());
                    System.err.println("Не получилось открыть книгу. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
                }
            } else if (sc.hasNext()) {
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("S")) {
                    sortList(allBooks);
                }
            }
        }

        public void sortList (ArrayList < String > allBooks) {
            System.out.println();
            System.out.println("Для сортировки по длине произведения введите - 'L'");
            System.out.println();
            System.out.println("Для сортировки по фамилии автора введите - 'N'");
            System.out.println();
            System.out.println("Для сортировки по названию произведения введите - 'B'");
            Scanner sc = new Scanner(System.in);
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("L")) {
                sortByLength(allBooks);
            } else if (answer.equalsIgnoreCase("N")) {
                sortByAuthor(allBooks);
            } else if (answer.equalsIgnoreCase("B")) {
                sortByName(allBooks);
            }
        }

        public String getName (String fileName){
            String[] authorAndName = fileName.split("_");
            return authorAndName[1].replace(".txt", "");
        }

        public String getAuthor (String fileName){
            String[] authorAndName = fileName.split("_");
            return authorAndName[0];
        }

        public Integer getLengthCharsInFile (String fileName){
            String fileFullName = pathDirectory + "\\" + fileName;
            File file = new File(fileFullName);
            int count = 0;
            try (FileInputStream fis = new FileInputStream(file)) {
                Scanner sc = new Scanner(fis);
                sc.useDelimiter("");
                while (sc.hasNext()) {
                    sc.next();
                    count++;
                }
                fis.close();
                return count;
            } catch (IOException foundException) {
                System.err.println(foundException.getMessage());
                System.err.println("Не получилось проверить длину книги. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
                return -1;
            }
        }

        public Integer getLengthCharsInFile (File fileName){
            String fileFullName = String.valueOf(fileName);
            File file = new File(fileFullName);
            int count = 0;
            try (FileInputStream fis = new FileInputStream(file)) {
                Scanner sc = new Scanner(fis);
                sc.useDelimiter("");
                while (sc.hasNext()) {
                    sc.next();
                    count++;
                }
                fis.close();
                System.out.println(count);
                return count;
            } catch (IOException foundException) {
                System.err.println(foundException.getMessage());
                System.err.println("Не получилось проверить длину книги. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
                return -1;
            }
        }

        Comparator<String> comparatorByAuthor = (o1, o2) -> getAuthor(o1).compareTo(getAuthor(o2));
        Comparator<String> comparatorByName = (o1, o2) -> getName(o1).compareTo(getName(o2));
        Comparator<String> comparatorByLength = (o1, o2) -> getLengthCharsInFile(o1).compareTo(getLengthCharsInFile(o2));

        @Override
        public void sortByAuthor (ArrayList < String > allBooks) {
            System.out.println();
            allBooks.sort(comparatorByAuthor);
            for (int i = 0; i < allBooks.size(); i++) {
                System.out.println(((i + 1) + "." + " " + allBooks.get(i).replace(".txt", "")));
            }
            openFile(allBooks);
            sortList(allBooks);
        }

        @Override
        public void sortByName (ArrayList < String > allBooks) {
            System.out.println();
            allBooks.sort(comparatorByName);
            for (int i = 0; i < allBooks.size(); i++) {
                String fileNameWithoutTxt = ((allBooks.get(i).replace(".txt", "")));
                String[] nameAndAuthor = fileNameWithoutTxt.split("_");
                String name = nameAndAuthor[1];
                String author = nameAndAuthor[0];
                System.out.println((i + 1) + "." + " " + name + "_" + author);
            }
            openFile(allBooks);
            sortList(allBooks);
        }

        @Override
        public void sortByLength (ArrayList < String > allBooks) {
            System.out.println();
            allBooks.sort(comparatorByLength);
            for (int i = 0; i < allBooks.size(); i++) {
                System.out.println((i + 1) + "." + " " + allBooks.get(i).replace(".txt", ""));
            }
            openFile(allBooks);
            sortList(allBooks);
        }

        @Override
        public void sortByGenre () {

        }
    }