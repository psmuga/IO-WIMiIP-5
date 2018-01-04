package golf.model;

import io2017.pierogimroku.task.api.TaskLook;
import javafx.beans.property.*;

/**
 * Created by Michał Słowikowski.
 */
public class TaskModel {

    private static final int assigneeIdForEmptyTask = 0;

    private StringProperty name;
    private IntegerProperty assignee;
    private ObjectProperty<TaskLook.Status> status;
    private StringProperty description;

    public TaskModel(){
        this(null,assigneeIdForEmptyTask,null,null);
    }

    public TaskModel(String name, int idAssignee, TaskLook.Status status, String description) {
        this.name = new SimpleStringProperty(name);
        this.assignee = new SimpleIntegerProperty(idAssignee);
        this.status = new SimpleObjectProperty<>(status);
        this.description = new SimpleStringProperty(description);
    }

    public StringProperty getName(){
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAssignee() {
        return assignee.get();
    }

    public IntegerProperty assigneeProperty() {
        return assignee;
    }

    public void setAssignee(int assignee) {
        this.assignee.set(assignee);
    }

    public TaskLook.Status getStatus() {
        return status.get();
    }

    public ObjectProperty<TaskLook.Status> statusProperty() {
        return status;
    }

    public void setStatus(TaskLook.Status status) {
        this.status.set(status);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
