package library.authorization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static library.messages.ErrorMessages.*;
import static library.messages.InstructionsMessages.*;

public class AuthorizationService implements AuthorizationInterface {

    AuthorizationResources authTools = new AuthorizationResources();

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
            printNotCorrectAnswerYesOrNotMessage();
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
                printSuccessEnterInAccountMessage();
                return true;
            } else {
                printNotRightEnterYourPasswordMessage();
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
        printEnterLoginMessage();
        String login = getAnswerString();
        if (authTools.checkForAnLogin(login)) {
            return login;
        } else {
            printNotRegisteredAccountMessage();
            if (!wantRegister()) {
                return enterLogin();
            } else {
                registerUser();
                return null;
            }
        }
    }

    public String enterPassword() {
        printEnterPasswordMessage();
        return getAnswerString();
    }


    public boolean wantRegister() {
        printWantRegisterAccountMessage();
        return getAnswerYesOrNot();
    }

    public boolean wantLogin() {
        printWantLoginAccountMessage();
        return getAnswerYesOrNot();
    }

    @Override
    public boolean registerUser() {
        String login = createLogin();
        if (login != null) {
            String password = createPassword();
            if (authTools.writeLoginAndPasswordInFile(login, password)) {
                printSuccessCreateAccountMessage();
                loginUser();
                return true;
            } else {
                printNotSuccessCreateAccountMessage();
                wantLogin();
                return false;
            }
        } else {
            return false;
        }
    }

    public String createLogin() {
        printEnterEmailMessage();
        String login = getAnswerString();
        if (authTools.validateLogin(login)) {
            if (!authTools.checkForAnLogin(login)) {
                return login;
            } else {
                printThisAccountAlreadyExistMessage();
                if (wantLogin()) {
                    loginUser();
                    return null;
                } else {
                    return createLogin();
                }
            }
        } else {
            printNotCorrectEmailMessage();
            return createLogin();
        }
    }

    public String createPassword() {
        printCreateYourPasswordMessage();
        String password = getAnswerString();
        if (authTools.validatePassword(password)) {
            return password;
        } else {
            printNotCorrectPasswordMessage();
            return createPassword();
        }
    }
}