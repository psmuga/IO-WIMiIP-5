package golf.controller;

import golf.model.AuthorisationManager;
import golf.model.ViewSetupManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.testfx.api.FxAssert.verifyThat;

/**
 * Created by Piotr Smuga on 22.01.2018.
 */

public class AuthorisationControllerTest extends ApplicationTest {
    AuthorisationController controller;
    ViewSetupManager view;
    @Mock
    AuthorisationManager manager;


    @Override
    public void start(Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(getClass().getClassLoader().getResource("golf/view/Login.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        view = new ViewSetupManager(stage);
        stage.toFront();
    }
    @Before
    public void setUp () throws Exception {
        controller = new AuthorisationController();
        manager = AuthorisationManager.getInstance();
        controller.setData(manager);

        controller.setViewManager(view);
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    @Test
    public void onGoodCredentialsShouldLogin() {
        clickOn("#login");
        write("user1");
        clickOn("#password");
        write("password");
        clickOn("#ok");
        assertThat(controller.isLogged(),is(true));
        //assertTrue(controller.isLogged());
    }
    @Test
    public void onWrongCredentialsShouldNotContinue () {
        clickOn("#login");
        write("user1user");
        clickOn("#password");
        write("password");
        clickOn("#ok");
        assertThat(controller.isLogged(),is(true));
    }
    @Test
    public void onEmptyFieldsShouldNotContinue () {
        clickOn("#ok");
        assertThat(controller.isLogged(), is(false));
    }
    @Test
    public void windowShouldContainPasswordLoginAndButton() {
        verifyThat("#ok", hasText("Login"));
        verifyThat("#login",hasText(""));
        verifyThat("#password",hasText(""));
    }
}
