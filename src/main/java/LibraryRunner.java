public class LibraryRunner {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать! У Вас уже есть учётная запись в нашей библиотеке? (Y/N?)");
        Authorization authorization = new Authorization();
        authorization.greetingsUser();
    }
}