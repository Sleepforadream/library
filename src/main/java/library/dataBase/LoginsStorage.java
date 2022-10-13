package library.dataBase;

import library.messages.ErrorMessages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoginsStorage extends UserDirectory {

    public static String pathLoginsFile = "C:\\Users\\" + userDir + "\\Desktop\\Library\\LoginsStorage\\Logins.txt";
    public static String pathLoginsDirectory = "C:\\Users\\" + userDir + "\\Desktop\\Library\\LoginsStorage";
    public static File loginsFile = new File(pathLoginsFile);
    public static File loginsDirectory = new File(pathLoginsDirectory);

    public void createFolder() {
        try {
            if (!loginsDirectory.exists()) {
                Files.createDirectories(Paths.get(pathLoginsDirectory));
            }
            if (!loginsFile.exists()) {
                Files.createFile(Paths.get(pathLoginsFile));
            }
        } catch (IOException folderException) {
            ErrorMessages exceptionsMessages = new ErrorMessages();
            exceptionsMessages.printNotCreateFolderExceptionMessage();
        }
    }
}
