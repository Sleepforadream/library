package library.authorization;

import java.io.*;
import java.util.*;

import static library.dataBase.LoginsStorage.*;
import static library.messages.InstructionsMessages.*;

public class AuthorizationResources {

    private final Scanner scanner = new Scanner(System.in);

    public ArrayList<String> getAccountList() {
        return getWordsByIndexFromEachLineInAccountFile(0);
    }

    public ArrayList<String> getPasswordList() {
        return getWordsByIndexFromEachLineInAccountFile(1);
    }

    public boolean checkExistLogin(String login) {
        ArrayList<String> logins = getAccountList();
        return logins.contains(login);
    }

    public boolean checkIfTheAccountMatchesThePassword(String login, String password) {
        ArrayList<String> logins = getAccountList();
        int loginIndex = logins.indexOf(login);
        ArrayList<String> passwords = getPasswordList();
        int passwordIndex = passwords.indexOf(password);
        return loginIndex == passwordIndex;
    }

    public boolean validateLogin(String login) {
        String regex = "^[A-Za-z\\d+_.-]+@(.+)$";
        return login.matches(regex);
    }

    public boolean validatePassword(String password) {
        String regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_])(?=\\S+$).*";
        if (password.matches(regex)) {
            return true;
        } else if (password.length() < 8) {
            return false;
        } else {
            return false;
        }
    }

    public ArrayList<String> getWordsByIndexFromEachLineInAccountFile(int index) {
        ArrayList<String> words = new ArrayList<>();
        try (FileReader fileReader = new FileReader(pathLoginsFile)) {
            Scanner scFr = new Scanner(fileReader);
            while (scFr.hasNextLine()) {
                String[] allWordsInLine = scFr.nextLine().split(" ");
                String word = allWordsInLine[index];
                words.add(word);
            }
        } catch (IOException ioException) {
            //ioException.getMessage();
        }
        return words;
    }

    public boolean writeLoginAndPasswordInFile(String login, String password) {
        try (FileWriter fileWriter = new FileWriter(pathLoginsFile, true)) {
            if (loginsFile.length() != 0) {
                fileWriter.write("\n");
            }
            fileWriter.write(login + " ");
            fileWriter.write(password);
            fileWriter.close();
            return true;
        } catch (IOException ioException) {
            return false;
        }
    }

    public boolean getAnswerYesOrNot(String line) {
        Boolean result;
        do {
            System.out.println(line);
            result = checkAnswer(scanner.nextLine());
        }
        while (result == null);
        return result;
    }

    private final List<String> yes = Arrays.asList("Y", "ДА", "YES");
    private final List<String> no = Arrays.asList("N", "НЕТ", "NO");

    public Boolean checkAnswer(String answer) {
        if (yes.contains(answer.toUpperCase())) {
            return true;
        } else if (no.contains(answer.toUpperCase())) {
            return false;
        }
        return null;
    }

    public String getAnswerString() {
        return scanner.nextLine();
    }

    public Map.Entry<LogInState, String> enterLogin() {
        String login = getAnswerString();
        if (checkExistLogin(login)) {
            return Map.entry(LogInState.IS_EXIST, login);
        } else {
            return Map.entry(LogInState.NOT_EXIST, login);
        }
    }

    public Map.Entry<LogInState, String> enterPassword() {
        String password = getAnswerString();
        return Map.entry(LogInState.IS_EXIST, password);
    }

    public boolean wantRegister() {
        return getAnswerYesOrNot(getWantRegisterAccountMessage());
    }

    public boolean wantLogin() {
        return getAnswerYesOrNot(getWantLoginAccountMessage());
    }

    public Map.Entry<RegisterState, String> createLogin() {
        String login = getAnswerString();
        if (validateLogin(login)) {
            if (!checkLoginExist(login)) {
                return Map.entry(RegisterState.CREATED, login);
            } else {
                return Map.entry(RegisterState.EXIST, login);
            }
        } else {
            return Map.entry(RegisterState.NOT_VALID, "");
        }
    }

    public Map.Entry<RegisterState, String> createPassword() {
        String password = getAnswerString();
        if (validatePassword(password)) {
            return Map.entry(RegisterState.CREATED, password);
        } else {
            return Map.entry(RegisterState.NOT_VALID, "");
        }
    }

    public boolean checkLoginExist(String login) {
        return checkExistLogin(login);
    }

}

enum RegisterState {
    CREATED,
    EXIST,
    NOT_VALID
}

enum LogInState {
    IS_EXIST,
    NOT_EXIST
}
