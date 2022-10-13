package library.dataBase;

import library.entities.Press;
import library.general.GeneralService;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class LibraryStorage extends UserDirectory {
    public static String pathLibraryDirectory = "C:\\Users\\" + userDir + "\\Desktop\\Library\\LibraryStorage";
    public static File libraryDirectory = new File(pathLibraryDirectory);

}