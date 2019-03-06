package com.brs.orderprcoess.api;

import java.util.Map;

/**
 * 订单流程操作服务
 * @author tiny lin
 * @date 2019/2/27
 */
public interface OrderProcessHandlerService {

    /**
     * 启动订单流程
     * @param client
     * @param variables
     * @return
     */
    public String startProcess(String client, Map<String,Object>variables);

    /**
     * 用户申领处理任务
     * @param assignee
     * @param taskId
     */
    public void claimTask(String assignee,String taskId);


    /**
     * 确认订单（总经理）
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
     * 编辑审核，是否接受分配的订单任务，并给出说明备注
     * @param taskId
     * @param editorApproval
     */
    public void editorApprovalTask(String taskId,boolean editorApproval);

    /**
     * 编辑创建新的文章
     * @param taskId
     */
    public void createNewArticleTask(String taskId);

    /**
     * 编辑出草稿
     * @param taskId
     */
    public void writeDraftTask(String taskId);

    /**
     * 编辑修改文章
     * @param taskId
     */
    public void modifyArticleTask(String taskId);

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
    public void dispatchDataTask(String taskId, String dataProcessorId);
    /**
     * 实验人员处理数据
     * @param taskId
     */
    public void handleDataTask(String taskId);


    /**
     * 投稿人处理投稿任务
     * @param taskId
     */
    public void handleContributeTask(String taskId);


    /**
     * 投稿人处理投稿反馈结果任务
     * @param taskId
     */
    public void handleFeedBackTask(String taskId);

}
