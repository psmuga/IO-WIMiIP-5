package golf;

/**
 * Created by Piotr Smuga on 06.12.2017.
 */
public class User {
    private String username;
    private int userId;
    private int role;
    private String hashCode;

    public User() {
    }

    public User(String username, int userId, int role, String hashCode) {
        this.username = username;
        this.userId = userId;
        this.role = role;
        this.hashCode = hashCode;
    }
    public void print(){
        System.out.println("UserID: " + userId);
        System.out.println("Username: " + username);
        System.out.println("Role: " + role);
        System.out.println("HashCode: " + hashCode);
    }
}
