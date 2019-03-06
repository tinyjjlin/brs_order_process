package com.brs.idm.rest.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
@Data
public class PrivilegeRoleMappingDto {
    private String privId;
    private List<String>roleIds;
}
