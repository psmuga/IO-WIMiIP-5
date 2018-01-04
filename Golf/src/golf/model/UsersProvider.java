package golf.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michał Słowikowski.
 */
public class UsersProvider {
    private Map<Integer,String> users;
    private static UsersProvider instance;

    public static UsersProvider getInstance(){
        if(instance == null){
            instance = new UsersProvider();
        }

        return instance;
    }

    /**TODO
     * zaimplementować tak aby odzyskiwała listę tasków korzystając z metod dostarczonych przez Pierogi Mroku
     */
    public void refreshUsersData(){
        users = new HashMap<>();
        users.put(1,"User1");
        users.put(2,"User2");
        users.put(4,"User4");
    }

    public Map<Integer,String> getUsers(){
        return users;
    }
}
