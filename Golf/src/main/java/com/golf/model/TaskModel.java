package com.golf.model;

import io2017.pierogimroku.task.api.TaskLook;

public class TaskModel {

    private static final int assigneeIdForEmptyTask = 0;
    private static final int estimatedTimeForEmptyTask = 0;
    private static final int priorityForEmptyTask = 0;
    private static final int ownerIdForEmptyTask = 0;
    private static final int idForEmptyTask = 0;

    private String name;
    private int assignee;
    private TaskLook.Status status;
    private String description;
    private int estimatedTime;
    private int priority;
    private int ownerId;
    private int id;

    public TaskModel()
    {
        this(null, assigneeIdForEmptyTask, null, null, estimatedTimeForEmptyTask,
                priorityForEmptyTask, ownerIdForEmptyTask, idForEmptyTask);
    }

    public TaskModel(String name, int idAssignee, TaskLook.Status status, String description,
                     int estimatedTime, int priority, int ownerId, int taskId)
    {
        this.name = name;
        this.assignee = idAssignee;
        this.status = status;
        this.description = description;
        this.estimatedTime =estimatedTime;
        this.priority = priority;
        this.ownerId = ownerId;
        this.id = taskId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAssignee() {
        return assignee;
    }

    public void setAssignee(int assignee) {
        this.assignee = assignee;
    }


    public TaskLook.Status getStatus()
    {
        return status;
    }

    public void setStatus(TaskLook.Status status)
    {
        this.status = status;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

   public int getId() {
       return id;
   }

    public void setId(int id) {
        this.id = id;
    }

}
