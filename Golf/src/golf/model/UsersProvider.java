package golf.model;

import usermanagement.UserManagement;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michał Słowikowski.
 */
public class UsersProvider {
    private Map<Integer,String> users;
    private static UsersProvider instance;
    private static UserManagement userManager;

    public static UsersProvider getInstance(){
        if(instance == null){
            instance = new UsersProvider();
        }

        return instance;
    }

    /**TODO
     * zaimplementować tak aby odzyskiwała listę użytko
     */
    public void refreshUsersData(){
        users = new HashMap<>();
        users.put(1,"User1");
        users.put(2,"User2");
        users.put(4,"User4");

//        users = userManager.getUsersMap();
    }

    public Map<Integer,String> getUsers(){
        return users;
    }
}
