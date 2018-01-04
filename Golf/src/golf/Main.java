package golf;

/**
 * WAŻNE, metody można rozdzielić pomiędzy kolejne obiekty np LoginManager
 */

import com.spanishinquisition.functions.IAuth;
import golf.controller.AuthorisationController;
import golf.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * TODO
 * start - metoda inicjująca core, ustawiająca okno (primary stage), oraz ukazuje okno logowania
 * metody show - (showEditTask, showTaskOverview) - wyświetlają okna z danymi (na razie można zrobić na logi lub system.out)
 * loadTasks - wczytywanie tasków, przeciążenie dla wszystkich tasków i z użytkownikiem jako argumentem taki konkretnego użytkownika
 * editTask - wysyła zedytowany task
 * authorize - metoda korzystająca z wystawionego interfejsu sprawdza czy dane są poprawne i zwraca zalogowanego użytkownika
 * loadUsers - wczytuje listę userów następnie za pomocą UserWraper  zrzuca ich na konkretnych userów
 * loadCurrentUser - wczytuje zalogowanego usera
 */
public class Main  extends Application{
    private IAuth authorization;
    private User currentUser;
    private Stage primaryStage;
    private Parent root;
    private boolean canContinue = false;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Golf III GTI");
        authorization = new IAuth() {};
        currentUser = new User();

        //this.root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/Login.fxml"));
        this.root = fxmlLoader.load();
//        AuthorisationController controller = fxmlLoader.<AuthorisationController>getController();
//        controller.setData(this.authorization, this.currentUser);


        logIn(fxmlLoader);

    }
    public void logIn(FXMLLoader fxmlLoader){

        AuthorisationController controller = fxmlLoader.getController();
        controller.setData(this.authorization, this.currentUser, this.canContinue);
        primaryStage.setScene(new Scene(root, 585, 540));
        primaryStage.show();

    }


}