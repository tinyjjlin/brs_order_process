package com.brs.order.api.model;

import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/2/14
 */
@Data
public class TaskRepresentation {
    private String taskId;
    private String taskName;

    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 编辑
     */
    private String editor;
    /**
     * 数据处理人员
     */
    private String dataProcessor;
    /**
     * 投稿人员
     */
    private String submitter;

    public TaskRepresentation(String taskId, String taskName,String orderStatus,String editor,String dataProcessor,String submitter) {
      this.taskId = taskId;
      this.taskName = taskName;
      this.orderStatus = orderStatus;
      this.editor = editor;
      this.dataProcessor = dataProcessor;
      this.submitter = submitter;
    }
    public TaskRepresentation(String taskId, String taskName) {
      this(taskId,taskName,null,null,null,null);
    }
}
