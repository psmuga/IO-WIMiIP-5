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
        taskModel.setAssignee(taskLook.getAssignedId());
        taskModel.setDescription(taskLook.getDescription());
        taskModel.setName(taskLook.getName());
        taskModel.setStatus(taskLook.getStatus());
        taskModel.setId(taskLook.getId());

        return taskModel;
    }

    public static TaskLook transformModel(TaskModel taskModel)
    {
        TaskLook taskLook = new TaskLook();

        taskLook.setAssignedId(taskModel.getAssignee());
        taskLook.setDescription(taskModel.getDescription());
        taskLook.setName(taskModel.getName().getValue());
        taskLook.setStatus(taskModel.getStatus());
        taskLook.setTimeEstimate(taskModel.getEstimatedTime());
        taskLook.setOwnerId(taskModel.getOwnerId());
        taskLook.setPriority(taskModel.getPriority());
        taskLook.setId(taskModel.getId());

        return taskLook;
    }

    public static ObservableList<TaskModel> transformLooks(List<TaskLook> taskLooks)
    {
        ObservableList<TaskModel> models = FXCollections.observableArrayList();

        taskLooks.forEach(look -> models.add(transformLook(look)));

        return models;
    }
}

