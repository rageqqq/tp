package seedu.duke;

import java.util.Scanner;

public class OverallParser {
    /*
        boolean teamPlanner;
        boolean modulePlanner;
        boolean capCalculator;
        boolean foodRecommendation;
    */
    public OverallParser() {

    }

    public static void main(String[] args) {
        new OverallParser().run();
    }

    public void run() {

        greetUserAndDisplayFeatures();
        requestForFeatureMode();
        displayExitMessage();
    }

    public void displayExitMessage() {
        System.out.println("Thank you for using All-in-oneNUS, we hope to see you again!");
    }

    void greetUserAndDisplayFeatures() {
        System.out.print("Welcome to All-in-OneNUS app!\n");
        System.out.print(
                "Press 1 to access Team Planner\n" +
                        "Press 2 to access Module Planner\n" +
                        "Press 3 to access Cap Calculator\n" +
                        "Press 4 to access Food Recommendation app\n");
    }

    void requestForFeatureMode() {
        while (true) {

            Scanner choice = new Scanner(System.in);
            String chosen = choice.nextLine();
            switch (chosen) {
            case "bye":
                return;
            case "1":
                break;
            case "2":
                new CapCalculator().run();
                break;
            case "3":
                break;
            case "4":
                new FoodRecommendation().run();
                break;
            default:
                System.out.print("Invalid Try again!\n");//replace this line with exception in the future
                break;
            }
        }
    }
}
