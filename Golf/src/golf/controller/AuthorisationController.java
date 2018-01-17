package golf.controller;

import golf.model.AuthorisationManager;
import golf.model.UserWrapper;
import golf.model.ViewSetupManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * TODO
 * handleOk - obsługa przycisku
 * handleExit - obsługa wyjścia
 */

public class AuthorisationController  implements Initializable{
    @FXML
    public TextField login;
    @FXML
    public PasswordField password;
    private AuthorisationManager authorisationManager;
    private ViewSetupManager viewManager;

    public void setData(AuthorisationManager authorisationManager) {
        this.authorisationManager = authorisationManager;
    }

    public void setViewManager(ViewSetupManager manager){
        this.viewManager = manager;
    }

    public void handleSend(){
        System.out.println(login.getText() + " " + password.getText());
        logIn();
        if(authorisationManager.isCanContinue()){
            closeScene();
            viewManager.showTaskOverview();
        }
    }
    private void logIn(){
        try {
            String what = authorisationManager.getAuthorization().login(login.getText(),password.getText());
            authorisationManager.setCurrentUser(UserWrapper.Wrapp(what));
            authorisationManager.getCurrentUser().print();
            //sprawdzenie poprawnosci i wyjscie z tego okna;
            authorisationManager.setCanContinue(true);
        }
        catch (NullPointerException ex){
            System.out.println("Cannot login");
            showAlert(Alert.AlertType.ERROR,login.getScene().getWindow(),"Wrong credentials","Please try again or exit");
            authorisationManager.setCanContinue(false);
        } catch (NoClassDefFoundError ex){
            System.out.println("internal error");
            showAlert(Alert.AlertType.ERROR,login.getScene().getWindow(),"Wrong credentials","Please try again or exit");
            authorisationManager.setCanContinue(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void closeScene(){
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
//        stage.hide();
    }


    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
