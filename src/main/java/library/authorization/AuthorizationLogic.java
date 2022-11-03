package library.authorization;

import java.util.AbstractMap;
import java.util.Map;

import static library.authorization.LogInState.IS_EXIST;
import static library.authorization.RegisterState.*;
import static library.messages.ErrorMessages.*;
import static library.messages.InstructionsMessages.*;
import static library.messages.InstructionsMessages.printEnterLoginMessage;

public class AuthorizationLogic {

    AuthorizationResources resources = new AuthorizationResources();

    public Map.Entry<String, String> getUser() {
        printEnterLoginMessage();
        Map.Entry<LogInState, String> login = resources.enterLogin();
        while (login.getKey() != IS_EXIST) {
            printNotRegisteredAccountMessage();
            if (resources.wantRegister()) {
                return new AbstractMap.SimpleEntry<>("", "WANT_REGISTER");
            } else {
                printEnterLoginMessage();
                login = resources.enterLogin();
            }
        }
        printEnterPasswordMessage();
        Map.Entry<LogInState, String> password = resources.enterPassword();
        while (!resources.checkIfTheAccountMatchesThePassword(login.getValue(), password.getValue())) {
            printNotRightEnterYourPasswordMessage();
            printEnterPasswordMessage();
            password = resources.enterPassword();
        }
        return new AbstractMap.SimpleEntry<>(login.getValue(), password.getValue());
    }


    public Map.Entry<String, String> addUser() {
        printEnterEmailMessage();
        Map.Entry<RegisterState, String> login = resources.createLogin();
        while (login.getKey() != CREATED) {
            if (login.getKey() == EXIST) {
                printThisAccountAlreadyExistMessage();
                if (resources.wantLogin()) {
                    return new AbstractMap.SimpleEntry<>("", "WANT_LOGIN");
                } else {
                    printEnterEmailMessage();
                    login = resources.createLogin();
                }
            } else if (login.getKey() == NOT_VALID) {
                printNotCorrectEmailMessage();
                printEnterEmailMessage();
                login = resources.createLogin();
            }
        }
        printCreateYourPasswordMessage();
        Map.Entry<RegisterState, String> password = resources.createPassword();
        while (password.getKey() != CREATED) {
            printNotCorrectPasswordMessage();
            printCreateYourPasswordMessage();
            password = resources.createPassword();
        }
        return new AbstractMap.SimpleEntry<>(login.getValue(), password.getValue());
    }
}