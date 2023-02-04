package library.dataBase;

import java.io.File;

public class LoginsStorage extends UserDirectory {
    static String path = new File("").getAbsolutePath();
    public static String pathLoginsFile = path + "\\src\\main\\java\\library\\dataBase\\library\\LoginsStorage\\Logins.txt";
    public static String pathLoginsDirectory = path + "\\src\\main\\java\\library\\dataBase\\library\\LoginsStorage";
    public static File loginsFile = new File(pathLoginsFile);
    public static File loginsDirectory = new File(pathLoginsDirectory);

}
