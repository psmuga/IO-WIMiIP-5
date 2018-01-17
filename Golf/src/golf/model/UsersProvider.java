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
    private static UserManagement userManager = UserManagement.getInstance();

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
        users = userManager.getUsersMap();
    }

    public Map<Integer,String> getUsers(){
        return users;
    }
}
