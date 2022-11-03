package library.authorization;
public interface AuthorizationService {

    Result loginUser(String login, String password);

    Result registerUser(String login, String password);

}