package com.brs.orderprocess.service;

import com.brs.orderprcoess.api.ArticleStatusConstant;
import com.brs.orderprcoess.api.OrderProcessConstant;
import com.brs.orderprcoess.api.OrderProcessHandlerService;
import com.brs.orderprcoess.api.OrderStatusConstant;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2019/3/5
 */
public class OrderProcessHandlerServiceImpl implements OrderProcessHandlerService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;


    public String startProcessWrapper(String client, String orderId) {
        Map<String,Object> variables = new HashMap<>(6);
        variables.put(OrderProcessConstant.VARI_CREATOR, client);
        variables.put(OrderProcessConstant.VARI_ORDER_ID,orderId );
        variables.put(OrderProcessConstant.VARI_ORDER_STATUS, OrderStatusConstant.ORDER_STATUS_NEW);
       return  startProcess(client,variables );
    }

    @Override
    public String startProcess(String client, Map<String, Object> variables) {
        //启动流程，添加流程变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(OrderProcessConstant.BPMN_PROCESS_ID,variables);
        return processInstance.getId();
    }

    @Override
    public void claimTask(String assignee, String taskId) {
        // 申领任务
        taskService.claim(assignee,taskId);
    }

    @Override
    public void confirmOrder(String taskId) {
        System.out.println("##cofirmOrder...........taskId:"+taskId);
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        //修改流程变量的值
        taskService.setVariable(taskId,OrderProcessConstant.VARI_ORDER_STATUS,OrderStatusConstant.ORDER_STATUS_CONFIRM  );

        taskService.complete(taskId);

    }

    @Override
    public void dispatchOrder(String taskId, String editorId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        //修改流程变量的值
        taskService.setVariable(taskId,OrderProcessConstant.VARI_ORDER_STATUS,OrderStatusConstant.ORDER_STATUS_DISPATCH  );

        //指定editor
        HashMap<String,Object> map = new HashMap <>(1);
        map.put(OrderProcessConstant.VARI_EDITOR,editorId );

        taskService.complete(taskId,map);
    }

    @Override
    public void editorApprovalTask(String taskId, boolean editorApproval) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        //指定editor
        HashMap<String,Object> map = new HashMap <>(1);
        map.put("editorApproval",editorApproval );

        taskService.complete(taskId,map);

    }

    @Override
    public void createNewArticleTask(String taskId) {

    }

    @Override
    public void writeDraftTask(String taskId) {
        Task task = getTaskByTaskId(taskId);
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        //modify article status
        HashMap<String,Object> map = new HashMap <>(1);
        map.put(OrderProcessConstant.VARI_ARTICLE_STATUS, ArticleStatusConstant.ARTICLE_STATUS_DRAFT);

        taskService.complete(taskId,map);
    }

    @Override
    public void modifyArticleTask(String taskId) {

        Task task = getTaskByTaskId(taskId);
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        //modify article status
        HashMap<String,Object> map = new HashMap <>(1);
        map.put(OrderProcessConstant.VARI_ARTICLE_STATUS, ArticleStatusConstant.ARTICLE_STATUS_MODIFY);

        taskService.complete(taskId,map);
    }

    @Override
    public void completeArticle(String taskId) {
        Task task = getTaskByTaskId(taskId);
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        //modify article status
        HashMap<String,Object> map = new HashMap <>(1);
        map.put(OrderProcessConstant.VARI_ARTICLE_STATUS, ArticleStatusConstant.ARTICLE_STATUS_COMPLETE);

        taskService.complete(taskId,map);
    }

    @Override
    public void dispatchDataTask(String taskId, String dataProcessorId) {
        Task task = getTaskByTaskId(taskId);
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        HashMap<String,Object> map = new HashMap <>(1);
        map.put(OrderProcessConstant.VARI_DATA_PROCESSOR,dataProcessorId );
        map.put(OrderProcessConstant.VARI_ARTICLE_STATUS, ArticleStatusConstant.ARTICLE_STATUS_DISPATCH_DATA);
        taskService.complete(taskId,map);
    }

    @Override
    public void handleDataTask(String taskId) {
        Task task = getTaskByTaskId(taskId);
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        HashMap<String,Object> map = new HashMap <>(1);
        map.put(OrderProcessConstant.VARI_ARTICLE_STATUS, ArticleStatusConstant.ARTICLE_STATUS_COMPLETE_DATA);
        taskService.complete(taskId,map);

    }

    @Override
    public void handleContributeTask(String taskId) {

        Task task = getTaskByTaskId(taskId);
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        HashMap<String,Object> map = new HashMap <>(1);
        map.put(OrderProcessConstant.VARI_ORDER_STATUS, OrderStatusConstant.ORDER_STATUS_CONTRIBUTE);
        map.put(OrderProcessConstant.VARI_ARTICLE_STATUS, ArticleStatusConstant.ARTICLE_STATUS_CONTRIBUTE);
        taskService.complete(taskId,map);
    }

    @Override
    public void handleFeedBackTask(String taskId) {
        Task task = getTaskByTaskId(taskId);
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        HashMap<String,Object> map = new HashMap <>(6);
        map.put(OrderProcessConstant.VARI_ARTICLE_STATUS, ArticleStatusConstant.ARTICLE_STATUS_MODIFY);
        taskService.complete(taskId,map);
    }


    public Task getTaskByTaskId(String taskId ){
        return    taskService.createTaskQuery().taskId(taskId).singleResult();
    }
}
