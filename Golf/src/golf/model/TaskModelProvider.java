package golf.model;

import io2017.pierogimroku.task.api.TaskLook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Michał Słowikowski.
 */
public class TaskModelProvider {
    private static TaskModelProvider instance;
    private ObservableList<TaskModel> taskData;

    private TaskModelProvider(){
        taskData = FXCollections.observableArrayList();
    }

    public static TaskModelProvider getInstance(){
        if(instance == null){
            instance = new TaskModelProvider();
        }

        return instance;
    }

    /**TODO
     * zaimplementować tak aby odzyskiwała listę tasków korzystając z metod dostarczonych przez Pierogi Mroku
     */
    public void refreshTaskData(){
        taskData = FXCollections.observableArrayList();
        taskData.add(new TaskModel("Task1",2, TaskLook.Status.NEW,"Opis jest bardzo wazny"));
        taskData.add(new TaskModel("Different name",1, TaskLook.Status.ASSIGNED,"Opis jest wazny"));
    }

    /**TODO
     * zaimplementować tak aby dodać nowy task korzystając z metod dostarczonych przez Pierogi Mroku
     */
    public void addNewTask(TaskModel newTask){
        taskData.add(newTask);
    }

    /**TODO
     * zaimplementować tak aby edytować nowy task korzystając z metod dostarczonych przez Pierogi Mroku
     */
    public void editTask(TaskModel taskToEdit){
        System.out.println("Udało zedytować się taska");
    }

    public ObservableList<TaskModel> getTaskData(){
        return taskData;
    }
}
