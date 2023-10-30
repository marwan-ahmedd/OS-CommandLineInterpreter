import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private String commandName;
    private String[] args;

    public boolean parse(String input) {
        commandName = null;
        args = null;
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(input);

        ArrayList<String> argsList = new ArrayList<>();
        if (!matcher.find()) return false;
        commandName = matcher.group();
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                argsList.add(matcher.group(1));
            } else {
                argsList.add(matcher.group(2));
            }
        }
        args = new String[argsList.size()];
        argsList.toArray(args);

        return true;
    }
    public String getCommandName() {
        return commandName;
    }

    public String[] getArgs() {
        return args;
    }

    public static void main(String[] a) {
        Parser parser = new Parser();
        boolean valid = parser.parse("cd D:/College/resume");

        System.out.println(parser.getCommandName());
        String[] args = parser.getArgs();
        System.out.println(args[0]);

    }
}
