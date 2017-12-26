package golf.view;


import com.spanishinquisition.functions.IAuth;
import golf.TempTasks;
import golf.model.User;
import golf.model.UserWrapper;
import io2017.pierogimroku.task.ORMLiteTaskManager;
import io2017.pierogimroku.task.api.ITaskManager;
import io2017.pierogimroku.task.api.ITaskView;

import java.util.Scanner;

/**
 * Created by Piotr Smuga on 06.12.2017.
 */
public class Controller {

    private IAuth authorization;
    static Scanner input = new Scanner(System.in);
    ITaskManager userTaskManager;
    ITaskView taskSearcher;
    TempTasks tempTasks;
    User currentUser;

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.authorization = new IAuth() {};
        controller.currentUser = new User();


        int selection = 0;
        do {
            if (controller.logIn()){
                controller.userTaskManager = new ORMLiteTaskManager();
                controller.taskSearcher = new ORMLiteTaskManager();
                controller.tempTasks = new TempTasks(controller.userTaskManager, controller.taskSearcher);

                controller.performOperation();

                selection = 0;
            }
            else {
                System.out.println("Ups :( You cannot get access to this secret information!");
                System.out.println("To quit type 0!");
                selection = input.nextInt();
            }
        } while (selection!=0);



    }
    private void performOperation(){

        tempTasks.takeUserTasks(0);
        tempTasks.showTasks();
//                tempTasks.editTask(0);
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
            currentUser = UserWrapper.Wrapp(what);
            currentUser.print();
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
