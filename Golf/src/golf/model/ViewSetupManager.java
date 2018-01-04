package golf.model;

import com.spanishinquisition.functions.IAuth;
import golf.controller.AuthorisationController;
import golf.controller.EditTaskController;
import golf.controller.TaskOverviewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Michał Słowikowski.
 */
public class ViewSetupManager {

    private Stage primaryStage;
    private FXMLLoader fxmlLoader;

    public ViewSetupManager(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public void showLogin() throws IOException {
        try{
            IAuth authorization = new IAuth() {};
            User currentUser = new User();
            boolean canContinue = false;

            setPrimaryStageSceneFromPathToFxmlFile("/golf/view/Login.fxml",primaryStage);

            AuthorisationController controller = fxmlLoader.getController();
            controller.setViewManager(this);
            controller.setData(authorization, currentUser, canContinue);
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showTaskOverview(){
        try{
            TaskModelProvider taskModelProvider = TaskModelProvider.getInstance();
            UsersProvider usersProvider = UsersProvider.getInstance();
            usersProvider.refreshUsersData();
            setPrimaryStageSceneFromPathToFxmlFile("/golf/view/TaskOverview.fxml",primaryStage);
            TaskOverviewController controller = fxmlLoader.getController();
            controller.setViewManager(this);
            controller.setTasksProvider(taskModelProvider);
            controller.setAllUsers(usersProvider.getUsers());

            primaryStage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public boolean showTaskEdit(TaskModel taskToEdit){
        try {
            UsersProvider usersProvider = UsersProvider.getInstance();
            usersProvider.refreshUsersData();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            setPrimaryStageSceneFromPathToFxmlFile("/golf/view/TaskEdit.fxml",dialogStage);


            EditTaskController controller = fxmlLoader.getController();
            controller.setAllUsers(usersProvider.getUsers());
            controller.setTaskToEdit(taskToEdit);
            controller.setDialogStage(dialogStage);


            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setPrimaryStageSceneFromPathToFxmlFile(String path, Stage stage) throws IOException{
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ViewSetupManager.class.getResource(path));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
