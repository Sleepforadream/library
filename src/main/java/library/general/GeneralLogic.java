package library.general;

import library.entities.Press;

import java.util.*;
import java.util.List;


import static library.messages.ActionMessages.*;
import static library.messages.ErrorMessages.*;
import static library.messages.InstructionsMessages.*;

public class GeneralLogic {

    GeneralResources generalResources = new GeneralResources();
    GeneralServiceImpl generalService = new GeneralServiceImpl();

    Scanner sc = new Scanner(System.in);

    public List<String> enterActionsKeys() {
        return Arrays.asList("F", "E", "B", "M", "X");
    }

    public List<String> enterSortsKeys() {
        return Arrays.asList("L", "A", "N", "D", "T", "G");
    }

    public ArrayList<Press> choiceAction() {
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase(enterActionsKeys().get(0))) {
            printEnterTitleOfSearchMessage();
            return generalService.searchPressByParameter();
        } else if (answer.equalsIgnoreCase(enterActionsKeys().get(1))) {
            generalService.viewAllPress();
            return generalResources.getListAllPress();
        } else if (answer.equalsIgnoreCase(enterActionsKeys().get(2))) {
            generalService.viewAllBooks();
            return generalResources.getAllBooks();
        } else if (answer.equalsIgnoreCase(enterActionsKeys().get(3))) {
            generalService.viewAllMagazines();
            return generalResources.getAllMagazines();
        } else if (answer.equalsIgnoreCase(enterActionsKeys().get(4))) {
            return generalResources.getExitOption();
        } else {
            return generalResources.getNotValidOption();
        }
    }

    public ArrayList<Press> choiceSortAction(ArrayList<Press> listPress, String answer) {
        if (answer.equalsIgnoreCase(enterSortsKeys().get(0))) {
            generalService.sortByLength(listPress);
            return listPress;
        } else if (answer.equalsIgnoreCase(enterSortsKeys().get(1))) {
            generalService.sortByAuthor(listPress);
            return listPress;
        } else if (answer.equalsIgnoreCase(enterSortsKeys().get(2))) {
            generalService.sortByName(listPress);
            return listPress;
        } else if (answer.equalsIgnoreCase(enterSortsKeys().get(3))) {
            generalService.sortByDate(listPress);
            return listPress;
        } else if (answer.equalsIgnoreCase(enterSortsKeys().get(4))) {
            generalService.sortByType(listPress);
            return listPress;
        } else if (answer.equalsIgnoreCase(enterSortsKeys().get(5))) {
            generalService.sortByGenre(listPress);
            return listPress;
        } else {
            return null;
        }
    }

    public ArrayList<Press> actionsWithList(ArrayList<Press> press) {
        printChoosePressWantOpenMessage();
        System.out.println();
        printListSorts();
        printToReturnInMainMenu();
        String answer = sc.nextLine();
        if (generalResources.validateEnter(answer, enterSortsKeys())) {
            return choiceSortAction(press, answer);
        } else if (answer.matches("\\d+")) {
            generalResources.openFileByNumberInList(press, (Integer.valueOf(answer)));
            generalResources.printListWithNumbers(press);
            return press;
        } else if (answer.equalsIgnoreCase("q")) {
            return generalResources.getBackOption();
        } else {
            return generalResources.getNotValidOption();
        }
    }

    public void printListSorts() {
        List<String> list = generalResources.getListSort();
        for (String s : list) {
            System.out.println(s);
        }
    }

    public ArrayList<Press> getPressListChooseAction() {
        generalResources.printListByNumbers(generalResources.getListActions());
        return choiceAction();
    }

    public void runLibrary() {
        boolean isRun = true;
        while (isRun) {
            ArrayList<Press> pressFromAction = getPressListChooseAction();
            if (Objects.equals(pressFromAction.get(0).getTitle(), "e")) {
                isRun = false;
                continue;
            }
            boolean isActionMenu = true;
            while (isActionMenu) {
                pressFromAction = actionsWithList(pressFromAction);
                if (Objects.equals(pressFromAction.get(0).getTitle(), "q")) {
                    isActionMenu = false;
                }
                if (Objects.equals(pressFromAction.get(0).getTitle(), "n")) {
                    printNotCorrectAnswerMessage();
                }
            }
        }
        generalService.exit();
    }
}