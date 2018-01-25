package com.golf.controller;

import com.golf.model.TaskModel;
import com.golf.model.TaskModelProvider;
import com.golf.model.ViewSetupManager;
import io.qameta.allure.Description;
import io2017.pierogimroku.task.api.TaskLook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.loadui.testfx.GuiTest.exists;
import static org.loadui.testfx.GuiTest.find;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testfx.api.FxAssert.verifyThat;

public class TaskOverviewControllerTest extends ApplicationTest {
    private TaskOverviewController controller;
    private ViewSetupManager viewManager;
    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(ViewSetupManager.class.getResource("/TaskOverview.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        viewManager = Mockito.mock(ViewSetupManager.class);
        controller.setViewManager(viewManager);
        when(viewManager.getPrimaryStage()).thenReturn(stage);
        when(viewManager.showTaskEdit(any())).thenReturn(true);
        HashMap users = new HashMap();
        users.put(1,"user1");
        users.put(2,"user2");
        controller.setAllUsers(users);
        stage.setScene(new Scene(mainNode));
        stage.show();
    }

    @Test
    @Description("Details should be empty")
    public void shouldDetailsBeEmptyIfClickOnEmptyTable(){
        moveTo("#taskName");
        moveBy(0,30);
        clickOn();
        verifyThat("#nameLabel", hasText(""));
        verifyThat("#assigneeLabel",hasText(""));
        verifyThat("#statusLabel",hasText(""));
        verifyThat("#descriptionLabel", hasText(""));
        verifyThat("#estimatedTimeLabel",hasText(""));
        verifyThat("#priorityLabel",hasText(""));
        verifyThat("#ownerLabel",hasText(""));
    }

    @Test
    @Description("Checks if detail view show task on click")
    public void shouldDetailsBeFilledIfClickOnTaskInTable() {
        moveTo("#taskName");
        populateTable();
        moveBy(0,30);
        clickOn();
        verifyThat("#nameLabel", hasText("Title"));
        verifyThat("#assigneeLabel",hasText("user1"));
        verifyThat("#statusLabel",hasText("ASSIGNED"));
        verifyThat("#descriptionLabel", hasText("Description"));
        verifyThat("#estimatedTimeLabel",hasText("0"));
        verifyThat("#priorityLabel",hasText("1"));
        verifyThat("#ownerLabel",hasText("user2"));
    }

    @Test
    @Description("Checks if field exist")
    public void shouldContainAllFields(){
        exists("#taskTable");
        exists("#nameLabel");
        exists("#assigneeLabel");
        exists("#statusLabel");
        exists("#descriptionLabel");
        exists("#estimatedTimeLabel");
        exists("#priorityLabel");
        exists("#ownerLabel");
        exists("#taskName");
        exists("#taskAssignee");
        exists("#taskStatus");
    }

    @Test
    @Description("Alert on edit if nothing is selected")
    public void shouldShowAlertIfNoTaskIsSelectedOnEdit(){
        clickOn("#editButton");
        TextArea contentArea = find("#alertContent");
        assertEquals("Please select a item in the table before edit.",contentArea.getText());
        clickOn("#alertOk");
    }

    @Test
    @Description("Alert on deletion if nothing is selected")
    public void shouldShowAlertIfNoTaskIsSelectedOnDelete(){
        clickOn("#deleteButton");
        TextArea contentArea = find("#alertContent");
        assertEquals("Please select a item in the table before deletion.",contentArea.getText());
        clickOn("#alertOk");
    }

    private void populateTable(){
        TaskModelProvider taskProvider = Mockito.mock(TaskModelProvider.class);
        ObservableList<TaskModel> models = FXCollections.observableArrayList();
        TaskModel model = setTask();
        models.add(model);
        when(taskProvider.getTaskData()).thenReturn(models);
        controller.setTasksProvider(taskProvider);
    }

    private TaskModel setTask(){
        TaskModel taskModel = new TaskModel();
        taskModel.setId(1);
        taskModel.setName("Title");
        taskModel.setAssignee(1);
        taskModel.setDescription("Description");
        taskModel.setStatus(TaskLook.Status.ASSIGNED);
        taskModel.setEstimatedTime(0);
        taskModel.setPriority(1);
        taskModel.setOwnerId(2);
        return taskModel;
    }
}
