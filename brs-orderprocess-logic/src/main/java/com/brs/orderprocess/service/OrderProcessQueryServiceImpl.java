package com.brs.orderprocess.service;

import com.alibaba.fastjson.JSONObject;
import com.brs.orderinfo.api.Order;
import com.brs.orderinfo.api.OrderService;
import com.brs.orderprcoess.api.OrderProcessConstant;
import com.brs.orderprcoess.api.OrderProcessQueryService;
import com.brs.orderprcoess.model.OrderDescription;
import com.brs.orderprcoess.model.OrderInfoWrapper;
import com.brs.orderprcoess.model.Paging;
import com.brs.orderprcoess.model.TaskInfo;
import org.flowable.engine.HistoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2019/3/6
 */
@Service
public class OrderProcessQueryServiceImpl implements OrderProcessQueryService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;


    @Autowired
    private OrderService orderService;

    /**
     * 订单记录返回列表名
     */
    private final static String ORDER_RECORD_LIST="orderRecordList";
    /**
     * 订单记录返回分页名
     */
    private final static String ORDER_RECORD_PAGING ="paging";

    @Override
    public List<Object> getHistoricOrderRecordList(String variableName, String value) {
        //获取客户流程列表
        List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery().variableValueEquals(variableName,value ).orderByProcessInstanceStartTime().desc().list();

        List<Object> orderDescriptionList = getHistoricOrderInfoList(value,hpiList);

        return orderDescriptionList;
    }

    /**
     * 获取订单信息
     * @param hpiList
     * @return
     */
    protected   List<Object> getHistoricOrderInfoList(String userId, List<HistoricProcessInstance> hpiList){
        List<Object> orderInfoList = new ArrayList<>();
        for (int i = 0; i < hpiList.size(); i++) {
            HistoricProcessInstance hip = hpiList.get(i);
            OrderDescription orderDescription = getHistoricOrderDesc(hip.getId());
            //OrderRecord
            Order orderRecord = getOrderRecord(userId,orderDescription.getOrderId().toString() );
            OrderInfoWrapper orderInfoWrapper = new OrderInfoWrapper(orderRecord,orderDescription , null);
            System.out.println("## item history.......orderRecord:"+ JSONObject.toJSON(orderInfoWrapper));
            orderInfoList.add(orderInfoWrapper);
        }
        return orderInfoList;
    }

    @Override
    public List <Object> getHistoricOrderRecordList(String assignee) {
        //获取客户流程列表
        List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery().involvedUser(assignee).orderByProcessInstanceStartTime().desc().list();

        List<Object> orderDescriptionList = getHistoricOrderInfoList(assignee,hpiList);

        return orderDescriptionList;
    }



    @Override
    public Map<String, Object> getHistoricOrderRecordPaging(String variableName, String value, int page, int size) {
        //获取客户流程列表
        List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery().variableValueEquals(variableName,value ).orderByProcessInstanceStartTime().desc().listPage(page-1,size );

        List<Object> orderDescriptionList = getHistoricOrderInfoList(value,hpiList);

        Map<String,Object> orderDescMap = new HashMap<>(2);
        orderDescMap.put(ORDER_RECORD_LIST,orderDescriptionList );

        long total = getHistoricProcessInstanceCount(variableName,value );
        Paging paging = new Paging();
        paging.setPage(page);
        paging.setSize(size);
        paging.setTotal(total);
        orderDescMap.put(ORDER_RECORD_PAGING,paging );

        return orderDescMap;
    }

    @Override
    public Map <String, Object> getHistoricOrderRecordPaging(String assignee, int page, int size) {
        //获取客户流程列表
        List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery().involvedUser(assignee).orderByProcessInstanceStartTime().desc().listPage(page-1, size);

        List<Object> orderDescriptionList = getHistoricOrderInfoList(assignee,hpiList);

        Map<String,Object> orderDescMap = new HashMap <>(2);
        orderDescMap.put(ORDER_RECORD_LIST,orderDescriptionList );

        long total = getHistoricProcessInstanceCount(assignee );
        Paging paging = new Paging();
        paging.setPage(page);
        paging.setSize(size);
        paging.setTotal(total);
        orderDescMap.put(ORDER_RECORD_PAGING,paging );

        return orderDescMap;
    }

    @Override
    public List <Object> getTaskOrderRecordList(String assignee) {
        List<Task>tasks =  taskService.createTaskQuery().taskAssignee(assignee).list();

        List<Object> taskDescList =getOrderInfo(assignee,tasks);

        return taskDescList;
    }

    public List<Object> getOrderInfo( String userId, List<Task>tasks){
        List<Object> list = new ArrayList <>();
        for (Task task : tasks) {
            OrderDescription orderDescription = getTaskOrderDesc(task.getId());
            TaskInfo taskInfo = new TaskInfo(task.getId(),task.getName() );
            //OrderRecord
            Order orderRecord = getOrderRecord(userId,orderDescription.getOrderId().toString() );
            OrderInfoWrapper orderInfoWrapper = new OrderInfoWrapper(orderRecord,orderDescription , taskInfo);
            System.out.println("##..item.....orderRecord:"+ JSONObject.toJSON(orderInfoWrapper));
            list.add(orderInfoWrapper);
        }
        return list;
    }
    @Override
    public Map <String, Object> getTaskOrderRecordPaging(String assignee, int page, int size) {
        List<Task>tasks =  taskService.createTaskQuery().taskAssignee(assignee).listPage(page-1,size );
        System.out.println("## taskList..........................."+tasks.size()+".........assignee:"+assignee+","+page+","+size);
        List<Object> taskDescList = getOrderInfo(assignee,tasks);

        Map<String,Object> orderDescMap = new HashMap <>(2);
        orderDescMap.put(ORDER_RECORD_LIST, taskDescList);

        long total = getTaskCount(assignee );
        Paging paging = new Paging();
        paging.setPage(page);
        paging.setSize(size);
        paging.setTotal(total);
        orderDescMap.put(ORDER_RECORD_PAGING,paging );
        return orderDescMap;

    }

    @Override
    public long getTaskCount(String assignee) {
        return   taskService.createTaskQuery().taskAssignee(assignee).count();
    }

    @Override
    public long getHistoricProcessInstanceCount(String assignee) {
        return  historyService.createHistoricProcessInstanceQuery().involvedUser(assignee).count();
    }

    @Override
    public long getHistoricProcessInstanceCount(String variableName, String value) {
        return  historyService.createHistoricProcessInstanceQuery().variableValueEquals(variableName,value ).count();
    }


    @Override
    public OrderDescription getHistoricOrderDesc(String processId) {
        OrderDescription orderDescription = new OrderDescription();
        //一个流程实例对应的所有变量
        List <HistoricVariableInstance> hviList = historyService.createHistoricVariableInstanceQuery().processInstanceId(processId).list();
        System.out.println("##variable count:"+hviList.size());
        for (int p = 0; p < hviList.size(); p++) {
            HistoricVariableInstance hv = hviList.get(p);
            String variableName = hv.getVariableName();
            System.out.println("##variable name------------------>"+variableName);
            String value  = hv.getValue().toString();
            System.out.println("##variable value------------------->"+value);
            switch (variableName) {
                case OrderProcessConstant.VARI_ORDER_ID:
                    orderDescription.setOrderId(value);
                    break;
                case OrderProcessConstant.VARI_ORDER_STATUS:
                    orderDescription.setOrderStatus(value);
                    break;
                case OrderProcessConstant.VARI_EDITOR:
                    orderDescription.setEditor(value);
                    break;
                case OrderProcessConstant.VARI_DATA_PROCESSOR:
                    orderDescription.setDataProcessor(value);
                    break;
                case OrderProcessConstant.VARI_SUBMITTER:
                    orderDescription.setSubmitter(value);
                    break;
            }
        }
        return orderDescription;
    }

    @Override
    public OrderDescription getTaskOrderDesc(String taskId) {
        String orderId = (String)taskService.getVariable(taskId, OrderProcessConstant.VARI_ORDER_ID );
        Object orderStatus = taskService.getVariable(taskId,OrderProcessConstant.VARI_ORDER_STATUS );
        Object editor = taskService.getVariable(taskId,OrderProcessConstant.VARI_EDITOR );
        Object dataProcessor = taskService.getVariable(taskId,OrderProcessConstant.VARI_DATA_PROCESSOR );
        Object submitter = taskService.getVariable(taskId,OrderProcessConstant.VARI_SUBMITTER );

        OrderDescription orderDescription = new OrderDescription(orderId,orderStatus,editor,dataProcessor,submitter);
        return orderDescription;
    }

    /**
     * 获取订单记录
     * @param userId
     * @param orderId
     * @return
     */
    public Order getOrderRecord(String userId, String orderId){
        Order order = orderService.getOrder(orderId);
        return order;
    }

}
