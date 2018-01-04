package golf.model;

import com.spanishinquisition.functions.IAuth;

/**
 * Created by Piotr Smuga on 04.01.2018.
 */
public class AuthorizationManager {
    private IAuth authorization;
    private User currentUser;
    private boolean canContinue = false;
    private static AuthorizationManager instance;

    public static AuthorizationManager getInstance(){
        if(instance == null){
            instance = new AuthorizationManager();
        }

        return instance;
    }

    public boolean isCanContinue() {
        return canContinue;
    }

    public void setCanContinue(boolean canContinue) {
        this.canContinue = canContinue;
    }

    private AuthorizationManager() {
        this.authorization = new IAuth() {};
        this.currentUser = new User();
        this.canContinue = false;

    }

    public IAuth getAuthorization() {
        return authorization;
    }

    public void setAuthorization(IAuth authorization) {
        this.authorization = authorization;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
