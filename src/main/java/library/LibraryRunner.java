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

        InstructionsMessages instructionsText = new InstructionsMessages();
        LoginsStorage loginsStorage = new LoginsStorage();
        AuthorizationService authService = new AuthorizationService();
        GeneralService generalService = new GeneralService();
        GeneralTools generalTools = new GeneralTools();

        File[] allFiles = libraryDirectory.listFiles();

        loginsStorage.createFolder();

        instructionsText.printGreetingsMessage();

        authService.choiceLoginOrRegister();

        instructionsText.printActionDoYouWantToImplementMessage();

        generalService.getListActionsByNumbers();

        generalService.choiceAction();


    }
}