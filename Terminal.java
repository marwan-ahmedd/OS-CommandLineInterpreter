import java.io.File;
import java.nio.file.Path;

public class Terminal {
    Parser parser;
    Path currentPath;
    public Terminal() {
        currentPath = new File(System.getProperty("user.home")).toPath();
        parser = new Parser();
    }

    public String pwd() {
        return "";
    }
    public void cd(String[] args) {}

    public void chooseCommandAction() {}
    public static void main(String[] args) {
        Terminal terminal = new Terminal();
    }
}
