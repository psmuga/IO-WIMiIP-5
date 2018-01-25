package com.golf;

import com.golf.model.ViewSetupManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Michał Słowikowski.
 */
public class Main extends Application{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewSetupManager view = new ViewSetupManager(primaryStage);
        view.showLogin();
    }
}
