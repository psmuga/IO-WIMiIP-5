package com.golf.view;

import com.golf.model.ViewSetupManager;
import io.qameta.allure.junit4.DisplayName;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.GuiTest.exists;
import static org.loadui.testfx.GuiTest.find;
import static org.loadui.testfx.controls.Commons.hasText;



/**
 * Created by Michał Słowikowski.
 */


public class TaskEditTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception{
        Parent mainNode = FXMLLoader.load(ViewSetupManager.class.getResource("/TaskEdit.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
    }

    @Test
    public void windowShouldContainNameAssigneeStatusDescriptionEstimatedTimePriorityAndOwner() {
        exists("#name");
        exists("#assignee");
        exists("#status");
        exists("#description");
        exists("#estimatedTime");
        exists("#priority");
        exists("#owner");
    }

    @Test
    public void estimatedTimeFieldShouldContainOnlyNumbers(){

        TextField estimatedTime = find("#estimatedTime");
        verifyThat("#estimatedTime",hasText(""));

        estimatedTime.setText("abcd");
        verifyThat("#estimatedTime",hasText(""));

        estimatedTime.setText("123");
        verifyThat("#estimatedTime",hasText("123"));

        estimatedTime.setText("a1bc2d3");
        verifyThat("#estimatedTime",hasText("123"));
    }

    @Test
    public void priorityFieldShouldContainOnlyNumbers(){

        TextField priority = find("#priority");
        verifyThat("#priority",hasText(""));

        priority.setText("abcd");
        verifyThat("#priority",hasText(""));

        priority.setText("123");
        verifyThat("#priority",hasText("123"));

        priority.setText("a1bc2d3");
        verifyThat("#priority",hasText("123"));
    }


}