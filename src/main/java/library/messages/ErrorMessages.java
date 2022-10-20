package library.messages;

public class ErrorMessages {

    public static String getNotCreateFolderExceptionMessage() {
        return "Не удалось создать папку для размещения логинов и паролей!";
    }

    public static void printNotCreateFolderExceptionMessage() {
        System.err.println("Не удалось создать папку для размещения логинов и паролей!");
    }

    public static String getNotCheckHaveAnAccountMessage() {
        return "Не удалось проверить наличие аккаунта. Попробуйте ещё раз или обратитесь в службу тех.поддержки";
    }

    public static void printNotCheckHaveAnAccountMessage() {
        System.err.println("Не удалось проверить наличие аккаунта. Попробуйте ещё раз или обратитесь в службу тех.поддержки");
    }

    public static String getNotCheckAccountMatchesThePasswordMessage() {
        return "Не удалось проверить верность пароля. Попробуйте ещё раз или обратитесь в службу тех.поддержки";
    }

    public static void printNotCheckAccountMatchesThePasswordMessage() {
        System.err.println("Не удалось проверить верность пароля. Попробуйте ещё раз или обратитесь в службу тех.поддержки");
    }

    public static String getNotRegisteredAccountMessage() {
        return "Такая учётная запись не была зарегистрирована. Проверьте правильность написания или зарегистрируйтесь.";
    }

    public static void printNotRegisteredAccountMessage() {
        System.err.println("Такая учётная запись не была зарегистрирована. Проверьте правильность написания или зарегистрируйтесь.");
    }

    public static String getNotRightEnterYourPasswordMessage() {
        return "Пароль введён неверно";
    }

    public static void printNotRightEnterYourPasswordMessage() {
        System.err.println("Пароль введён неверно");
    }

    public static String getThisAccountAlreadyExistMessage() {
        return "Этот аккаунт уже существует";
    }

    public static void printThisAccountAlreadyExistMessage() {
        System.err.println("Этот аккаунт уже существует");
    }

    public static String getNotSuccessCreateAccountMessage() {
        return "Не удалось создать учётную запись. Попробуйте ещё раз или обратитесь в службу тех. поддержки";
    }

    public static void printNotSuccessCreateAccountMessage() {
        System.err.println("Не удалось создать учётную запись. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
    }

    public static String getNotCorrectEmailMessage() {
        return "Введите корректный e-mail";
    }

    public static void printNotCorrectEmailMessage() {
        System.err.println("Введите корректный e-mail");
    }

    public static String getNotCorrectPasswordMessage() {
        return "Введите корректный пароль. Он должен состоять как минимум из одного символа, цифры, строчной и заглавной буквы и содержать не менее 8 символов!";
    }

    public static void printNotCorrectPasswordMessage() {
        System.err.println("Введите корректный пароль. Он должен состоять как минимум из одного символа, цифры, строчной и заглавной буквы и содержать не менее 8 символов!");
    }

    public static String getNotCorrectAnswerYesOrNotMessage() {
        return "Введите корректный ответ: Y/N (Да/Нет)";
    }

    public static void printNotCorrectAnswerYesOrNotMessage() {
        System.err.println("Введите корректный ответ: Y/N (Да/Нет)");
    }

    public static String getNotFoundAccountsInAccountListMessage() {
        return "Не удалось найти учётные записи";
    }

    public static void printNotFoundAccountsInAccountListMessage() {
        System.err.println("Не удалось найти учётные записи");
    }

    public static String getNotCorrectAnswerMessage() {
        return "Введите корректный ответ";
    }

    public static void printNotCorrectAnswerMessage() {
        System.err.println("Введите корректный ответ");
    }

    public static String getNotFoundInLibraryMessage() {
        return "Таких данных не было найдено в библиотеке";
    }

    public static void printNotFoundInLibraryMessage() {
        System.out.println();
        System.err.println("Таких данных не было найдено в библиотеке");
    }

    public static String getNotOpenPressMessage() {
        return "Не получилось открыть книгу. Попробуйте ещё раз или обратитесь в службу тех. поддержки";
    }

    public static void printNotOpenPressMessage() {
        System.out.println();
        System.err.println("Не получилось открыть книгу. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
    }

    public static String getNotGetAccessToFileMessage() {
        return "Не получилось проверить длину книги. Попробуйте ещё раз или обратитесь в службу тех. поддержки";
    }

    public static void printNotGetAccessToFileMessage() {
        System.err.println("Не получилось проверить длину книги. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
    }

    public static String getGenreTypeDataMessage() {
        return "Значением поля sortBy должны быть - 'жанр', 'тип' или 'дата'";
    }

    public static void printGenreTypeDataMessage() {
        System.err.println("Значением поля sortBy должны быть - 'жанр', 'тип' или 'дата'");
    }

    public static String getNotFoundBooksOrMagazineMessage() {
        return "Не удалось найти книги или журналы библиотеки. Проверьте наличие файлов в папке для хранения прессы";
    }

    public static void printNotFoundBooksOrMagazineMessage() {
        System.err.println("Не удалось найти книги или журналы библиотеки. Проверьте наличие файлов в папке для хранения прессы");
    }

    public static String getNoThisNumberInListMessage() {
        return "Такого номера нет в списке";
    }

    public static void printNoThisNumberInListMessage() {
        System.err.println("Такого номера нет в списке");
    }
}
