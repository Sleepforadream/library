package library;

import library.authorization.AuthorizationService;
import library.general.GeneralService;
import library.general.GeneralResources;

import static library.messages.InstructionsMessages.*;

public class LibraryRunner {

    public static void main(String[] args) {

        GeneralResources generalResources = new GeneralResources();

        generalResources.createFolder();

        AuthorizationService authService = new AuthorizationService();
        GeneralService generalService = new GeneralService();

        printGreetingsMessage();

        boolean answer = authService.getAnswerYesOrNot();

        if (answer) {
            authService.loginUser();
        } else {
            authService.registerUser();
        }

        generalService.runLibrary();
    }
}