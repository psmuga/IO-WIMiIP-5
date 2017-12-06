package golf;

import com.spanishinquisition.implementation.IAuth;

import java.util.Scanner;

/**
 * Created by Piotr Smuga on 06.12.2017.
 */
public class Controller {

    IAuth authorization;

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.LogIn();
        controller.authorization = new IAuth();

        TempTasks tempTasks = new TempTasks();
        tempTasks.TakeTasks(0);
        tempTasks.ShowTasks();

        tempTasks.EditTask(0);
    }
    private void LogIn(){
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
