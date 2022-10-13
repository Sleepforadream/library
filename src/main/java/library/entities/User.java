package library.entities;

public class User {
    String login;
    String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return login;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
