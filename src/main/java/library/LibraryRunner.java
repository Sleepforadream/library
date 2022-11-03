package library;

import library.authorization.*;
import library.general.GeneralLogic;
import library.general.GeneralResources;

import java.util.Map;
import java.util.Objects;

import static library.messages.InstructionsMessages.*;

public class LibraryRunner {

    public static void main(String[] args) {

        GeneralResources generalResources = new GeneralResources();

        generalResources.createFolder();

        AuthorizationServiceImpl authService = new AuthorizationServiceImpl();
        AuthorizationResources authResources = new AuthorizationResources();
        AuthorizationLogic authLogic = new AuthorizationLogic();
        GeneralLogic generalLogic = new GeneralLogic();

        printGreetingsMessage();

        boolean answer = authResources.getAnswerYesOrNot("");

        Map.Entry<String, String> account;

        boolean isRun = false;

        while (!isRun) {
            while (!answer) {
                account = authLogic.addUser();
                if (!Objects.equals(account.getValue(), "WANT_LOGIN")) {
                    authService.registerUser(account.getKey(), account.getValue());
                }
                answer = true;
            }
            account = authLogic.getUser();
            if (!Objects.equals(account.getValue(), "WANT_REGISTER")) {
                authService.loginUser(account.getKey(), account.getValue());
                isRun = true;
            } else {
                answer = false;
            }
        }
        generalLogic.runLibrary();
    }
}