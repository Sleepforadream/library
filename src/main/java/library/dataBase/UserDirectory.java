package library.dataBase;

public class UserDirectory {
    public static String property = System.getProperty("user.home");
    public static String[] paths = property.split("\\\\");
    public static String userDir = paths[paths.length - 1];
}
