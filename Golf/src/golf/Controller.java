package golf;

import com.spanishinquisition.implementation.IAuth;
import io2017.pierogimroku.task.ORMLiteTaskManager;
import io2017.pierogimroku.task.api.ITaskManager;
import io2017.pierogimroku.task.api.ITaskView;

import java.util.Scanner;

/**
 * Created by Piotr Smuga on 06.12.2017.
 */
public class Controller {

    private IAuth authorization;

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.logIn();
        controller.authorization = new IAuth();

        ITaskManager userTaskManager = new ORMLiteTaskManager();
        ITaskView taskSearcher = new ORMLiteTaskManager();

        TempTasks tempTasks = new TempTasks(userTaskManager, taskSearcher);
        tempTasks.takeTasks(0);
        tempTasks.showTasks();
        tempTasks.editTask(0);
    }

    private void logIn(){
        String login;
        String password;
        System.out.print("Welcome! \nLogin: ");
        Scanner input = new Scanner(System.in);
        login=input.next();
        System.out.print("Password: ");
        password = input.next();
        try {
            String what = authorization.login(login,password);
            System.out.println(what);
        }catch (NullPointerException ex){
            System.out.println("Cannot login ");
        }
    }
}
