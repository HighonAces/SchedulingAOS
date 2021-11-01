//This is the main class and takes input in this format java Driver <Algorithm> <tasks file>

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) throws IOException, FileNotFoundException, CloneNotSupportedException {

        //Validating the input
        if (args.length != 2) {
            System.err.println("This file takes two arguments ");
            System.exit(0);
        }

        // Reading the file
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(args[1]));
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }


        String schedule;

        List<Task> taskList = new ArrayList<Task>();

        while ( (schedule = reader.readLine()) != null) {
            String[] params = schedule.split(",\\s*");
            taskList.add(new Task(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2])));
        }

        reader.close();

        Algoritm algoritm = null;
        String choice = args[0].toUpperCase();

        switch(choice) {
            case "FCFS":
                algoritm = new FCFS(taskList);
                break;
            case "SJF":
                algoritm = new SJF(taskList);
                break;
            case "PRI":
                algoritm = new Priority(taskList);
                break;
            case "RR":
                algoritm = new RR(taskList);
                break;
            case "PRI-RR":
                algoritm = new PriorityRR(taskList);
                break;
            default:
                System.err.println("Invalid algorithm");
                System.exit(0);
        }
        algoritm.schedule();
    }
}
