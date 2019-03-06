package com.brs.order.api;

import com.brs.order.api.model.OrderDescription;
import com.brs.order.api.model.TaskInfo;

import java.util.List;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2019/2/27
 */
public interface ProcessQueryService {
    /**
     * 当前用户对应的用户任务总数
     * @param assignee
     * @return
     */
    public long getTaskCount(String assignee);
    /**
     * 获取历史流程实例总数
     * @param variableName
     * @param value
     * @return
     */
    public long getHistoricProcessInstanceCount(String variableName,String value);
    public long getHistoricProcessInstanceCount(String assignee);

    /**
     *
     * @param variableName
     * @param value
     * @return
     */
    public List<Object> getHistoricOrderDescList(String variableName,String value);
    public List<Object> getHistoricOrderDescList(String assignee);

    public Map<String ,Object> getHistoricOrderDescPaging(String variableName, String value,int page,int size);
    public Map<String ,Object> getHistoricOrderDescPaging(String assignee,int page,int size);

    public List<Object> getTaskOrderDescList(String assignee);
    public Map<String ,Object> getTaskOrderDescPaging(String assignee ,int page,int size);

    public OrderDescription getHistoricOrderDesc(String processId);
    public OrderDescription getTaskOrderDesc(String taskId);


}
