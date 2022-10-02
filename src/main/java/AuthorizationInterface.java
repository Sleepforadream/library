import java.io.FileNotFoundException;
import java.io.IOException;

public interface AuthorizationInterface {

    void greetingsUser();

    void logInUser() throws IOException;

    void registerUser() throws IOException;

}