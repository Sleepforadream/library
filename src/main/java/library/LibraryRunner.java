package library;

import library.authorization.AuthorizationService;
import library.dataBase.LibraryStorage;
import library.dataBase.LoginsStorage;
import library.entities.Press;
import library.general.GeneralService;
import library.general.GeneralTools;
import library.messages.ErrorMessages;
import library.messages.InstructionsMessages;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static library.dataBase.LibraryStorage.libraryDirectory;

public class LibraryRunner {
    public static void main(String[] args) {
        GeneralTools generalTools = new GeneralTools();
        generalTools.createFolder();
        InstructionsMessages instructionsText = new InstructionsMessages();
        AuthorizationService authService = new AuthorizationService();
        GeneralService generalService = new GeneralService();

        instructionsText.printGreetingsMessage();

        authService.choiceLoginOrRegister();

        instructionsText.printActionDoYouWantToImplementMessage();

        generalService.getListActionsByNumbers();

        generalService.choiceAction();


    }
}