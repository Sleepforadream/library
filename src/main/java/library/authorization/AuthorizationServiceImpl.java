package library.authorization;

import static library.messages.InstructionsMessages.printSuccessCreateAccountMessage;

public class AuthorizationServiceImpl implements AuthorizationService {

    AuthorizationResources authTools = new AuthorizationResources();

    @Override
    public Result loginUser(String login, String password) {
        if (authTools.checkIfTheAccountMatchesThePassword(login, password)) {
            return Result.SUCCESS;
        } else {
            return Result.FAILED;
        }
    }

    @Override
    public Result registerUser(String login, String password) {
        if (authTools.writeLoginAndPasswordInFile(login, password)) {
            printSuccessCreateAccountMessage();
            return Result.SUCCESS;
        } else {
            return Result.FAILED;
        }
    }
}

enum Result {
    SUCCESS,
    FAILED,
}