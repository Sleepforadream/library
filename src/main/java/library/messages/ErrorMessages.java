package library.messages;

public class ErrorMessages {

    public String getNotCreateFolderExceptionMessage() {
        return "Не удалось создать папку для размещения логинов и паролей!";
    }

    public void printNotCreateFolderExceptionMessage() {
        System.err.println("Не удалось создать папку для размещения логинов и паролей!");
    }

    public String getNotCheckHaveAnAccountMessage() {
        return "Не удалось проверить наличие аккаунта. Попробуйте ещё раз или обратитесь в службу тех.поддержки";
    }

    public void printNotCheckHaveAnAccountMessage() {
        System.err.println("Не удалось проверить наличие аккаунта. Попробуйте ещё раз или обратитесь в службу тех.поддержки");
    }

    public String getNotCheckAccountMatchesThePasswordMessage() {
        return "Не удалось проверить верность пароля. Попробуйте ещё раз или обратитесь в службу тех.поддержки";
    }

    public void printNotCheckAccountMatchesThePasswordMessage() {
        System.err.println("Не удалось проверить верность пароля. Попробуйте ещё раз или обратитесь в службу тех.поддержки");
    }

    public String getNotRegisteredAccountMessage() {
        return "Такая учётная запись не была зарегистрирована. Проверьте правильность написания или зарегистрируйтесь.";
    }

    public void printNotRegisteredAccountMessage() {
        System.err.println("Такая учётная запись не была зарегистрирована. Проверьте правильность написания или зарегистрируйтесь.");
    }

    public String getNotRightEnterYourPasswordMessage() {
        return "Пароль введён неверно";
    }

    public void printNotRightEnterYourPasswordMessage() {
        System.err.println("Пароль введён неверно");
    }

    public String getThisAccountAlreadyExistMessage() {
        return "Этот аккаунт уже существует";
    }

    public void printThisAccountAlreadyExistMessage() {
        System.err.println("Этот аккаунт уже существует");
    }

    public String getNotSuccessCreateAccountMessage() {
        return "Не удалось создать учётную запись. Попробуйте ещё раз или обратитесь в службу тех. поддержки";
    }

    public void printNotSuccessCreateAccountMessage() {
        System.err.println("Не удалось создать учётную запись. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
    }

    public String getNotCorrectEmailMessage() {
        return "Введите корректный e-mail";
    }

    public void printNotCorrectEmailMessage() {
        System.err.println("Введите корректный e-mail");
    }

    public String getNotCorrectPasswordMessage() {
        return "Введите корректный пароль. Он должен состоять как минимум из одного символа, цифры, строчной и заглавной буквы и содержать не менее 8 символов!";
    }

    public void printNotCorrectPasswordMessage() {
        System.err.println("Введите корректный пароль. Он должен состоять как минимум из одного символа, цифры, строчной и заглавной буквы и содержать не менее 8 символов!");
    }

    public String getNotCorrectAnswerYesOrNotMessage() {
        return "Введите корректный ответ: Y/N (Да/Нет)";
    }

    public void printNotCorrectAnswerYesOrNotMessage() {
        System.err.println("Введите корректный ответ: Y/N (Да/Нет)");
    }

    public String getNotFoundAccountsInAccountListMessage() {
        return "Не удалось найти учётные записи";
    }

    public void printNotFoundAccountsInAccountListMessage() {
        System.err.println("Не удалось найти учётные записи");
    }

    public String getNotCorrectAnswerMessage() {
        return "Введите корректный ответ";
    }

    public void printNotCorrectAnswerMessage() {
        System.err.println("Введите корректный ответ");
    }

    public String getNotFoundInLibraryMessage() {
        return "Таких данных не было найдено в библиотеке";
    }

    public void printNotFoundInLibraryMessage() {
        System.out.println();
        System.err.println("Таких данных не было найдено в библиотеке");
    }

    public String getNotOpenPressMessage() {
        return "Не получилось открыть книгу. Попробуйте ещё раз или обратитесь в службу тех. поддержки";
    }

    public void printNotOpenPressMessage() {
        System.out.println();
        System.err.println("Не получилось открыть книгу. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
    }

    public String getNotGetAccessToFileMessage() {
        return "Не получилось проверить длину книги. Попробуйте ещё раз или обратитесь в службу тех. поддержки";
    }

    public void printNotGetAccessToFileMessage() {
        System.err.println("Не получилось проверить длину книги. Попробуйте ещё раз или обратитесь в службу тех. поддержки");
    }

    public String getGenreTypeDataMessage() {
        return "Значением поля sortBy должны быть - 'жанр', 'тип' или 'дата'";
    }

    public void printGenreTypeDataMessage() {
        System.err.println("Значением поля sortBy должны быть - 'жанр', 'тип' или 'дата'");
    }
}
