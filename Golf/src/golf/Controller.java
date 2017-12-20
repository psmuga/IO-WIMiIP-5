package golf;


import com.spanishinquisition.functions.IAuth;
 import io2017.pierogimroku.task.ORMLiteTaskManager;
import io2017.pierogimroku.task.api.ITaskManager;
import io2017.pierogimroku.task.api.ITaskView;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Piotr Smuga on 06.12.2017.
 */
public class Controller {

    private IAuth authorization;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.authorization = new IAuth() {};
        int selection = 1;
        do {
            if (controller.logIn()){
                ITaskManager userTaskManager = new ORMLiteTaskManager();
                ITaskView taskSearcher = new ORMLiteTaskManager();


                TempTasks tempTasks = new TempTasks(userTaskManager, taskSearcher);
                tempTasks.takeUserTasks(0);
                tempTasks.showTasks();
//                tempTasks.editTask(0);
            }
            else {
                System.out.println("Ups :( You cannot get access to this secret information!");
                System.out.println("To quit type 0!");
                selection = input.nextInt();
            }
        } while (selection!=0);



    }

    private boolean logIn(){
        String login;
        String password;
        System.out.print("Welcome! \nLogin: ");

        login = input.next();
        System.out.print("Password: ");
        password = input.next();
        try {
            String what = authorization.login(login,password);
            System.out.println(what);
        }
        catch (NullPointerException ex){
            System.out.println("Cannot login");
            return false;
        } catch (NoClassDefFoundError ex){
            System.out.println("internal error");
            return false;
        }
        return true;
    }
}
