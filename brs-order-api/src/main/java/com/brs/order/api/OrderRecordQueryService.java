package com.brs.order.api;
import com.brs.order.api.model.*;
import org.flowable.task.api.Task;
import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
public interface OrderRecordQueryService {

    public List<OrderTaskRepresentation> getGroupOrderTaskList(String groupName);
    public List<OrderTaskRepresentation> getOrderTaskRecordList(String assignee);
    public OrderTaskRecordPaging getOrderTaskRecordPaging(String assignee,int page,int size);

    /**
     * 当前用户对应的用户任务总数
     * @param assignee
     * @return
     */
    public long getTaskCount(String assignee);


    /**
     * 获取当前任务对应的订单信息
     * @param task
     * @return
     */
    public OrderTaskRepresentation getOrderTaskInfo(Task task);

    /**
     * 获取用户订单历史记录
     * @param assigneeName
     * @param assignee
     * @return
     */
    public List <HistoricOrderRecord> getHistoricOrderRecordList(String assigneeName, String  assignee) ;

    public HistoricOrderRecordPaging getOrderHistoryListPaging(String assigneeName, String  assignee, int page, int size);

    /**
     * 获取历史流程实例总数
     * @param assigneeName
     * @param assignee
     * @return
     */
    public long getHistoricProcessInstanceCount(String assigneeName, String  assignee);


}
