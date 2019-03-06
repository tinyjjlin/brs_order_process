package com.brs.orderprcoess.api;

import com.brs.orderprcoess.model.OrderDescription;

import java.util.List;
import java.util.Map;

/**
 * 订单流程查询服务
 * @author tiny lin
 * @date 2019/3/5
 */
public interface OrderProcessQueryService {

    /**
     * 当前用户对应的用户任务总数
     * @param assignee
     * @return
     */
    public long getTaskCount(String assignee);
    /**
     * 获取历史流程实例总数
     * @param variableName 流程变量名
     * @param value 流程变量值
     * @return
     */
    public long getHistoricProcessInstanceCount(String variableName,String value);

    /**
     * 通过 任务负责人，获取历史流程实例总数
     * @param assignee
     * @return
     */
    public long getHistoricProcessInstanceCount(String assignee);

    /**
     *
     * @param variableName
     * @param value
     * @return
     */
    public List<Object> getHistoricOrderRecordList(String variableName, String value);
    public List<Object> getHistoricOrderRecordList(String assignee);

    public Map<String ,Object> getHistoricOrderRecordPaging(String variableName, String value, int page, int size);
    public Map<String ,Object> getHistoricOrderRecordPaging(String assignee,int page,int size);

    public List<Object> getTaskOrderRecordList(String assignee);
    public Map<String ,Object> getTaskOrderRecordPaging(String assignee ,int page,int size);

    public OrderDescription getHistoricOrderDesc(String processId);
    public OrderDescription getTaskOrderDesc(String taskId);

}
