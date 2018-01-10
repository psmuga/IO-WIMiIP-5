package golf.model;

import io2017.pierogimroku.task.api.TaskLook;
import javafx.beans.property.*;

/**
 * Created by Michał Słowikowski.
 */
public class TaskModel {

    private static final int assigneeIdForEmptyTask = 0;
    private static final int estimatedTimeForEmptyTask = 0;
    private static final int priorityForEmptyTask = 0;
    private static final int ownerIdForEmptyTask = 0;
    private static final int idForEmptyTask = 0;

    private StringProperty name;
    private IntegerProperty assignee;
    private ObjectProperty<TaskLook.Status> status;
    private StringProperty description;
    private IntegerProperty estimatedTime;
    private IntegerProperty priority;
    private IntegerProperty ownerId;
    private IntegerProperty id;

    public TaskModel()
    {
        this(null, assigneeIdForEmptyTask, null, null, estimatedTimeForEmptyTask,
                priorityForEmptyTask, ownerIdForEmptyTask, idForEmptyTask);
    }

    public TaskModel(String name, int idAssignee, TaskLook.Status status, String description,
                     int estimatedTime, int priority, int ownerId, int taskId)
    {
        this.name = new SimpleStringProperty(name);
        this.assignee = new SimpleIntegerProperty(idAssignee);
        this.status = new SimpleObjectProperty<>(status);
        this.description = new SimpleStringProperty(description);
        this.estimatedTime = new SimpleIntegerProperty(estimatedTime);
        this.priority = new SimpleIntegerProperty(priority);
        this.ownerId = new SimpleIntegerProperty(ownerId);
        this.id = new SimpleIntegerProperty(taskId);
    }

    public StringProperty getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public int getAssignee()
    {
        return assignee.get();
    }

    public void setAssignee(int assignee)
    {
        this.assignee.set(assignee);
    }

    public IntegerProperty assigneeProperty()
    {
        return assignee;
    }

    public TaskLook.Status getStatus()
    {
        return status.get();
    }

    public void setStatus(TaskLook.Status status)
    {
        this.status.set(status);
    }

    public ObjectProperty<TaskLook.Status> statusProperty()
    {
        return status;
    }

    public String getDescription()
    {
        return description.get();
    }

    public void setDescription(String description)
    {
        this.description.set(description);
    }

    public StringProperty descriptionProperty()
    {
        return description;
    }

    public int getEstimatedTime()
    {
        return estimatedTime.get();
    }

    public IntegerProperty estimatedTimeProperty()
    {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime)
    {
        this.estimatedTime.set(estimatedTime);
    }

    public int getPriority()
    {
        return priority.get();
    }

    public IntegerProperty priorityProperty()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority.set(priority);
    }

    public int getOwnerId()
    {
        return ownerId.get();
    }

    public IntegerProperty ownerIdProperty()
    {
        return ownerId;
    }

    public void setOwnerId(int ownerId)
    {
        this.ownerId.set(ownerId);
    }

    public int getId()
    {
        return id.get();
    }

    public IntegerProperty idProperty()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id.set(id);
    }
}
