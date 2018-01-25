package com.golf.model;

import com.golf.controller.AuthorisationController;
import com.golf.controller.TaskEditController;
import com.golf.controller.TaskOverviewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
            AuthorisationManager authorisationManager = AuthorisationManager.getInstance();
            setPrimaryStageSceneFromPathToFxmlFile("/Login.fxml",primaryStage);
            AuthorisationController controller = fxmlLoader.getController();
            controller.setViewManager(this);
            controller.setData(authorisationManager);
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
            setPrimaryStageSceneFromPathToFxmlFile("/TaskOverview.fxml",primaryStage);
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
            setPrimaryStageSceneFromPathToFxmlFile("/TaskEdit.fxml",dialogStage);


            TaskEditController controller = fxmlLoader.getController();
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
