package com.brs.order.api.model;

import lombok.Data;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/26
 */
@Data
public class OrderTaskRecordPaging {
    private List<OrderTaskRepresentation> orderTaskRepresentations;
    private Paging paging;

    public OrderTaskRecordPaging(){}
    public OrderTaskRecordPaging(List<OrderTaskRepresentation> orderTaskRepresentations,Paging paging){
        this.orderTaskRepresentations = orderTaskRepresentations;
        this.paging = paging;
    }
}
