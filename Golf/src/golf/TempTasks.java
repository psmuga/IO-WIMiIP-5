package golf;

import io2017.pierogimroku.task.ORMLiteTaskManager;
import io2017.pierogimroku.task.api.ITaskManager;
import io2017.pierogimroku.task.api.ITaskView;
import io2017.pierogimroku.task.api.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr Smuga on 06.12.2017.
 */
public class TempTasks {
    private List<Task> userTasks;
    ITaskManager manager = new ORMLiteTaskManager();
    ITaskView temp = new ORMLiteTaskManager();
    public TempTasks() {
        userTasks = new ArrayList<>();

    }
    public void TakeTasks(int id){

        try {
            userTasks = temp.searchTaskByAssignedEmployee(id);
        } catch (Exception ex){
            //logging error
            System.out.println("Error: " + ex.getMessage());
        }

    }
    public void ShowTasks(){
        for (Task task:userTasks) {
            System.out.println(task.getName() +" | " + task.getDescription());
        }
    }
    public void EditTask(int id){
        userTasks.get(id).setName("another name of task");
        manager.editTask(userTasks.get(id));
    }
}
