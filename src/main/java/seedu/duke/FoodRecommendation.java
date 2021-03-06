package seedu.duke;

import seedu.duke.foodstore.FoodStore;
import seedu.duke.foodstore.FoodExceptions;
import seedu.duke.foodstore.FoodStoreParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FoodRecommendation {

    private static final String FIND_ERROR_MESSAGE =
            "Sorry, We are unable to find any food stores that fit the description";
    private static final String WELCOME_MESSAGE = "Welcome to Food Recommendation Bot!\n";
    private static final String AVAILABLE_COMMANDS = "Here are the list of commands: \n"
            + "1. help - Displays the list of commands available\n"
            + "2. add [store name] [store description] - Adds a new food store\n"
            + "3. list - Prints out all the food stores\n"
            + "4. delete [store index] - Deletes the store with that index\n"
            + "5. recommend - Recommends a random food store\n"
            + "6. clear - Clears all the food stores in the list";
    private static final String EXIT_MESSAGE = "Goodbye, hope to see you again!";
    private static final String OUT_OF_BOUNDS_MESSAGE = "Sorry, the number you inputted is out of bounds.";
    private static final String RECOMMEND_MESSAGE = "The bot recommends this store for you to try!";
    private static final String CLEAR_MESSAGE = "All food stores have been cleared from the list.";
    private static final String STORE_REMOVE_MESSAGE = "This store has been removed:";
    private static final String LIST_MESSAGE = "Here is the list of food stores:";
    private static final String ADD_MESSAGE = "This food store has been added to the list:";


    private final List<FoodStore> foodStoreList = new ArrayList<>();

    public FoodRecommendation() {

    }

    public void add(FoodStore toAdd) {
        print(ADD_MESSAGE);
        printStore(toAdd);
        foodStoreList.add(toAdd);
    }

    public void printStore(FoodStore toPrint) {
        System.out.println(toPrint.toString());
    }

    public void printList() {
        print(LIST_MESSAGE);
        for (int i = 0; i < foodStoreList.size(); i++) {
            FoodStore foodStore = foodStoreList.get(i);
            Integer index = i + 1;
            print(index + ". " + foodStore.toString());
        }
    }

    public void delete(int index) {
        if (index <= foodStoreList.size() - 1) {
            print(STORE_REMOVE_MESSAGE);
            printStore(foodStoreList.get(index));
            foodStoreList.remove(index);
        } else {
            print(OUT_OF_BOUNDS_MESSAGE);
        }
    }

    public void clearList() {
        print(CLEAR_MESSAGE);
        foodStoreList.clear();
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void recommend() {
        Random random = new Random();
        FoodStore foodStore = foodStoreList.get(random.nextInt(foodStoreList.size()));
        print(RECOMMEND_MESSAGE);
        printStore(foodStore);
    }

    public void find(FoodStore toFind) {
        if (foodStoreList.contains(toFind)) {
            printStore(toFind);
        } else {
            print(FIND_ERROR_MESSAGE);
        }
    }

    public void setHalal(Integer index) {
        FoodStore foodStore = foodStoreList.get(index);
        foodStore.setHalal();
    }

    public void clearHalal(Integer index) {
        FoodStore foodStore = foodStoreList.get(index);
        foodStore.clearHalal();
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printAvailableCommands() {
        System.out.println(AVAILABLE_COMMANDS);
    }

    public void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    public void run() {
        printWelcomeMessage();
        printAvailableCommands();
        runUntilExit();
        printExitMessage();
    }

    public void runUntilExit() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        boolean isExit = userInput.equals("bye");
        while (!isExit) {
            String[] inputArguments = userInput.split(" ");
            String keyword = inputArguments[0];
            boolean isAdd = keyword.equals("add");
            boolean isDelete = keyword.equals("delete");
            boolean isList = keyword.equals("list");
            boolean isRecommend = keyword.equals("recommend");
            boolean isClear = keyword.equals("clear");
            if (isAdd) {
                try {
                    FoodStore foodStore = FoodStoreParser.parseAddCommand(userInput);
                    add(foodStore);
                } catch (FoodExceptions e) {
                    System.out.println(e.getMessage());
                }
            } else if (isDelete) {
                int index = Integer.parseInt(inputArguments[1]) - 1;
                delete(index);
            } else if (isList) {
                printList();
            } else if (isRecommend) {
                recommend();
            } else if (isClear) {
                clearList();
            }
            userInput = in.nextLine();
            isExit = userInput.equals("bye");
        }
    }


}
