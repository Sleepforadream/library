package library.dataBase;

import java.io.File;

public class LibraryStorage extends UserDirectory {

    static String path = new File("").getAbsolutePath();
    public static String pathLibraryDirectory = path + "\\src\\main\\java\\library\\dataBase\\library\\LibraryStorage";
    public static File libraryDirectory = new File(pathLibraryDirectory);

}