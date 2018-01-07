package golf.controller;

import golf.model.TaskModel;
import golf.model.TaskModelProvider;
import golf.model.ViewSetupManager;
import io2017.pierogimroku.task.api.TaskNotFoundException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Map;


/**
 * TODO
 *showTaskDetails(Task task) - pokazuje wszystkie informacje danego taska
 * metody FXML-owe mogą być na razie proste outy
 * deleteTask - obsługa przycisku usuwania taska
 * newTask - przycisk dodawanie taksa
 * editTask - wywołanie widoku eycji taska
 */
public class TaskOverviewController {

    @FXML
    private TableColumn<TaskModel,String> sideColumn;
    @FXML
    private TableView<TaskModel> taskTable;

    @FXML
    private Label nameLabel;
    @FXML
    private Label assigneeLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label descriptionLabel;

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
            sideColumn.setCellValueFactory(cell->cell.getValue().getName());
            clearSelectedTask();
            taskTable.getSelectionModel().selectedItemProperty().addListener((
                    (observable, oldValue, newValue) -> setSelectedTask(newValue)
            ));
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
            String name = selectedTask.getName().get();
            String assignee = allUsers.get(selectedTask.getAssignee());
            String status = selectedTask.getStatus().name();
            String description = selectedTask.getDescription();

            nameLabel.setText(name);
            assigneeLabel.setText(assignee);
            statusLabel.setText(status);
            descriptionLabel.setText(description);
        }
    }

    private void clearSelectedTask(){
        nameLabel.setText("");
        assigneeLabel.setText("");
        statusLabel.setText("");
        descriptionLabel.setText("");
    }

    private void refreshTableItems()
    {
        taskProvider.refreshTaskData();
        taskTable.setItems(taskProvider.getTaskData());
    }
}
