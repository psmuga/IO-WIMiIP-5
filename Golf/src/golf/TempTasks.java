package golf;

import io2017.pierogimroku.task.ORMLiteTaskManager;
import io2017.pierogimroku.task.api.ITaskView;
import io2017.pierogimroku.task.api.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr Smuga on 06.12.2017.
 */
public class TempTasks {
    private List<Task> userTasks;
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
        //send this one task to temp, but now we have problem , we cannot execute function editTask(), and i don't know why, sad but happens
    }
}
