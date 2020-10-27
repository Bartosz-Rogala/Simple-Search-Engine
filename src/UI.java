import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UI {
    Scanner scan;
    ArrayList<String> database;
    Map<String, ArrayList<Integer>> map;

    public UI() {

    }

    public void startUI() {

        //scanner for taking user input
        scan = new Scanner(System.in);

        //for storing records from database
        database = new ArrayList<>();

        //for inverted indexes, which will optimise search
        map = new HashMap<>();

        //initial loading of file - has to be .txt and has to be in current directory
        System.out.println("Load a file - (File has to be .txt format and has to be in current directory)");
        System.out.print("> ");
        String fileName = scan.nextLine();
        File file = new File("./" + fileName + ".txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                database.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + fileName);
        }

        // creation of inverted index to optimise search
        for (int i = 0; i < database.size(); i++) {
            for (String a: database.get(i).split(" ")) {
                map.putIfAbsent(a.toUpperCase(), new ArrayList<Integer>());
                map.get(a.toUpperCase()).add(i);
            }
        }




        while (true) {
            // ------MENU------
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            System.out.println("Enter the number:");
            System.out.print("> ");
            // ----------------

            //taking user input (numeric)
            int menuOption = Integer.valueOf(scan.nextLine());
            switch (menuOption) {
                case 1:
                    find();
                    break;
                case 2:
                    list();
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
    }


    public void find() {

        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String inputedStrategy = scan.nextLine();

        ContextChooser context = new ContextChooser();

        switch (inputedStrategy.toUpperCase()) {
            case "ALL":
                context.setMethod(new AllMethod());
                break;
            case "ANY":
                context.setMethod(new AnyMethod());
                break;
            case "NONE":
                context.setMethod(new NoneMethod());
                break;
            default:
                System.out.println("Incorrect option! Try again.");
                return;
        }


        System.out.println("Enter a name or email to search all suitable people.");
        System.out.print("> ");
        String value = scan.nextLine();
        System.out.println(context.search(map, database, value));

    }

    public void list() {
        System.out.println("=== List of people ===");
        for (String a : database) {
            System.out.println(a);
        }
        System.out.println("");
    }
}
