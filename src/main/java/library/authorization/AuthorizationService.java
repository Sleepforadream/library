package library.authorization;

import library.messages.ErrorMessages;
import library.messages.InstructionsMessages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AuthorizationService implements AuthorizationInterface {

    InstructionsMessages instructionsMessages = new InstructionsMessages();
    ErrorMessages errorMessages = new ErrorMessages();
    AuthorizationTools authTools = new AuthorizationTools();

    ArrayList<String> allUsers = authTools.getListAllAccountsAndPasswords();

    Scanner sc = new Scanner(System.in);

    @Override
    public boolean logInUser() {
        instructionsMessages.printEnterLoginMessage();
        String login = sc.nextLine();
        if (authTools.checkForAnLogin(login)) {
            return checkPasswordUser(login);
        } else {
            errorMessages.printNotRegisteredAccountMessage();
            instructionsMessages.printWantRegisterAccountMessage();
            if (getAnswerYesOrNot()) {
                return registerUser();
            }
            else {
                return logInUser();
            }
        }
    }

    public boolean checkPasswordUser(String login) {
        instructionsMessages.printEnterPasswordMessage();
        String password = sc.nextLine();
        if (authTools.checkIfTheAccountMatchesThePassword(login, password)) {
            instructionsMessages.printSuccessEnterInAccountMessage();
            return true;
        } else {
            errorMessages.printNotRightEnterYourPasswordMessage();
            return checkPasswordUser(login);
        }
    }

    public boolean registerUser() {
        String login = createLogin();
        String password = createPassword();
        if (authTools.writeLoginAndPasswordInFile(login, password)) {
            instructionsMessages.printSuccessCreateAccountMessage();
            return true;
        } else {
            errorMessages.printNotSuccessCreateAccountMessage();
            return false;
        }
    }

    public String createLogin() {
        instructionsMessages.printEnterEmailMessage();
        String login = sc.nextLine();
        if (!authTools.checkForAnLogin(login)) {
            if (authTools.validateLogin(login)) {
                return login;
            } else {
                errorMessages.printNotCorrectEmailMessage();
                return createLogin();
            }
        } else {
            errorMessages.printThisAccountAlreadyExistMessage();
            instructionsMessages.printWantLoginAccountMessage();
            if (getAnswerYesOrNot()){
                logInUser();
                return errorMessages.getNotSuccessCreateAccountMessage();
            }
            else {
                return createLogin();
            }
        }
    }

    public String createPassword() {
        instructionsMessages.printCreateYourPasswordMessage();
        String password = sc.nextLine();
        if (authTools.validatePassword(password)) {
            return password;
        } else {
            errorMessages.printNotCorrectPasswordMessage();
            return createPassword();
        }
    }

    public boolean getAnswerYesOrNot() {
        String answer = sc.nextLine();
        List<String> yes = Arrays.asList("Y", "ДА", "YES");
        List<String> no = Arrays.asList("N", "НЕТ", "NO");
        if (yes.contains(answer.toUpperCase())) {
            return true;
        } else if (no.contains(answer.toUpperCase())) {
            return false;
        } else {
            errorMessages.printNotCorrectAnswerYesOrNotMessage();
            return getAnswerYesOrNot();
        }
    }

    public void choiceLoginOrRegister () {
        if (getAnswerYesOrNot()) {
            logInUser();
        }
        else {
            registerUser();
        }
    }
}