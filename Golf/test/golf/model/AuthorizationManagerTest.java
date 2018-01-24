package golf.model;

import golf.controller.AuthorisationController;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Piotr Smuga on 23.01.2018.
 */
public class AuthorizationManagerTest {
    AuthorisationManager manager;
    @Test
    public void settingCanContinueShouldSetCorrectly(){
        manager.setCanContinue(true);

        assertThat(manager.isCanContinue(), is(true));
    }
    @Test
    public void settingUserShouldFinishCorrectly(){
        User user = new User("user",1,2,"hash");
        manager.setCurrentUser(user);

        assertThat(manager.getCurrentUser(),is(user));
    }
    @Before
    public void setUp () throws Exception {
        manager = AuthorisationManager.getInstance();
    }
}
