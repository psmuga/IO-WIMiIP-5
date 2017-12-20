package golf;

import io2017.pierogimroku.task.api.ITaskManager;
import io2017.pierogimroku.task.api.ITaskView;
import io2017.pierogimroku.task.api.TaskNotFoundException;
import io2017.pierogimroku.task.api.TaskWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Piotr Smuga on 06.12.2017.
 */
public class TempTasks{
    private List<TaskWrapper> userTasks;
    private ITaskManager userTaskManager;
    private ITaskView taskSearcher;


    public List<TaskWrapper> getUserTasks() {
        return userTasks;
    }

    public TempTasks(ITaskManager userTaskManager, ITaskView taskSearcher){
        this.userTasks = new ArrayList<>();
        this.userTaskManager = userTaskManager;
        this.taskSearcher = taskSearcher;
    }

    public void takeUserTasks(int id) throws NoSuchElementException{
        try {
            userTasks = taskSearcher.searchTaskByAssignedEmployee(id);
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            throw new NoSuchElementException();
        }
    }

    public void showTasks(){
        for (TaskWrapper task: userTasks) {
            System.out.println(task.getId() +" | " +task.getName() +" | " + task.getDescription());
        }
    }
    public void editTaskName(int id,String name){
        userTasks.get(id).setName(name);
        try {
            userTaskManager.editTask(userTasks.get(id));
        } catch ( TaskNotFoundException ex){
            System.out.println("cannot edit task name");
        }

    }
}
