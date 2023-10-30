import java.io.File;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
public class Terminal {
    Parser parser;
    Path currentPath, homeDir;
    public Terminal() {
        homeDir = new File(System.getProperty("user.home")).toPath();
        currentPath = homeDir;
        parser = new Parser();
    }

    public void echo(String arg) {
        System.out.println(arg);
    }

    public String pwd() {
        return currentPath.toString();
    }
    public void cd() {
        currentPath = homeDir;
        System.out.println(currentPath);;
    }
    public void cd(String path) {
        if (path.equals("..")) {
            currentPath = currentPath.getParent();
            System.out.println(currentPath);;
        } else {
            try {
                currentPath = currentPath.resolve(path).toRealPath(LinkOption.NOFOLLOW_LINKS);
                System.out.println(currentPath);;
            } catch (IOException e) {
                System.out.println("Invalid Path");
            }
        }
    }

    public void chooseCommandAction() {}
    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        System.out.println(terminal.pwd());
        terminal.cd("../test");
        terminal.cd("..");

    }
}
