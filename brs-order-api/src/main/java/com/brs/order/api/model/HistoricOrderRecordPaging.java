package com.brs.order.api.model;

import lombok.Data;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/26
 */
@Data
public class HistoricOrderRecordPaging {
    private List<HistoricOrderRecord> historicOrderRecords;
    private Paging paging;

    public HistoricOrderRecordPaging(){}
    public HistoricOrderRecordPaging(List<HistoricOrderRecord> historicOrderRecords , Paging paging){
        this.historicOrderRecords = historicOrderRecords;
        this.paging = paging;
    }
}
