package com.golf.model;

import io2017.pierogimroku.task.ORMLiteTaskManager;
import io2017.pierogimroku.task.api.TaskLook;
import io2017.pierogimroku.task.api.TaskNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TaskModelProvider {
    private static TaskModelProvider instance;
    private ObservableList<TaskModel> taskData;
    private ORMLiteTaskManager taskManager = new ORMLiteTaskManager();

    private TaskModelProvider()
    {
        taskData = FXCollections.observableArrayList();
    }

    public static TaskModelProvider getInstance()
    {
        if (instance == null)
        {
            instance = new TaskModelProvider();
        }

        return instance;
    }

    public void refreshTaskData()
    {
        List<TaskLook> looks = taskManager.getAll();
        taskData = TaskModelTransformer.transformLooks(looks);
    }

    public void addNewTask(TaskModel newTask)
    {
        taskManager.addTask(TaskModelTransformer.transformModel(newTask));
    }

    public void editTask(TaskModel taskToEdit) throws TaskNotFoundException
    {
        taskManager.editTask(TaskModelTransformer.transformModel(taskToEdit));
    }

    public void deleteTask(TaskModel taskToDelete) throws TaskNotFoundException
    {
        taskManager.removeTask(TaskModelTransformer.transformModel(taskToDelete));
    }

    public ObservableList<TaskModel> getTaskData()
    {
        return taskData;
    }
}
