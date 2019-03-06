package com.brs.order.service;
import com.brs.order.api.Order;
import com.brs.order.api.OrderProcessConstant;
import com.brs.order.api.OrderRecordQueryService;
import com.brs.order.api.OrderService;
import com.brs.order.api.model.*;
import org.flowable.engine.HistoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
@Service
public class OrderRecordQueryServiceImpl implements OrderRecordQueryService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private OrderService orderService;


    @Override
    public long getTaskCount(String assignee) {
        return   taskService.createTaskQuery().taskAssignee(assignee).count();
    }

    @Override
    public List <OrderTaskRepresentation> getOrderTaskRecordList(String assignee) {
        List<Task>tasks =  taskService.createTaskQuery().taskAssignee(assignee).list();
        List<OrderTaskRepresentation> dtos = new ArrayList<>();
        for (Task task : tasks) {
            OrderTaskRepresentation  orderTaskRepresentation = getOrderTaskInfo(task);
            dtos.add(orderTaskRepresentation);
        }
        return dtos;
    }

    @Override
    public OrderTaskRecordPaging getOrderTaskRecordPaging(String assignee, int page, int size) {
        OrderTaskRecordPaging orderTaskRecordPaging = new OrderTaskRecordPaging();
        List<Task>tasks =  taskService.createTaskQuery().taskAssignee(assignee).listPage(page-1,size );
        List<OrderTaskRepresentation> dtos = new ArrayList<>();
        for (Task task : tasks) {
            OrderTaskRepresentation  orderTaskRepresentation = getOrderTaskInfo(task);
            dtos.add(orderTaskRepresentation);
        }
        orderTaskRecordPaging.setOrderTaskRepresentations(dtos);

        long total = getTaskCount(assignee);
        Paging paging = new Paging();
        paging.setPage(page);
        paging.setSize(size);
        paging.setTotal(total);
        orderTaskRecordPaging.setPaging(paging);

        return orderTaskRecordPaging;
    }


    @Override
    public List <HistoricOrderRecord> getHistoricOrderRecordList(String assigneeName, String  assignee) {
        //获取客户流程列表
        List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery().variableValueEquals(assigneeName,assignee ).list();
        System.out.println(hpiList.size());
        List<HistoricOrderRecord> historicOrderRecords = getHistoricOrderRecordList(hpiList);
        return historicOrderRecords;
    }

    @Override
    public HistoricOrderRecordPaging getOrderHistoryListPaging(String assigneeName, String assignee, int page, int size) {
        HistoricOrderRecordPaging historicOrderRecordPagings = new HistoricOrderRecordPaging();

        //获取客户流程列表
        List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery().variableValueEquals(assigneeName,assignee ).listPage(page,size );
        List<HistoricOrderRecord> orderHistoryRecords = getHistoricOrderRecordList(hpiList);
        System.out.println(hpiList.size());
        historicOrderRecordPagings.setHistoricOrderRecords(orderHistoryRecords);


        long total = getHistoricProcessInstanceCount(assigneeName,assignee );
        Paging paging = new Paging();
        paging.setPage(page);
        paging.setSize(size);
        paging.setTotal(total);
        historicOrderRecordPagings.setPaging(paging);

        return historicOrderRecordPagings;
    }

    @Override
    public long getHistoricProcessInstanceCount(String assigneeName, String assignee) {
        return  historyService.createHistoricProcessInstanceQuery().variableValueEquals(assigneeName,assignee ).count();
    }


    /**
     *
     * @param hpiList
     * @return
     */
    public List<HistoricOrderRecord> getHistoricOrderRecordList( List<HistoricProcessInstance> hpiList){
        List<HistoricOrderRecord> historicOrderRecords = new ArrayList <>();
        //获取客户流程列表
        System.out.println(hpiList.size());
        for (int i = 0; i < hpiList.size(); i++) {
            HistoricProcessInstance hip = hpiList.get(i);
            String procId = hip.getId();
            //获取每个流程实例的记录
            HistoricOrderRecord historicOrderRecord = getHistoricOrderRecord(procId);
            historicOrderRecords.add(historicOrderRecord);
        }
        return historicOrderRecords;
    }

    public void getHistoricActivity(){
        historyService.createHistoricActivityInstanceQuery().list();
    }


    /**
     * 根据流程id获取历史订单记录
     * @param procId
     * @return
     */
    public HistoricOrderRecord getHistoricOrderRecord( String procId){
        List <HistoricVariableInstance> hviList = historyService.createHistoricVariableInstanceQuery().processInstanceId(procId).list();
        System.out.println(hviList.size());
        HistoricOrderRecord orderHistoryRecord = new HistoricOrderRecord();
        for (int p = 0; p < hviList.size(); p++) {
            HistoricVariableInstance hv = hviList.get(p);
            String variableName = hv.getVariableName();
            System.out.println("vari name----->"+variableName);
            String value  = hv.getValue().toString();
            System.out.println("vari value----->"+value);
            switch (variableName) {
                case OrderProcessConstant.VARI_ORDER_ID:
                    Order orderInfo = orderService.getOrder(value);
                    orderHistoryRecord.setOrderInfo(orderInfo);
                    break;
                case OrderProcessConstant.VARI_ORDER_STATUS:
                    orderHistoryRecord.setOrderStatus(value);
                    break;
                case OrderProcessConstant.VARI_EDITOR:
                    orderHistoryRecord.setEditor(value);
                    break;
                case OrderProcessConstant.VARI_DATA_PROCESSOR:
                    orderHistoryRecord.setDataProcessor(value);
                    break;
                case OrderProcessConstant.VARI_SUBMITTER:
                    orderHistoryRecord.setSubmitter(value);
                    break;
            }
        }
        return orderHistoryRecord;

    }

    @Override
    public List <OrderTaskRepresentation> getGroupOrderTaskList(String groupName) {
        List<Task>tasks =  taskService.createTaskQuery().taskCandidateGroup(groupName).list();
        List<OrderTaskRepresentation> dtos = new ArrayList<>();
        for (Task task : tasks) {
            OrderTaskRepresentation  orderTaskRepresentation = getOrderTaskInfo(task);
            dtos.add(orderTaskRepresentation);
        }
        return dtos;
    }

    @Override
    public OrderTaskRepresentation getOrderTaskInfo(Task task){
        String orderId = (String)taskService.getVariable(task.getId(),OrderProcessConstant.VARI_ORDER_ID );
        Object orderStatus = taskService.getVariable(task.getId(),OrderProcessConstant.VARI_ORDER_STATUS );
        Object editor = taskService.getVariable(task.getId(),OrderProcessConstant.VARI_EDITOR );
        Object dataProcessor = taskService.getVariable(task.getId(),OrderProcessConstant.VARI_DATA_PROCESSOR );
        Object submitter = taskService.getVariable(task.getId(),OrderProcessConstant.VARI_SUBMITTER );
        Order orderInfo = orderService.getOrder(orderId);
        OrderTaskRepresentation  orderTaskRepresentation = new OrderTaskRepresentation(task.getId(), task.getName(),orderStatus,editor,dataProcessor,submitter,orderInfo);
        return orderTaskRepresentation;
    }


}
