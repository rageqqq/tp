package ModulePlanner;

public class Parser {
    /***
     * Parses a string to integer.
     * @param input A string type number.
     * @return A int type number.
     */
    protected static int parserToInteger(String input) {
        return Integer.parseInt(input);
    }

    protected static boolean parserToBoolean(String input){
        return Boolean.parseBoolean(input);
    }
}