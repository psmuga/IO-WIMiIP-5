package com.golf.controller;

import com.golf.model.TaskModel;
import com.golf.model.TaskModelProvider;
import com.golf.model.ViewSetupManager;
import io2017.pierogimroku.task.api.TaskNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class TaskOverviewController {

    @FXML
    private TableColumn<TaskModel,String> taskName;
    @FXML
    private TableColumn<TaskModel,String> taskAssignee;
    @FXML
    private TableColumn<TaskModel,String> taskStatus;
    @FXML
    private TableView<TaskModel> taskTable;

    @FXML
    private Label nameLabel;
    @FXML
    private Label assigneeLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private TextArea descriptionLabel;
    @FXML
    private Label estimatedTimeLabel;
    @FXML
    private Label priorityLabel;
    @FXML
    private Label ownerLabel;

    private ViewSetupManager viewManager;
    private TaskModelProvider taskProvider;
    private Map<Integer,String> allUsers;

    public void setViewManager(ViewSetupManager manager){
        this.viewManager = manager;
    }

    public void setTasksProvider(TaskModelProvider tasksProvider){
        this.taskProvider=tasksProvider;
        tasksProvider.refreshTaskData();
        taskTable.setItems(tasksProvider.getTaskData());
    }

    public void setAllUsers(Map<Integer,String> allUsers){
        this.allUsers = allUsers;
    }

    @FXML
    private void initialize() {
        taskName.setCellValueFactory(cell->
                new SimpleStringProperty(
                        cell.getValue().getName()
                )
        );
        taskAssignee.setCellValueFactory(cell->
                new SimpleStringProperty(
                        allUsers.get(cell.getValue().getAssignee())
                )
        );
        taskStatus.setCellValueFactory(cell->
                new SimpleStringProperty(
                        cell.getValue().getStatus().name()
                )
        );
        taskTable.getSelectionModel().selectedItemProperty().addListener((
                (observable, oldValue, newValue) -> setSelectedTask(newValue)
        ));
        clearSelectedTask();
    }

    @FXML
    private void handleNewTask(){
        TaskModel newTask = new TaskModel();
        boolean confirmed = viewManager.showTaskEdit(newTask);
        if(confirmed){
            taskProvider.addNewTask(newTask);
            refreshTableItems();
        }
    }

    @FXML
    private void handleEditTask() throws TaskNotFoundException
    {
         TaskModel selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if(selectedTask != null){
            boolean confirmed = viewManager.showTaskEdit(selectedTask);
            if(confirmed){
                setSelectedTask(selectedTask);
                taskProvider.editTask(selectedTask);
                refreshTableItems();
            }
        }
    }

    @FXML
    private void handleDeleteTask() throws TaskNotFoundException
    {
            TaskModel selectedTask = taskTable.getSelectionModel().getSelectedItem();
            if(selectedTask != null){
                taskProvider.deleteTask(selectedTask);
                refreshTableItems();
            } else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(viewManager.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Item Selected");
                alert.setContentText("Please select a item in the table.");

                alert.showAndWait();
            }
    }

    private void setSelectedTask(TaskModel selectedTask){
        if(selectedTask != null) {
            String name = selectedTask.getName();
            String assignee = allUsers.get(selectedTask.getAssignee());
            String status = selectedTask.getStatus().name();
            String description = selectedTask.getDescription();
            String estimatedTime = Integer.toString(selectedTask.getEstimatedTime());
            String priority = Integer.toString(selectedTask.getPriority());
            String owner = allUsers.get(selectedTask.getOwnerId());

            nameLabel.setText(name);
            assigneeLabel.setText(assignee);
            statusLabel.setText(status);
            descriptionLabel.setText(description);
            estimatedTimeLabel.setText(estimatedTime);
            priorityLabel.setText(priority);
            ownerLabel.setText(owner);
        } else {
            clearSelectedTask();
        }
    }

    private void clearSelectedTask(){
        nameLabel.setText("");
        assigneeLabel.setText("");
        statusLabel.setText("");
        descriptionLabel.setText("");
        estimatedTimeLabel.setText("");
        priorityLabel.setText("");
        ownerLabel.setText("");
    }

    private void refreshTableItems()
    {
        taskProvider.refreshTaskData();
        taskTable.setItems(taskProvider.getTaskData());
    }

    public void handleLogout(ActionEvent actionEvent) {
        try {
            Stage stage;
            stage = (Stage) taskTable.getScene().getWindow();
            stage.close();
            viewManager.showLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
