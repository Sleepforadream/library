package library.dataBase;

import java.io.File;

public class LoginsStorage extends UserDirectory {

    public static String pathLoginsFile = "C:\\Users\\" + userDir + "\\Desktop\\Library\\LoginsStorage\\Logins.txt";
    public static String pathLoginsDirectory = "C:\\Users\\" + userDir + "\\Desktop\\Library\\LoginsStorage";
    public static File loginsFile = new File(pathLoginsFile);
    public static File loginsDirectory = new File(pathLoginsDirectory);

}
