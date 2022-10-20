package library.authorization;

import java.io.*;
import java.util.*;

import static library.dataBase.LoginsStorage.*;

public class AuthorizationTools {

    public ArrayList<String> getAccountList() {
        return getWordsByIndexFromEachLineInAccountFile(0);
    }

    public ArrayList<String> getPasswordList() {
        return getWordsByIndexFromEachLineInAccountFile(1);
    }

    public ArrayList<String> getListAllAccountsAndPasswords() {
        ArrayList <String> passwordsList = getPasswordList();
        ArrayList <String> accountsList = getAccountList();
        ArrayList <String> allAccountsAndPasswords = getAccountList();
        for (int i = 0; i < passwordsList.size(); i++) {
            String account = accountsList.get(i);
            String password = passwordsList.get(i);
            String accountAndPassword = account + "_" + password;
            allAccountsAndPasswords.add(accountAndPassword);
        }
        return allAccountsAndPasswords;
    }

    public boolean checkForAnLogin(String login) {
        ArrayList<String> logins = getAccountList();
        return logins.contains(login);
    }

    public boolean checkIfTheAccountMatchesThePassword(String login, String password) {
        ArrayList<String> logins = getAccountList();
        int loginIndex = logins.indexOf(login);
        ArrayList<String> passwords = getPasswordList();
        return Objects.equals(passwords.get(loginIndex), password);
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
            ioException.getMessage();
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
}