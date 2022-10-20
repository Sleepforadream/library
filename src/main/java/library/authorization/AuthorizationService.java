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

    public String getAnswerString() {
        return sc.nextLine();
    }

    @Override
    public boolean loginUser() {
        String login = enterLogin();
        if (login != null) {
            String password = enterPassword();
            if (authTools.checkIfTheAccountMatchesThePassword(login, password)) {
                instructionsMessages.printSuccessEnterInAccountMessage();
                return true;
            } else {
                errorMessages.printNotRightEnterYourPasswordMessage();
                if (wantRegister()) {
                    registerUser();
                } else {
                    loginUser();
                }
                return false;
            }
        } else {
            return false;
        }
    }

    public String enterLogin() {
        instructionsMessages.printEnterLoginMessage();
        String login = getAnswerString();
        if (authTools.checkForAnLogin(login)) {
            return login;
        } else {
            errorMessages.printNotRegisteredAccountMessage();
            if (!wantRegister()) {
                return enterLogin();
            } else {
                registerUser();
                return null;
            }
        }
    }

    public String enterPassword() {
        instructionsMessages.printEnterPasswordMessage();
        return getAnswerString();
    }


    public boolean wantRegister() {
        instructionsMessages.printWantRegisterAccountMessage();
        return getAnswerYesOrNot();
    }

    public boolean wantLogin() {
        instructionsMessages.printWantLoginAccountMessage();
        return getAnswerYesOrNot();
    }

    @Override
    public boolean registerUser() {
        String login = createLogin();
        if (login != null) {
            String password = createPassword();
            if (authTools.writeLoginAndPasswordInFile(login, password)) {
                instructionsMessages.printSuccessCreateAccountMessage();
                loginUser();
                return true;
            } else {
                errorMessages.printNotSuccessCreateAccountMessage();
                wantLogin();
                return false;
            }
        } else {
            return false;
        }
    }

    public String createLogin() {
        instructionsMessages.printEnterEmailMessage();
        String login = getAnswerString();
        if (authTools.validateLogin(login)) {
            if (!authTools.checkForAnLogin(login)) {
                return login;
            } else {
                errorMessages.printThisAccountAlreadyExistMessage();
                if (wantLogin()) {
                    loginUser();
                    return null;
                } else {
                    return createLogin();
                }
            }
        } else {
            errorMessages.printNotCorrectEmailMessage();
            return createLogin();
        }
    }

    public String createPassword() {
        instructionsMessages.printCreateYourPasswordMessage();
        String password = getAnswerString();
        if (authTools.validatePassword(password)) {
            return password;
        } else {
            errorMessages.printNotCorrectPasswordMessage();
            return createPassword();
        }
    }
}