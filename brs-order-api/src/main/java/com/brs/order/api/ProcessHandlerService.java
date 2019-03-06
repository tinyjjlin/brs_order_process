package com.brs.order.api;

/**
 * @author tiny lin
 * @date 2019/2/27
 */
public interface ProcessHandlerService {

    /**
     * 启动订单流程
     * @param client
     * @param orderId
     * @return
     */
    public String startProcess(String client,String orderId);
    /**
     * 确认订单
     * @param taskId
     */
    public void confirmOrder(String taskId);

    /**
     * 分发订单（编辑主管向编辑人员分发订单）,编辑主管需要指导编辑
     * @param taskId
     * @param editorId
     */
    public void dispatchOrder(String taskId, String editorId);

    /**
     * 编辑写草稿
     * @param taskId
     */
    public void writeDraft(String taskId);

    /**
     * 编辑完成文章
     * @param taskId
     */
    public void completeArticle(String taskId);

    /**
     * 分发实验任务，实验室负责人分发实验给实验人员
     * @param taskId
     * @param dataProcessorId
     */
    public void dispatchData(String taskId,String dataProcessorId);
    /**
     * 实验人员处理数据
     * @param taskId
     */
    public void handleData(String taskId);

}
