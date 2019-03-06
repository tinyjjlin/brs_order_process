package com.brs.idm.rest.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
@Data
public class PrivilegeUserMappingDto {
    private String privId;
    private List<String>userIds;
}
