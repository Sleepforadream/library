package library;

import library.authorization.AuthorizationService;
import library.general.GeneralService;
import library.general.GeneralTools;
import library.messages.InstructionsMessages;

public class LibraryRunner {

    public static void main(String[] args) {
        InstructionsMessages instructionsText = new InstructionsMessages();

        GeneralTools generalTools = new GeneralTools();

        generalTools.createFolder();

        AuthorizationService authService = new AuthorizationService();
        GeneralService generalService = new GeneralService();

        instructionsText.printGreetingsMessage();

        boolean answer = authService.getAnswerYesOrNot();

        if (answer) {
            authService.loginUser();
        } else {
            authService.registerUser();
        }

        generalService.runLibrary();
    }
}