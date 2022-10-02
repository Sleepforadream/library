import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Authorization implements AuthorizationInterface {

    String property = System.getProperty("user.home");
    String[] paths = property.split("\\\\");
    String userDir = paths[paths.length - 1];
    String pathFile = "C:\\Users\\" + userDir + "\\Desktop\\Library\\Logins.txt";
    String pathDirectory = "C:\\Users\\" + userDir + "\\Desktop\\Library";
    File passwords = new File(pathFile);
    File directory = new File(pathDirectory);


    @Override
    public void greetingsUser() {
        try {
            if (!directory.exists()) {
                Files.createDirectories(Paths.get(pathDirectory));
            }
            if (!passwords.exists()) {
                Files.createFile(Paths.get(pathFile));
            }
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
            System.err.println("Не удалось создать папку для размещения логинов и паролей!");
        }

        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("ДА") || answer.equalsIgnoreCase("YES")) {
            logInUser();
        } else if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("НЕТ") || answer.equalsIgnoreCase("NO")) {
            registerUser();
        } else {
            System.err.println("Введите корректный ответ: Y/N (Да/Нет)");
            greetingsUser();
        }

    }

    public boolean checkForAnAccount(String login) {
        try (FileReader fileReader = new FileReader(pathFile)) {
            Scanner scFr = new Scanner(fileReader);
            while (scFr.hasNextLine()) {
                String[] logins = scFr.nextLine().split(" ");
                if (logins[0].equals(login)) {
                    return true;
                }
            }
        }
        catch (IOException ioException) {
            System.err.println("Не удалось проверить наличие аккаунта. Попробуйте ещё раз или обратитесь в службу тех.поддержки");
        }
        return false;
    }

    public boolean checkIfTheAccountMatchesThePassword(String Login, String password) {
        try (FileReader fileReader = new FileReader(pathFile)) {
            Scanner scFr = new Scanner(fileReader);
            while (scFr.hasNextLine()) {
                String[] logins = scFr.nextLine().split(" ");
                if (logins[0].equals(Login) && logins[1].equals(password)) {
                    return true;
                }
            }
        }
        catch (IOException ioException){
            System.err.println("Не удалось проверить верность пароля. Попробуйте ещё раз или обратитесь в службу тех.поддержки");
        }
        return false;
    }

    @Override
    public void logInUser() {
        System.out.println();
        System.out.println("Введите свой логин");
        Scanner sc = new Scanner(System.in);
        String login = sc.next();
        if (Objects.equals(login, "1")) {
            registerUser();
        }
        if (checkForAnAccount(login)) {
            System.out.println();
            System.out.println("Введите свой пароль");
            String password = sc.next();
            if (checkIfTheAccountMatchesThePassword(login, password)) {
                System.out.println();
                System.out.println("Вы вошли в свой аккаунт");
                LibraryStorage libraryStorage = new LibraryStorage();
                libraryStorage.toGreet();
            } else {
                System.err.println("Пароль введён неверно");
                logInUser();
            }
        } else {
            System.err.println("Такая учётная запись не была зарегистрирована. Проверьте правильность написания или зарегистрируйтесь. Для регистрации введите 1");
            logInUser();
        }
    }

    @Override
    public void registerUser() {
        String login = createLogin();
        if (checkForAnAccount(login)) {
            System.err.println("Этот аккаунт уже существует");
            logInUser();
        }
        String password = createPassword();
        try (FileWriter fileWriter = new FileWriter(pathFile, true)) {
            if (passwords.length() != 0) {
                fileWriter.write("\n");
            }
            fileWriter.write(login + " ");
            fileWriter.write(password);
            fileWriter.close();
            System.out.println();
            System.out.println("Ваша учётная запись была создана");
            logInUser();
        } catch (IOException ioException) {
            System.err.println("Не удалось создать учётную запись. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
        }
    }

    public String createLogin() {
        System.out.println();
        System.out.println("Введите свой e-mail:");
        Scanner sc = new Scanner(System.in);
        String regex = "^[A-Za-z\\d+_.-]+@(.+)$";
        Pattern patternEmail = Pattern.compile(regex);
        String login;
        try {
            login = sc.next(patternEmail);
        } catch (Exception notValidateMailException) {
            System.err.println("Введите корректный e-mail");
            return createLogin();
        }
        return (login);
    }

    public String createPassword() {
        System.out.println();
        System.out.println("Придумайте свой пароль:");
        Scanner sc = new Scanner(System.in);
        String regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_])(?=\\S+$).*";
        Pattern patternPassword = Pattern.compile(regex);
        String password;
        try {
            password = sc.next(patternPassword);
        } catch (Exception notValidatePassException) {
            System.err.println("Введите корректный пароль. Он должен состоять как минимум из одного символа, цифры, строчной и заглавной буквы");
            return createPassword();
        }
        if (sc.hasNextLine()) {
            if (password.length() < 8) {
                System.err.println("Ваш пароль должен содержать не менее 8 символов!");
                return createPassword();
            }
        }
        return (password);
    }
}