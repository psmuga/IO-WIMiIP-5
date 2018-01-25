package com.golf.controller;

import com.golf.model.TaskModel;
import com.golf.model.ViewSetupManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io2017.pierogimroku.task.api.TaskLook;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;

import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.GuiTest.exists;
import static org.loadui.testfx.GuiTest.find;
import static org.loadui.testfx.controls.Commons.hasText;

/**
 * Created by Michał Słowikowski.
 */
public class TaskEditControllerTest extends ApplicationTest{
    private TaskEditController controller;

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(ViewSetupManager.class.getResource("/TaskEdit.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        HashMap users = new HashMap();
        users.put(1,"user1");
        users.put(2,"user2");
        controller.setAllUsers(users);
        stage.setScene(new Scene(mainNode));
        stage.show();
    }

    @Test
    @DisplayName("Check if window contains all fields")
    @Description("Window should contain name, assignee, status, description, estimated time, priority and owner")
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
    @DisplayName("Estimated time should be numeric")
    @Description("Estimated time field should take only numeric input")
    public void estimatedTimeFieldShouldContainOnlyNumbers(){

        TextField estimatedTime = find("#estimatedTime");
        shouldBeNumeric(estimatedTime);
    }

    @Test
    @DisplayName("Priority should be numeric")
    @Description("Priority field should take only numeric input")
    public void priorityFieldShouldContainOnlyNumbers(){

        TextField priority = find("#priority");
        shouldBeNumeric(priority);
    }

    @Test
    @DisplayName("Alert message on empty fields")
    @Description("If all fields are empty window should show message")
    public void windowShouldShowAlertIfAllFieldsAreEmpty() {
        alertShouldContainMessage(constructAlertMessage(-1));
    }

    @Test
    @DisplayName("Alert message without title error")
    @Description("Alert content shouldn't contains \"Wrong title\" message")
    public void windowShouldShowAlertIfNameWithoutFieldFilledMessage() {
        TextField name = find("#name");
        name.setText("Abc");
        alertShouldContainMessage(constructAlertMessage(0));
    }

    @Test
    @DisplayName("Alert message without assignee error")
    @Description("Alert content shouldn't contains \"no assignee\" message")
    public void windowShouldShowAlertIfAssigneWithoutFieldFilledMessage() {
        ComboBox<String> assignee = find("#assignee");
        clickOn(assignee);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        alertShouldContainMessage(constructAlertMessage(1));
    }

    @Test
    @DisplayName("Alert message without status error")
    @Description("Alert content shouldn't contains \"no status\" message")
    public void windowShouldShowAlertIfStatusWithoutFieldFilledMessage() {
        ComboBox<TaskLook.Status> status = find("#status");
        clickOn(status);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        alertShouldContainMessage(constructAlertMessage(2));
    }
    @Test
    @DisplayName("Alert message without description error")
    @Description("Alert content shouldn't contains \"no description\" message")
    public void windowShouldShowAlertIfDescriptionWithoutFieldFilledMessage() {
        TextArea description = find("#description");
        description.setText("Abc");
        alertShouldContainMessage(constructAlertMessage(3));
    }

    @Test
    @DisplayName("Alert message without estimated time error")
    @Description("Alert content shouldn't contains \"no estimated time\" message")
    public void windowShouldShowEstimatedTimeWithoutFieldFilledMessage() {
        TextField estimatedTime = find("#estimatedTime");
        estimatedTime.setText("1");

        alertShouldContainMessage(constructAlertMessage(4));
    }
    @Test
    @DisplayName("Alert message without priority error")
    @Description("Alert content shouldn't contains \"no priority\" message")
    public void windowShouldShowPriorityWithoutFieldFilledMessage() {
        TextField priority = find("#priority");
        priority.setText("1");
        alertShouldContainMessage(constructAlertMessage(5));
    }

    @Test
    @DisplayName("Alert message without owner error")
    @Description("Alert content shouldn't contains \"no owner\" message")
    public void windowShouldShowOwnerWithoutFieldFilledMessage() {
        ComboBox<String> owner = find("#owner");
        clickOn(owner);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        alertShouldContainMessage(constructAlertMessage(6));
    }

    @Test
    @DisplayName("When ok button")
    @Description("Ok button should return true if all fields are filled")
    public void okButtonShouldReturnTrueIfAllFieldsAreFilled(){
        assertFalse(controller.isOkClicked());
        TaskModel task = Mockito.mock(TaskModel.class);
        controller.setTaskToEdit(task);
        TextField name = find("#name");
        name.setText("Abc");

        ComboBox<String> assignee = find("#assignee");
        clickOn(assignee);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        ComboBox<TaskLook.Status> status = find("#status");
        clickOn(status);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        TextArea description = find("#description");
        description.setText("Abc");

        TextField estimatedTime = find("#estimatedTime");
        estimatedTime.setText("1");

        TextField priority = find("#priority");
        priority.setText("1");

        ComboBox<String> owner = find("#owner");
        clickOn(owner);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        clickOn("#ok");
        assertTrue(controller.isOkClicked());
    }

    @Step
    private void shouldBeNumeric(TextField textFieldToCheck)
    {
        String id = "#"+textFieldToCheck.getId();
        verifyThat(id,hasText(""));

        textFieldToCheck.setText("abcd");
        verifyThat(id,hasText(""));

        textFieldToCheck.setText("123");
        verifyThat(id,hasText("123"));

        textFieldToCheck.setText("a1bc2d3");
        verifyThat(id,hasText("123"));
    }

    @Step
    private void alertShouldContainMessage(String message)
    {
        clickOn("#ok");
        TextArea contentArea = find("#alertContent");
        assertEquals(contentArea.getText(),message);
        clickOn("#alertOk");
    }

    @Step
    private String constructAlertMessage(int skipId) {
        String fullMessage[] = {"Wrong title!\n",
                "Set assignee!\n",
                "Set status!\n",
                "Wrong description!\n",
                "Set estimated time!\n",
                "Set priority\n",
                "Set ownership\n"};
        String message ="";
        for(int i = 0; i < fullMessage.length; i++){
            if(i!=skipId) {
                message += fullMessage[i];
            }
        }

        return message;
    }
}
