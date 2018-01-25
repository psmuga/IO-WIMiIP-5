package com.golf.controller;

import com.golf.model.PopUpAlert;
import com.golf.model.TaskModel;
import io2017.pierogimroku.task.api.TaskLook;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Map;

public class TaskEditController {
    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> assignee;
    @FXML
    private ComboBox<TaskLook.Status> status;
    @FXML
    private TextArea description;
    @FXML
    private TextField estimatedTime;
    @FXML
    public TextField priority;
    @FXML
    public ComboBox<String> owner;

    private Stage dialogStage;
    private TaskModel task;
    private Map<Integer,String> allUsers;
    private boolean okClicked = false;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setAllUsers(Map<Integer,String> allUsers){
        this.allUsers = allUsers;
        for (int key: allUsers.keySet()) {
            assignee.getItems().add(allUsers.get(key));
            owner.getItems().add(allUsers.get(key));
        }
    }
    public void setTaskToEdit(TaskModel taskToEdit){
        this.task = taskToEdit;

        String taskToEditName = taskToEdit.getName();
        TaskLook.Status taskToEditStatus = taskToEdit.getStatus();
        String taskToEditDescription = taskToEdit.getDescription();
        int userID = taskToEdit.getAssignee();
        String taskToEditAssignee = allUsers.get(userID);
        String taskToEditEstimatedTime = Integer.toString(taskToEdit.getEstimatedTime());
        String taskToEditPriority = Integer.toString(taskToEdit.getPriority());
        int ownerId = taskToEdit.getOwnerId();
        String taskToEditOwner = allUsers.get(ownerId);

        name.setText(taskToEditName);
        assignee.setValue(taskToEditAssignee);
        status.setValue(taskToEditStatus);
        description.setText(taskToEditDescription);
        estimatedTime.setText(taskToEditEstimatedTime);
        priority.setText(taskToEditPriority);
        owner.setValue(taskToEditOwner);
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void initialize() {
        status.getItems().addAll(TaskLook.Status.values());
        setTextFieldAsNumeric(estimatedTime);
        setTextFieldAsNumeric(priority);
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            task.setName(name.getText());
            task.setStatus(status.getValue());
            task.setDescription(description.getText());
            task.setEstimatedTime(Integer.parseInt(estimatedTime.getText()));
            task.setPriority(Integer.parseInt(priority.getText()));
            task.setAssignee(getUserIdFromName(assignee.getValue()));
            task.setOwnerId(getUserIdFromName(owner.getValue()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private void setTextFieldAsNumeric(TextField fieldToSet){
        fieldToSet.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")) {
                fieldToSet.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private boolean isInputValid(){

        String errorMessage = "";

        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage += "Wrong title!\n";
        }
        if(assignee.getValue() == null){
            errorMessage += "Set assignee!\n";
        }
        if(status.getValue() == null){
            errorMessage += "Set status!\n";
        }
        if(description.getText() == null || description.getText().length() == 0) {
            errorMessage += "Wrong description!\n";
        }
        if(estimatedTime.getText() == null || estimatedTime.getText().length() == 0) {
            errorMessage += "Set estimated time!\n";
        }
        if(priority.getText() == null || priority.getText().length() == 0) {
            errorMessage += "Set priority\n";
        }
        if(owner.getValue() == null) {
            errorMessage += "Set ownership\n";
        }

        TextArea content = new TextArea(errorMessage);
        content.setId("alertContent");
        if(errorMessage.length() != 0){
            Alert alert = new PopUpAlert.AlertBuilder(Alert.AlertType.ERROR,dialogStage)
                    .title("Invalid Fields")
                    .header("Please correct invalid fields")
                    .content(content)
                    .build()
                    .get();
                    new Alert(Alert.AlertType.ERROR);

            Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setId("alertOk");

            alert.showAndWait();

            return false;
        }

        return true;
    }

    private int getUserIdFromName(String name) throws NullPointerException {

        for(Map.Entry<Integer,String> entry : allUsers.entrySet()){
            if(name.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return -1;
    }
}
