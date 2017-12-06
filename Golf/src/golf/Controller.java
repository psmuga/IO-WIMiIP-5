package golf;

import io2017.pierogimroku.task.api.Task;

import java.util.Scanner;

/**
 * Created by Piotr Smuga on 06.12.2017.
 */
public class Controller {

    //token here

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.LogIn();

        TempTasks tempTasks = new TempTasks();
        tempTasks.TakeTasks(0);
        tempTasks.ShowTasks();

    }
    private void LogIn(){
        String login;
        String password;
        System.out.println("Welcome! \nLogin: ");
        Scanner input = new Scanner(System.in);
        login=input.next();
        System.out.println("Password: ");
        password = input.next();

    }

}
