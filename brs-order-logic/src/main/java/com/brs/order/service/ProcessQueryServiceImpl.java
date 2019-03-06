//package com.brs.order.service;
//import com.alibaba.fastjson.JSONObject;
//import com.brs.idm.api.IdmService;
//import com.brs.idm.api.RoleConstant;
//import com.brs.idm.api.dto.RoleDTO;
//import com.brs.order.api.Order;
//import com.brs.order.api.OrderProcessConstant;
//import com.brs.order.api.OrderService;
//import com.brs.order.api.ProcessQueryService;
//import com.brs.order.api.dto.OrderInfoWrapper;
//import com.brs.order.api.model.*;
//import org.flowable.engine.HistoryService;
//import org.flowable.engine.TaskService;
//import org.flowable.engine.history.HistoricProcessInstance;
//import org.flowable.task.api.Task;
//import org.flowable.variable.api.history.HistoricVariableInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author tiny lin
// * @date 2019/2/27
// */
//@Service
//public class ProcessQueryServiceImpl implements ProcessQueryService {
//    @Autowired
//    private TaskService taskService;
//    @Autowired
//    private HistoryService historyService;
//
//    @Autowired
//    private IdmService idmService;
//
//    @Autowired
//    private OrderService orderService;
//
//
//    @Override
//    public List<Object> getHistoricOrderDescList(String variableName, String value) {
//        //获取客户流程列表
//        List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery().variableValueEquals(variableName,value ).orderByProcessInstanceStartTime().desc().list();
//
//        List<Object> orderDescriptionList = getHistoricOrderInfoList(value,hpiList);
//
//        return orderDescriptionList;
//    }
//
//    /**
//     * 获取订单信息
//     * @param hpiList
//     * @return
//     */
//    protected   List<Object> getHistoricOrderInfoList(String userId, List<HistoricProcessInstance> hpiList){
//        List<Object> orderInfoList = new ArrayList<>();
//        for (int i = 0; i < hpiList.size(); i++) {
//            HistoricProcessInstance hip = hpiList.get(i);
//            OrderDescription orderDescription = getHistoricOrderDesc(hip.getId());
//            //OrderRecord
//            Order orderRecord = getOrderRecord(userId,orderDescription.getOrderId().toString() );
//            OrderInfoWrapper orderInfoWrapper = new OrderInfoWrapper(orderRecord,orderDescription , null);
//            System.out.println("## item history.......orderRecord:"+JSONObject.toJSON(orderInfoWrapper));
//            orderInfoList.add(orderInfoWrapper);
//        }
//        return orderInfoList;
//    }
//
//    @Override
//    public List <Object> getHistoricOrderDescList(String assignee) {
//        //获取客户流程列表
//        List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery().involvedUser(assignee).orderByProcessInstanceStartTime().desc().list();
//
//        List<Object> orderDescriptionList = getHistoricOrderInfoList(assignee,hpiList);
//
//        return orderDescriptionList;
//    }
//
//
//
//    @Override
//    public Map<String, Object> getHistoricOrderDescPaging(String variableName, String value, int page, int size) {
//        //获取客户流程列表
//        List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery().variableValueEquals(variableName,value ).orderByProcessInstanceStartTime().desc().listPage(page-1,size );
//
//        List<Object> orderDescriptionList = getHistoricOrderInfoList(value,hpiList);
//
//        Map<String,Object> orderDescMap = new HashMap <>(2);
//        orderDescMap.put("orderRecordList",orderDescriptionList );
//
//        long total = getHistoricProcessInstanceCount(variableName,value );
//        Paging paging = new Paging();
//        paging.setPage(page);
//        paging.setSize(size);
//        paging.setTotal(total);
//        orderDescMap.put("paging",paging );
//
//        return orderDescMap;
//    }
//
//    @Override
//    public Map <String, Object> getHistoricOrderDescPaging(String assignee, int page, int size) {
//        //获取客户流程列表
//        List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery().involvedUser(assignee).orderByProcessInstanceStartTime().desc().listPage(page-1, size);
//
//        List<Object> orderDescriptionList = getHistoricOrderInfoList(assignee,hpiList);
//
//        Map<String,Object> orderDescMap = new HashMap <>(2);
//        orderDescMap.put("orderRecordList",orderDescriptionList );
//
//        long total = getHistoricProcessInstanceCount(assignee );
//        Paging paging = new Paging();
//        paging.setPage(page);
//        paging.setSize(size);
//        paging.setTotal(total);
//        orderDescMap.put("paging",paging );
//
//        return orderDescMap;
//    }
//
//    @Override
//    public List <Object> getTaskOrderDescList(String assignee) {
//        List<Task>tasks =  taskService.createTaskQuery().taskAssignee(assignee).list();
//
//        List<Object> taskDescList =getOrderInfo(assignee,tasks);
//
//        return taskDescList;
//    }
//
//    public List<Object> getOrderInfo( String userId, List<Task>tasks){
//        List<Object> list = new ArrayList <>();
//        for (Task task : tasks) {
//            OrderDescription orderDescription = getTaskOrderDesc(task.getId());
//            TaskInfo taskInfo = new TaskInfo(task.getId(),task.getName() );
//            //OrderRecord
//            Order orderRecord = getOrderRecord(userId,orderDescription.getOrderId().toString() );
//            OrderInfoWrapper orderInfoWrapper = new OrderInfoWrapper(orderRecord,orderDescription , taskInfo);
//            System.out.println("##..item.....orderRecord:"+ JSONObject.toJSON(orderInfoWrapper));
//            list.add(orderInfoWrapper);
//        }
//        return list;
//    }
//    @Override
//    public Map <String, Object> getTaskOrderDescPaging(String assignee, int page, int size) {
//        List<Task>tasks =  taskService.createTaskQuery().taskAssignee(assignee).listPage(page-1,size );
//        System.out.println("## taskList..........................."+tasks.size()+".........assignee:"+assignee+","+page+","+size);
//        List<Object> taskDescList = getOrderInfo(assignee,tasks);
//
//        Map<String,Object> orderDescMap = new HashMap <>(2);
//        orderDescMap.put("orderRecordList", taskDescList);
//
//        long total = getTaskCount(assignee );
//        Paging paging = new Paging();
//        paging.setPage(page);
//        paging.setSize(size);
//        paging.setTotal(total);
//        orderDescMap.put("paging",paging );
//        return orderDescMap;
//
//    }
//
//    @Override
//    public long getTaskCount(String assignee) {
//        return   taskService.createTaskQuery().taskAssignee(assignee).count();
//    }
//
//    @Override
//    public long getHistoricProcessInstanceCount(String assignee) {
//        return  historyService.createHistoricProcessInstanceQuery().involvedUser(assignee).count();
//    }
//
//    @Override
//    public long getHistoricProcessInstanceCount(String variableName, String value) {
//        return  historyService.createHistoricProcessInstanceQuery().variableValueEquals(variableName,value ).count();
//    }
//
//
//    @Override
//    public OrderDescription getHistoricOrderDesc(String processId) {
//        OrderDescription orderDescription = new OrderDescription();
//        //一个流程实例对应的所有变量
//        List <HistoricVariableInstance> hviList = historyService.createHistoricVariableInstanceQuery().processInstanceId(processId).list();
//        System.out.println("##variable count:"+hviList.size());
//        for (int p = 0; p < hviList.size(); p++) {
//            HistoricVariableInstance hv = hviList.get(p);
//            String variableName = hv.getVariableName();
//            System.out.println("##variable name------------------>"+variableName);
//            String value  = hv.getValue().toString();
//            System.out.println("##variable value------------------->"+value);
//            switch (variableName) {
//                case OrderProcessConstant.VARI_ORDER_ID:
//                    orderDescription.setOrderId(value);
//                    break;
//                case OrderProcessConstant.VARI_ORDER_STATUS:
//                    orderDescription.setOrderStatus(value);
//                    break;
//                case OrderProcessConstant.VARI_EDITOR:
//                    orderDescription.setEditor(value);
//                    break;
//                case OrderProcessConstant.VARI_DATA_PROCESSOR:
//                    orderDescription.setDataProcessor(value);
//                    break;
//                case OrderProcessConstant.VARI_SUBMITTER:
//                    orderDescription.setSubmitter(value);
//                    break;
//            }
//        }
//        return orderDescription;
//    }
//
//    @Override
//    public OrderDescription getTaskOrderDesc(String taskId) {
//        String orderId = (String)taskService.getVariable(taskId,OrderProcessConstant.VARI_ORDER_ID );
//        Object orderStatus = taskService.getVariable(taskId,OrderProcessConstant.VARI_ORDER_STATUS );
//        Object editor = taskService.getVariable(taskId,OrderProcessConstant.VARI_EDITOR );
//        Object dataProcessor = taskService.getVariable(taskId,OrderProcessConstant.VARI_DATA_PROCESSOR );
//        Object submitter = taskService.getVariable(taskId,OrderProcessConstant.VARI_SUBMITTER );
//
//        OrderDescription orderDescription = new OrderDescription(orderId,orderStatus,editor,dataProcessor,submitter);
//        return orderDescription;
//    }
//
//    /**
//     * 获取订单记录
//     * @param userId
//     * @param orderId
//     * @return
//     */
//    public Order getOrderRecord(String userId,String orderId){
//        Order order = null;
//        List<RoleDTO> roleDTOList =idmService.getUserBindRole(userId);
//        for (int i = 0; i < roleDTOList.size(); i++) {
//            if(roleDTOList.get(i).getRoleId().equalsIgnoreCase(RoleConstant.ROLE_DATA_CLIENT)){
//                 order = orderService.getClientOrder(orderId);
//            }else if(roleDTOList.get(i).getRoleId().equalsIgnoreCase(RoleConstant.ROLE_MANAGER)){
//                order = orderService.getManagerOrder(orderId);
//                break;
//            }else{
//                order = orderService.getStaffOrder(orderId);
//            }
//        }
//        return order;
//    }
//
//
//}
