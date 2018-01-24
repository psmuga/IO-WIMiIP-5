package golf.model;

import io2017.pierogimroku.task.api.TaskLook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * @author dawid.necka on 07.01.18.
 */
public class TaskModelTransformer {
    public static TaskModel transformLook(TaskLook taskLook)
    {
        TaskModel taskModel = new TaskModel();
        taskModel.setId(taskLook.getId());
        taskModel.setAssignee(taskLook.getAssignedId());
        taskModel.setName(taskLook.getName());
        taskModel.setStatus(taskLook.getStatus());
        taskModel.setDescription(taskLook.getDescription());
        taskModel.setEstimatedTime(taskLook.getTimeEstimate());
        taskModel.setPriority(taskLook.getPriority());
        taskModel.setOwnerId(taskLook.getOwnerId());

        return taskModel;
    }

    public static TaskLook transformModel(TaskModel taskModel)
    {
        TaskLook taskLook = new TaskLook();

        taskLook.setId(taskModel.getId());
        taskLook.setName(taskModel.getName());
        taskLook.setAssignedId(taskModel.getAssignee());
        taskLook.setStatus(taskModel.getStatus());
        taskLook.setDescription(taskModel.getDescription());
        taskLook.setTimeEstimate(taskModel.getEstimatedTime());
        taskLook.setPriority(taskModel.getPriority());
        taskLook.setOwnerId(taskModel.getOwnerId());

        return taskLook;
    }

    public static ObservableList<TaskModel> transformLooks(List<TaskLook> taskLooks)
    {
        ObservableList<TaskModel> models = FXCollections.observableArrayList();

        taskLooks.forEach(look -> models.add(transformLook(look)));

        return models;
    }
}

