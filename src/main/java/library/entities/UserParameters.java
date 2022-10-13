package library.entities;

public enum UserParameters {
    login,
    password;

    public String toString(UserParameters userParameters) {
        if (userParameters == login) {
            return "Логин: ";
        } else if (userParameters == password) {
            return "Пароль: ";
        } else {
            return "";
        }
    }
}
