import java.io.File;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Terminal {
    Parser parser;
    Path currentPath, homeDir;
    public Terminal() {
        homeDir = new File(System.getProperty("user.dir")).toPath();
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
        } else {
            try {
                currentPath = currentPath.resolve(path).toRealPath(LinkOption.NOFOLLOW_LINKS);
            } catch (IOException e) {
                System.out.println("Invalid Path");
            }
        }
    }

    public void ls() {
        String[] arr = currentPath.toFile().list();
        assert arr != null;
        Arrays.sort(arr);
        for (String a : arr) {
            System.out.print(a + "     ");
        }
        System.out.println();
    }

    public void ls_r() {
        String[] arr = currentPath.toFile().list();
        assert arr != null;
        Arrays.sort(arr, Collections.reverseOrder());
        for (String a : arr) {
            System.out.print(a + "     ");
        }
        System.out.println();
    }

    public void mkdir(String[] args) {
        for (String dir : args) {
            File f = currentPath.resolve(dir).toFile();
            if (f.exists()) {
                System.out.println(currentPath + ": Directory Already Exists!");
            }
            else if (!f.mkdir()) {
                System.out.println("Error: invalid parameters are entered!");
            }
        }
    }

    public void rmdir(String arg) {
        if (arg.equals("*")) {
            File cur = currentPath.toFile();
            for (File f : Objects.requireNonNull(cur.listFiles())) {
                if (f.isDirectory() && Objects.requireNonNull(f.listFiles()).length == 0) {
                    f.delete();
                }
            }
        } else {
            File cur = currentPath.resolve(arg).toFile();
            if (!cur.exists()) {
                System.out.println(arg + " doesn't exist!");
            }
            else if (!cur.delete()) {
                System.out.println(arg + " is not empty!");
            }
        }
    }

    public void touch(String path) {
        File f = currentPath.resolve(path).toFile();
        try {
            f.createNewFile();
        } catch (IOException ignored) {
            System.out.println(ignored.getMessage());
        }

    }

    public void rm(String name) {
        File f = currentPath.resolve(name).toFile();
        if (!f.delete()) {
            System.out.println(currentPath + ": No such file!");
        }
    }

    public void chooseCommandAction() {}
    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        System.out.println(terminal.pwd());
        terminal.cd("../test");
        terminal.touch("../another/bcc.txt");

    }
}
