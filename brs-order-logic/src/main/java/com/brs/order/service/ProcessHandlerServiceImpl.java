package com.brs.order.service;

import com.brs.idm.api.RoleConstant;
import com.brs.idm.service.UserService;
import com.brs.order.api.OrderProcessConstant;
import com.brs.order.api.ProcessHandlerService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;

import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2019/2/27
 */
@Service
public class ProcessHandlerServiceImpl implements ProcessHandlerService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;


    @Override
    public String startProcess(String client, String orderId) {
        Map<String,Object> variables = new HashMap<>(16);
        variables.put("client", client);
        variables.put("orderId",orderId );
        variables.put("orderStatus", "新建");
        //下一个处理人
        variables.put("manager",getUserIdFromList(RoleConstant.ROLE_MANAGER) );
        //启动流程，添加流程变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(OrderProcessConstant.BPMN_PROCESS_ID,variables);
        System.out.println("流程id-------------->"+processInstance.getId());
        System.out.println("Number of process instances:"+runtimeService.createProcessInstanceQuery().count());
        return processInstance.getId();
    }


    @Override
    public void confirmOrder(String taskId){
        System.out.println("##cofirmOrder...........taskId:"+taskId);
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        //确认订单
        HashMap<String,Object> map = new HashMap <>(1);
        map.put("orderValidation",true );
        //修改流程变量的值
        taskService.setVariable(taskId,"orderStatus","确认"  );

        //下一个处理人
        map.put("editorialDirector",getUserIdFromList(RoleConstant.ROLE_EDITOR_DIRECTOR) );

        taskService.complete(taskId,map);
    }

    /**
     * 分发订单（编辑主管向编辑人员分发订单）
     * @param taskId
     * @param editorId
     */
    @Override
    public void dispatchOrder(String taskId,String editorId){
        Task task = getTaskByTaskId(taskId);
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        //修改流程变量的值
        taskService.setVariable(taskId,"orderStatus","编辑"  );
        //确认订单
        HashMap<String,Object> map = new HashMap <>(1);
        map.put("editor",editorId );


        taskService.complete(taskId,map);
    }

    /**
     * 编辑写草稿
     * @param taskId
     */
    @Override
    public void writeDraft(String taskId){
        Task task = getTaskByTaskId(taskId);
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        //确认订单
        HashMap<String,Object> map = new HashMap <>(1);
        map.put("editStatus", "草稿");
        //下一个处理人
        map.put("dataDirector",getUserIdFromList(RoleConstant.ROLE_DATA_DIRECTOR) );
        taskService.complete(taskId,map);
    }

    /**
     * 编辑完成文章
     * @param taskId
     */
    @Override
    public void completeArticle(String taskId){
        Task task = getTaskByTaskId(taskId);
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        //确认订单
        HashMap<String,Object> map = new HashMap <>(1);
        map.put("editStatus", "撰写完");
        taskService.setVariable(taskId,"orderStatus","编辑完成"  );
        //下一个处理人
        map.put("submitter",getUserIdFromList(RoleConstant.ROLE_SUBMITTER) );
        taskService.complete(taskId,map);
    }

    /**
     *实验室负责人分配任务
     * @param taskId
     * @param dataProcessorId
     */
    @Override
    public void dispatchData(String taskId, String dataProcessorId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        //修改流程变量的值
        taskService.setVariable(taskId,"orderStatus","数据处理"  );
        //确认订单
        HashMap<String,Object> map = new HashMap <>(1);
        map.put("dataProcessor",dataProcessorId );
        taskService.complete(taskId,map);
    }


    /**
     * 实验人员处理数据
     * @param taskId
     */
    @Override
    public void handleData(String taskId){
        Task task = getTaskByTaskId(taskId);
        if(task == null){
            throw new RuntimeException("流程不存在！");
        }
        taskService.complete(taskId);
    }

    public Task getTaskByTaskId(String taskId ){
        return    taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    public String getUserIdFromList(String roleId){
        String userId =null;
        List<String> userIds = userService.getUserIdsByRoleId(roleId);
        if(userIds != null && userIds.size()>0){
            userId = userIds.get(0);
        }
        System.out.println("##...............下一个任务负责人：......"+userId);
        return userId;
    }

}
