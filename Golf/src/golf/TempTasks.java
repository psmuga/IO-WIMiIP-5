package golf;

import io2017.pierogimroku.task.api.ITaskManager;
import io2017.pierogimroku.task.api.ITaskView;
import io2017.pierogimroku.task.api.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Piotr Smuga on 06.12.2017.
 */
public class TempTasks{
    private List<Task> userTasks;
    private ITaskManager userTaskManager;
    private ITaskView taskSearcher;


    public List<Task> getUserTasks() {
        return userTasks;
    }

    public TempTasks(ITaskManager userTaskManager, ITaskView taskSearcher){
        this.userTasks = new ArrayList<>();
        this.userTaskManager = userTaskManager;
        this.taskSearcher = taskSearcher;
    }

    public void takeTasks(int id) throws NoSuchElementException{
        try {
            userTasks = taskSearcher.searchTaskByAssignedEmployee(id);
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            throw new NoSuchElementException();
        }
    }

    public void showTasks(){
        for (Task task:userTasks) {
            System.out.println(task.getName() +" | " + task.getDescription());
        }
    }
    public void editTask(int id){
        userTasks.get(id).setName("another name of task");
        userTaskManager.editTask(userTasks.get(id));
    }
}
