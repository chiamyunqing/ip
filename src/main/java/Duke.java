/*
 * Duke is a retired old uncle who likes to speak in Singlish.
 */
import java.util.*;
import java.util.Scanner;

public class Duke {
    private List<Task> taskList = new ArrayList<>();

    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Eh yo, I am Duke! Some kids call me 'Lao Duke' (which means old Duke).\n" +
                "I like to speak Singlish but I also can remember your tasks one. Come try la! ";
        System.out.println(logo);
        System.out.println(intro);
    }

    private void exit() {
        String bye = "Alamak, you sure you finished all your tasks? Ok lah I also need to sleep anyway Zzzz.\n" +
                "Goodbye!";
        System.out.println(bye);
    }

    private void addTask(String input) {
        //Here, we do the classification & processing of tasks: to-do/deadline/event
        //input is from String from user
        String[] content = input.split(" ");
        Task tsk = null;
        if (content[0].toLowerCase().equals("todo")) {
            tsk = new Todo(input.replaceFirst(content[0] + " ", ""));
        } else if (content[0].toLowerCase().equals("deadline")) {
            String[] splitBySlash = input.split("/");
            tsk = new Deadline(splitBySlash[0].replaceFirst(content[0] + " ", ""),
                    splitBySlash[1].replaceFirst("by ", ""));
        } else {
            String[] splitBySlash = input.split("/");
            tsk = new Event(splitBySlash[0].replaceFirst(content[0] + " ", ""),
                    splitBySlash[1].replaceFirst("at ", ""));
        }
        taskList.add(tsk);
        System.out.println("Lao Duke has added this task for you:\n" + tsk);
        System.out.println("You have " + taskList.size() + " task(s) in your list!");
    }

    private void listTask() {
        System.out.println("Lao Duke not so blur like you. Tsk. I got remember your tasks one hor.");
        for (int i = 0; i < taskList.size(); i++) {
            Task tsk = taskList.get(i);
            System.out.println("Task " + (i + 1) + ": "  + tsk);
        }
    }

    private void markDone(int taskNum) {
        if (taskNum < 1 || taskNum > taskList.size()) {
            System.out.println("Aiyo, don't have this task number leh...");
        } else {
            Task tsk = taskList.get(taskNum - 1);
            tsk.markAsDone();
            System.out.println("Wah very good! I am proud that you got do your task!");
            System.out.println(tsk);
        }
    }

    public void process() {
        greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")) {
            //to list?
            if (input.toLowerCase().equals("list")) {
                listTask();
            } else {
                //could it be a done task?
                String[] cmd = input.split(" ");
                if (cmd[0].toLowerCase().equals("done")) {
                    try {
                        Integer taskNum = Integer.parseInt(cmd[1]);
                        markDone(taskNum);
                    } catch (NumberFormatException e) {
                        System.out.println("Eh I not so smart one la..." +
                                "Can you format your done task properly or not?");
                    }
                } else {
                    //simply add tasks
                    addTask(input);
                }
            }
            input = sc.nextLine();
        }
        exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.process();
    }
}
