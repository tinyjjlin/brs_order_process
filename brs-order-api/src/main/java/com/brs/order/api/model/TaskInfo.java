package com.brs.order.api.model;

import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/2/27
 */
@Data
public class TaskInfo {
    private String taskId;
    private String taskName;
    public TaskInfo(){}
    public TaskInfo(String taskId,String taskName){
        this.taskId = taskId;
        this.taskName = taskName;
    }
}
