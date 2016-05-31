package de.rabea;

public class Main {
    public static void main(String[] args) {
        String port;
        String directory;

        if (args.length == 4) {
            port = args[1];
            directory = args[3];
        } else {
            port = "5000";
            directory = "PUBLIC_DIR";
        }

        System.out.println("Port: " + port + "\nDirectory: " + directory);
    }
}
