package com.brs.orderinfo.api.domain;

import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
@Data
public class AuthorAddrDomain {
    private String addrId;
    private String authorId;
    private String serialNum;
    private String addrInfo;
}
